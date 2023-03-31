package com.sunset.service;

import com.sunset.entity.LoginPwd;
import com.sunset.entity.LoginVerCode;
import com.sunset.entity.PwdEntity;
import com.sunset.entity.RegisterEntity;
import com.sunset.mapper.SignMapper;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SignService {
    @Autowired
    SignMapper signMapper;

    // 注册 【待用】
    public ReturnJson<String> RegisterInsert(RegisterEntity registerEntity) {
        String phone = registerEntity.phone;
        String verCode = registerEntity.verCode;
        RegisterEntity p = signMapper.FindUserPhone(phone);// 查询手机号是否存在
        if (p != null) {
            return ReturnJson.fail(-1, "手机号已注册");
        }
        if (phone == null || phone == "") {
            return ReturnJson.fail(-1, "手机号不能为空");
        }
        if (verCode == null || verCode == "") {
            return ReturnJson.fail(-1, "验证码不能为空");
        }
        String uuid = UUID.randomUUID().toString().toUpperCase();
        registerEntity.setUid(uuid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        registerEntity.setCreate_time(dateTime);
        signMapper.RegisterInsert(registerEntity);
        return ReturnJson.success(null, "ok");
    }

    // 验证码登录
    public ReturnJson<String> LoginVerToken(LoginVerCode loginVerCode) {
        String phone = loginVerCode.getPhone();
        RegisterEntity p = signMapper.FindUserPhone(phone);
        // 手机号不存在直接注册
        if (p == null) {
            RegisterEntity registerEntity = new RegisterEntity();
            registerEntity.setPhone(phone);
            String uuid = UUID.randomUUID().toString().toUpperCase();
            registerEntity.setUid(uuid);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateTime = formatter.format(LocalDateTime.now());
            registerEntity.setCreate_time(dateTime);
            signMapper.RegisterInsert(registerEntity);
            RegisterEntity p1 = signMapper.FindUserPhone(phone);
            String token = TokenUtils.setToken(p1.getUid());

            return ReturnJson.success(token, "register::ok");
        }
        // 查询已注册直接登录
        String token = TokenUtils.setToken(p.getUid());
        return ReturnJson.success(token, "login::ok");
    }

    // 密码登录
    public ReturnJson<String> LoginPwdToken(LoginPwd loginPwd) {
        String phone = loginPwd.getPhone();
        String pwd = DigestUtils.md5Hex(loginPwd.getPassword());
        RegisterEntity p = signMapper.FindUserPhone(phone);
        if (p.getPhone() == null) {
            return ReturnJson.fail(-1, "该手机号未注册");
        }
        if (p.getPassword() == null) {
            return ReturnJson.fail(-1, "该手机号未设置密码，请使用验证码登录");
        }
        if (!p.getPassword().equals(pwd)) {
            return ReturnJson.fail(-1, "密码错误");
        }
        String token = TokenUtils.setToken(p.getUid());
        return ReturnJson.success(token, "ok");
    }

    // 设置密码
    public ReturnJson<String> SetPassword(PwdEntity pwdEntity, HttpServletRequest request) {

        String password = DigestUtils.md5Hex(pwdEntity.getPassword());
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        log.info("用户id：" + map.get("uid"));
        String uid = map.get("uid");
        if (uid != null) {
            signMapper.UpdatePwd(password, uid);
        }
        return ReturnJson.success(null, "ok");
    }
    // 手机号是否存在
    public ReturnJson<String> FindIsPhone(LoginVerCode loginVerCode) {
        RegisterEntity p = signMapper.FindUserPhone(loginVerCode.getPhone());// 查询手机号是否存在
        if (p == null) {
            return ReturnJson.fail(-1, "手机号不存在");
        }
        return ReturnJson.success(null, "ok");
    }
    // 重置密码
    public ReturnJson<String> ResetPwd(LoginPwd loginPwd){
        signMapper.ResetPwd(loginPwd);
        return ReturnJson.success(null,"重置密码成功");
    }
}

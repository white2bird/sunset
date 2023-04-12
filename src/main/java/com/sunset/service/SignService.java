package com.sunset.service;

import com.sunset.entity.Sign.LoginPwd;
import com.sunset.entity.Sign.LoginVerCode;
import com.sunset.entity.Sign.PwdEntity;
import com.sunset.entity.Sign.RegisterEntity;
import com.sunset.entity.User.UserFollow;
import com.sunset.entity.User.UserInfoEntity;
import com.sunset.mapper.SignMapper;
import com.sunset.mapper.TrendsMapper;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class SignService {
    @Autowired
    SignMapper signMapper;
    @Autowired
    TrendsMapper trendsMapper;
    // 注册 【待用】
    public ReturnJson<String> RegisterInsert(RegisterEntity registerEntity) {
        String phone = registerEntity.phone;
        String verCode = registerEntity.verCode;
        RegisterEntity p = signMapper.FindUserPhone(phone);// 查询手机号是否存在
        if (p != null) {
            return ReturnJson.fail(-1, "手机号已注册");
        }
        if (phone == null || phone.equals("")) {
            return ReturnJson.fail(-1, "手机号不能为空");
        }
        if (verCode == null || verCode.equals("")) {
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


            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setCreate_time(dateTime);
            String userId = UUID.randomUUID().toString().toUpperCase();
            userInfoEntity.setId(userId);
            int random = (int) (Math.random() * 999999);
            String nickname = "用户-" + random;
            userInfoEntity.setNickname(nickname);
            userInfoEntity.setUid(uuid);
            int sid = (int) (Math.random() * 999999);
            String tmp = System.currentTimeMillis() + "";
            String showid = sid + tmp.substring(tmp.length() - 4, tmp.length());
            userInfoEntity.setShowid(showid);

            // 初始化粉丝，关注，获赞
            UserFollow userFollow = new UserFollow();
            userFollow.setFollowers(0);
            userFollow.setStar(0);
            userFollow.setFollowers(0);
            userFollow.setUid(uuid);
            String followId = UUID.randomUUID().toString().toUpperCase();
            userFollow.setId(followId);

            trendsMapper.SetInitFollow(userFollow);
            signMapper.RegisterInsert(registerEntity);
            signMapper.UserInfoInsert(userInfoEntity);
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
        if (p == null) {
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
    public ReturnJson<String> ResetPwd(LoginPwd loginPwd) {
        String password = DigestUtils.md5Hex(loginPwd.getPassword());
        String phone = loginPwd.getPhone();
        signMapper.ResetPwd(phone, password);
        return ReturnJson.success(null, "重置密码成功");
    }

    // 换绑手机号
    public ReturnJson<String> UpdatePhone(String phone, HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        RegisterEntity r = signMapper.FindUserInfo(uid);
        String oPhone = r.getPhone();
        if (Objects.equals(phone, oPhone)) {
            return ReturnJson.fail(-1, "当前手机号与已绑手机号重复");
        }
        signMapper.UpdatePhone(uid, phone);
        return ReturnJson.success(null, "ok");
    }

    // 获取用户信息
    public ReturnJson<UserInfoEntity> GetUserInfo(HttpServletRequest request) {
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        UserInfoEntity userInfoEntity = signMapper.GetUserInfo(uid);
        userInfoEntity.setDescription(EmojiParser.parseToUnicode(userInfoEntity.getDescription()));
        return ReturnJson.success(userInfoEntity, "ok");
    }
    // 更新用户信息
    public  ReturnJson<String> UpdateUserInfo(UserInfoEntity userInfoEntity,HttpServletRequest request) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(LocalDateTime.now());
        userInfoEntity.setUpdate_time(dateTime);
        userInfoEntity.setDescription(EmojiParser.parseToAliases(userInfoEntity.getDescription()));
        log.info(String.valueOf(userInfoEntity));
        int state = userInfoEntity.getState();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(userInfoEntity.getBirthday());
        // 根据日期转换星座
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DATE);
        String constellation = constellation(month+1, day);
        log.info(constellation);
        userInfoEntity.setConstellation(constellation);
        // 仅更新用户简介
        if(state == 1){
            signMapper.UpdateUserDesc(userInfoEntity);
            return ReturnJson.success(null,"ok");
        }
        signMapper.UpdateUserInfo(userInfoEntity);
        return ReturnJson.success(null,"ok");
    }
    // 注销账号
    public ReturnJson<String> DistryAccount(HttpServletRequest request){
        String token = request.getHeader("ms_token");
        Map<String, String> map = TokenUtils.SelectToken(token);
        String uid = map.get("uid");
        RegisterEntity r = signMapper.FindUserInfo(uid);
        if(r != null){
            signMapper.DeleteUser(uid);
            signMapper.DeleteUserInfo(uid);
            return ReturnJson.success(null,"ok");
        }
        return ReturnJson.fail(-1,"账号不存在");
    }

    public static String constellation(int month, int day) {
        String constellation = "";
        if (month == 1 && day >= 20 || month == 2 && day <= 18) {
            constellation = "水瓶座";
        }
        if (month == 2 && day >= 19 || month == 3 && day <= 20) {
            constellation = "双鱼座";
        }
        if (month == 3 && day >= 21 || month == 4 && day <= 19) {
            constellation = "白羊座";
        }
        if (month == 4 && day >= 20 || month == 5 && day <= 20) {
            constellation = "金牛座";
        }
        if (month == 5 && day >= 21 || month == 6 && day <= 21) {
            constellation = "双子座";
        }
        if (month == 6 && day >= 22 || month == 7 && day <= 22) {
            constellation = "巨蟹座";
        }
        if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            constellation = "狮子座";
        }
        if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            constellation = "处女座";
        }
        if (month == 9 && day >= 23 || month == 10 && day <= 23) {
            constellation = "天秤座";
        }
        if (month == 10 && day >= 24 || month == 11 && day <= 22) {
            constellation = "天蝎座";
        }
        if (month == 11 && day >= 23 || month == 12 && day <= 21) {
            constellation = "射手座";
        }
        if (month == 12 && day >= 22 || month == 1 && day <= 19) {
            constellation = "摩羯座";
        }
        return constellation;
    }

}

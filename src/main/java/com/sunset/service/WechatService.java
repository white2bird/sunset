package com.sunset.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sunset.config.WechatConfig;
import com.sunset.entity.Sign.Register;
import com.sunset.entity.Sign.RegisterEntity;
import com.sunset.entity.User.UserFollow;
import com.sunset.entity.User.UserInfoEntity;
import com.sunset.mapper.RegisterMapper;
import com.sunset.mapper.SignMapper;
import com.sunset.mapper.TrendsMapper;
import com.sunset.response.AccessToken;
import com.sunset.response.WechatUser;
import com.sunset.utils.ReturnJson;
import com.sunset.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WechatService {

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private RegisterMapper registerMapper;

    @Resource
    private TrendsMapper trendsMapper;

    @Autowired
    private SignMapper signMapper;



    /**
     * needBind 是否需要绑定手机 1：需要 0：不需要
     * token    如果发现绑定过 直接返回 否则返回“”
     * openid   需要绑定手机的时候会返回openid
     *
     * @param code
     * @return
     */

    public Map<String, Object> getLocalTokenOrBindPhone(String code){
        Map<String, Object> result = new HashMap<>();
        AccessToken accessToken = getAccessToken(code);
        if(accessToken == null){
            throw new RuntimeException("获取access_token失败");
        }
        result.put("needBind", 0);

        LambdaQueryWrapper<Register> registerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        registerLambdaQueryWrapper.eq(Register::getOpenid, accessToken.getOpenid());
        Register register = registerMapper.selectOne(registerLambdaQueryWrapper);
        if(register == null){
            result.put("needBind",  1);
            result.put("token", "");
            result.put("openid", accessToken.getOpenid());
            return result;
        }
        // 返回token
        result.put("openid", "");
        String token = TokenUtils.setToken(register.getUid());
        result.put("token", token);
        return result;
    }

    /**
     * token 返回token
     * @param openid
     * @param phone
     * @return
     */
    public Map<String, Object> bindPhone(String openid, String phone){
        Map<String, Object> result = new HashMap<>();
        LambdaQueryWrapper<Register> registerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        registerLambdaQueryWrapper.eq(Register::getPhone, phone);
        registerLambdaQueryWrapper.isNotNull(Register::getOpenid);
        Register register = registerMapper.selectOne(registerLambdaQueryWrapper);
        if(register != null){
            throw new RuntimeException("该手机号已经绑定");
        }
        registerLambdaQueryWrapper.clear();
        // 绑定手机
        registerLambdaQueryWrapper.eq(Register::getPhone, phone);
        register = registerMapper.selectOne(registerLambdaQueryWrapper);
        if(register != null){
            UpdateWrapper<Register> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("phone", phone);
            updateWrapper.set("openid", openid);
            registerMapper.update(null, updateWrapper);
            result.put("token", TokenUtils.setToken(register.getUid()));
            return result;
        }
        // 创建一个新用户
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setPhone(phone);
        String uuid = UUID.randomUUID().toString().toUpperCase();
        registerEntity.setUid(uuid);
        registerEntity.setOpenid(openid);
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
        result.put("token", token);
        return result;
    }

    /**
     * 通过code获取access_token
     */
    @Operation(summary = "通过code获取自己的access_token，绑定手机相关")
    public AccessToken getAccessToken(String code) {
        String url = wechatConfig.getAccessTokenUrl(code);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return JSON.parseObject(response.getBody(), AccessToken.class);
    }

    /**
     * 通过access_token和openid获取用户信息
     */
    public WechatUser getUserInfo(String accessToken, String openId) {
        String url = wechatConfig.getUserUserInfoUrl(accessToken, openId);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return JSON.parseObject(response.getBody(), WechatUser.class);
    }
}

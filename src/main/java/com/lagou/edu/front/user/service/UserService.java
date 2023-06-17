package com.lagou.edu.front.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lagou.edu.common.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author xianhongle
 * @data 2022/3/8 11:27 下午
 **/
@Service
@Slf4j
@RefreshScope
public class UserService {

    @Autowired
    @Qualifier("com.lagou.edu.front.user.service.OAuthRemoteService")
    private OAuthRemoteService oAuthRemoteService;

    @Value("${spring.oauth.client_id}")
    private String clientId;
    @Value("${spring.oauth.client_secret}")
    private String clientSecret;
    @Value("${spring.oauth.scope}")
    private String scope;
    @Value("${spring.oauth.grant_type}")
    private String grantType;
    @Value("${spring.oauth.refresh_grant_type}")
    private String refreshGrantType;

    public ResponseDTO<String> createAuthToken(String phone,String password,String code,Integer type){
        log.info("phone[{}],password[{}],scope[{}],grantType[{}],clientId[{}],clientSecret[{}]",phone,password,scope,grantType,clientId,clientSecret);
        String token = null;

        try{
            if(type == 0){
                // 用户名和密码登陆
                token = oAuthRemoteService.createToken(phone,password,scope,grantType,clientId,clientSecret,null);
            }else if(type == 1){
                // 手机和验证码登陆
                token = oAuthRemoteService.createToken(phone,password,scope,grantType,clientId,clientSecret,"mobile");
            }else if(type == 2){
                // 微信登陆
                token = oAuthRemoteService.createToken(phone,password,scope,grantType,clientId,clientSecret,"weixin");
            }
            if(StringUtils.isBlank(token)){
                ResponseDTO.ofError(206,"登陆失败");
            }
            JSONObject parseObject = JSON.parseObject(token);
            String tokenCode = parseObject.getString("code");
            if(StringUtils.isNotBlank(tokenCode)){
                log.info("phone {},jwt token is null, token {}",phone,token);
                if("040072".equals(tokenCode)){
                    return ResponseDTO.ofError(207,"验证码错误("+tokenCode+")");
                }
                return ResponseDTO.ofError(206,"登陆失败("+tokenCode+")");
            }
            String userId = parseObject.getString("user_id");
            if(StringUtils.isBlank(userId)){
                return ResponseDTO.ofError(-1,"login fail");
            }
            return ResponseDTO.response(1,"success",token);
        }catch(Exception e){
            log.error("",e);
            return ResponseDTO.ofError(206,"登陆失败");
        }
    }

    public String getRefreshTokenByWeixin(String unionId, String openId) {
        log.info("unionId:{}, openId:{}, scope:{}, grantType:{}, clientId:{}, clientSecret:{}", unionId, openId, scope, grantType, clientId, clientSecret);
        try {
            String token = this.oAuthRemoteService.createToken(unionId, openId, scope, grantType, clientId, clientSecret, "weixin");
            log.info("unionId:{}, openId:{}, refresh_token:{}", unionId, openId, token);
            if (StringUtils.isBlank(token)) {
                return null;
            }
            JSONObject jsonObject = JSON.parseObject(token);
            return jsonObject.getString("refresh_token");
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

}

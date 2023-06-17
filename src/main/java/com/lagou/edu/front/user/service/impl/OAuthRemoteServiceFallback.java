package com.lagou.edu.front.user.service.impl;

import com.lagou.edu.front.user.service.OAuthRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * OAuthRemoteServiceFallback
 *
 * @author xianhongle
 * @data 2022/4/5 4:39 下午
 **/
@Component("OAuthRemoteServiceFallback")
@Slf4j
public class OAuthRemoteServiceFallback implements OAuthRemoteService {
    @Override
    public String createToken(String phone, String password, String scope, String grantType, String clientId, String clientSecret, String authType) {
        log.error("手机号[{}],发放access_token发生异常",phone);
        return null;
    }

    @Override
    public String refreshToken(String refreshToken, String grantType, String clientId, String clientSecret) {
        log.error("refreshToken[{}],刷新access_token发生异常", refreshToken);
        return null;
    }
}

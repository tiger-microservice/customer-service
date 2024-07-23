package com.tiger.customer.utils;

import com.tiger.common.utils.ObjectMapperUtil;
import com.tiger.customer.constants.AppConstants;
import com.tiger.customer.dtos.UserPayload;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public final class UserInfoUtil {

    public static UserPayload getAccountUser() {
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        var data = jwt.getClaims().get(AppConstants.JwtKey.DATA);
        return ObjectMapperUtil.castToObject((String) data, UserPayload.class);
    }
}

package com.tiger.customer.controllers.external.v1;

import com.tiger.common.utils.ObjectMapperUtil;
import com.tiger.customer.constants.AppConstants;
import com.tiger.customer.dtos.UserPayload;
import com.tiger.customer.dtos.response.ApiResponse;
import com.tiger.customer.utils.UserInfoUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class HospitalController {

    @GetMapping
    public ApiResponse<Object>getCustomerInfo() {
        return ApiResponse.responseOK(UserInfoUtil.getAccountUser());
    }
}

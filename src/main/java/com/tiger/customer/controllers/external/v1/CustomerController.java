package com.tiger.customer.controllers.external.v1;

import com.tiger.customer.dtos.response.ApiResponse;
import com.tiger.customer.services.CustomerService;
import com.tiger.customer.utils.UserInfoUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    final CustomerService service;

    @GetMapping("/info/{customerId}")
    public ApiResponse<Object>getCustomerInfo(@PathVariable UUID customerId) {
        return ApiResponse.responseOK(service.getProfile(customerId));
    }
}

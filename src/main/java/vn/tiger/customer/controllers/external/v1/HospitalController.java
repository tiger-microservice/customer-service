package vn.tiger.customer.controllers.external.v1;

import com.tiger.cores.dtos.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tiger.customer.utils.UserInfoUtil;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class HospitalController {

    @GetMapping
    public ApiResponse<Object> getCustomerInfo() {
        return ApiResponse.responseOK(UserInfoUtil.getAccountUser());
    }
}

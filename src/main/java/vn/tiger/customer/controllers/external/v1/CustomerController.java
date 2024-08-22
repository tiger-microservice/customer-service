package vn.tiger.customer.controllers.external.v1;

import com.tiger.cores.dtos.responses.ApiResponse;
import vn.tiger.customer.services.CustomerService;
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
    public ApiResponse<Object> getCustomerInfo(@PathVariable UUID customerId) {
        return ApiResponse.responseOK(service.getProfile(customerId));
    }
}

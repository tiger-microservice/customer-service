package vn.tiger.customer.services;

import com.tiger.cores.exceptions.BusinessLogicException;
import com.tiger.cores.exceptions.ErrorCode;
import vn.tiger.customer.repositories.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    final CustomerRepository customerRepository;

    public Object getProfile(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}

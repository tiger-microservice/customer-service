package vn.tiger.customer.services;

import vn.tiger.customer.entities.Customer;
import vn.tiger.customer.exceptions.AppErrorCode;
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
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessLogicException(AppErrorCode.RESOURCE_NOT_FOUND));
        return customer;
    }
}

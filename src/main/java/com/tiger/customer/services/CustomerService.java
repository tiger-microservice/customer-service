package com.tiger.customer.services;

import com.tiger.customer.entities.Customer;
import com.tiger.customer.exceptions.BusinessLogicException;
import com.tiger.customer.exceptions.ErrorCode;
import com.tiger.customer.repositories.CustomerRepository;
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
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.RESOURCE_NOT_FOUND));
        return customer;
    }
}

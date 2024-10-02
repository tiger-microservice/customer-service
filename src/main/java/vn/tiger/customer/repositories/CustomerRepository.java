package vn.tiger.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tiger.customer.entities.Customer;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}

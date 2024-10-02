package vn.tiger.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tiger.customer.entities.Department;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}

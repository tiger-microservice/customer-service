package com.tiger.customer.repositories;

import com.tiger.customer.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}

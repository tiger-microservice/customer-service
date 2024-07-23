package com.tiger.customer.repositories;

import com.tiger.customer.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}

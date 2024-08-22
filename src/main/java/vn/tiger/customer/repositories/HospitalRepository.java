package vn.tiger.customer.repositories;

import vn.tiger.customer.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}

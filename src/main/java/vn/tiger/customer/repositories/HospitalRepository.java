package vn.tiger.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tiger.customer.entities.Hospital;

import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}

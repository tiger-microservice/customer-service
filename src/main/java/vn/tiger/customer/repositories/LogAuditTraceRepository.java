package vn.tiger.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tiger.customer.entities.LogAuditTrace;

import java.util.UUID;

public interface LogAuditTraceRepository extends JpaRepository<LogAuditTrace, UUID> {
}

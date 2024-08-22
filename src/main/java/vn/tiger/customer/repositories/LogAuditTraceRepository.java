package vn.tiger.customer.repositories;

import vn.tiger.customer.entities.LogAuditTrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogAuditTraceRepository extends JpaRepository<LogAuditTrace, UUID> {
}

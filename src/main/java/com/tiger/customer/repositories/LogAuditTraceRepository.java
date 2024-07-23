package com.tiger.customer.repositories;

import com.tiger.customer.entities.LogAuditTrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogAuditTraceRepository extends JpaRepository<LogAuditTrace, UUID> {
}

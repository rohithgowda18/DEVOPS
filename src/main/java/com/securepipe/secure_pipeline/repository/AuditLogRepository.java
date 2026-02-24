package com.securepipe.securepipeline.repository;

import com.securepipe.securepipeline.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}
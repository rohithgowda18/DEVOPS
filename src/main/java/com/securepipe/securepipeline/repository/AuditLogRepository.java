package com.securepipe.securepipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securepipe.securepipeline.model.AuditLog;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}
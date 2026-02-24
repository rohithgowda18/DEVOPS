package com.securepipe.securepipeline.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private int secretsFound;

    private LocalDateTime scanTime;

    // Default constructor
    // Touch: forced update for recompilation
    public AuditLog() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSecretsFound() {
        return secretsFound;
    }

    public void setSecretsFound(int secretsFound) {
        this.secretsFound = secretsFound;
    }

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }
}
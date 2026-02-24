package com.securepipe.securepipeline.model;

import jakarta.persistence.*;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;

    @Column(length = 5000)
    private String redactedContent;

    public AuditLog() {}

    public AuditLog(String filePath, String redactedContent) {
        this.filePath = filePath;
        this.redactedContent = redactedContent;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getFilePath() { return filePath; }

    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getRedactedContent() { return redactedContent; }

    public void setRedactedContent(String redactedContent) {
        this.redactedContent = redactedContent;
    }
}
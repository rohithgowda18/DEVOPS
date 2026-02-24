package com.securepipe.securepipeline.service;

import com.securepipe.securepipeline.model.AuditLog;
import com.securepipe.securepipeline.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SecretScannerService {

    private final AuditLogRepository auditLogRepository;

    public SecretScannerService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    // Secret Patterns
    private static final Pattern API_KEY =
            Pattern.compile("sk_[a-zA-Z0-9]+");

    private static final Pattern PASSWORD =
            Pattern.compile("(?i)password\\s*=\\s*.+");

    private static final Pattern EMAIL =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}");

    public String scanFile(String filePath) throws IOException {

        String content = Files.readString(Path.of(filePath));
        String result = content;

        int secretsFound = 0;

        // Detect API Keys
        Matcher apiMatcher = API_KEY.matcher(result);
        while (apiMatcher.find()) {
            secretsFound++;
        }
        result = API_KEY.matcher(result).replaceAll("sk_****");

        // Detect Passwords
        Matcher passwordMatcher = PASSWORD.matcher(result);
        while (passwordMatcher.find()) {
            secretsFound++;
        }
        result = PASSWORD.matcher(result).replaceAll("password=****");

        // Detect Emails
        Matcher emailMatcher = EMAIL.matcher(result);
        while (emailMatcher.find()) {
            secretsFound++;
        }
        result = EMAIL.matcher(result).replaceAll("****@****");

        // Save Audit Log
        AuditLog log = new AuditLog();
        log.setFileName(filePath);
        log.setSecretsFound(secretsFound);
        log.setScanTime(LocalDateTime.now());

        auditLogRepository.save(log);

        return result;
    }
}
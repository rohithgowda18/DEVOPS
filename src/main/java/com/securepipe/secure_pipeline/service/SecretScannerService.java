package com.securepipe.securepipeline.service;

import org.springframework.stereotype.Service;

import com.securepipe.securepipeline.model.AuditLog;
import com.securepipe.securepipeline.repository.AuditLogRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

@Service
public class SecretScannerService {

    private final AuditLogRepository repository;

    public SecretScannerService(AuditLogRepository repository) {
        this.repository = repository;
    }

    // Simple patterns for demo
    private static final Pattern API_KEY =
            Pattern.compile("sk_[a-zA-Z0-9]+");

    private static final Pattern PASSWORD =
            Pattern.compile("(?i)password\\s*=\\s*.+");

    private static final Pattern EMAIL =
            Pattern.compile(
                "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}"
            );

    public String scanFile(String filePath) throws IOException {

    String content = Files.readString(Path.of(filePath));
    String result = content;

    result = API_KEY.matcher(result).replaceAll("sk_****");
    result = PASSWORD.matcher(result).replaceAll("password=****");
    result = EMAIL.matcher(result).replaceAll("****@****");

    // Save to database
    AuditLog log = new AuditLog(filePath, result);
    repository.save(log);

    return result;
}
}
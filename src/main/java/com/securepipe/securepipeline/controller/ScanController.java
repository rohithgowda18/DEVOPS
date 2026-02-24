package com.securepipe.securepipeline.controller;

import com.securepipe.securepipeline.service.SecretScannerService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ScanController {

    private final SecretScannerService scanner;

    public ScanController(SecretScannerService scanner) {
        this.scanner = scanner;
    }

    @GetMapping("/scan")
    public String scanFile(@RequestParam String path)
            throws IOException {

        return scanner.scanFile(path);
    }
}
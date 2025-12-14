package com.demo.SpringAIDemo.controller;

import com.demo.SpringAIDemo.service.DocumentUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@CrossOrigin
public class DocumentUploadController {

    private final DocumentUploadService documentService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            documentService.store(file);
        }
        return ResponseEntity.ok("Documents uploaded successfully");
    }
}

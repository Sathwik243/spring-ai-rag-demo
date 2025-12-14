package com.demo.SpringAIDemo.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentUploadService {

    private final VectorStore vectorStore;

    public void store(MultipartFile file) throws IOException {
        String content = extractText(file);
        Document doc = new Document(content); // constructor takes String content
        vectorStore.add(List.of(doc));
    }

    private String extractText(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename().toLowerCase();

        if (filename.endsWith(".pdf")) {
            return extractPdf(file.getInputStream());
        } else if (filename.endsWith(".docx")) {
            return extractDocx(file.getInputStream());
        } else {
            return new String(file.getBytes(), StandardCharsets.UTF_8);
        }
    }

    private String extractPdf(InputStream inputStream) throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String extractDocx(InputStream inputStream) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(inputStream)) {
            return doc.getParagraphs()
                    .stream()
                    .map(XWPFParagraph::getText)
                    .collect(Collectors.joining("\n"));
        }
    }
}




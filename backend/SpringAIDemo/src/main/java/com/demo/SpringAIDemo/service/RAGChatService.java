package com.demo.SpringAIDemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RAGChatService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public String ask(String question) {

        //Retrieve top 5 similar documents
        List<Document> docs = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question)
                        .topK(5)
                        .build()
        );

        //Combine content
        String context = docs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));

        //Create prompt
        String prompt = "Use the following context to answer the question:\n"
                + context + "\nQuestion: " + question;

        //Call Gemma3
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}



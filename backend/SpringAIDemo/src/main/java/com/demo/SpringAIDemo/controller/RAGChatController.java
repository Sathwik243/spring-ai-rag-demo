package com.demo.SpringAIDemo.controller;


import com.demo.SpringAIDemo.dto.ChatRequest;
import com.demo.SpringAIDemo.service.RAGChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin
public class RAGChatController {

    private final RAGChatService chatService;

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return chatService.ask(request.getMessage());
    }
}



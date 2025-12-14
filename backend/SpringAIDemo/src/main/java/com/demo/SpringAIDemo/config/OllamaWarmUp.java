package com.demo.SpringAIDemo.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OllamaWarmUp {

    private final ChatClient chatClient;

    @PostConstruct
    public void warmup() {
        chatClient.prompt()
                .user("Hello")
                .call();
    }
}


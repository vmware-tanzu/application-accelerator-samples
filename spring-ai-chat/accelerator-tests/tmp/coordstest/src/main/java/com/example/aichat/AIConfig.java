package com.example.aichat;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.memory.ConversationBufferMemory;
import org.springframework.ai.memory.Memory;
import org.springframework.ai.operator.AiOperator;
import org.springframework.ai.operator.DefaultPromptTemplateStrings;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class AIConfig {

    @Bean
    @Scope(value="session", proxyMode = ScopedProxyMode.INTERFACES)
    Memory memory() {
        return new ConversationBufferMemory();
    }

    @Bean
    AiOperator aiOperator(ChatClient aiClient, VectorStore vectorStore, Memory memory) {
        return AiOperator.builder()
                .aiClient(aiClient)
                .promptTemplate(DefaultPromptTemplateStrings.RAG_PROMPT)
                .conversationMemory(memory)
                .vectorStore(vectorStore)
                .build();
    }

}

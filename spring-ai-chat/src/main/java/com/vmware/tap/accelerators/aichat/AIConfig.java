package com.vmware.tap.accelerators.aichat;

import org.springframework.ai.chat.ChatClient;
import com.vmware.tap.accelerators.aichat.memory.ConversationBufferMemory;
import com.vmware.tap.accelerators.aichat.memory.Memory;
import com.vmware.tap.accelerators.aichat.operator.AiOperator;
import com.vmware.tap.accelerators.aichat.operator.DefaultPromptTemplateStrings;
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

package com.vmware.tap.accelerators.aichat;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class SimpleVectorStoreConfig {

    @Value("${app.vectorstore.path}")
    private String vectorStorePath;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingClient embeddingClient) {
        SimpleVectorStore simpleVectorStore = new SimpleVectorStore(embeddingClient);
        File vectorStoreFile = new File(vectorStorePath);
        if (vectorStoreFile.exists()) {
            simpleVectorStore.load(vectorStoreFile);
        }
        return simpleVectorStore;
    }

}

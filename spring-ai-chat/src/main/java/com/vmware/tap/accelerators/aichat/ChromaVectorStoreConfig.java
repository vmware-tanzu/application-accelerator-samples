package com.vmware.tap.accelerators.aichat;

import org.springframework.ai.chroma.ChromaApi;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorsore.ChromaVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChromaVectorStoreConfig {

    @Bean
    VectorStore chromaVectorStore(EmbeddingClient embeddingClient) {
        ChromaApi chromaApi = new ChromaApi("http://localhost:8000", new RestTemplate());
        return new ChromaVectorStore(embeddingClient, chromaApi);
    }

}

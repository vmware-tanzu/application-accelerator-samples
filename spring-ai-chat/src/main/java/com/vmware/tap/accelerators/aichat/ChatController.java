package com.vmware.tap.accelerators.aichat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(
            ChatClient.Builder chatClientBuilder,
            VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(
                        QuestionAnswerAdvisor.builder(vectorStore)
                                .build(),
                        MessageChatMemoryAdvisor.builder(
                                MessageWindowChatMemory.builder().build())
                                .build())
                .defaultTemplateRenderer(StTemplateRenderer.builder()
                        .startDelimiterToken('<').endDelimiterToken('>').build())
                .build();
    }

    @PostMapping
    public Answer chat(@RequestBody Question question, Authentication user) {
        return chatClient.prompt()
                .user(question.question())
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, user.getPrincipal()))
                .call()
                .entity(Answer.class);
    }

}

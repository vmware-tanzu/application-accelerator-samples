package com.vmware.tap.accelerators.aichat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.template.ValidationMode;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

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
                                // The following is a workaround for a bug in Spring AI 1.0.0-M8.
                                // Should be fixed in 1.0.0-RC1.
                                .promptTemplate(PromptTemplate.builder()
                                        .renderer(StTemplateRenderer.builder()
                                                .startDelimiterToken('<').endDelimiterToken('>').build())
                                        .template("""
                                                Context information is below, surrounded by ---------------------
                                                
                                                ---------------------
                                                <question_answer_context>
                                                ---------------------
                                                
                                                Given the context and provided history information and not prior knowledge,
                                                reply to the user comment. If the answer is not in the context, inform
                                                the user that you can't answer the question.
                                                """)
                                        .build())
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
                .advisors(advisorSpec -> advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, user.getPrincipal()))
                .call()
                .entity(Answer.class);
    }

}

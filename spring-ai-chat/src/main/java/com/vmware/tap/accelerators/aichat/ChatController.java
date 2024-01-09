package com.vmware.tap.accelerators.aichat;

import org.slf4j.Logger;
import org.springframework.ai.operator.AiOperator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ChatController.class);

    private final AiOperator aiOperator;

    public ChatController(AiOperator aiOperator) {
        this.aiOperator = aiOperator;
    }

    @PostMapping
    public Answer chat(@RequestBody Question question) {
        try {
            return new Answer(aiOperator.generate(Map.of("input", question.question())));
        } catch (Exception e) {
            LOG.error("Error generating response.", e);
            return new Answer("I'm sorry, there was a problem. Try again.");
        }
    }

    @PostMapping("/clear")
    public void clear() {
        aiOperator.clear();
    }

}

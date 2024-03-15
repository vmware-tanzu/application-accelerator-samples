package com.vmware.tap.accelerators.aichat.operator;

import org.springframework.ai.chat.ChatClient;
import com.vmware.tap.accelerators.aichat.memory.Memory;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.Map;

public interface AiOperator {

	String generate();

	String generate(Map<String, Object> parameters);

	void clear();

	static AiOperator create(ChatClient aiClient) {
		return (new DefaultAiOperatorBuilder()).aiClient(aiClient).build();
	}

	static Builder builder() {
		return new DefaultAiOperatorBuilder();
	}

	AiOperator promptTemplate(String promptTemplate);

	public interface Builder {

		Builder aiClient(ChatClient aiClient);

		Builder promptTemplate(String promptTemplate);

		Builder vectorStore(VectorStore vectorStore);

		Builder vectorStoreKey(String vectorStoreKey);

		Builder conversationMemory(Memory memory);

		Builder inputParameterName(String inputParameterName);

		Builder historyParameterName(String historyParameterName);

		Builder k(int k);

		AiOperator build();

	}

}

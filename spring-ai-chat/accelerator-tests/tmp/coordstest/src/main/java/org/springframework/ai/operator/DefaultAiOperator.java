package org.springframework.ai.operator;

import org.slf4j.Logger;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.memory.Memory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DefaultAiOperator implements AiOperator {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DefaultAiOperator.class);

	private final ChatClient aiClient;

	private PromptTemplate promptTemplate;

	private VectorStore vectorStore;

	private String vectorStoreKey;

	private String inputParameterName = "input";

	private String historyParameterName = "history";

	private int k = 2;

	private Memory memory;

	protected DefaultAiOperator(ChatClient aiClient, PromptTemplate promptTemplate) {
		this.aiClient = aiClient;
		this.promptTemplate = promptTemplate;
	}

	public AiOperator promptTemplate(String promptTemplate) {
		this.promptTemplate = new PromptTemplate(promptTemplate);
		return this;
	}

	public AiOperator vectorStore(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
		return this;
	}

	public AiOperator vectorStoreKey(String vectorStoreKey) {
		this.vectorStoreKey = vectorStoreKey;
		return this;
	}

	public AiOperator conversationMemory(Memory memory) {
		this.memory = memory;
		return this;
	}

	public AiOperator inputParameterName(String inputParameterName) {
		this.inputParameterName = inputParameterName;
		return this;
	}

	public AiOperator historyParameterName(String historyParameterName) {
		this.historyParameterName = historyParameterName;
		return this;
	}

	public AiOperator k(int k) {
		this.k = k;
		return this;
	}

	@Override
	public String generate() {
		return generate(Map.of());
	}

	@Override
	public String generate(Map<String, Object> parameters) {
		Map<String, Object> resolvedParameters = new HashMap<>(parameters);

		if (vectorStore != null) {
			String input = memory != null ? generateStandaloneQuestion(parameters)
					: parameters.get(inputParameterName).toString();

			LOG.info("input: {}", input);

			List<Document> documents = vectorStore.similaritySearch(SearchRequest.query(input).withTopK(k));
			List<String> contentList = documents.stream().map(doc -> {
				return doc.getContent() + "\n";
			}).toList();
			resolvedParameters.put(inputParameterName, input);
			resolvedParameters.put(vectorStoreKey, contentList);
		}
		else {
			resolvedParameters = preProcess(resolvedParameters);
		}

		PromptTemplate promptTemplateCopy = new PromptTemplate(promptTemplate.getTemplate());
		String prompt = promptTemplateCopy.render(resolvedParameters).trim();
		LOG.debug("Submitting prompt: {}", prompt);
		ChatResponse aiResponse = aiClient.call(new Prompt(prompt));
		logUsage(aiResponse);
		String generationResponse = aiResponse.getResult().getOutput().getContent();

		// post-process memory
		postProcess(parameters, aiResponse);

		return generationResponse;
	}

	private void logUsage(ChatResponse aiResponse) {
		Usage usage = aiResponse.getMetadata().getUsage();
		LOG.info("Usage: Prompt Tokens: {}; Generation Tokens: {}; Total Tokens: {}", usage.getPromptTokens(), usage.getGenerationTokens(), usage.getTotalTokens());
	}

	private String generateStandaloneQuestion(Map<String, Object> parameters) {
		Map<String, Object> resolvedParameters = new HashMap<>(parameters);
		resolvedParameters = preProcess(resolvedParameters);

		PromptTemplate standalonePromptTemplate = new PromptTemplate(
				DefaultPromptTemplateStrings.STANDALONE_QUESTION_PROMPT);
		String prompt = standalonePromptTemplate.render(resolvedParameters);
		LOG.debug("Submitting standalone question prompt: {}", prompt);
		ChatResponse aiResponse = aiClient.call(new Prompt(prompt));
		logUsage(aiResponse);
		return aiResponse.getResult().getOutput().getContent();
	}

	@Override
	public void clear() {
		if (memory != null) {
			memory.clear();
		}
	}

	private Map<String, Object> preProcess(Map<String, Object> parameters) {
		Map<String, Object> combinedParameters = new HashMap<>(parameters);
		if (memory != null) {
			combinedParameters.putAll(memory.load(parameters));
		}
		return combinedParameters;
	}

	private void postProcess(Map<String, Object> parameters, ChatResponse aiResponse) {
		if (memory != null) {
			memory.save(parameters, Map.of(historyParameterName, aiResponse.getResult().getOutput().getContent()));
		}
	}

}

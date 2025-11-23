package br.com.fiap.chatia.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChatService {

    private final Client genaiClient;
    private final String modelName;

    public ChatService(@Value("${spring.ai.google.ai.chat.options.model:gemini-1.5-flash}") String modelName) {
        String apiKey = System.getenv("GOOGLE_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("A variável de ambiente 'GOOGLE_API_KEY' não foi definida.");
        }
        this.modelName = modelName;
        this.genaiClient = new Client();
    }

    public String conversar(String mensagem) throws IOException {

        String prompt = """
            Você é um assistente de apoio emocional.
            Responda com empatia, acolhimento e objetividade.
            Sempre em português.
            
            Usuário: %s
        """.formatted(mensagem);

        GenerateContentResponse response = this.genaiClient.models.generateContent(
                this.modelName,
                prompt,
                null
        );

        return response.text();
    }
}
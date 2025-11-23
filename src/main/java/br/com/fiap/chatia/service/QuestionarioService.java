package br.com.fiap.chatia.service;

import br.com.fiap.chatia.dto.request.QuestionarioRequest;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QuestionarioService {

    private final Client genaiClient;
    private final String modelName;

    public QuestionarioService(@Value("${spring.ai.google.ai.chat.options.model:gemini-1.5-flash}") String modelName) {
        String apiKey = System.getenv("GOOGLE_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("A variável de ambiente 'GOOGLE_API_KEY' não foi definida.");
        }
        this.modelName = modelName;
        this.genaiClient = new Client();
    }

    @Cacheable(value = "questionarioCache", key = "{#req.ansiedade, #req.horasSono, #req.estresse, #req.sobrecarga}")
    public String analisar(QuestionarioRequest req) throws IOException {
        System.out.println("=== GERANDO NOVA ANÁLISE (CACHE MISS) ===");
        System.out.println("Ansiedade: " + req.getAnsiedade() + ", Sono: " + req.getHorasSono() +
                ", Estresse: " + req.getEstresse() + ", Sobrecarga: " + req.getSobrecarga());

        String prompt = """
            Você é um assistente de bem-estar emocional.
            Analise o estado mental do usuário com base nas notas:

            Ansiedade: %d/10
            Horas de sono: %d horas
            Estresse: %d/10
            Sobrecarga: %d/10

            Gere uma análise empática e prática em português, com:
            1. Um diagnóstico simples sobre o estado emocional
            2. Dicas práticas para o dia
            3. Quando considerar procurar ajuda profissional

            IMPORTANTE: Formate a resposta como HTML simples:
            - Use <h4> para títulos principais
            - Use <h5> para subtítulos  
            - Use <strong> para negrito
            - Use <ul><li> para listas
            - Use <br> para quebras de linha
            - NÃO use markdown (##, *, **)

            Seja empático, humano e sem termos médicos.
            Responda em português.
        """.formatted(
                req.getAnsiedade(),
                req.getHorasSono(),
                req.getEstresse(),
                req.getSobrecarga()
        );

        try {
            GenerateContentResponse response = this.genaiClient.models.generateContent(
                    this.modelName,
                    prompt,
                    null
            );

            String resposta = response.text();
            System.out.println("Resposta da IA gerada e armazenada em cache");
            return resposta;

        } catch (Exception e) {
            System.out.println("ERRO na chamada da IA: " + e.getMessage());
            return "<p>Desculpe, houve um erro ao processar sua análise. Por favor, tente novamente.</p>";
        }
    }

    // Método para limpar o cache (opcional)
    public void limparCache() {
        // O cache será limpo automaticamente após o TTL (30 minutos)
        System.out.println("Cache do questionário será limpo automaticamente após 30 minutos");
    }
}
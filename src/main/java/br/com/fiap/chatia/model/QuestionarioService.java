package br.com.fiap.chatia.model;

import br.com.fiap.chatia.service.QuestionarioRequest;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionarioService {
    @Autowired
    private OpenAiChatModel chatModel;

    public String analisar(QuestionarioRequest req) {

        String prompt = """
            Você é um assistente de bem-estar emocional.
            Analise o estado mental do usuário com base nas notas:

            Ansiedade: %d/10
            Horas de sono: %d horas
            Estresse: %d/10
            Sobrecarga: %d/10

            Gere:

            1. Um diagnóstico simples sobre o estado emocional
            2. Dicas práticas para o dia
            3. Quando considerar procurar ajuda profissional

            Seja empático, humano e sem termos médicos.
            Respondendo em português.
        """.formatted(
                req.getAnsiedade(),
                req.getHorasSono(),
                req.getEstresse(),
                req.getSobrecarga()
        );

        return chatModel.call(prompt);
    }
}

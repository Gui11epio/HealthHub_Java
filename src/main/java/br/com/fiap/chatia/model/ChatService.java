package br.com.fiap.chatia.model;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private OpenAiChatModel chatModel;

    public String conversar(String mensagem) {

        String prompt = """
            Você é um assistente de apoio emocional.
            Responda com empatia, acolhimento e objetividade.
            Sempre em português.
            
            Usuário: %s
        """.formatted(mensagem);

        return chatModel.call(prompt);
    }
}

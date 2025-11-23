package br.com.fiap.chatia.controller;

import br.com.fiap.chatia.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService service;

    @GetMapping
    public String chatPage() {
        System.out.println("=== ACESSANDO PÁGINA DO CHAT ===");
        return "chat";
    }

    @PostMapping("/enviar")
    public String enviar(@RequestParam String mensagem, Model model) {
        System.out.println("=== RECEBENDO MENSAGEM DO CHAT ===");
        System.out.println("Mensagem: " + mensagem);

        try {
            String resposta = service.conversar(mensagem);
            System.out.println("Resposta da IA: " + resposta);

            model.addAttribute("mensagem", mensagem);
            model.addAttribute("resposta", resposta);
        } catch (Exception e) {
            System.out.println("ERRO no chat: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao processar a mensagem: " + e.getMessage());
        }
        return "chat";
    }
}
package br.com.fiap.chatia.controller;

import br.com.fiap.chatia.model.ChatService;
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
        return "chat";  // chat.html
    }

    @PostMapping("/enviar")
    public String enviar(@RequestParam String msg, Model model) {
        String resposta = service.conversar(msg);
        model.addAttribute("mensagem", msg);
        model.addAttribute("resposta", resposta);
        return "chat";
    }
}

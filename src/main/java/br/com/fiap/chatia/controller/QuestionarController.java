package br.com.fiap.chatia.controller;

import br.com.fiap.chatia.dto.request.QuestionarioRequest;
import br.com.fiap.chatia.dto.response.QuestionarioResponse;
import br.com.fiap.chatia.service.QuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questionario")
public class QuestionarController {

    @Autowired
    private QuestionarioService service;

    @GetMapping
    public String formulario() {
        System.out.println("=== ACESSANDO FORMULÁRIO ===");
        return "questionario";
    }

    @PostMapping("/analisar")
    public String analisar(
            @RequestParam("ansiedade") int ansiedade,
            @RequestParam("horasSono") int horasSono,
            @RequestParam("estresse") int estresse,
            @RequestParam("sobrecarga") int sobrecarga,
            Model model) {

        System.out.println("=== PROCESSANDO FORMULÁRIO ===");
        System.out.println("Dados recebidos: " + ansiedade + ", " + horasSono + ", " + estresse + ", " + sobrecarga);

        try {
            QuestionarioRequest req = new QuestionarioRequest(ansiedade, horasSono, estresse, sobrecarga);
            String respostaIA = service.analisar(req);

            System.out.println("=== REDIRECIONANDO PARA RESULTADO ===");
            model.addAttribute("resultado", new QuestionarioResponse(respostaIA));
            return "resultado";

        } catch (Exception e) {
            System.out.println("=== ERRO NO CONTROLLER ===");
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro: " + e.getMessage());
            return "questionario";
        }
    }
}
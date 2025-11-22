package br.com.fiap.chatia.controller;

import br.com.fiap.chatia.model.QuestionarioService;
import br.com.fiap.chatia.service.QuestionarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/questionario")
public class QuestionarController {
    @Autowired
    private QuestionarioService service;

    // Exibe a página do formulário
    @GetMapping
    public String formulario(Model model) {
        model.addAttribute("questionario", new QuestionarioRequest());
        return "questionario";  // questionario.html
    }

    // Processa o formulário e retorna a resposta da IA
    @PostMapping("/analisar")
    public String analisar(
            @Valid @ModelAttribute("questionario") QuestionarioRequest req,
            Model model
    ) {
        String resultado = service.analisar(req);
        model.addAttribute("resultado", resultado);
        return "resultado"; // resultado.html
    }
}

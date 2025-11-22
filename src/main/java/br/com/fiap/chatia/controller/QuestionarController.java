package br.com.fiap.chatia.controller;

import br.com.fiap.chatia.dto.request.QuestionarioRequest;
import br.com.fiap.chatia.dto.response.QuestionarioResponse;
import br.com.fiap.chatia.service.QuestionarioService;
import br.com.fiap.chatia.model.Questionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("questionario", new Questionario());
        return "questionario";  // questionario.html
    }

    // Processa o formulário e retorna a resposta da IA
    @PostMapping("/analisar")
    public String analisar(
            @Valid @ModelAttribute("questionario") QuestionarioRequest req,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "questionario";
        }

        String respostaIA = service.analisar(req);
        model.addAttribute("resultado", new QuestionarioResponse(respostaIA));

        return "resultado";
    }
}

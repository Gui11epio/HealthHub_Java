package br.com.fiap.chatia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, Locale locale) {
        System.out.println("Idioma atual: " + locale.getLanguage());
        model.addAttribute("currentLocale", locale);
        return "home"; // home.html
    }
}

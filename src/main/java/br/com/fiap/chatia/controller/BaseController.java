package br.com.fiap.chatia.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Locale;

@ControllerAdvice
public class BaseController {

    @ModelAttribute
    public void addCommonAttributes(Model model, Locale locale) {
        model.addAttribute("currentLocale", locale);
        model.addAttribute("isPortuguese", locale.getLanguage().equals("pt"));
        model.addAttribute("isEnglish", locale.getLanguage().equals("en"));
    }
}

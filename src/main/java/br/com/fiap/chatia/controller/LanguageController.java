package br.com.fiap.chatia.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
public class LanguageController {

    private final LocaleResolver localeResolver;

    public LanguageController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam String lang,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        Locale newLocale = new Locale(lang);
        localeResolver.setLocale(request, response, newLocale);

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
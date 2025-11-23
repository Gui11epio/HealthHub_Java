package br.com.fiap.chatia.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
public class LocaleInterceptor implements HandlerInterceptor {

    private final LocaleResolver localeResolver;

    public LocaleInterceptor(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Locale locale = localeResolver.resolveLocale(request);
        request.setAttribute("currentLocale", locale);
        return true;
    }
}

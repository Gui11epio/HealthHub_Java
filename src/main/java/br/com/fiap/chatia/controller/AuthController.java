package br.com.fiap.chatia.controller;

import br.com.fiap.chatia.dto.request.UserRequest;
import br.com.fiap.chatia.model.User;
import br.com.fiap.chatia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("user") UserRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        if (repo.findByEmail(request.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email já cadastrado");
            return "register";
        }

        User user = new User();
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setSenha(encoder.encode(request.getSenha()));

        repo.save(user);

        return "redirect:/auth/login";
    }
}

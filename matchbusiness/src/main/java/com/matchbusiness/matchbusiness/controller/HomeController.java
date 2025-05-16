package com.matchbusiness.matchbusiness.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Página pública ("/")
    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    // Página após login ("/dashboard")
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        // Obtém o e-mail do usuário autenticado
        String email = authentication.getName();
        // Busca o usuário no banco (certifique-se de ter um método findByEmail() no serviço)
        Usuario currentUser = usuarioRepository.findByEmail(email);
        model.addAttribute("currentUser", currentUser);
        return "dashboard";
    }
}

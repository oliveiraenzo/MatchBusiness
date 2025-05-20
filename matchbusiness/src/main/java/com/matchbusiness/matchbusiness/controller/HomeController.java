package com.matchbusiness.matchbusiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.repository.UsuarioRepository;

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
    if (authentication != null && authentication.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_MASTER"))) {
        return "redirect:/master/users";
    }

    // Obtém o e-mail do usuário autenticado
    String email = authentication.getName();
    Usuario currentUser = usuarioRepository.findByEmail(email);
    model.addAttribute("currentUser", currentUser);
    return "dashboard"; // templates/dashboard.html
}


    // Página "Sobre" ("/sobre")
@GetMapping("/sobre")
public String sobre(Model model, Authentication authentication) {
    // Obtém o e-mail do usuário autenticado
    String email = authentication.getName();
    // Busca o usuário no banco
    Usuario currentUser = usuarioRepository.findByEmail(email);
    model.addAttribute("currentUser", currentUser);
    return "sobre";
}

}

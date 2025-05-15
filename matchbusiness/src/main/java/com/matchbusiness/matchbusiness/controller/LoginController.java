/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.controller;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Heitor
 */
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Exibe a página inicial (/home) para usuários autenticados.
     * O objeto Principal contém dados do usuário autenticado.
     */
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        // Exemplo: recupera o e-mail do usuário autenticado
        String email = principal.getName();
        
        // Você pode recuperar mais dados do usuário se necessário, através do serviço.
        model.addAttribute("message", "Bem-vindo ao MatchBusiness, " + email + "!");
        return "index"; // direciona para o template src/main/resources/templates/index.html
    }

    /**
     * Retorna a página de login.
     * O Spring Security intercepta as requisições para /login, processando-as conforme definido na configuração.
     */
    @GetMapping("/login")
    public String login() {
        return "login";  // renderiza src/main/resources/templates/login.html
    }

    /**
     * Exibe o formulário de registro para criação de um novo usuário.
     * O objeto "usuario" é adicionado ao Model para ser utilizado no formulário (via Thymeleaf).
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register"; // renderiza src/main/resources/templates/register.html
    }
    
    /**
     * Processa os dados do formulário de registro.
     * Criptografa a senha e salva o usuário no banco de dados.
     * Redireciona para a página de login após o cadastro.
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("usuario") Usuario usuario) {
        // Criptografa a senha antes de salvar no banco
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        
        // Chama o serviço para salvar o novo usuário
        usuarioService.saveUser(usuario);
        
        // Após o registro, redireciona para a página de login
        return "redirect:/login";
    }
}
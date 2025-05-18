package com.matchbusiness.matchbusiness.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.service.UsuarioService;

@Controller
@RequestMapping("/master")
// Essa anotação garante que apenas usuários com a autoridade ROLE_MASTER possam acessar todas as rotas deste controller.
@PreAuthorize("hasRole('MASTER')")
public class MasterController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Exibe a lista de todos os usuários cadastrados.
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('MASTER')")
    public String listUsers(Model model, @RequestParam(value="error", required = false) String error) {
        List<Usuario> users = usuarioService.findAllUsers();
        model.addAttribute("users", users);
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "master/user-list";  // Template: src/main/resources/templates/master/user-list.html
    }

    /**
     * Exibe o formulário para cadastrar um novo usuário.
     */
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "master/user-form";
    }


    /**
     * Processa a criação de um novo usuário.
     * Note que, para produção, é importante criptografar a senha antes de salvar.
     */
    @PreAuthorize("hasRole('MASTER')")
    @PostMapping("/users")
    public String createUser(@ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "master/user-form";
        }
        usuarioService.saveUser(usuario);
        return "redirect:/master/users";
    }

    /**
     * Exibe o formulário para editar um usuário já existente.
     */
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> optUser = usuarioService.findUserById(id);
        if (!optUser.isPresent()) {
            return "redirect:/master/users?error=Usuário não encontrado";
        }
        model.addAttribute("usuario", optUser.get());
        return "master/user-form"; // Esse template espera que o objeto "usuario" contenha seu ID
    }

    /**
     * Processa a atualização de um usuário.
     */
    @PreAuthorize("hasRole('MASTER')")
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "master/user-form";
        }
        // Garante que o ID esteja setado corretamente para que o JPA reconheça a atualização
        usuario.setId(id);
        usuarioService.saveUser(usuario);
        return "redirect:/master/users";
    }

    /**
     * Deleta um usuário baseado no ID, com verificação para não excluir o superMaster.
     */
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        Optional<Usuario> optUser = usuarioService.findUserById(id);
        if (optUser.isPresent()) {
            Usuario user = optUser.get();
            if (user.getSuperMaster() != null && user.getSuperMaster()) {
                // Se for o superMaster, redireciona com mensagem de erro.
                return "redirect:/master/users?error=Não é permitido excluir o superMaster";
            }
            usuarioService.deleteUserById(id);
        } else {
            return "redirect:/master/users?error=Usuário não encontrado";
        }
        return "redirect:/master/users";
    }

    /**
     * Promove um usuário para master.
     */
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/users/{id}/promote")
    public String promoteUser(@PathVariable("id") Long id) {
        try {
            usuarioService.promoteUser(id);
        } catch (Exception ex) {
            return "redirect:/master/users?error=" + ex.getMessage();
        }
        return "redirect:/master/users";
    }

    /**
     * Demove um usuário de master, impedindo a despromoção se o usuário for o superMaster.
     */
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/users/{id}/demote")
    public String demoteUser(@PathVariable("id") Long id) {
        try {
            usuarioService.demoteUser(id);
        } catch (Exception ex) {
            return "redirect:/master/users?error=" + ex.getMessage();
        }
        return "redirect:/master/users";
    }
}
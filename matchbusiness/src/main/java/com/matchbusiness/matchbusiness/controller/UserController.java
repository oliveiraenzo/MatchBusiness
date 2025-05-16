package com.matchbusiness.matchbusiness.controller;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.repository.UsuarioRepository;
import com.matchbusiness.matchbusiness.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    // Método GET para exibir o formulário de configurações pessoais
    @GetMapping("/configuracoes")
    public String configuracoes(Model model, Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);
        model.addAttribute("usuario", usuario);
        return "user/configuracoes";  // Exibe o template: src/main/resources/templates/user/configuracoes.html
    }

    // Método POST para tratar a atualização dos dados pessoais
    @PostMapping("/configuracoes")
    public String updateConfiguracoes(@ModelAttribute("usuario") Usuario usuarioForm,
                                      Authentication authentication,
                                      RedirectAttributes redirectAttributes) {
        // Obtém o usuário atualmente autenticado pelo e-mail
        String email = authentication.getName();
        Usuario usuarioAtual = usuarioRepository.findByEmail(email);

        // Atualiza os campos desejados (por exemplo, nome, e-mail e senha)
        usuarioAtual.setNome(usuarioForm.getNome());
        usuarioAtual.setEmail(usuarioForm.getEmail());
        // Se uma nova senha foi informada, atualiza-a (o serviço já trata de criptografia, se configurado)
        if (usuarioForm.getSenha() != null && !usuarioForm.getSenha().isEmpty()) {
            usuarioAtual.setSenha(usuarioForm.getSenha());
        }

        // Salva as alterações. O método saveUser() do serviço irá cuidar da atualização.
        usuarioService.saveUser(usuarioAtual);

        // Adiciona uma mensagem flash de sucesso (opcional)
        redirectAttributes.addFlashAttribute("success", "Configurações atualizadas com sucesso!");

        // Redireciona para o dashboard (ou para outra página de sua escolha)
        return "redirect:/dashboard";
    }
}
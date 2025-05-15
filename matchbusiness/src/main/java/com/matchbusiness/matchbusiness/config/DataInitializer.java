package com.matchbusiness.matchbusiness.config;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe um superMaster no banco
        Usuario superMaster = usuarioRepository.findBySuperMasterTrue();
        if (superMaster == null) {
            superMaster = new Usuario();
            superMaster.setNome("Admin");
            superMaster.setEmail("admin@matchbusiness.com");
            superMaster.setSenha(passwordEncoder.encode("senha123")); // Certifique-se de criptografar a senha
            superMaster.setMaster(true);
            superMaster.setSuperMaster(true);
            usuarioRepository.save(superMaster);
            System.out.println("Usuário superMaster criado com sucesso!");
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.service;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;
/**
 *
 * @author Heitor
 */
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carrega o usuário a partir do e-mail (ou nome de usuário) para a autenticação.Essa implementação é chamada pelo Spring Security durante o processo de login.
     * @param email
     * @return 
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário com base no email informado
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        // Define a autoridade do usuário. Aqui, estamos assumindo que todo usuário possui a role "ROLE_USER"
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        
        // Retorna o objeto UserDetails (que contém email, senha e as authorities)
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.singleton(authority)
        );
    }

    /**
     * Método para salvar/registrar um novo usuário no banco de dados.
     * Antes de salvar, é altamente recomendado criptografar a senha.
     */
    public Usuario saveUser(Usuario usuario) {
        // Neste ponto, você deve criptografar a senha, por exemplo, utilizando BCryptPasswordEncoder
        return usuarioRepository.save(usuario);
    }
}
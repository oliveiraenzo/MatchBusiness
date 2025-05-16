/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.service;

import com.matchbusiness.matchbusiness.model.Usuario;
import com.matchbusiness.matchbusiness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Heitor
 */
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
        String role = usuario.getMaster() != null && usuario.getMaster() ? "ROLE_MASTER" : "ROLE_USER";
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.singleton(authority)
        );
    }

    public Usuario saveUser(Usuario usuario) {
        // Verifica se a senha não está vazia e se não está previamente codificada.
        if (usuario.getSenha() != null && !usuario.getSenha().startsWith("$2a$")) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUserPartially(Long id, Usuario userUpdates) {
        Optional<Usuario> optUser = usuarioRepository.findById(id);
        if (!optUser.isPresent()) {
            throw new RuntimeException("Usuário não encontrado com ID " + id);
        }
        Usuario existing = optUser.get();
        if (userUpdates.getNome() != null) {
            existing.setNome(userUpdates.getNome());
        }
        if (userUpdates.getEmail() != null) {
            existing.setEmail(userUpdates.getEmail());
        }
        if (userUpdates.getSenha() != null && !userUpdates.getSenha().isEmpty() && !userUpdates.getSenha().startsWith("$2a$")) {
            existing.setSenha(passwordEncoder.encode(userUpdates.getSenha()));
        }
        if (userUpdates.getMaster() != null) {
            existing.setMaster(userUpdates.getMaster());
        }
        if (userUpdates.getSuperMaster() != null) {
            existing.setSuperMaster(userUpdates.getSuperMaster());
        }
        return usuarioRepository.save(existing);
    }

    public Usuario promoteUser(Long id) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (!opt.isPresent()) {
            throw new RuntimeException("Usuário não encontrado com ID " + id);
        }
        Usuario user = opt.get();
        user.setMaster(true);
        return usuarioRepository.save(user);
    }

    public Usuario demoteUser(Long id) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (!opt.isPresent()) {
            throw new RuntimeException("Usuário não encontrado com ID " + id);
        }
        Usuario user = opt.get();
        if (user.getSuperMaster() != null && user.getSuperMaster()) {
            throw new RuntimeException("Não é permitido despromover o superMaster.");
        }
        user.setMaster(false);
        return usuarioRepository.save(user);
    }

    public void exportUsersToText(String filePath) throws IOException {
        List<Usuario> users = usuarioRepository.findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.format("%-5s %-20s %-30s %-10s %-12s%n", "ID", "Nome", "Email", "Master", "SuperMaster"));
            writer.write("--------------------------------------------------------------------------------\n");
            for (Usuario user : users) {
                writer.write(String.format("%-5s %-20s %-30s %-10s %-12s%n",
                        user.getId(),
                        user.getNome(),
                        user.getEmail(),
                        user.getMaster(),
                        user.getSuperMaster()));
            }
        }
    }
}
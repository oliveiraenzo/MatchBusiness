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

    /**
     * Carrega os detalhes do usuário com base no email (identificador).
     * Caso o usuário esteja marcado como master, atribuímos ROLE_MASTER; 
     * caso contrário, ROLE_USER.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
        // Verifica se o usuário é master para definir a role
        String role = (usuario.getMaster() != null && usuario.getMaster()) ? "ROLE_MASTER" : "ROLE_USER";
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.singleton(authority)
        );
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     * Observe que, para senhas, é necessário criptografá-las antes de salvar.
     */
    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Retorna uma lista com todos os usuários cadastrados.
     */
    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca um usuário pelo seu ID.
     */
    public Optional<Usuario> findUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Deleta um usuário com base no ID.
     */
    public void deleteUserById(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Atualiza parcialmente um usuário. Somente os campos não nulos de "userUpdates"
     * substituirão os valores existentes no usuário persistido.
     */
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
        if (userUpdates.getSenha() != null) {  // lembre-se de criptografar a senha, se necessário
            existing.setSenha(userUpdates.getSenha());
        }
        if (userUpdates.getMaster() != null) {
            existing.setMaster(userUpdates.getMaster());
        }
        if (userUpdates.getSuperMaster() != null) {
            existing.setSuperMaster(userUpdates.getSuperMaster());
        }
        return usuarioRepository.save(existing);
    }

    /**
     * Promove um usuário para master.
     */
    public Usuario promoteUser(Long id) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (!opt.isPresent()) {
            throw new RuntimeException("Usuário não encontrado com ID " + id);
        }
        Usuario user = opt.get();
        user.setMaster(true);
        return usuarioRepository.save(user);
    }

    /**
     * Demove um usuário de master. Caso o usuário seja o superMaster, lança exceção para
     * impedir a despromoção.
     */
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

    /**
     * Exporta os dados de todos os usuários cadastrados para um arquivo de texto no formato de tabela.
     * @param filePath o caminho onde o arquivo '.txt' será criado, por exemplo, "usuarios.txt".
     * @throws IOException se ocorrer algum problema de escrita no arquivo.
     */
    public void exportUsersToText(String filePath) throws IOException {
        List<Usuario> users = usuarioRepository.findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve o cabeçalho da tabela
            writer.write(String.format("%-5s %-20s %-30s %-10s %-12s%n", "ID", "Nome", "Email", "Master", "SuperMaster"));
            writer.write("--------------------------------------------------------------------------------\n");
            // Itera sobre cada usuário e escreve os dados formatados
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
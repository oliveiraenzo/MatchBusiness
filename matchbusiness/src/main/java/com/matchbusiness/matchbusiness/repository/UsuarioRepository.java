/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matchbusiness.matchbusiness.model.Usuario;
/**
 *
 * @author Heitor
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findBySuperMasterTrue(); // retorna um usuário cujo campo 'superMaster' esteja true, se existir
}
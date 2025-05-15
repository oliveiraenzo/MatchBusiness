/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
/**
 *
 * @author Heitor
 */
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cada Match envolve dois usuários. Utilizamos ManyToOne para cada referência.
    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    private Usuario usuario1;
    
    @ManyToOne
    @JoinColumn(name = "usuario2_id")
    private Usuario usuario2;
    
    private LocalDateTime matchDate;

    // Construtores, getters e setters...
    
    public Match() {}

    public Match(Usuario usuario1, Usuario usuario2, LocalDateTime matchDate) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.matchDate = matchDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }
}

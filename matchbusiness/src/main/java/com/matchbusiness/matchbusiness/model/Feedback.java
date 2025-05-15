/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.model;

import jakarta.persistence.*;
/**
 *
 * @author Heitor
 */
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int nota;
    
    private String comentario;
    
    // Feedback vinculado a um encontro
    @ManyToOne
    @JoinColumn(name = "encontro_id")
    private Encontro encontro;
    
    // Usuário que avaliou
    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private Usuario avaliador;

    // Construtores, getters e setters...
    public Feedback() {}

    public Feedback(int nota, String comentario, Encontro encontro, Usuario avaliador) {
        this.nota = nota;
        this.comentario = comentario;
        this.encontro = encontro;
        this.avaliador = avaliador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Encontro getEncontro() {
        return encontro;
    }

    public void setEncontro(Encontro encontro) {
        this.encontro = encontro;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }
}
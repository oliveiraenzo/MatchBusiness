/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.model;

/**
 *
 * @author Heitor
 */
public class Feedback {
    private int id;
    private int encontroId;
    private int avaliadorId;
    private int nota;
    private String comentario;

    public Feedback() {}

    public Feedback(int id, int encontroId, int avaliadorId, int nota, String comentario) {
        this.id = id;
        this.encontroId = encontroId;
        this.avaliadorId = avaliadorId;
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEncontroId() { return encontroId; }
    public void setEncontroId(int encontroId) { this.encontroId = encontroId; }
    public int getAvaliadorId() { return avaliadorId; }
    public void setAvaliadorId(int avaliadorId) { this.avaliadorId = avaliadorId; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
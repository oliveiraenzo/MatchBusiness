/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.model;

/**
 *
 * @author Heitor
 */
public class Match {
    private int id;
    private int usuario1Id;
    private int usuario2Id;

    public Match() {}

    public Match(int id, int usuario1Id, int usuario2Id) {
        this.id = id;
        this.usuario1Id = usuario1Id;
        this.usuario2Id = usuario2Id;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuario1Id() { return usuario1Id; }
    public void setUsuario1Id(int usuario1Id) { this.usuario1Id = usuario1Id; }
    public int getUsuario2Id() { return usuario2Id; }
    public void setUsuario2Id(int usuario2Id) { this.usuario2Id = usuario2Id; }
}

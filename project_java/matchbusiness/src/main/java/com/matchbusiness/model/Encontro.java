/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.model;

/**
 *
 * @author Heitor
 */
import java.sql.Timestamp;

public class Encontro {
    private int id;
    private int usuario1Id;
    private int usuario2Id;
    private Timestamp dataHora;
    private String local;

    public Encontro() {}

    public Encontro(int id, int usuario1Id, int usuario2Id, Timestamp dataHora, String local) {
        this.id = id;
        this.usuario1Id = usuario1Id;
        this.usuario2Id = usuario2Id;
        this.dataHora = dataHora;
        this.local = local;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuario1Id() { return usuario1Id; }
    public void setUsuario1Id(int usuario1Id) { this.usuario1Id = usuario1Id; }
    public int getUsuario2Id() { return usuario2Id; }
    public void setUsuario2Id(int usuario2Id) { this.usuario2Id = usuario2Id; }
    public Timestamp getDataHora() { return dataHora; }
    public void setDataHora(Timestamp dataHora) { this.dataHora = dataHora; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
}

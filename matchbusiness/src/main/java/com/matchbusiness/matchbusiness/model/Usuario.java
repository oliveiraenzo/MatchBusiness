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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @Column(unique = true)
    private String email;
    
    private String senha;
    
    // Indica se o usuário é master (que pode acessar o CRUD)
    private Boolean master = false;
    
    // Se verdadeiro, esse usuário não pode ser despromovido (master principal)
    private Boolean superMaster = false;

    // Construtores
    public Usuario() {}

    public Usuario(String nome, String email, String senha, Boolean master, Boolean superMaster) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.master = master;
        this.superMaster = superMaster;
    }
    
    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getSenha() {
        return senha;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getMaster() {
        return master;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    public Boolean getSuperMaster() {
        return superMaster;
    }

    public void setSuperMaster(Boolean superMaster) {
        this.superMaster = superMaster;
    }
}
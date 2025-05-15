/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.matchbusiness.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author Heitor
 */
@Entity
@Table(name = "encontros")
public class Encontro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dataHora;
    
    // Um encontro pode ocorrer em um estabelecimento específico
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;
    
    // Participantes do encontro (usando ManyToMany)
    @ManyToMany
    @JoinTable(name = "encontro_usuarios",
        joinColumns = @JoinColumn(name = "encontro_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> participantes;

    // Construtores, getters e setters...
    public Encontro() {}

    public Encontro(LocalDateTime dataHora, Estabelecimento estabelecimento, List<Usuario> participantes) {
        this.dataHora = dataHora;
        this.estabelecimento = estabelecimento;
        this.participantes = participantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
}
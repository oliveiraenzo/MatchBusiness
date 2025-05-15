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
@Table(name = "mensagens")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Remetente da mensagem
    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;
    
    // Destinatário da mensagem
    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;
    
    private String conteudo;
    
    private LocalDateTime horaMensagem;

    // Construtores, getters e setters...
    public Mensagem() {}

    public Mensagem(Usuario remetente, Usuario destinatario, String conteudo, LocalDateTime horaMensagem) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.horaMensagem = horaMensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getHoraMensagem() {
        return horaMensagem;
    }

    public void setHoraMensagem(LocalDateTime horaMensagem) {
        this.horaMensagem = horaMensagem;
    }
}

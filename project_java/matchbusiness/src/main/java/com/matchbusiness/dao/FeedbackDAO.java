/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.dao;

import com.matchbusiness.model.Feedback;
import com.matchbusiness.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Heitor
 */
public class FeedbackDAO {

    // Método para registrar um feedback (INSERT)
    public boolean registrarFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (encontro_id, avaliador_id, nota, comentario) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, feedback.getEncontroId());
            ps.setInt(2, feedback.getAvaliadorId());
            ps.setInt(3, feedback.getNota());
            ps.setString(4, feedback.getComentario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar um feedback pelo ID (SELECT)
    public Feedback buscarPorId(int id) {
        Feedback feedback = null;
        String sql = "SELECT * FROM feedback WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setEncontroId(rs.getInt("encontro_id"));
                feedback.setAvaliadorId(rs.getInt("avaliador_id"));
                feedback.setNota(rs.getInt("nota"));
                feedback.setComentario(rs.getString("comentario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    // Método para listar todos os feedbacks (SELECT)
    public List<Feedback> listarTodos() {
        List<Feedback> lista = new ArrayList<>();
        String sql = "SELECT * FROM feedback";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setEncontroId(rs.getInt("encontro_id"));
                feedback.setAvaliadorId(rs.getInt("avaliador_id"));
                feedback.setNota(rs.getInt("nota"));
                feedback.setComentario(rs.getString("comentario"));
                lista.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para atualizar um feedback (UPDATE)
    public boolean atualizarFeedback(Feedback feedback) {
        String sql = "UPDATE feedback SET encontro_id = ?, avaliador_id = ?, nota = ?, comentario = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, feedback.getEncontroId());
            ps.setInt(2, feedback.getAvaliadorId());
            ps.setInt(3, feedback.getNota());
            ps.setString(4, feedback.getComentario());
            ps.setInt(5, feedback.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um feedback pelo ID (DELETE)
    public boolean excluirFeedback(int id) {
        String sql = "DELETE FROM feedback WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
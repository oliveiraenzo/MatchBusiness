/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.dao;

import com.matchbusiness.model.Match;
import com.matchbusiness.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heitor
 */
public class MatchDAO {

    // Método para criar um novo match (INSERT)
    public boolean criarMatch(Match match) {
        String sql = "INSERT INTO matches (usuario1_id, usuario2_id) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, match.getUsuario1Id());
            ps.setInt(2, match.getUsuario2Id());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar um match pelo ID (SELECT)
    public Match buscarPorId(int id) {
        Match match = null;
        String sql = "SELECT * FROM matches WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                match = new Match();
                match.setId(rs.getInt("id"));
                match.setUsuario1Id(rs.getInt("usuario1_id"));
                match.setUsuario2Id(rs.getInt("usuario2_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    // Método para listar todos os matches (SELECT)
    public List<Match> listarTodos() {
        List<Match> lista = new ArrayList<>();
        String sql = "SELECT * FROM matches";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getInt("id"));
                match.setUsuario1Id(rs.getInt("usuario1_id"));
                match.setUsuario2Id(rs.getInt("usuario2_id"));
                lista.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para atualizar um match (UPDATE)
    public boolean atualizar(Match match) {
        String sql = "UPDATE matches SET usuario1_id = ?, usuario2_id = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, match.getUsuario1Id());
            ps.setInt(2, match.getUsuario2Id());
            ps.setInt(3, match.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um match pelo ID (DELETE)
    public boolean excluir(int id) {
        String sql = "DELETE FROM matches WHERE id = ?";
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
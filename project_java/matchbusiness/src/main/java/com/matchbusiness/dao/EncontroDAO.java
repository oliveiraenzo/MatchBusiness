/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.dao;

import com.matchbusiness.model.Encontro;
import com.matchbusiness.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heitor
 */
public class EncontroDAO {

    // Método para criar um novo encontro (INSERT)
    public boolean criarEncontro(Encontro encontro) {
        String sql = "INSERT INTO encontros (usuario1_id, usuario2_id, dataHora, local) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, encontro.getUsuario1Id());
            ps.setInt(2, encontro.getUsuario2Id());
            ps.setTimestamp(3, encontro.getDataHora());
            ps.setString(4, encontro.getLocal());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar um encontro pelo ID (SELECT)
    public Encontro buscarPorId(int id) {
        Encontro encontro = null;
        String sql = "SELECT * FROM encontros WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                encontro = new Encontro();
                encontro.setId(rs.getInt("id"));
                encontro.setUsuario1Id(rs.getInt("usuario1_id"));
                encontro.setUsuario2Id(rs.getInt("usuario2_id"));
                encontro.setDataHora(rs.getTimestamp("dataHora"));
                encontro.setLocal(rs.getString("local"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encontro;
    }

    // Método para listar todos os encontros cadastrados (SELECT)
    public List<Encontro> listarTodos() {
        List<Encontro> lista = new ArrayList<>();
        String sql = "SELECT * FROM encontros";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Encontro encontro = new Encontro();
                encontro.setId(rs.getInt("id"));
                encontro.setUsuario1Id(rs.getInt("usuario1_id"));
                encontro.setUsuario2Id(rs.getInt("usuario2_id"));
                encontro.setDataHora(rs.getTimestamp("dataHora"));
                encontro.setLocal(rs.getString("local"));
                lista.add(encontro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para atualizar um encontro (UPDATE)
    public boolean atualizar(Encontro encontro) {
        String sql = "UPDATE encontros SET usuario1_id = ?, usuario2_id = ?, dataHora = ?, local = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, encontro.getUsuario1Id());
            ps.setInt(2, encontro.getUsuario2Id());
            ps.setTimestamp(3, encontro.getDataHora());
            ps.setString(4, encontro.getLocal());
            ps.setInt(5, encontro.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para excluir um encontro pelo ID (DELETE)
    public boolean excluir(int id) {
        String sql = "DELETE FROM encontros WHERE id = ?";
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
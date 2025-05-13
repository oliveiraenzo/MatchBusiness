/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.util;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Heitor
 */

public class DBInitializer {
    public static void initializeDatabase() {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            
            String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL UNIQUE,
                    senha VARCHAR(255) NOT NULL
                );

                CREATE TABLE IF NOT EXISTS matches (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario1_id INT NOT NULL,
                    usuario2_id INT NOT NULL,
                    CONSTRAINT fk_usuario1 FOREIGN KEY (usuario1_id) REFERENCES usuarios(id),
                    CONSTRAINT fk_usuario2 FOREIGN KEY (usuario2_id) REFERENCES usuarios(id)
                );

                CREATE TABLE IF NOT EXISTS encontros (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario1_id INT NOT NULL,
                    usuario2_id INT NOT NULL,
                    dataHora TIMESTAMP NOT NULL,
                    local VARCHAR(255),
                    CONSTRAINT fk_encontro_usuario1 FOREIGN KEY (usuario1_id) REFERENCES usuarios(id),
                    CONSTRAINT fk_encontro_usuario2 FOREIGN KEY (usuario2_id) REFERENCES usuarios(id)
                );

                CREATE TABLE IF NOT EXISTS feedback (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    encontro_id INT NOT NULL,
                    avaliador_id INT NOT NULL,
                    nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
                    comentario VARCHAR(500),
                    CONSTRAINT fk_feedback_encontro FOREIGN KEY (encontro_id) REFERENCES encontros(id),
                    CONSTRAINT fk_feedback_avaliador FOREIGN KEY (avaliador_id) REFERENCES usuarios(id)
                );
            """;
            
            stmt.executeUpdate(sql);
            System.out.println("Banco de dados inicializado com sucesso!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
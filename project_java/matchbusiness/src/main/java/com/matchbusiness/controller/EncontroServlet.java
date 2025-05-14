/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.controller;

import com.matchbusiness.dao.EncontroDAO;
import com.matchbusiness.model.Encontro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Heitor
 */
@WebServlet("/EncontroServlet")
public class EncontroServlet extends HttpServlet {

    private final EncontroDAO encontroDAO = new EncontroDAO();

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (null != acao) switch (acao) {
            case "criar" -> {
                int usuario1Id = Integer.parseInt(request.getParameter("usuario1Id"));
                int usuario2Id = Integer.parseInt(request.getParameter("usuario2Id"));
                Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));
                String local = request.getParameter("local");
                Encontro encontro = new Encontro(0, usuario1Id, usuario2Id, dataHora, local);
                encontroDAO.criarEncontro(encontro);
                response.sendRedirect("encontros.jsp?sucesso=1");
                }
            case "atualizar" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                int usuario1Id = Integer.parseInt(request.getParameter("usuario1Id"));
                int usuario2Id = Integer.parseInt(request.getParameter("usuario2Id"));
                Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));
                String local = request.getParameter("local");
                Encontro encontro = new Encontro(id, usuario1Id, usuario2Id, dataHora, local);
                encontroDAO.atualizar(encontro);
                response.sendRedirect("encontros.jsp?sucesso=1");
                }
            case "excluir" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                encontroDAO.excluir(id);
                response.sendRedirect("encontros.jsp?encontroExcluido=1");
                }
            default -> {
            }
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Encontro> listaEncontros = encontroDAO.listarTodos();
        request.setAttribute("listaEncontros", listaEncontros);
        request.getRequestDispatcher("encontros.jsp").forward(request, response);
    }
}
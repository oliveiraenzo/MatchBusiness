/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.controller;

import com.matchbusiness.dao.MatchDAO;
import com.matchbusiness.model.Match;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Heitor
 */
@WebServlet("/MatchServlet")
public class MatchServlet extends HttpServlet {

    private final MatchDAO matchDAO = new MatchDAO();

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
                Match match = new Match(0, usuario1Id, usuario2Id);
                matchDAO.criarMatch(match);
                response.sendRedirect("matches.jsp?sucesso=1");
                }
            case "atualizar" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                int usuario1Id = Integer.parseInt(request.getParameter("usuario1Id"));
                int usuario2Id = Integer.parseInt(request.getParameter("usuario2Id"));
                Match match = new Match(id, usuario1Id, usuario2Id);
                matchDAO.atualizar(match);
                response.sendRedirect("matches.jsp?sucesso=1");
                }
            case "excluir" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                matchDAO.excluir(id);
                response.sendRedirect("matches.jsp?matchExcluido=1");
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

        List<Match> listaMatches = matchDAO.listarTodos();
        request.setAttribute("listaMatches", listaMatches);
        request.getRequestDispatcher("matches.jsp").forward(request, response);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.controller;

import com.matchbusiness.dao.FeedbackDAO;
import com.matchbusiness.model.Feedback;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Heitor
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

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
            case "registrar" -> {
                int encontroId = Integer.parseInt(request.getParameter("encontroId"));
                int avaliadorId = Integer.parseInt(request.getParameter("avaliadorId"));
                int nota = Integer.parseInt(request.getParameter("nota"));
                String comentario = request.getParameter("comentario");
                Feedback feedback = new Feedback(0, encontroId, avaliadorId, nota, comentario);
                feedbackDAO.registrarFeedback(feedback);
                response.sendRedirect("feedbacks.jsp?sucesso=1");
                }
            case "atualizar" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                int encontroId = Integer.parseInt(request.getParameter("encontroId"));
                int avaliadorId = Integer.parseInt(request.getParameter("avaliadorId"));
                int nota = Integer.parseInt(request.getParameter("nota"));
                String comentario = request.getParameter("comentario");
                Feedback feedback = new Feedback(id, encontroId, avaliadorId, nota, comentario);
                feedbackDAO.atualizarFeedback(feedback);
                response.sendRedirect("feedbacks.jsp?sucesso=1");
                }
            case "excluir" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                feedbackDAO.excluirFeedback(id);
                response.sendRedirect("feedbacks.jsp?feedbackExcluido=1");
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

        List<Feedback> listaFeedbacks = feedbackDAO.listarTodos();
        request.setAttribute("listaFeedbacks", listaFeedbacks);
        request.getRequestDispatcher("feedbacks.jsp").forward(request, response);
    }
}
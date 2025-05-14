/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matchbusiness.controller;

import com.matchbusiness.model.Usuario;
import com.matchbusiness.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Heitor
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

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
            case "cadastrar" -> {
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                Usuario usuario = new Usuario(0, nome, email, senha);
                if (usuarioDAO.cadastrar(usuario)) {
                    response.sendRedirect("login.jsp?cadastro=sucesso");
                } else {
                    response.sendRedirect("cadastro.jsp?erro=1");
                }                      }
            case "login" -> {
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);
                if (usuario != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioLogado", usuario);
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("login.jsp?erro=1");
                }                      }
            case "atualizar" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                Usuario usuario = new Usuario(id, nome, email, senha);
                usuarioDAO.atualizar(usuario);
                response.sendRedirect("perfil.jsp?sucesso=1");
                }
            case "excluir" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                usuarioDAO.excluir(id);
                response.sendRedirect("index.jsp?usuarioExcluido=1");
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

        String acao = request.getParameter("acao");

        if ("listar".equals(acao)) {
            List<Usuario> listaUsuarios = usuarioDAO.listarTodos();
            request.setAttribute("listaUsuarios", listaUsuarios);
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        }
    }
}

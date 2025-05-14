<%-- 
    Document   : feedbacks
    Created on : May 13, 2025, 9:22:18 PM
    Author     : Heitor
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.matchbusiness.model.Feedback" %>
<jsp:useBean id="feedbackDAO" class="com.matchbusiness.dao.FeedbackDAO" scope="page"/>

<html>
<head>
    <title>Feedbacks - MatchBusiness</title>
</head>
<body>
    <h1>Feedback dos Encontros</h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Encontro ID</th>
            <th>Avaliador ID</th>
            <th>Nota</th>
            <th>Comentário</th>
        </tr>

        <% List<Feedback> feedbacks = feedbackDAO.listarTodos();
           for (Feedback feedback : feedbacks) { %>
            <tr>
                <td><%= feedback.getId() %></td>
                <td><%= feedback.getEncontroId() %></td>
                <td><%= feedback.getAvaliadorId() %></td>
                <td><%= feedback.getNota() %></td>
                <td><%= feedback.getComentario() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>

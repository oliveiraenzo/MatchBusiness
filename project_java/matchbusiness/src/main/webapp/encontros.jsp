<%-- 
    Document   : encontros
    Created on : May 13, 2025, 9:21:56 PM
    Author     : Heitor
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.matchbusiness.model.Encontro" %>
<jsp:useBean id="encontroDAO" class="com.matchbusiness.dao.EncontroDAO" scope="page"/>

<html>
<head>
    <title>Encontros - MatchBusiness</title>
</head>
<body>
    <h1>Seus Encontros</h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Usuário 1</th>
            <th>Usuário 2</th>
            <th>Data e Hora</th>
            <th>Local</th>
        </tr>

        <% List<Encontro> encontros = encontroDAO.listarTodos();
           for (Encontro encontro : encontros) { %>
            <tr>
                <td><%= encontro.getId() %></td>
                <td><%= encontro.getUsuario1Id() %></td>
                <td><%= encontro.getUsuario2Id() %></td>
                <td><%= encontro.getDataHora() %></td>
                <td><%= encontro.getLocal() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>

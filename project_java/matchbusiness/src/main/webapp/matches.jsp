<%-- 
    Document   : matches
    Created on : May 13, 2025, 9:21:23 PM
    Author     : Heitor
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.matchbusiness.model.Match" %>
<jsp:useBean id="matchDAO" class="com.matchbusiness.dao.MatchDAO" scope="page"/>

<html>
<head>
    <title>Matches - MatchBusiness</title>
</head>
<body>
    <h1>Seus Matches</h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Usuário 1</th>
            <th>Usuário 2</th>
        </tr>

        <% List<Match> matches = matchDAO.listarTodos();
           for (Match match : matches) { %>
            <tr>
                <td><%= match.getId() %></td>
                <td><%= match.getUsuario1Id() %></td>
                <td><%= match.getUsuario2Id() %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>

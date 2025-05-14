<%-- 
    Document   : login
    Created on : May 13, 2025, 9:20:27 PM
    Author     : Heitor
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - MatchBusiness</title>
</head>
<body>
    <h1>Login</h1>

    <% String erro = request.getParameter("erro");
       if (erro != null) {
           out.println("<p style='color:red;'>Email ou senha incorretos.</p>");
       }
    %>

    <form action="UsuarioServlet" method="post">
        <input type="hidden" name="acao" value="login">
        
        <label>Email:</label>
        <input type="email" name="email" required /><br/>

        <label>Senha:</label>
        <input type="password" name="senha" required /><br/>

        <input type="submit" value="Entrar">
    </form>
</body>
</html>

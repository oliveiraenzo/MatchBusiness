<%-- 
    Document   : cadastro
    Created on : May 13, 2025, 9:19:23 PM
    Author     : Heitor
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro - MatchBusiness</title>
</head>
<body>
    <h1>Cadastro de Usuário</h1>

    <% String erro = request.getParameter("erro");
       if (erro != null) {
           out.println("<p style='color:red;'>Erro no cadastro. Tente novamente.</p>");
       }
    %>

    <form action="UsuarioServlet" method="post">
        <input type="hidden" name="acao" value="cadastrar">
        
        <label>Nome:</label>
        <input type="text" name="nome" required /><br/>

        <label>Email:</label>
        <input type="email" name="email" required /><br/>

        <label>Senha:</label>
        <input type="password" name="senha" required /><br/>

        <input type="submit" value="Cadastrar">
    </form>
</body>
</html>
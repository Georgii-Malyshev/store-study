<%--
  Created by IntelliJ IDEA.
  User: George
  Date: 02.06.2021
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="loginForm" method="post" action="loginAuthServlet">
        Email:      <input type="text" name="email"/><br>
        Password:   <input type="password" name="password"/><br>
        <input type="submit" value="Login" />
    </form>
</body>
</html>

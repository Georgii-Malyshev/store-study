<%--
  Created by IntelliJ IDEA.
  User: George
  Date: 02.06.2021
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Home JSP</title>
</head>
<body>
	<div>
		Hello, ${sessionScope.appUser.firstName}! Your user ID is
		${sessionScope.appUser.id}. <br>
	</div>
</body>
</html>

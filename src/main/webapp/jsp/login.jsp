<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login page</title>
</head>
<body>
	<form name="loginForm" method="post" action="login">
		Email: <input type="text" name="email" /><br> Password: <input
			type="password" name="password" /><br> <input type="submit"
			value="Log in" />
	</form>
</body>
</html>
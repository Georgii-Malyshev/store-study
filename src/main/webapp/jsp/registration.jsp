<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration page</title>
</head>
<body>
	<form name="registrationForm" method="post" action="register">
		Email: <input type="text" name="email" /><br> Password: <input
			type="password" name="password" /><br> <input type="submit"
			value="register" />
	</form>
</body>
</html>
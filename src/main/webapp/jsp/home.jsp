<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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

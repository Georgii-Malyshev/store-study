<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin panel page</title>
</head>
<body>
	<div>
		<h1>admin panel</h1>
		<br>
		<table>
			<tr>
				<th>Users</th>
			</tr>
			<tr>
				<th>ID</th>
				<th>E-mail</th>
			</tr>
			<c:forEach var="appUser" items="${requestScope.appUsers}">
				<tr>
					<td>${appUser.id}</td>
					<td>${appUser.email}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
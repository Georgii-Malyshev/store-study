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

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product page</title>
</head>
<body>
	<div>
		<ul>
			<li>name: ${requestScope.product.name} </li>
			<li>price: ${requestScope.product.price} </li>
		</ul>
	</div>
</body>
</html>
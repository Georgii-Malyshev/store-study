<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<li>name: ${requestScope.product.name}</li>
			<li>price: ${requestScope.product.price}</li>
		</ul>
		<form name="addToCartForm" action="add-to-cart" method="post">
				<input type="hidden" name="id" value="${requestScope.product.id}">
				<input type="number" name="quantity" value=1>
				<input type="submit" value="Add to cart"> 
		</form>	
	</div>
</body>
</html>
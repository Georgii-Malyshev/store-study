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
		<form name="addToCart" method="post" action="cart-operations">
			<button name="add-to-cart" value="add">Add to cart</button>
		</form>
	</div>
</body>
</html>
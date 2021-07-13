<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping cart page</title>
</head>
<body>
	<div>
		Products in your shopping cart: <br> 
		<ul>
			<c:forEach var="cartItem"
				items="${requestScope.cart.cartItems}">
				<li>${cartItem.product.name}, ${cartItem.product.price}$, quantity: ${cartItem.quantity}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
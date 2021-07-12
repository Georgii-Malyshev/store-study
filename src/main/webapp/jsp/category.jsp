<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Product category page</title>
</head>
<body>
	<div>
		<ul>
			<c:forEach var="product"
				items="${requestScope.productCategory.products}">
				<li><a href="product?product_id=${product.id}">${product.name}
				</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
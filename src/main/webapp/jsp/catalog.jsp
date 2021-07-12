<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Product catalog</title>
</head>
<body>
	<div>
		<ul>
			<c:forEach var="productCategory"
				items="${requestScope.productCatalog.productCategories}">
				<li><a href="category?category_id=${productCategory.id}">${productCategory.name}
				</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
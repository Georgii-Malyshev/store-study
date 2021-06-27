<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Product category page</title>
</head>
<body>
	<div>
		<ul>
			<c:forEach var="productId" items="${requestScope.productCategoryId}">
				<li>${productId}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>

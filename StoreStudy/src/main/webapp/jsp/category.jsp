<%--
  Created by IntelliJ IDEA.
  User: George
  Date: 11.06.2021
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<%--
  Created by IntelliJ IDEA.
  User: George
  Date: 10.06.2021
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product catalog</title>
</head>
<body>
	<div>
		<ul>
			<c:forEach var="productCategory"
				items="${applicationScope.productCatalog.productCategories}">
				<li>${productCategory.name}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>

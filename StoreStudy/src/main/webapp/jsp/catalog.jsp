<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Product catalog</title>
</head>
<body>
	<div>
		<ul>
			<c:forEach var="productCategory"
				items="${applicationScope.productcatalog.productCategories}">
				<li>${productCategory.name}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
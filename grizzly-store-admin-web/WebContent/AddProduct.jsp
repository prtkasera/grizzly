<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AddProductServlet" method="post">
productId<input type="text" name="productId"><br>
category<input type="text" name="category"><br>
name<input type="text" name="name"><br>
<br>
description<input type="text" name="description">

<br>price<input type="text" name="price">
<br>submit<input type="submit" value="submit">
</form>

</body>
</html>
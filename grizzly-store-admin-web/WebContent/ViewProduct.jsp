<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>sql:query Tag</title>
</head>
<body>

	<sql:setDataSource var="db" driver="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:xe" user="system"
		password="root" />

	<sql:query dataSource="${db}" var="rs">  
select*from product  
</sql:query>

	<table border="2" width="100%">
		<tr>
			<th>Product ID</th>
			<th>Category</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
		</tr>
		<c:forEach var="table" items="${rs.rows}">
			<tr>
				<td><c:out value="${table.product_id}" /></td>
				<td><c:out value="${table.category}" /></td>
				<td><c:out value="${table.name}" /></td>
				<td><c:out value="${table.description}" /></td>
				<td><c:out value="${table.price}" /></td>	
				
				<td>
				<form action="ViewDescriptionServlet" method ="post" >
				<input type="checkbox" name="productId" value="${table.product_id}">
				<input type="submit" value="view">
				</form></td>
			</tr>
		</c:forEach>
	</table>
	<form action ="LogoutServlet" method="post" >
	<input type="submit" value="Logout">
	</form>
	<form action ="ViewToAddProductservlet" method="post" >
	<input type="submit" value="add product">
	</form>

</body>
</html>

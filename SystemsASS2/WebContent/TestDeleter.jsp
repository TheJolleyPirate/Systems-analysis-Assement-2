<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Test</title>
</head>
<body>
${feedback} <br><br>
<form action = "TestDeleterDatabase" >
Test name:
	<input type = "text" name = "test"> <br>
	<input type = "submit" value = "Delete">
</form>
<form action = "TestSelect.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
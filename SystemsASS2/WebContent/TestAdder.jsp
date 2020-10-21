<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Test</title>
</head>
<body>
<form action = "AddTestNames">
Test Name:
	<input type = "text" name = "Name"> <br>
Subject:
	<input type = "text" name = "Subject"> <br>
Class (if not specific, enter all):
	<input type = "text" name = "Class" value = "all"> <br>
	<input type = "submit" value = "Enter">
</form>
<form action = "TestSelect.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
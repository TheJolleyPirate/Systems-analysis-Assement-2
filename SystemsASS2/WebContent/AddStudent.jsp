<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Student</title>
</head>
<body>
<form action = "AddStudentToDatabase" >
First name (Required):
	<input type = "text"  name = "fname"> <br>
Last name:
	<input type = "text" name = "lname"> <br>
DOB (dd/mm/yyyy):
	<input type = "text" name = "DOB"> <br>
Class (Required):
	<input type = "text" name = "class"> <br>
	<input type = "submit" value = "add">
</form>
<form action = "index.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
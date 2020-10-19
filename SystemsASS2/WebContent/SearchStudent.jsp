<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Students</title>
</head>
<body>
<form action = "SearchStudentDatabase">
	<input type = "text" name = "query"> <br>
Search by: <br>
First name: 
	<input type = "radio" id = "fname" name = "searchBy" value = "fname" checked>
Last name:
	<input type = "radio" id = "lname" name = "searchBy" value = "lname"> <br>
	<input type = "submit" value = "search">
</form>
<form action = "index.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
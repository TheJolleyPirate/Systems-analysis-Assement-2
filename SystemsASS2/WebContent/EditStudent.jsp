<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Student</title>
</head>
<body>
<form action="EditStudentDatabaase" method = "post">
First name:
	<input type = "text" name = "fname" value = "${fname}"> <br>
Last name:
	<input type = "text" name = "lname" value = "${lname}"> <br>
Class:
	<input type = "text" name = "Class" value = "${Class}"> <br>
DOB (dd/mm/yyyy):
	<input type = "text" name = "date" value = "${date}"> <br>
StudentID:
	<input type = "text" name = "StudentID" value = "${StudentID}" readonly> <br>
	<input type = "submit" value = "change">
</form>
<form action = "SearchStudentDatabase">
	<input type = "hidden" name = "searchValue" value = "${searchValue}">
	<input type = "hidden" name = "searchBy" value = "${searchBy}">
	<input type = "submit" value = "back">
</form>
</body>
</html>
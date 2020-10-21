<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Students</title>
</head>
<body>
<form action = "SearchStudentDatabase">
	<input type = "text" name = "searchValue" value = "${searchValue}"> <br>
Search by: <br>
StudentID 
	<input type = "radio" id = "StudentID" name = "searchBy" value = "StudentID" checked>
First name: 
	<input type = "radio" id = "fname" name = "searchBy" value = "fname">
Last name:
	<input type = "radio" id = "lname" name = "searchBy" value = "lname"> <br>
All:
	<input type = "radio" id = "all" name = "searchBy" value = "all">
Class:
	<input type = "radio" id = "Class" name = "searchBy" value = "Class">
DOB (dd//mm/yyyy):
	<input type = "radio" id = "DOB" name = "searchBy" value = "DOB">
	<input type = "submit" value = "search">
</form>
<form action = "index.jsp" >
	<input type = "submit" value = "Back">
</form>
<table style="width:100%">
<tr>
    <th>Student ID</th>
    <th>Name</th>
    <th>Class</th>
    <th>Manage</th>
</tr>
<c:forEach items="${Rows}" var="row">
		${row}
	</c:forEach>
</table>
</body>
</html>
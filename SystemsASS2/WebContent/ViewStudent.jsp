<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Details</title>
</head>
<body>
<form action = "SearchStudentDatabase">
	<input type = "hidden" name = "searchValue" value = "${searchValue}">
	<input type = "hidden" name = "searchBy" value = "${searchBy}">
	<input type = "submit" value = "back">
</form>
<h1>Student Details</h1>
Name: "${fname} ${lname}" <br>
StudentID: "${StudentID}" <br>
DOB: "${date}" <br>
Class: "${Class}" <br>
<h2>Attendance</h2>
<table style="width:100%">
<tr>
    <th>Date</th>
    <th>Attendance</th>
</tr>
<c:forEach items="${Attendace}" var="row">
		${row}
	</c:forEach>
</table>
<h2>Test Marks</h2>
<table style="width:100%">
<tr>
    <th>Test</th>
    <th>Mark</th>
    <th>Completed</th>
</tr>
<c:forEach items="${Tests}" var="row">
		${row}
	</c:forEach>
</table>
</body>
</html>
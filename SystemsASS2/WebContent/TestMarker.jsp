<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mark Test</title>
</head>
<body>
<form action = "TestAdder.jsp" >
	<input type = "submit" value = "Add Test">
</form>
<form action = "TestSelect.jsp" >
	<input type = "submit" value = "Back">
</form>
<form action = "TestFinder">
Search: 
	<input type = "text" name = "searchvalue" value = "${TestName}">
By:<br>
Name:
	<input type = "radio" id = "TestName" name = "searchBy" value = "TestName" checked>
All:
	<input type = "radio" id = "all" name = "searchBy" value = "all">
Subject:
	<input type = "radio" id = "subject" name = "searchBy" value = "subject">
	<input type = "submit" value = "enter">
</form>
<table style="width:100%">

<tr>
    <th>Student ID</th>
    <th>Name</th>
    <th>Test</th>
    <th>Subject</th>
    <th>Score</th>
    <th>Date Completed</th>
    <th>Edit</th>
</tr>

<c:forEach items="${Rows}" var="row">
		${row}
	</c:forEach>
	
</table>
</body>
</html>
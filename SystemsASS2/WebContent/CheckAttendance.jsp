<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Attendance</title>
</head>
<body>
<form action="CheckAttendanceDatabase">
Date:
	<input type = "text" name = "date" value = "19/09/1999">
	<input type = "submit" value = "submit">
</form>

<table style="width:100%">

<tr>
    <th>Student ID</th>
    <th>Name</th>
    <th>Attendance</th>
    <th>Class</th>
</tr>

<c:forEach items="${Rows}" var="row">
		${row}
	</c:forEach>
	
</table>
</body>
</html>
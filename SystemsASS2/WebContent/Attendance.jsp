<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendance</title>
</head>
<body>
<form action = "AttendanceDatabaseIn">
	<c:forEach items="${names}" var="name">
		<c:out value= "${name}"></c:out>
		<input type = "checkbox" id = "${name}" name = "${name}"> <br>
	</c:forEach>
	<input type = "submit" value = "submit">
</form>
<form action = "index.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
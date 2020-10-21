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
Date (dd/mm/yyyy):
	<input type = "text" id = "Adate" name = "Adate" value = "19/09/1999"> <br>
	<c:forEach items="${names}" var="name">
		<c:out value= "${name}"></c:out>
		<input type = "checkbox" id = "${name}" name = "${name}"> <br>
	</c:forEach>
	<input type = "hidden" id = "class2" name = "class2" value = "${class2}" readonly> <br>
	<input type = "submit" value = "submit">
</form>
<form action = "index.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Database</title>
</head>
<body>
<h2>Student Database</h2>
<form action = "AddStudent.jsp" >
	<input type = "submit" value = "Add Student">
</form>
<form action = "SearchStudent.jsp" >
	<input type = "submit" value = "Search Students">
</form>
<form action="PreAttendance.jsp">
	<input type = "submit" value = "Mark Attendace">
</form>
<form action="CheckAttendance.jsp">
	<input type = "submit" value = "Check Attendace">
</form>
<form action = "TestSelect.jsp">
	<input type = "submit" value = "Tests">
</form>
<form action = "LeaderboardDatabase">
	<input type = "submit" value = "LeaderBoard">
</form>
</body>
</html>
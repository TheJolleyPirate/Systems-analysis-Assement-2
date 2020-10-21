<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tests</title>
</head>
<body>
<h3>Add Test</h3>
<form action="TestAdder.jsp">	
	<input type = "submit" value = "Add test">
</form>
<h3>Mark Test</h3>
<form action="TestMarker.jsp">
	<input type = "submit" value = "Mark test">
</form>
<h3>Delete Test</h3>
<form action = "TestDeleter.jsp" >
	<input type = "submit" value = "Delete test">
</form>
<br>
<form action = "index.jsp" >
	<input type = "submit" value = "Back">
</form>
</body>
</html>
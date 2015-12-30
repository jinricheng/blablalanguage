<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "java.util.List" %>
<%@ page import=" java.sql.Timestamp" %>
<%@ page import="Models.Event" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NEW MEETING</title>
</head>
<body>
<h1>
Create a new Meeting
</h1>

<form method="post" action="Event">
Establishment name: <br>
<input type="text" name ="establishment">
<br>
Language:<br>
<input type="text" name ="language">
<br>
Meeting name:<br>
<input type="text" name ="name">
<br>
Meeting Date (aaaa-mm-dd HH:mm):<br>
<input type="text" name ="date">
<br>
Description:<br>
<input type="text" name ="description">
<br><br>
<input type="submit" value="CREATE">
</form>

</body>
</html>

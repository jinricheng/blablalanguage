<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import=" java.sql.Timestamp" %>  
<%@ page import="Models.Event" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ModifyEvent</title>
</head>
<body>
<h3>
Modify Event
</h3>
<%
Event event = (Event) session.getAttribute("event");
int id=-1;%>

<form method="post" action="Event">
Id:
<input type="text" name = "id" value="${event.getId()}" maxlength="10"><br>
Establishment name: 
<input type="text" name ="establishment" value="${event.getEstab()}" maxlength="100">
<br>
Language:
<input type="text" name ="language" value="${event.getLang()}"  maxlength="100">
<br>
Meeting name:
<input type="text" name ="name" value="${event.getName()}"  maxlength="100">
<br>
Meeting Date (aaaa-mm-dd HH:mm):
<input type="text" name ="date">
<br>
Description:
<input type="text" name ="description" value="${event.getDescription()}"  maxlength="100">
<br><br>


<input type="submit" value="MODIFY">
</form>
<br>
</body>
</html>
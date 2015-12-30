<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "java.util.List" %>
<%@ page import=" java.sql.Timestamp" %>
<%@ page import="Models.Event" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
List<Event> l = (List<Event>) session.getAttribute("events");
int id=-1;%>
<h2>
My Meetings
</h2>
<table border=1>
<thead>
    <tr>
        <th> Id</th>
        <th>Establishment </th>
        <th>Language</th>
        <th>Name</th>
        <th>Time</th>
        <th colspan=2>Action</th>
    </tr>
</thead>
<tbody>
    <% for (int i=0 ; i<l.size();i++){
	Event e = l.get(i);
	 id=e.getId();
	 
	String eventId = Integer.toString(id);
	String la=e.getLang();
	String est = e.getEstab();
    String name=e.getName();
	Timestamp t = e.getTim();

%>
		 <tr>
            <td><%=id %></td>
            <td><%=est %></td>
            <td><%=la %></td>
            <td><%=name %></td>
            <td><%=t %></td>
            <td><a href="Event?action=deleteEvent&eventId=<%=eventId %>">Delete</a></td>
            <td><a href="Event?action=editEvent&eventId=<%=eventId %>">Update</a></td>
        </tr>
        <%} %>
</tbody>
</table>
<br>
<input type="button" onclick="location.href='/BlaBlaLanguageWeb';" value="MainMenu" />

</body>
</html>
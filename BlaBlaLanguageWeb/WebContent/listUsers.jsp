<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<H1>List of Users</H1>
 <body>
   <table border=1>
        <thead>
            <tr>
                <th>User Id</th>
                <th>User Name</th>
                <th>User Login</th>
                <th>User Sex</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.login}" /></td>
                    <td><c:out value="${user.sex}" /></td>
                    <td><a href="UserController1svl?action=edit&userId=<c:out value="${user.id}"/>">Update</a></td>
                    <td><a href="UserController1svl?action=delete&userId=<c:out value="${user.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <input type="button" onclick="location.href='UserController1svl?action=insert';" value="CreateUser" />
   	<br><br>
   	<input type="button" onclick="location.href='/BlaBlaLanguageWeb';" value="MainMenu" />
</body>
</html>
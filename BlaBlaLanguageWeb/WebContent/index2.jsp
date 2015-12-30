<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Administration of Blablalanguage Application</title>
</head>
<body>
<H2><img src = "http://www.smcdsb.on.ca/UserFiles/Servers/Server_6/Image/Board%20Office%20Images/Parents/ConEd/IL-GIF.gif" alt="Moutain View" style="width:100px;height:100px">Welcome to Blablalanguage APP</H2>
<br>
<H4>Choose a option:</H4>
<form method="get" action="UserController1svl">
<input type="submit" name="action" value="ListAllUser" style="width: 120px" >
</form>
<br>
<form method="get" action="UserController1svl">
<input type="submit" name="action" value="CreateNewUser" style="width: 120px" >
</form>
<br>
<form method="get" action="Event">
<input type="submit" name="action" value="ListAllEvents" style="width: 120px" >
</form>
<br>
<form method="get" action="Event">
<input type="submit" name="action" value="createEvent" style="width: 120px" >
</form>

</body>
</html>
<%
    session.setAttribute("user",null);
    response.sendRedirect(request.getContextPath()+"/index.jsp");    	
%>
<%
    if (session.getAttribute("user") == null) {
    	response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
%>
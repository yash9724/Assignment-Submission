<%-- 
    Document   : showexception
    Created on : 6 Mar, 2019, 1:53:59 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception e = (Exception)request.getAttribute("exception");
    out.println("Problem occurred while trying to log in. We will fix this soon<br>");
%>


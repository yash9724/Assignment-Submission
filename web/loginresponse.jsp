<%-- 
    Document   : loginresponse
    Created on : 6 Mar, 2019, 1:53:02 PM
    Author     : yash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  boolean result=(Boolean)request.getAttribute("result");
  String username=(String)request.getAttribute("username");
  String usertype=(String)request.getAttribute("usertype");
  if(result==true && usertype.equalsIgnoreCase("STUDENT"))
  {
    String url="StudentControllerServlet;jsessionid="+session.getId();
    out.println(url);
    HttpSession sess=request.getSession();
    sess.setAttribute("username", username);
    
  }
  else if(result==true && usertype.equalsIgnoreCase("FACULTY"))
  {
    String url="FacultyControllerServlet;jsessionid="+session.getId();
    out.println(url);
    HttpSession sess=request.getSession();
    sess.setAttribute("username", username);
    
  }
   else if(result==true && usertype.equalsIgnoreCase("ADMIN"))
  {
    String url="AdminControllerServlet;jsessionid="+session.getId();
    out.println(url);
    HttpSession sess=request.getSession();
    sess.setAttribute("username", username);
    
  }
  else
    out.println("Invalid Credentials.Try Again...");
%>


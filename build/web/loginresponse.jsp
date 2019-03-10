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
  System.out.println("result:" + result);
  System.out.println("username: "+username);
  System.out.println("usertype: "+usertype);
  if(result==true && usertype.equalsIgnoreCase("STUDENT"))
  {
    String url="StudentControllerServlet;jsessionid="+session.getId();
    HttpSession sess=request.getSession();
    sess.setAttribute("username", username);
    out.println(url);
  }
  else if(result==true && usertype.equalsIgnoreCase("FACULTY"))
  {
    String url="FacultyControllerServlet;jsessionid="+session.getId();
    HttpSession sess=request.getSession();
    sess.setAttribute("username", username);
    out.println(url);
  }
   else if(result==true && usertype.equalsIgnoreCase("ADMIN"))
  {
    String url="AdminControllerServlet;jsessionid="+session.getId();
    HttpSession sess=request.getSession();
    sess.setAttribute("username", username);
    out.println(url);
  }
  else
    out.println("invalid");
%>


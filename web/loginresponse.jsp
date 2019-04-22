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
  String lastlogged = (String)request.getAttribute("lastlogged");
  String status = (String)request.getAttribute("status");
  status = status.toLowerCase();
  System.out.println("result:" + result);
  System.out.println("username: "+username);
  System.out.println("usertype: "+usertype);
  System.out.println("lastlogged: "+lastlogged);
  System.out.println("status: "+status);
  if(result == true && status.equals("inactive")){
      System.out.println("if");
      out.println("inactive");
  } 
  else if(result==true && usertype.equalsIgnoreCase("STUDENT"))
  {
    System.out.println("first else-if");  
    String url="StudentControllerServlet;jsessionid="+session.getId();
    HttpSession oldSession = request.getSession(false);
    if(oldSession != null){
        oldSession.invalidate();
    }
    HttpSession newSession = request.getSession(true);
    newSession.setAttribute("username", username);
    newSession.setAttribute("lastlogged", lastlogged);
    out.println(url);
  }
  else if(result==true && usertype.equalsIgnoreCase("FACULTY"))
  {
    System.out.println("second else-if");    
    String url="FacultyControllerServlet;jsessionid="+session.getId();
    HttpSession oldSession=request.getSession(false);
    if(oldSession != null){
        oldSession.invalidate();
    }
    HttpSession newSession = request.getSession(true);
    newSession.setAttribute("username", username);
    newSession.setAttribute("lastlogged", lastlogged);
    out.println(url);
  }
   else if(result==true && usertype.equalsIgnoreCase("ADMIN"))
  {
    System.out.println("third else-if");    
    String url="AdminControllerServlet;jsessionid="+session.getId();
    HttpSession oldSession=request.getSession(false);
    if(oldSession != null){
        oldSession.invalidate();
    }
    HttpSession newSession = request.getSession(true);
    newSession.setAttribute("username", username);
    newSession.setAttribute("lastlogged", lastlogged);
    out.println(url);
  }
   else{
    System.out.println("else");     
    out.println("invalid");
   }
%>


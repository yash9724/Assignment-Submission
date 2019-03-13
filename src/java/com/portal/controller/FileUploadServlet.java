package com.portal.controller;

import java.io.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import com.oreilly.servlet.MultipartRequest;  
  
public class FileUploadServlet extends HttpServlet {  
  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter pw = response.getWriter();
MultipartRequest m =new MultipartRequest(request,"e:/new");  
pw.print("successfully uploaded");
pw.print("<p>File is"+request.getParameter("filename")+ "</p>");
String url = "FileDownloadServlet?filename="+request.getParameter("filename");
pw.println("To Download <a href="+url+">click here</a>");
}  
}

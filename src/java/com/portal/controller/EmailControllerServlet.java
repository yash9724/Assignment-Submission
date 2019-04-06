/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author user
 */
public class EmailControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        HttpSession Hsession = request.getSession();
        String username = (String)Hsession.getAttribute("username");
        if(username == null){
            Hsession.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
            
        try{
            String json_data = request.getParameter("json_data");
            //if someone tries to access this servlet directly
            //without specifying username and password, in that case
            //json_data will be null
            if(json_data == null){
                pw.println("<center><h2>Access Denied.</h2>");
                pw.println("<h3>To Login <a href='index.html'>click here</a></h3></center>");
                return; 
            }
            JSONObject json_obj = (JSONObject)new JSONParser().parse(json_data);
            String to = (String)json_obj.get("to");
            String from = (String)json_obj.get("from");
            String subject = (String)json_obj.get("subject");
            String message = (String)json_obj.get("message");
            String password =(String)json_obj.get("password");
            System.out.println(to+" "+from+" "+subject+" "+message+""+password);
            String[] mailAddressTo = to.split(",");
            Properties props = new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.host",465);
            props.put("mail.smtp.user",from);
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.debug","true");
            props.put("mail.smtp.socketFactory.port",465);
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback","false");
            
            Session session = Session.getDefaultInstance(props,null);
            session.setDebug(true);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setText(message);
            mimeMessage.setSubject(subject);
            mimeMessage.setFrom(new InternetAddress(from.trim()));
            InternetAddress[] receivers = new InternetAddress[mailAddressTo.length];
            for(int i=0;i<mailAddressTo.length;i++){
                 receivers[i] = new InternetAddress(mailAddressTo[i].trim());
            }
            mimeMessage.addRecipients(Message.RecipientType.TO, receivers);
            // code for attachment
//            String filename = "Put the Path For the Attachment Here";
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//            messageBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename);
//            multipart.addBodyPart(messageBodyPart);
//            message.setContent(multipart);
            // end of code for attachment
            mimeMessage.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com",from,password);
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
            pw.print("success");
        }catch(Exception e){
            pw.print("failed");
            System.out.println("Exception is: "+e.getMessage());
            e.printStackTrace();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

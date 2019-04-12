/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import com.portal.dao.LoginDAO;
import com.portal.dao.UserDAO;
import com.portal.dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author user
 */
public class SaveNewUserController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        if(username == null){
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        String json_data = request.getParameter("json_data");
        //if someone tries to access this servlet directly
        //without specifying username and password, in that case
        //json_data will be null
        if(json_data == null){
            pw.println("<center><h2>Direct Access to this page is not Allowed</h2>");
            return; 
        }
        
        try{
            JSONObject json_obj = (JSONObject)new JSONParser().parse(json_data);
            String operation = (String)json_obj.get("data_id");
            String newusername = (String)json_obj.get("username");
            String password = (String)json_obj.get("password");
            String usertype = (String)json_obj.get("usertype");
            String status = (String)json_obj.get("status");

            UserDTO userCredentials = new UserDTO();
            userCredentials.setUsername(newusername);
            userCredentials.setUsertype(usertype);
            userCredentials.setUserstatus(status);
            userCredentials.setPassword(password);

            System.out.println("In savenewusercontroller");
            System.out.println("data-id: "+operation+' '+userCredentials.getUsername()+' '+userCredentials.getUsertype()+' '+userCredentials.getPassword()+' '+userCredentials.getUserstatus());
            boolean result = false;
            if(operation.equals("add")){
                System.out.println("inside if");
                result = UserDAO.saveNewUser(userCredentials);
            }else if(operation.equals("change")){
                System.out.println("inside else");
                result = UserDAO.editUserDetails(userCredentials);
            }else if(operation.equals("delete")){
                System.out.println("inside else");
                result = UserDAO.deleteUserDetails(userCredentials);
            }
            System.out.println("result in savenewcontroller: "+result);
            if(result){
                pw.println("success");
                System.out.println("Inside if");
            }else{
                pw.println("failed");
                System.out.println("inside else");
            }
        }catch(Exception e){
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

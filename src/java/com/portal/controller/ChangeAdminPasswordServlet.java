/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import com.portal.dao.AdminDAO;
import com.portal.dto.ChangePassDTO;
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
public class ChangeAdminPasswordServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        if(username == null){
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        
        try{
            String json_data = request.getParameter("json_data");
            PrintWriter pw = response.getWriter();
            //if someone tries to access this servlet directly
            //without specifying username and password, in that case
            //json_data will be null
            if(json_data == null){
                pw.println("<center><h2>Access Denied</h2>");
                pw.println("<h3>To Go Back to Admin Home <a href='AdminHome.jsp'>click here</a></h3></center>");
                return; 
            }
            JSONObject json_obj = (JSONObject)new JSONParser().parse(json_data);
            String oldPass = (String)json_obj.get("oldPass");
            String newPass = (String)json_obj.get("newPass");
            
            ChangePassDTO changePassObj = new ChangePassDTO();
            changePassObj.setNewPass(newPass);
            changePassObj.setOldPass(oldPass);
            changePassObj.setUsername(username);
            System.out.println("Details in servlet:" +changePassObj.getNewPass() +' '+changePassObj.getUsername() + ' '+changePassObj.getOldPass());
        
            
            boolean result = AdminDAO.changeAdminPassword(changePassObj);
            System.out.println("Result in ChangeAdminPasswordServlet: "+result);
            if(result){
                pw.println("success");
            }else{
                pw.println("failed");
            }
        }catch(Exception e){
            System.out.println("inside catch:"+e);
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

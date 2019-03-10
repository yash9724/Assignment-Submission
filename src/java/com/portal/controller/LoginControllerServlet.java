/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import com.portal.dao.LoginDAO;
import com.portal.dto.UserDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author user
 */
public class LoginControllerServlet extends HttpServlet {

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
        RequestDispatcher rd = null;
        try{
            String json_data = request.getParameter("json_data");
            JSONObject json_obj = (JSONObject)new JSONParser().parse(json_data);
            String username = (String)json_obj.get("username");
            String password = (String)json_obj.get("password");
            String usertype = (String)json_obj.get("usertype");
            UserDTO user = new UserDTO();
            user.setUsername(username);
            user.setPassword(password);
            user.setUsertype(usertype);
            boolean result = LoginDAO.validateUser(user);
            //System.out.println("Result in Loginntroller:" + result);
            request.setAttribute("username",username);
            request.setAttribute("usertype",usertype);
            request.setAttribute("result",result);
            rd = request.getRequestDispatcher("loginresponse.jsp");
        }catch(Exception e){
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("showexception.jsp");
            System.out.println("inside catch:"+e);
            e.printStackTrace();
        }finally{
            rd.forward(request, response);
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

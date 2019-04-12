/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;


import com.portal.dao.FacultyDAO;
import com.portal.dto.FacultyDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class SaveFacultyDetailsController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
            String username = (String)session.getAttribute("username");
            if(username == null){
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
            
        String operation = request.getParameter("data_id");
        FacultyDTO facultyDetails = new FacultyDTO();
        facultyDetails.setUsername(request.getParameter("username"));
        facultyDetails.setAddress(request.getParameter("address"));
        facultyDetails.setFacultyName(request.getParameter("facultyName"));
        facultyDetails.setContactNo(request.getParameter("contact"));
        facultyDetails.setEmail(request.getParameter("email"));
        
        System.out.println("In savefacultycontroller");
        System.out.println("data-id: "+operation+' '+facultyDetails.getFacultyName()+' '+facultyDetails.getAddress()+' '+facultyDetails.getEmail()+' '+facultyDetails.getContactNo());
        
        boolean result = false;
        if(operation.equals("add")){
            System.out.println("Inside if ");
            result = FacultyDAO.saveFacultyDetails(facultyDetails);
        }else if(operation.equals("change")){
            System.out.println("inside first else");
            result =  FacultyDAO.editFacultyDetails(facultyDetails);
        }else if(operation.equals("delete")){
            System.out.println("inside second else");
            result =  FacultyDAO.deleteFacultyDetails(facultyDetails);
        }
        System.out.println("result in savefacultycontroller: "+result);
        if(result){
            pw.println("success");
            System.out.println("Inside if");
        }else{
            pw.println("failed");
            System.out.println("inside else");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaveFacultyDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaveFacultyDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

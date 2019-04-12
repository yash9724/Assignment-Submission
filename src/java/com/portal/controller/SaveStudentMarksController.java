/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import com.portal.dao.MarksDAO;
import com.portal.dto.MarksDTO;
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
public class SaveStudentMarksController extends HttpServlet {

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
            
        MarksDTO marksDetails = new MarksDTO();
        String operation = request.getParameter("data_id");
        marksDetails.setSubject(request.getParameter("subject"));
        marksDetails.setRollNo(request.getParameter("rollNo"));
        marksDetails.setMidsem1(request.getParameter("midsem_1"));
        marksDetails.setMidsem2(request.getParameter("midsem_2"));
        marksDetails.setMidsem3(request.getParameter("midsem_3"));
        marksDetails.setSemester(request.getParameter("semester"));
                
        System.out.println("In savestudentmarkscontroller");
        System.out.println("data_id: "+operation+' '+marksDetails.getSubject()+' '+marksDetails.getRollNo()+' '+marksDetails.getMidsem1()+' '+marksDetails.getMidsem2()+' '+marksDetails.getMidsem3()+' '+marksDetails.getSemester());
        boolean result = false;
        if(operation.equals("add")){
            result = MarksDAO.saveStudentMarks(marksDetails);
        }else if(operation.equals("change")){
            result = MarksDAO.editStudentMarks(marksDetails);
        }else if(operation.equals("delete")){
            result = MarksDAO.deleteStudentMarks(marksDetails);
        }
        System.out.println("result in savestudentmarkscontroller: "+result);
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
            Logger.getLogger(SaveStudentMarksController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SaveStudentMarksController.class.getName()).log(Level.SEVERE, null, ex);
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

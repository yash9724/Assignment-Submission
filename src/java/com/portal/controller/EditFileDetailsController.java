/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import com.portal.dao.FileDAO;
import com.portal.dto.FileDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class EditFileDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private static final String DELETE_DIR = "uploads";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String operation = request.getParameter("data_id");
        boolean result = false;
        FileDTO file = new FileDTO();
        if(operation.equalsIgnoreCase("delete")){
            String fileId = request.getParameter("fileId");
            String fileType = request.getParameter("filetype");
            String fileName = request.getParameter("filename");
            file.setFileId(fileId);
            file.setFileType(fileType);
            file.setFileName(fileName);
            result = FileDAO.deleteFileDetails(file);
            if(result){
                String applicationPath = request.getServletContext().getRealPath("");
                System.out.println(applicationPath);
                // constructs path of the directory where file is located
                String deleteFile = applicationPath + File.separator + DELETE_DIR+File.separator + fileId;
                File actualFile = new File(deleteFile);
                if(actualFile.delete()){
                    System.out.println(deleteFile+" cannot be deleted");
                }else{
                     System.out.println(deleteFile+" deleted successfully");
                }
               }
            }else if(operation.equalsIgnoreCase("edit")){

            }
        
        if(result){
            pw.println("success");
        }else{
            pw.println("failed");
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
            Logger.getLogger(EditFileDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
        try{
            
        processRequest(request, response);
        }catch(SQLException e){
            Logger.getLogger(EditFileDetailsController.class.getName()).log(Level.SEVERE, null, e);
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

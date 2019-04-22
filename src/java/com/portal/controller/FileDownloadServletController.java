/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.controller;

import com.portal.dao.FileDAO;
import com.portal.dto.FileDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class FileDownloadServletController extends HttpServlet {

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
        
        response.setContentType("APPLICATION/OCTET-STREAM");   
        String filepath = "C:\\Users\\user\\Documents\\NetBeansProjects\\dept-portal-reboot\\build\\web\\uploads\\";   
        PrintWriter out = response.getWriter();
        String filetype = (String)request.getParameter("filetype");
        System.out.println(filetype);
        String fileId = "";
        FileDTO file = new FileDTO();
        file.setFileType(filetype);
        try{
            if(!filetype.equals("notice")){
                String semester = (String)request.getParameter("data_id");
                System.out.println("Semester: "+semester);
                file.setSemester(semester);
                if(filetype.equalsIgnoreCase("timetable") || filetype.equalsIgnoreCase("syllabus") 
                    || filetype.equalsIgnoreCase("scheme")){
                        fileId = FileDAO.getTimeSySch(file);
                }
            }
            System.out.println(fileId);
            response.setHeader("Content-Disposition","attachment; filename=\"" + fileId + "\"");
            FileInputStream fileInputStream = new FileInputStream(filepath + fileId);
            int i;   
            while ((i=fileInputStream.read()) != -1) {  
            out.write(i);   
            }   
            fileInputStream.close();   
            out.close();
            out.println("success");
        }catch(FileNotFoundException e){
            System.out.println("FileNotFoundException in FileDownloadServletController: "+e);
            e.printStackTrace();
            out.println("failed");
        }catch(SQLException e){
            System.out.println("SQLException in FileDownloadServletController: "+e);
            e.printStackTrace();
            out.println("failed");
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

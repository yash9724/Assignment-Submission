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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author user
 */
public class FileUploadServletController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String UPLOAD_DIR = "uploads";
    
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
        String operation = request.getParameter("data_id");
        System.out.println(operation);
        try{
            int previousCount = FileDAO.getFilesCount();
            if(operation == null){
                System.out.println("Inside if");
                //to get all files from request object we need 
                //object of ServletFileUpload provided by apache commons

                
                ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
                //FileType is built in type in apache commons to handle a file
                List<FileItem> multifiles = sf.parseRequest(new ServletRequestContext(request));
                if(multifiles == null){
                    pw.println("<center><h2>Access Denied.</h2>");
                    pw.println("<h3>To Login <a href='index.html'>click here</a></h3></center>");
                    return; 
                }

                // gets absolute path of the web application
                String applicationPath = request.getServletContext().getRealPath("");
                System.out.println(applicationPath);
                // constructs path of the directory to save uploaded file
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

                // creates the save directory if it does not exists
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());

                int newSeq = previousCount + 1;
                for(FileItem item: multifiles){
                    //write file on the server side
                    System.out.println(item.getName()+' '+item.getSize());
                    String uniqueName = newSeq++ + item.getName(); 
                    item.write(new File(uploadFilePath + File.separator + uniqueName));
                }
                pw.println("success");
            }else if(operation.equals("details")){
                int newSeq = previousCount + 1;
                System.out.println(request.getParameter("data")+' '+request.getParameter("data").getClass());
                String json_data = request.getParameter("data");
                JSONArray json_arr = (JSONArray)new JSONParser().parse(json_data);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
                ArrayList<FileDTO> details = new ArrayList();
                for(Object o : json_arr){
                    JSONObject file = (JSONObject)o;
                    String filename = (String)file.get("name");
                    String description = (String)file.get("description");
                    String semester = (String)file.get("semester");
                    String filetype = (String)file.get("filetype");
                    
                    System.out.println(filename+' '+description+' '+filetype+' '+semester);
                    String fileId =  newSeq++ + filename + filetype;
                    String timestamp = sdf.format(new Date());
                    FileDTO fileDesc = new FileDTO(fileId, filename, description, timestamp, filetype,semester);
                    details.add(fileDesc);
                }
                System.out.println("Details in servlet:"+ details);
                boolean result = FileDAO.saveFileDetails(details);
                System.out.println(result);
                if(result){
                    pw.println("success");
                }else{
                    pw.println("failed");
                }
            }
        } catch (FileUploadException ex) {
            System.out.println("File upload Exception");
            pw.println("failed");
            ex.printStackTrace();
        }catch(Exception e){
            System.out.println("Exception");
            pw.println("failed");
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

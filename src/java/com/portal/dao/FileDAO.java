/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.FileDTO;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class FileDAO {
    public static int getFilesCount() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("Select count(*) from files");
        }catch(NullPointerException npe){
            System.out.println("Null Pointer Exception in FileDAO getFilesCount:"+npe);
            System.exit(0);
        }
        
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            count = rs.getInt(1);
        }
        
        return count;
    }
    
    public static boolean saveFileDetails(ArrayList<FileDTO> details) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("Insert into files(file_id,file_name,description,time_stamp,file_type,sem) values(?,?,?,?,?,?)");
            //conn.setAutoCommit(false);
            System.out.println("Details in DAO: "+ details);
        for(int i=0; i<details.size(); i++){
            String file_id = details.get(i).getFileId();
            String file_name = details.get(i).getFileName();
            String description = details.get(i).getDescription();
            String time_stamp = details.get(i).getTimeStamp();
            String file_type = details.get(i).getFileType();
            String semester = details.get(i).getSemester();
//            String query = "Insert into files(file_id,file_name,description,time_stamp,file_type)"+
//                           " values("+file_id+","+file_name+","+description+","+time_stamp+","+file_type+")";
            
            ps.setString(1,file_id);
            ps.setString(2,file_name);
            ps.setString(3,description);
            ps.setString(4,time_stamp);
            ps.setString(5,file_type);
            ps.setString(6,semester);
            ps.addBatch();
        }
        int[] results = ps.executeBatch();
        System.out.println("Results array: "+ results);
        if(results.length == details.size()){
            System.out.println("inside if");
            success = true;
        }
        }catch(NullPointerException npe){
            System.out.println("Null Pointer Exception in FileDAO saveFileDetails:"+npe);
            System.exit(0);
        }catch(BatchUpdateException e){
            int[] arr = e.getUpdateCounts();
            e.printStackTrace();
        }finally{
            System.out.println("inside finally");
            if(ps!=null){
                ps.close();
            }
        }
        return success;
    }
    
    public static String getTimeSySch(FileDTO file) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        String fileId = "";
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("Select file_id from files where file_type=? and sem=?");
        }catch(NullPointerException npe){
            System.out.println("Null Pointer Exception in FileDAO getTimeSySc:"+npe);
            System.exit(0);
        }
        String filetype = file.getFileType();
        String sem = file.getSemester();
        System.out.println(filetype+' '+sem);
        ps.setString(1, file.getFileType());
        ps.setString(2, file.getSemester());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            fileId = rs.getString(1);
        }
        
        return fileId;
    }
    
    public static ArrayList<FileDTO> getAllFiles() throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       ArrayList fileList = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("select * from files");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in FileDAO getAllFiles:"+npe);
           System.exit(0);
       }
       
       fileList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            String fileId = rs.getString("file_id");
            String filename = rs.getString("file_name");
            String subject = rs.getString("subject");
            String description = rs.getString("description");
            String filetype = rs.getString("file_type");
            String semester = rs.getString("sem");
            
            FileDTO fileObj = new FileDTO();
            fileObj.setFileId(fileId);
            fileObj.setFileName(filename);
            fileObj.setSubject(subject);
            fileObj.setDescription(description);
            fileObj.setFileType(filetype);
            fileObj.setSemester(semester);
            fileList.add(fileObj);
       }
       return fileList;
    }
    
     public static boolean deleteFileDetails(FileDTO fileDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Delete files where upper(file_id)=? and upper(file_name)=? and upper(file_type)=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in FileDAO deletefiledetails:"+npe);
           System.exit(0);
       }
       System.out.println(fileDetails.getFileId());
       ps.setString(1,fileDetails.getFileId().toUpperCase());
       ps.setString(2,fileDetails.getFileName().toUpperCase());
       ps.setString(3,fileDetails.getFileType().toUpperCase());
       
       int result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
     
     public static ArrayList<FileDTO> getAllNotices() throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList noticeList = null;
        int result;
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("select * from files where upper(file_type)=?");
        }catch(NullPointerException npe){
            System.out.println("Null Pointer Exception in FileDAO getAllFiles:"+npe);
            System.exit(0);
        }

        noticeList = new ArrayList<>();
        ps.setString(1,"notice".toUpperCase());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
             String fileId = rs.getString("file_id");
             String filename = rs.getString("file_name");
             String subject = rs.getString("subject");
             String description = rs.getString("description");
             String filetype = rs.getString("file_type");
             String semester = rs.getString("sem");

             FileDTO fileObj = new FileDTO();
             fileObj.setFileId(fileId);
             fileObj.setFileName(filename);
             fileObj.setSubject(subject);
             fileObj.setDescription(description);
             fileObj.setFileType(filetype);
             fileObj.setSemester(semester);
             noticeList.add(fileObj);
        }
        return noticeList;
      }
}

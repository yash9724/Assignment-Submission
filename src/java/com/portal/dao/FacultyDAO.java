/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.FacultyDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class FacultyDAO {
    public static boolean saveFacultyDetails(FacultyDTO facultyDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Insert into faculty(fname,photo,address,email,mob,username) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in FacultyDAO saveFacultyDetails:"+npe);
           System.exit(0);
       }
       
       System.out.println("In savefacultyDAO");
       System.out.println(facultyDetails.getFacultyName()+' '+facultyDetails.getAddress()+' '+facultyDetails.getEmail()+' '+facultyDetails.getContactNo());
       ps.setString(1,facultyDetails.getFacultyName());
       ps.setString(2,facultyDetails.getPhotoPath());
       ps.setString(3,facultyDetails.getAddress());
       ps.setString(4,facultyDetails.getEmail());
       ps.setLong(5,Long.parseLong(facultyDetails.getContactNo()));
       ps.setString(6,facultyDetails.getUsername());
       System.out.println("After setLong");
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
    
    public static boolean editFacultyDetails(FacultyDTO facultyDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       int result1, result2;
       boolean success = true;
       try{
           conn = DBConnection.getConnection();
           conn.setAutoCommit(false);
           ps1 = conn.prepareStatement("Delete faculty where email=? or username=?");
           ps2 = conn.prepareStatement("Insert into faculty(fname,photo,address,email,mob,username) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in FacultyDAO editFacultyDetails:"+npe);
           System.exit(0);
       }
       System.out.println(facultyDetails.getUsername()+' '+facultyDetails.getFacultyName()+' '+facultyDetails.getPhotoPath()+' '+facultyDetails.getAddress()+' '+facultyDetails.getEmail()+' '+facultyDetails.getContactNo());
       ps1.setString(1,facultyDetails.getEmail());
       ps1.setString(2,facultyDetails.getUsername());
       ps2.setString(1,facultyDetails.getFacultyName());
       ps2.setString(2,facultyDetails.getPhotoPath());
       ps2.setString(3,facultyDetails.getAddress());
       ps2.setString(4,facultyDetails.getEmail());
       ps2.setLong(5,Long.parseLong(facultyDetails.getContactNo()));
       System.out.println("After setLong");
       ps2.setString(6,facultyDetails.getUsername());
       
       try{
            result1 = ps1.executeUpdate();
            result2 = ps2.executeUpdate();
            System.out.println("result1: "+result1 +" result2: "+result2);
            conn.commit();
            System.out.println("After conn.commit()");
       }catch(Exception e){
           System.out.println(e.getMessage());
           conn.rollback();
           System.out.println("After conn.rollback()");
           success = false;
       }
       return success;
    }
    
    public static ArrayList<FacultyDTO> getAllFaculties() throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       ArrayList facultyList = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("select * from faculty");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in FacultyDAO getAllFaculties:"+npe);
           System.exit(0);
       }
       
       facultyList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            String facultyName = rs.getString("fname");
            String contactNo = Long.toString(rs.getLong("mob"));
            String email = rs.getString("email");
            String address = rs.getString("address");
            String photoPath = rs.getString("photo");
            
            FacultyDTO facultyObj = new FacultyDTO(facultyName, contactNo, email,address, photoPath);
            facultyList.add(facultyObj);
       }
       return facultyList;
    }
    
    public static boolean deleteFacultyDetails(FacultyDTO facultyDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Delete faculty where upper(email)=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in FacultyDAO deletefacultydetails:"+npe);
           System.exit(0);
       }
       System.out.println(facultyDetails.getEmail());
       ps.setString(1,facultyDetails.getEmail().toUpperCase());
       
       int result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

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
import java.sql.SQLException;

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
           ps = conn.prepareStatement("Insert into faculty(fname,photo,address,email,mob) values(?,?,?,?,?)");
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
       System.out.println("After setLong");
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

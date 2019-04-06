/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.StudentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class StudentDAO {
    public static boolean saveStudentDetails(StudentDTO studentDetails) throws SQLException{
       int result;
       PreparedStatement ps = null;
       Connection conn = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Insert into student(sname,photo,address,email,mob,roll_no,sem) values(?,?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in StudentDAO saveStudentDetails:"+npe);
           System.exit(0);
       }
       
       System.out.println("In saveStudentDetails");
       System.out.println(studentDetails.getStudentName()+' '+studentDetails.getAddress()+' '+studentDetails.getEmail()+' '+studentDetails.getContactNo()+' '+studentDetails.getRollNo()+' '+studentDetails.getSemester());
       ps.setString(1,studentDetails.getStudentName());
       ps.setString(2,studentDetails.getPhotoPath());
       ps.setString(3,studentDetails.getAddress());
       ps.setString(4,studentDetails.getEmail());
       ps.setLong(5,Long.parseLong(studentDetails.getContactNo()));
       System.out.println("After setLong");
       ps.setString(6,studentDetails.getRollNo());
       ps.setString(7,studentDetails.getSemester());
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

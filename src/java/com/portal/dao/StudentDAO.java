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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public static ArrayList<StudentDTO> getAllStudents() throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       ArrayList studentList = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("select * from student");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in StudentDAO getAllStudents:"+npe);
           System.exit(0);
       }
       
       studentList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            String studentName = rs.getString("sname");
            String rollNo = rs.getString("roll_no");
            String semester = Integer.toString(rs.getInt("sem"));
            String contactNo = Long.toString(rs.getLong("mob"));
            String email = rs.getString("email");
            String address = rs.getString("address");
            String photoPath = rs.getString("photo");
            
            StudentDTO studentObj = new StudentDTO(studentName,rollNo,semester,contactNo,email,address, photoPath);
            studentList.add(studentObj);
       }
       return studentList;
    }
    
    public static boolean editStudentDetails(StudentDTO studentDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       int result1, result2;
       boolean success = true;
       try{
           conn = DBConnection.getConnection();
           conn.setAutoCommit(false);
           ps1 = conn.prepareStatement("Delete student where roll_no=?");
           ps2 = conn.prepareStatement("Insert into student(sname,address,email,mob,roll_no,sem) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in StudentDAO editStudentDetails:"+npe);
           System.exit(0);
       }
       System.out.println(studentDetails.getStudentName()+' '+studentDetails.getAddress()+' '+studentDetails.getSemester()+' '+studentDetails.getContactNo()+' '+studentDetails.getEmail()+' '+studentDetails.getRollNo());
       ps1.setString(1,studentDetails.getRollNo());
       ps2.setString(1,studentDetails.getStudentName());
       ps2.setString(2,studentDetails.getAddress());
       ps2.setString(3,studentDetails.getEmail());
       ps2.setLong(4,Long.parseLong(studentDetails.getContactNo()));
       ps2.setString(5,studentDetails.getRollNo());
       ps2.setInt(6,Integer.parseInt(studentDetails.getSemester()));
       
       
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
    
    public static boolean deleteStudentDetails(StudentDTO studentDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Delete student where upper(roll_no)=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in StudentDAO deletestudentdetails:"+npe);
           System.exit(0);
       }
       System.out.println(studentDetails.getRollNo());
       ps.setString(1,studentDetails.getRollNo().toUpperCase());
       
       int result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

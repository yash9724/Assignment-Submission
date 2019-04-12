/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.MarksDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class MarksDAO {
       public static boolean saveStudentMarks(MarksDTO marksDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Insert into mark(roll_no,sem,subject,midsem1,midsem2,midsem3) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in MarksDAO saveStudentMarks:"+npe);
           System.exit(0);
       }
       
       System.out.println("In saveMarksDAO");
       System.out.println(marksDetails.getSubject()+' '+marksDetails.getRollNo()+' '+marksDetails.getMidsem1()+' '+marksDetails.getMidsem2()+' '+marksDetails.getMidsem3()+' '+marksDetails.getSemester());
       ps.setString(1,marksDetails.getRollNo());
       ps.setString(2,marksDetails.getSemester());
       ps.setString(3,marksDetails.getSubject());
       ps.setString(4,marksDetails.getMidsem1());
       ps.setString(5,marksDetails.getMidsem2());
       ps.setString(6,marksDetails.getMidsem3());
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
    
     public static ArrayList<MarksDTO> getAllMarks() throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       ArrayList marksList = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("select * from mark");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in MarksDAO getAllMarks:"+npe);
           System.exit(0);
       }
       
       marksList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            String rollno = rs.getString("roll_no");
            String semester = rs.getString("sem");
            String subject = rs.getString("subject");
            String midsem1 = rs.getString("midsem1");
            String midsem2 = rs.getString("midsem2");
            String midsem3 = rs.getString("midsem3");
            
            MarksDTO marksObj = new MarksDTO(rollno, semester, subject, midsem1, midsem2, midsem3);
            marksList.add(marksObj);
       }
       return marksList;
    }
     
     public static boolean editStudentMarks(MarksDTO marksDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       int result1, result2;
       boolean success = true;
       try{
           conn = DBConnection.getConnection();
           conn.setAutoCommit(false);
           ps1 = conn.prepareStatement("Delete mark where roll_no=? and subject=? and sem=?");
           ps2 = conn.prepareStatement("Insert into mark(roll_no,sem,subject,midsem1,midsem2,midsem3) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in MarksDAO editMarksDetails:"+npe);
           System.exit(0);
       }
       System.out.println(marksDetails.getRollNo()+' '+marksDetails.getSubject()+' '+marksDetails.getSemester()+' '+marksDetails.getMidsem1()+' '+marksDetails.getMidsem2()+' '+marksDetails.getMidsem3());
       ps1.setString(1,marksDetails.getRollNo());
       ps1.setString(2,marksDetails.getSubject());
       ps1.setString(3,marksDetails.getSemester());
       ps2.setString(1,marksDetails.getRollNo());
       ps2.setString(2,marksDetails.getSemester());
       ps2.setString(3,marksDetails.getSubject());
       ps2.setString(4,marksDetails.getMidsem1());
       ps2.setString(5,marksDetails.getMidsem2());
       ps2.setString(6,marksDetails.getMidsem3());
       
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
     
    public static boolean deleteStudentMarks(MarksDTO marksDetails) throws SQLException{
        System.out.println("Inside MarksDAO");
       Connection conn = null;
       PreparedStatement ps = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Delete mark where roll_no=? and subject=? and sem=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in MarksDAO deleteStudentMarks:"+npe);
           System.exit(0);
       }
       System.out.println(marksDetails.getRollNo()+' '+marksDetails.getSubject()+' '+marksDetails.getSemester()+' '+marksDetails.getMidsem1()+' '+marksDetails.getMidsem2()+' '+marksDetails.getMidsem3());
       ps.setString(1,marksDetails.getRollNo());
       ps.setString(2,marksDetails.getSubject());
       ps.setString(3,marksDetails.getSemester());
       
       int result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

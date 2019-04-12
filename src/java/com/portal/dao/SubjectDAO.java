/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.SubjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class SubjectDAO {
    public static boolean saveSubjectDetails(SubjectDTO subjectDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Insert into subjects(sub_name,sub_code,faculty_a,faculty_b,sem) values(?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in SubjectDAO saveSubjectDetails:"+npe);
           System.exit(0);
       }
       
       System.out.println("In savesubjectDAO");
       System.out.println(subjectDetails.getSubjectName()+' '+subjectDetails.getSubjectCode()+' '+subjectDetails.getFaculty_A()+' '+subjectDetails.getFaculty_B()+' '+subjectDetails.getSemester());
       ps.setString(1,subjectDetails.getSubjectName());
       ps.setString(2,subjectDetails.getSubjectCode());
       ps.setString(3,subjectDetails.getFaculty_A());
       ps.setString(4,subjectDetails.getFaculty_B());
       ps.setLong(5,Integer.parseInt(subjectDetails.getSemester()));
       System.out.println("After setInt");
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
    
    public static ArrayList<SubjectDTO> getAllSubjects() throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       ArrayList subjectList = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("select * from subjects");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in SubjectDAO getAllSubjects:"+npe);
           System.exit(0);
       }
       
       subjectList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            String subjectCode = rs.getString("sub_code");
            String subjectName = rs.getString("sub_name");
            String semester = Integer.toString(rs.getInt("sem"));
            String faculty_A = rs.getString("faculty_a");
            String faculty_B = rs.getString("faculty_b");
            
            SubjectDTO subjectObj = new SubjectDTO(subjectCode, subjectName, semester, faculty_A, faculty_B); 
            subjectList.add(subjectObj);
       }
       
       return subjectList;
    }
    
    public static boolean editSubjectDetails(SubjectDTO subjectDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       int result1, result2;
       boolean success = true;
       try{
           conn = DBConnection.getConnection();
           conn.setAutoCommit(false);
           ps1 = conn.prepareStatement("Delete subjects where sub_code=? ");
           ps2 = conn.prepareStatement("Insert into subjects(sub_code,sub_name,sem,faculty_a,faculty_b) values(?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in SubjectDAO editSubjectDetails:"+npe);
           System.exit(0);
       }
       System.out.println(subjectDetails.getSubjectName()+' '+subjectDetails.getSubjectCode()+' '+subjectDetails.getFaculty_A()+' '+subjectDetails.getFaculty_B()+' '+subjectDetails.getSemester());
       ps1.setString(1,subjectDetails.getSubjectCode());
       ps2.setString(1,subjectDetails.getSubjectCode());
       ps2.setString(2,subjectDetails.getSubjectName());
       ps2.setInt(3,Integer.parseInt(subjectDetails.getSemester()));
       ps2.setString(4,subjectDetails.getFaculty_A());
       ps2.setString(5,subjectDetails.getFaculty_B());
       
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
    
    public static boolean deleteSubjectDetails(SubjectDTO subjectDetails) throws SQLException{
        System.out.println("Inside MarksDAO");
       Connection conn = null;
       PreparedStatement ps = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Delete subjects where upper(sub_code)=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in MarksDAO deleteStudentMarks:"+npe);
           System.exit(0);
       }
       System.out.println(subjectDetails.getSubjectCode());
       ps.setString(1,subjectDetails.getSubjectCode().toUpperCase());
       
       int result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

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
import java.sql.SQLException;

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
}

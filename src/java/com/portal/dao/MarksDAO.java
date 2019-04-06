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
import java.sql.SQLException;

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
           ps = conn.prepareStatement("Insert into marks(roll_no,sem,subject,midsem1,midsem2,midsem3) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in MarksDAO saveStudentMarks:"+npe);
           System.exit(0);
       }
       
       System.out.println("In saveMarksDAO");
       System.out.println(marksDetails.getSubject()+' '+marksDetails.getRollNo()+' '+marksDetails.getMidsem1()+' '+marksDetails.getMidsem2()+' '+marksDetails.getMidsem3()+' '+marksDetails.getSemester());
       ps.setString(1,marksDetails.getRollNo());
       ps.setInt(2,Integer.parseInt(marksDetails.getSemester()));
       ps.setString(3,marksDetails.getSubject());
       ps.setInt(4,Integer.parseInt(marksDetails.getMidsem1()));
       ps.setInt(5,Integer.parseInt(marksDetails.getMidsem2()));
       ps.setInt(6,Integer.parseInt(marksDetails.getMidsem3()));
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

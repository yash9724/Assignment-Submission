/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;
import com.portal.dbutil.DBConnection;
import com.portal.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class UserDAO {
    public static ArrayList<UserDTO> getAllUsers() throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       ArrayList userList = null;
       int result;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("select * from users");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in UserDAO getAllUsers:"+npe);
           System.exit(0);
       }
       
       userList = new ArrayList<>();
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
            String username = rs.getString("username");
            String usertype = rs.getString("usertype");
            String status = rs.getString("status");
            
            UserDTO userObj = new UserDTO();
            userObj.setUsername(username);
            userObj.setUsertype(usertype);
            userObj.setUserstatus(status);
            userList.add(userObj);
       }
       return userList;
    }
    
    public static boolean editUserDetails(UserDTO userDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       int result;
       boolean success = true;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("update users set status=? where username=? and usertype=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in UserDAO editUserDetails:"+npe);
           System.exit(0);
       }
       System.out.println(userDetails.getUsername()+' '+userDetails.getUsertype()+' '+userDetails.getUserstatus());
       ps.setString(1, userDetails.getUserstatus());
       ps.setString(2,userDetails.getUsername());
       ps.setString(3,userDetails.getUsertype());
       
       result = ps.executeUpdate();
       System.out.println("result: "+result);
       
       return result>0;
    }
    
    public static boolean saveNewUser(UserDTO userCredentials) throws SQLException{
            PreparedStatement ps = null;
            Connection conn = null;
            int result;
            try{
                conn = DBConnection.getConnection();
                ps = conn.prepareStatement("Insert into users(username,password,usertype,status) values(?,?,?,?)");
            }catch(NullPointerException npe){
                System.out.println("Null Pointer Exception in FacultyDAO saveFacultyDetails:"+npe);
                System.exit(0);
            }

            System.out.println("In savenewuserDAO");
            System.out.println(userCredentials.getUsername()+' '+userCredentials.getUsertype()+' '+userCredentials.getPassword()+' '+userCredentials.getUserstatus());
            ps.setString(1,userCredentials.getUsername());
            ps.setString(2,userCredentials.getPassword());
            ps.setString(3,userCredentials.getUsertype());
            ps.setString(4,userCredentials.getUserstatus());

            result = ps.executeUpdate();
            System.out.println("result: "+result);
            return result>0;
    }
    
    public static boolean deleteUserDetails(UserDTO userDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Delete users where upper(username)=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in UserDAO deleteuserdetails:"+npe);
           System.exit(0);
       }
       System.out.println(userDetails.getUsername());
       ps.setString(1,userDetails.getUsername().toUpperCase());
       
       int result = ps.executeUpdate();
       System.out.println("result: "+result);
       return result>0;
    }
}

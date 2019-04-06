package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.AdminDTO;
import com.portal.dto.ChangePassDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    
   //getAdminDetails will return admin details using its username 
   public static AdminDTO getAdminDetails(String username) throws SQLException{
       Connection conn = null;
       PreparedStatement ps = null;
       AdminDTO adminObj = null;
       try{
           conn = DBConnection.getConnection();
           ps = conn.prepareStatement("Select * from admin where username=?");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in AdminDAO getAdminDetails:"+npe);
           System.exit(0);
       }
       ps.setString(1,username);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           adminObj = new AdminDTO();
           adminObj.setAddress(rs.getString("address"));
           adminObj.setAdminName(rs.getString("name"));
           adminObj.setContactNo(rs.getString("mob"));
           adminObj.setEmail(rs.getString("email"));
           adminObj.setPhotoPath(rs.getString("photo"));
           adminObj.setUsername(rs.getString("username"));
       }
       
       return adminObj;
   } 
   
   public static boolean saveAdminDetails(AdminDTO newDetails) throws SQLException{
       Connection conn = null;
       PreparedStatement ps1 = null;
       PreparedStatement ps2 = null;
       AdminDTO adminObj = null;
       int result1, result2;
       boolean success = true;
       try{
           conn = DBConnection.getConnection();
           conn.setAutoCommit(false);
           ps1 = conn.prepareStatement("Delete admin where username=?");
           ps2 = conn.prepareStatement("Insert into admin(name,photo,address,email,mob,username) values(?,?,?,?,?,?)");
       }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in AdminDAO getAdminDetails:"+npe);
           System.exit(0);
       }
       System.out.println(newDetails.getUsername()+' '+newDetails.getAdminName()+' '+newDetails.getPhotoPath()+' '+newDetails.getAddress()+' '+newDetails.getEmail()+' '+newDetails.getContactNo());
       ps1.setString(1,newDetails.getUsername());
       ps2.setString(1,newDetails.getAdminName());
       ps2.setString(2,newDetails.getPhotoPath());
       ps2.setString(3,newDetails.getAddress());
       ps2.setString(4,newDetails.getEmail());
       ps2.setLong(5,Long.parseLong(newDetails.getContactNo()));
       System.out.println("After setLong");
       ps2.setString(6,newDetails.getUsername());
       
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
   
   public static boolean changeAdminPassword(ChangePassDTO changePassObj) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement("update users set password=? where username=? and password=?");
        }catch(NullPointerException npe){
           System.out.println("Null Pointer Exception in AdminDAO getAdminDetails:"+npe);
           System.exit(0);
       }
       
        System.out.println("Details in DAO: " +changePassObj.getNewPass() +' '+changePassObj.getUsername() + ' '+changePassObj.getOldPass());
        ps.setString(1,changePassObj.getNewPass());
        ps.setString(2,changePassObj.getUsername());
        ps.setString(3,changePassObj.getOldPass());
       
        int result = ps.executeUpdate();
        System.out.println("Result in AdminDAO: "+result);
        return result>0;
   }
}

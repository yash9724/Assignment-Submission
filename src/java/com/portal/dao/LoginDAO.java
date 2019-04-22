/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.UserDTO;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author yash
 */
public class LoginDAO {
        private static PreparedStatement ps1,ps2;
        private static Connection conn;
        static{
            try{
                conn = DBConnection.getConnection();
                ps1 = conn.prepareStatement("Select * from users where usertype=? and username=? and password=?");
                ps2 = conn.prepareStatement("update users set lastlogged=? where username=?");
            }catch(SQLException e){
                System.out.println("Error in DB communication: "+e);
            }
        }
        
        public static Dictionary validateUser(UserDTO user) throws SQLException{
            Dictionary result = new Hashtable();
            
            String pattern = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String currentDate = simpleDateFormat.format(new Date());
            
            ps1.setString(1,user.getUsertype());
            System.out.println("Usertype in logindao: "+user.getUsertype());
            ps1.setString(2,user.getUsername());
            System.out.println("Username in logindao:"+user.getUsername());
            ps1.setString(3,user.getPassword());
            System.out.println("password in logindao:"+user.getPassword());
            ps2.setString(1,currentDate);
            ps2.setString(2,user.getUsername());
            System.out.println("Above executeQuery");
            ResultSet rs = ps1.executeQuery();
            boolean res = rs.next();
            String status = rs.getString("status");
            String lastlogged = rs.getString("lastlogged");
            result.put("result",res);
            if(res == true){
                if(status.toLowerCase().equals("active")){
                    int finalRes = ps2.executeUpdate();
                }
                result.put("status",status);
                result.put("lastlogged",lastlogged);
            }
            System.out.println("Below executeQuery");
            return result;
        }
}

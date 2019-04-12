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

/**
 *
 * @author yash
 */
public class LoginDAO {
    
        private static PreparedStatement ps;
        private static Connection conn;
        static{
            try{
                conn = DBConnection.getConnection();
                ps = conn.prepareStatement("Select * from users where usertype=? and username=? and password=?");
            }catch(SQLException e){
                System.out.println("Error in DB communication: "+e);
            }
        }
        
        public static boolean validateUser(UserDTO user) throws SQLException{
            ps.setString(1,user.getUsertype());
            System.out.println("Usertype in logindao: "+user.getUsertype());
            ps.setString(2,user.getUsername());
            System.out.println("Username in logindao:"+user.getUsername());
            ps.setString(3,user.getPassword());
            System.out.println("password in logindao:"+user.getPassword());
            //ps.setString(4,"active");
            System.out.println("Above executeQuery");
            ResultSet rs = ps.executeQuery();
            System.out.println("Below executeQuery");
            return rs.next();
        }
        
        

}

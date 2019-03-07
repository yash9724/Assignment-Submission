/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.dao;

import com.portal.dbutil.DBConnection;
import com.portal.dto.UserDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yash
 */
public class LoginDAO {
    
        private static PreparedStatement ps;
        static{
            try{
                ps = DBConnection.getConnection().prepareStatement("Select * from ? where username=? and password=? and status=active");
            }catch(SQLException e){
                System.out.println("Error in DB communication: "+e);
            }
        }
    
        public static boolean validateUser(UserDTO user) throws SQLException{
            boolean result = false;
            ps.setString(1,user.getUsertype());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                result=true;
            return result;
        
    }
}

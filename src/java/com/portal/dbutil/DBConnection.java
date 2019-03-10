package com.portal.dbutil;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    
    private static Connection conn;
    
    static
    {
        try{
            
            File a = new File(".");
            System.out.println(a.getCanonicalPath());
            for(String filenames : a.list())
                System.out.println(filenames);
            Properties dbConfig = new Properties();
            dbConfig.load(new FileInputStream(new File("E:\\Tomcat\\bin\\configuration.properties")));
            String driver = dbConfig.getProperty("driver");
            String url = dbConfig.getProperty("url");
            String username = dbConfig.getProperty("username");
            String password = dbConfig.getProperty("password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database successfully");
            
        } 
        catch (ClassNotFoundException ex){
            System.out.println("Error in connecting to database");
        }
        catch(FileNotFoundException fnfe){
            System.out.println("configuration.properties file missing"+fnfe);
        }
        catch(IOException ioe){
            System.out.println("Error in fetching configuration.properties file"+ioe);
        }
        catch(SQLException e){
            System.out.println("SQL Exception " + e);
        }
    }
    
    public static Connection getConnection()
    {
        return conn;
    }
    
    public static void closeConnection()
    {
        if(conn != null)
        {
            try
            {
                conn.close();
                System.out.println("Connection closed successfully");
                
            }
            catch(SQLException e)
            {
                System.out.println("Error in closing connetion " + e);
            }
        }
    }
    
    public static void main(String[] args)
    {
       Connection conn = DBConnection.getConnection();
       
       if(conn == null)
           System.out.println("Conn is null");
    }
}
    
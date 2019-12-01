/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
import java.sql.*;


public class myConnection {
    public  static Connection getConnection()
    {
        Connection con= null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tms2","root","root");
           
        } catch (Exception ex) {
             System.out.println("error");
            System.out.println(ex.getMessage());
            
        }
        return con;
        
    
    
    }
    
    
   
}

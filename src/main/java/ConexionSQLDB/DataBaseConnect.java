/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionSQLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josh
 */
public class DataBaseConnect {
    public static Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:oracle:thin:@MSI:1522:xe";
            String user = "BNDATABASE";
            String password = "Geometry2003";
            
            Connection cnx = DriverManager.getConnection(myDB, user, password);
            
            return cnx;
            
        } catch(SQLException ex){
            
            System.out.println(ex.getMessage());
            
        } catch(ClassNotFoundException ex){
            
            Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

import ConexionSQLDB.DataBaseConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Clients {
    private String name;
    private String ID;
    private String phoneNumber;

    public Clients(String name, String ID, String phoneNumber) {
        this.name = name;
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        sendDatabaseValues();
    }

    public Clients() {
    
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    private void sendDatabaseValues(){
        
        Connection cnx = DataBaseConnect.getConnection();
        CallableStatement sendValues;
        
        try{
            sendValues = cnx.prepareCall("{call CLIENT_INSERT(?, ?, ?)}");
            sendValues.setString(1, ID);
            sendValues.setString(2, name);
            sendValues.setString(3, phoneNumber);
            
            sendValues.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No Client Insert");
        }
        
    }
    
    public void deleteClientDatabase(){
        
        Connection cnx = DataBaseConnect.getConnection();
        CallableStatement sendValues;
        
        try{
            sendValues = cnx.prepareCall("{call CLIENT_DELETE(?)}");
            sendValues.setString(1, ID);
            
            sendValues.executeQuery();
            
        }catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No Client Delete");
        }
        
    }
}
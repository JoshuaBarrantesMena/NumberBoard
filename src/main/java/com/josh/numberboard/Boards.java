/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

import ConexionSQLDB.DataBaseConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josh
 */
public class Boards {
    
    private int ID;
    private String name;
    private String owner;
    private int numAmount;
    private int winAmount;
    private int numPrice;
    private String prize;
    private String boardDesc;
    private String limitDate;
    
    public Boards(){
        
    }
    
    public Boards(String name, String owner, int numAmount, int winAmount, int numPrice, String prize, String boardDesc, String limitDate){
        this.name = name;
        this.owner = owner;
        this.numAmount = numAmount;
        this.winAmount = winAmount;
        this.numPrice = numPrice;
        this.prize = prize;
        this.boardDesc = boardDesc;
        this.limitDate = limitDate;
        
        sendDatabaseValues();
    }
    
    public Boards(int id, String name, String owner, int numAmount, int winAmount, int numPrice, String prize, String boardDesc, String limitDate){
        this.ID = id;
        this.name = name;
        this.owner = owner;
        this.numAmount = numAmount;
        this.winAmount = winAmount;
        this.numPrice = numPrice;
        this.prize = prize;
        this.boardDesc = boardDesc;
        this.limitDate = limitDate;
        
    }
    
    private void sendDatabaseValues(){
        Connection cnx = DataBaseConnect.getConnection();
        CallableStatement sendValues; //llamar procedimientos
        Statement maxID; //llamar comandos directos
        ResultSet result;
        try {
            
            try{
            maxID = cnx.createStatement();
            result = maxID.executeQuery("select max(BOARD_ID) from BOARD_NUM_INFO");
            while(result.next()){
            ID = result.getInt("max(BOARD_ID)") + 1;
            }
            }catch(SQLException ex){
                System.out.println("no recibe maxID");
            }
            
            sendValues = cnx.prepareCall("{call INSERTBOARD(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            sendValues.setString(1, name);
            sendValues.setString(2,  owner);
            sendValues.setInt(3, numAmount);
            sendValues.setInt(4, winAmount);
            sendValues.setInt(5, numPrice);
            sendValues.setString(6, prize);
            sendValues.setString(7, boardDesc);
            sendValues.setString(8, limitDate);
            sendValues.setInt(9, ID);
            
            sendValues.executeQuery(); //ejecutar el comando deseado
             
        } catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteBoardDatabase(int ID){
        Connection cnx = DataBaseConnect.getConnection();
        CallableStatement sendValues;
        
        try{
            sendValues = cnx.prepareCall("{call DELETEBOARD(?)}");
            sendValues.setInt(1, ID);
            
            sendValues.executeQuery();
            
        }catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int aID) {
        ID = aID;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String aOwner) {
        owner = aOwner;
    }

    public int getNumAmount() {
        return numAmount;
    }

    public void setNumAmount(int aNumAmount) {
        numAmount = aNumAmount;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int aWinAmount) {
        winAmount = aWinAmount;
    }

    public int getNumPrice() {
        return numPrice;
    }

    public void setNumPrice(int aNumPrice) {
        numPrice = aNumPrice;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String aPrize) {
        prize = aPrize;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String aBoardDesc) {
        boardDesc = aBoardDesc;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String aLimitDate) {
        limitDate = aLimitDate;
    }
}

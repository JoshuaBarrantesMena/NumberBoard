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
    
    private static int ID;
    private static String name;
    private static String owner;
    private static int numAmount;
    private static int winAmount;
    private static int numPrice;
    private static String prize;
    private static String boardDesc;
    private static String limitDate;
    
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
    
    private void sendDatabaseValues(){
        Connection cnx = DataBaseConnect.getConnection();
       CallableStatement sendValues;
       Statement maxID;
        try {
            
            sendValues = cnx.prepareCall("{call INSERTBOARD(?, ?, ?, ?, ?, ?, ?, ?)}");
            sendValues.setString(1, name);
            sendValues.setString(2,  owner);
            sendValues.setInt(3, numAmount);
            sendValues.setInt(4, winAmount);
            sendValues.setInt(5, numPrice);
            sendValues.setString(6, prize);
            sendValues.setString(7, boardDesc);
            sendValues.setString(8, limitDate);
            
            ResultSet result = sendValues.executeQuery();
            
            maxID = cnx.createStatement();
            result = maxID.executeQuery("select max(BOARD_ID) from BOARD_NUM_INFO");
            ID = result.getInt("max(BOARD_ID)");
            
        } catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int getID() {
        return ID;
    }

    public static void setID(int aID) {
        ID = aID;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String aName) {
        name = aName;
    }

    public static String getOwner() {
        return owner;
    }

    public static void setOwner(String aOwner) {
        owner = aOwner;
    }

    public static int getNumAmount() {
        return numAmount;
    }

    public static void setNumAmount(int aNumAmount) {
        numAmount = aNumAmount;
    }

    public static int getWinAmount() {
        return winAmount;
    }

    public static void setWinAmount(int aWinAmount) {
        winAmount = aWinAmount;
    }

    public static int getNumPrice() {
        return numPrice;
    }

    public static void setNumPrice(int aNumPrice) {
        numPrice = aNumPrice;
    }

    public static String getPrize() {
        return prize;
    }

    public static void setPrize(String aPrize) {
        prize = aPrize;
    }

    public static String getBoardDesc() {
        return boardDesc;
    }

    public static void setBoardDesc(String aBoardDesc) {
        boardDesc = aBoardDesc;
    }

    public static String getLimitDate() {
        return limitDate;
    }

    public static void setLimitDate(String aLimitDate) {
        limitDate = aLimitDate;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

/**
 *
 * @author Josh
 */
public class Boards {
    
    static int ID;
    static String name;
    static String owner;
    static int numAmount;
    static int winAmount;
    static int numPrice;
    static String prize;
    static String boardDesc;
    static String limitDate;
    
    Boards(){
        
    }
    
    private void sendDatabaseValues(){
        
    }
    
    public void fillBoard(String name, String owner, int numAmount, int winAmount, int numPrice, String prize, String boardDesc, String limitDate){
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
}

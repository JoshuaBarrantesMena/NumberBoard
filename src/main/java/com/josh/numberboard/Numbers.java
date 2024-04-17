/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;


public class Numbers {
    
    private String ID;
    private int payState;
    private String payMethod;

    public Numbers(int payState, String payMethod, String ID) {
        this.payState = payState;
        this.payMethod = payMethod;
        this.ID=ID;
    }

    public Numbers() {
        
    }
    
    public void setID(String ID){
        
    this.ID=ID;
    }
    
    public String getID(){
    
    return ID;
    }

    public int getPayState() {
        return payState;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    } 
}
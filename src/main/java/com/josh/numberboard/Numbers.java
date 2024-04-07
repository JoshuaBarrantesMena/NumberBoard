/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;


public class Numbers {
    
    private int number;
    private int payState;
    private String payMethod;

    public Numbers(int number, int payState, String payMethod) {
        this.number = number;
        this.payState = payState;
        this.payMethod = payMethod;
    }

    public Numbers() {
    }

    public int getNumber() {
        return number;
    }

    public int getPayState() {
        return payState;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    
    
    
    
    
    
    
}

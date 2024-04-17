/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

/**
 *
 * @author Usuario
 */
public class Clients {
    private String name;
    private String ID;
    private int winNumber;
    private String phoneNumber;

    public Clients(String name, String ID, String phoneNumber) {
        this.name = name;
        this.ID = ID;
        this.phoneNumber = phoneNumber;
    }

    public Clients() {}

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public int getWinNumber() {
        return winNumber;
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

    public void setWinNumber(int winNumber) {
        this.winNumber = winNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
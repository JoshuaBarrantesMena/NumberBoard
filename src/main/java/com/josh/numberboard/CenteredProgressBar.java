/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

import javax.swing.*;
import java.awt.*;

public class CenteredProgressBar extends JProgressBar {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      
        FontMetrics fm = g.getFontMetrics();
        String text = String.valueOf(getValue()) + "%";
        
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        
        g.drawString(text, x, y);
    }
}
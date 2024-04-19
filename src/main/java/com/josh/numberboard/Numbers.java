/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

import ConexionSQLDB.DataBaseConnect;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Numbers {
    private String ID;
    private int payState;
    private String payMethod;
    private BufferedImage qrImage;
    private String date;

    public Numbers(String ID, int payState, String payMethod, BufferedImage qrImage,String date) {
        this.ID = ID;
        this.payState = payState;
        this.payMethod = payMethod;
        this.qrImage = qrImage;
        this.date=date;
    }

    public Numbers(String ID, int payState, String payMethod, String date) {
        this.ID = ID;
        this.payState = payState;
        this.payMethod = payMethod;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public void setPayState(int payState) {
        this.payState = payState;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    
     public BufferedImage getQrImage() {
        return qrImage;
    }

    public void setQrImage(BufferedImage qrImage) {
        this.qrImage = qrImage;
    }
      
      
      public static BufferedImage toBufferedNImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    public void sendDatabaseValues(int number, int boardID){
        
        Connection cnx = DataBaseConnect.getConnection();

        
        CallableStatement sendValues;
            
            try{
            sendValues = cnx.prepareCall("{call NUMBER_DELETE(?, ?)}");
            sendValues.setInt(1, number);
            sendValues.setInt(2, boardID);
            
            sendValues.executeQuery();
            
            }catch (SQLException ex) {
                Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No Number Delete (Number Class)");
            }

            try{
                sendValues = cnx.prepareCall("{call NUMBER_INSERT(?, ?, ?, ?, ?, ?)}");
                sendValues.setInt(1, number);
                sendValues.setInt(2, boardID);
                sendValues.setInt(3, payState);
                sendValues.setString(4, payMethod);
                sendValues.setString(5, date);
                sendValues.setString(6, ID);
            
                sendValues.executeQuery();
                
                System.out.println("creo numero " + number);
            
            } catch (SQLException ex) {
                Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No Number Insert (Number Class)");
            }
        
    }
    
    public void deleteNumberDatabase(int number, int boardID){
        
        Connection cnx = DataBaseConnect.getConnection();
        CallableStatement sendValues;
        
        try{
            sendValues = cnx.prepareCall("{call NUMBER_DELETE(?, ?)}");
            sendValues.setInt(1, number);
            sendValues.setInt(2, boardID);
            
            sendValues.executeQuery();
            
        }catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No Number Delete (Number Class)");
        }
    }
      
    public static BufferedImage generateNQR(String data) {
  
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200);
            return toBufferedNImage(bitMatrix);
        } catch (WriterException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void displayQRNImage(BufferedImage image, int x, int y) {

        if (image != null) {
            JFrame frame = new JFrame("Código QR");
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.drawImage(image, null, x, y); // Utiliza las coordenadas x e y especificadas, joshua culon
                }
            });
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }

     public static void displayQR2Image(BufferedImage image, int x, int y, JPanel panel) {
        if (image != null) {
            JLabel qrLabel = new JLabel(new ImageIcon(image)); // Crea un JLabel con la imagen del código QR
            qrLabel.setBounds(x, y, image.getWidth(), image.getHeight()); // Establece la posición y el tamaño del JLabel
            panel.removeAll(); // Elimina cualquier componente existente en el panel
            panel.add(qrLabel); // Agrega el JLabel al panel
            panel.revalidate(); // Vuelve a validar el panel para refrescarlo
            panel.repaint(); // Vuelve a pintar el panel para mostrar los cambios
        }
    }
}
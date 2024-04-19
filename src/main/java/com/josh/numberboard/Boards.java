package com.josh.numberboard;

import ConexionSQLDB.DataBaseConnect;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import static com.josh.numberboard.BuyTickets.selectedNumsList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */
public class Boards {
    
    private Numbers[] numbers;
    private int ID;
    private String name;
    private String owner;
    private int numAmount;
    private int winAmount;
    private BufferedImage qrImage;
    private int numPrice;
    private String prize;
    private String boardDesc;
    private String limitDate;
    
    public Boards(){
        numAmount = -1;
        winAmount = 1;
    }
    
    public Boards(String name, String owner, int numAmount,  int numPrice, String prize, String boardDesc, String limitDate, BufferedImage qrImage){
        this.name = name;
        this.owner = owner;
        this.numAmount = numAmount;
        this.winAmount = 1;
        this.qrImage = qrImage;
        this.numPrice = numPrice;
        this.prize = prize;
        this.boardDesc = boardDesc;
        this.limitDate = limitDate;
        numbers=new Numbers[numAmount];
        
        for (int i = 0; i < numAmount; i++) { 
            
            numbers[i]= new Numbers();
            numbers[i].setPayState(0);
            numbers[i].setPayMethod("");
            
        }
        sendDatabaseValues();
    }
    
    public Boards(int id, String name, String owner, int numAmount, int numPrice, String prize, String boardDesc, String limitDate,BufferedImage qrImage){
        this.ID = id;
        this.name = name;
        this.owner = owner;
        this.numAmount = numAmount;
        this.winAmount = 1;
        this.qrImage = qrImage;
        this.numPrice = numPrice;
        this.prize = prize;
        this.boardDesc = boardDesc;
        this.limitDate = limitDate;
        
        for (int i = 0; i < numAmount; i++) { 
            
            numbers[i]= new Numbers();
            numbers[i].setPayState(0);
            numbers[i].setPayMethod("");
        }
        
        getDatabaseNumbers();
        
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
            
            sendValues = cnx.prepareCall("{call BOARD_INSERT(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
            sendValues = cnx.prepareCall("{call BOARD_DELETE(?)}");
            sendValues.setInt(1, ID);
            
            sendValues.executeQuery();
            
        }catch (SQLException ex) {
            Logger.getLogger(Boards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNumID(int index)
            { 
                return numbers[index].getID();

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
        if(numAmount == -1){
            
            numAmount = aNumAmount;
            this.numbers=new Numbers[numAmount];
            
            for (int i = 0; i < numAmount; i++) { 
            
            numbers[i]= new Numbers();
            numbers[i].setPayState(0);
            numbers[i].setPayMethod("");
        }
        
            getDatabaseNumbers();
        }
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    public int getWinAmount() {
        return winAmount;
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
    public int getNumberState(int index){
        
        return numbers[index].getPayState();
    }
      
    public void setNumbersState(int index, int state){
        
        numbers[index].setPayState(state);
    }
     public void setDateNum(int index, String date){
        
        numbers[index].setDate(date);
    }
    public String getNumDate(int index){
        
        return numbers[index].getDate();
    }
      
             
      public void setNumberID(int index, String ID){
        
        numbers[index].setID(ID);
    }
         
      public void setPayMethod(int index, String payMethod){
        
        numbers[index].setPayMethod(payMethod);
          
    }
       public String getNumberID(int index){
        return numbers[index].getID();
    }

    public BufferedImage getQrImage() {
        return qrImage;
    }
     public BufferedImage getNQrImage(int index) {
         return numbers[index].getQrImage();
        
    }
     public String geNLimitDate(int index){
        return numbers[index].getDate();
    }
    
    public void sendDatabaseValues(int number){
        numbers[number].sendDatabaseValues(number, ID);
    }
    
    private void getDatabaseNumbers(){
        
        try(Connection conn = DataBaseConnect.getConnection()){
                CallableStatement sv = conn.prepareCall("{call NUMBER_GET(?, ?)}");
                sv.setInt(1, ID);
                sv.registerOutParameter(2, Types.REF_CURSOR);
                sv.execute();
                
                ResultSet rs = (ResultSet) sv.getObject(2);
                while(rs.next()){
                    
                    Numbers auxNum = new Numbers();
                    
                    auxNum.setID(rs.getString("CLIENT_ID"));
                    auxNum.setPayMethod(rs.getString("PAY_METHOD"));
                    auxNum.setDate(rs.getString("PAY_DATE"));
                    auxNum.setPayState(rs.getInt("STATE"));
                    
                    String data = "Numero: " + rs.getInt("BN_NUMBER") + "\nClientID: " + auxNum.getID() + "\nFecha de Compra: " + auxNum.getDate() + "\nMetodo de Pago: " + auxNum.getPayState();
                    BufferedImage numberQr= auxNum.generateNQR(data);
                    auxNum.setQrImage(numberQr);
                    
                    numbers[rs.getInt("BN_NUMBER")] = auxNum;
                }
                
        } catch (SQLException ex){
            System.out.println(ex);
            System.out.println("No Numbers Objects (Boards Class)");
        }
        
    }
    
    public void deleteAllNumbers(){
        
        for (int i = 0; i < numAmount; i++){
            numbers[i].deleteNumberDatabase(i, ID);
        }
    }
    public BufferedImage generateNumQrImage(int index, String data) {
        
        
        BufferedImage numberQr= numbers[index].generateNQR(data);
       numbers[index].setQrImage(numberQr);
        
        return numberQr;
                
    }
    public void setImageQR(int index, BufferedImage qrImage){
        
        numbers[index].setQrImage(qrImage);
    }
    
    public void setQrImage(BufferedImage qrImage) {
        this.qrImage = qrImage;
    }
     
      
      
      public static BufferedImage toBufferedImage(BitMatrix matrix) {
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

public static BufferedImage generateQR(String data) {
  
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200);
            return toBufferedImage(bitMatrix);
        } catch (WriterException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void displayQRImage(BufferedImage image, int x, int y) {
       
        if (image != null) {
            JFrame frame = new JFrame("Código QR");
            frame.setName("QR de Talonario");
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.drawImage(image, null, x, y); // Utiliza las coordenadas x e y especificadas, joshua culon
                }
            });
            frame.setSize(276, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
    }

    public void setNumberPayMethod(int index, String payMethod){
        
        numbers[index].setPayMethod(payMethod);
          
    }
      public String getNumberPayMethod(int index){
          return numbers[index].getPayMethod();
      }
    
}


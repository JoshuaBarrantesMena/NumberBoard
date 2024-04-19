/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.josh.numberboard;

import ConexionSQLDB.DataBaseConnect;
import static com.josh.numberboard.BoardsMenu.boardsList;
import static com.josh.numberboard.BuyTickets.selectedNumsList;
import static com.josh.numberboard.StartMenu.boardWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
/**
 *
 * @author Joshuar
 */
public class BoardTickets extends javax.swing.JFrame {
    /**
     * Creates new form BoardTickets
     */
    int rows;
    int rowsIndex;
    int Index;
    
    public static ArrayList<Clients>clientsList=new ArrayList<>();

    public BoardTickets( ) {
        initComponents();
        setLocationRelativeTo(null);
        
        try(Connection conn = DataBaseConnect.getConnection()){
            CallableStatement sv = conn.prepareCall("{call CLIENT_GET(?)}");
            sv.registerOutParameter(1, Types.REF_CURSOR);
            sv.execute();
        
            ResultSet rs = (ResultSet) sv.getObject(1);
            while(rs.next()){
                
                Clients auxClient = new Clients();
                    
                auxClient.setID(rs.getString("IDENTIFICATION"));
                auxClient.setName(rs.getString("NAME"));
                auxClient.setPhoneNumber(rs.getString("TELEPHONE"));
            
                clientsList.add(auxClient);
            }
                
        } catch (SQLException ex){
            System.out.println(ex);
            System.out.println("No Client Get");
        }
    }
    
    public void initRows(int num){
        System.out.println(num);
        rows = boardsList.get(num).getNumAmount();
        Index = num;
        jLabel1.setText( boardsList.get(num).getName());
        ticketsButtons();
    }
    
    public static void displayQRImage(BufferedImage image, int x, int y) {

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
    
    public JToggleButton [] ticketsButtonsArray= new JToggleButton[rows];
        public void ticketsButtons(){
            
        int ticketCounter=0;
        ticketsButtonsArray= new JToggleButton[rows];
            
        for (rowsIndex = 0; rowsIndex  < rows; rowsIndex ++) {
              
            ticketsButtonsArray[rowsIndex] = new JToggleButton();
            ticketsButtonsArray[rowsIndex].setText(""+ ticketCounter);
                    
            ActionTicketsButtons action= new  ActionTicketsButtons();
        
            ticketsButtonsArray[rowsIndex].addActionListener(action);
            ticketsPanel.add( ticketsButtonsArray[rowsIndex] );            
                        
            if (boardsList.get(Index).getNumberState(rowsIndex)==0) {     
            ticketsButtonsArray[rowsIndex].setBackground(Color.GREEN);
            }
            if (boardsList.get(Index).getNumberState(rowsIndex)==2) {
            ticketsButtonsArray[rowsIndex].setBackground(Color.RED);
            }
            if (boardsList.get(Index).getNumberState(rowsIndex)==3) {
            ticketsButtonsArray[rowsIndex].setBackground(Color.YELLOW);
            }
            ticketCounter++;
            
        }
    }
          
    public boolean verifySelectedNums(int index){
        if (!selectedNumsList.isEmpty()) {
            for (int i = 0; i < selectedNumsList.size(); i++) {
                if(selectedNumsList.get(i)==index){
                    return true;
                }
            }
        }
            
        return false;
    }
         
    public void getClientInformation(String ID,int numIndex){   
        System.out.println(boardsList.get(Index).getNumberID(rowsIndex));
        System.out.println(rowsIndex);
        System.out.println(clientsList.size());
        for (int i = 0; i < clientsList.size(); i++) {
            System.out.println("ID: "+ID);
            System.out.println(clientsList.get(i).getID());
            if (clientsList.get(i).getID().equals(ID)) {
                
                System.out.println(clientsList.get(i).getName());
                System.out.println(clientsList.get(i).getID());
                System.out.println(clientsList.get(i).getPhoneNumber());
                jLClientName.setText("Nombre: "+ clientsList.get(i).getName());
                jLClientID.setText("ID: "+ clientsList.get(i).getID());
                jLPhoneNumber.setText("Numero de telefono: "+ clientsList.get(i).getPhoneNumber());
                jLPayMethod.setText("Forma de paga: "+boardsList.get(Index).getNumberPayMethod(numIndex));
                jLBuyDate.setText("Fecha de compra: "+boardsList.get(Index).geNLimitDate(numIndex));
               
                BufferedImage jhashua =boardsList.get(Index).getNQrImage(numIndex);
                displayQR2Image(jhashua,0,0 ,qrPanel );
                System.out.println("la imagem se mostro");
                break;    
            }
        }
    }
         


    public class ActionTicketsButtons implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            for (rowsIndex = 0; rowsIndex  < rows; rowsIndex ++) {
                    
                if (ae.getSource().equals(ticketsButtonsArray[rowsIndex])&&ticketsButtonsArray[rowsIndex].getBackground() == Color.RED&&!verifySelectedNums(rowsIndex)) {
                    selectedNumsList.add(rowsIndex);
                }
                if (ae.getSource().equals(ticketsButtonsArray[rowsIndex])&&ticketsButtonsArray[rowsIndex].getBackground() == 
                    Color.RED||ticketsButtonsArray[rowsIndex].getBackground() ==  Color.YELLOW&&ticketsButtonsArray[rowsIndex].isSelected()) {                            
                        
                    getClientInformation(boardsList.get(Index).getNumberID(rowsIndex),rowsIndex);
                    ticketsButtonsArray[rowsIndex].setSelected(false);
                break;
                }
                if(ae.getSource().equals(ticketsButtonsArray[rowsIndex])&&ticketsButtonsArray[rowsIndex].getBackground()!=Color.YELLOW&&
                    ticketsButtonsArray[rowsIndex].getBackground()!=Color.RED) {
                        
                    if(ticketsButtonsArray[rowsIndex].isSelected() && ticketsButtonsArray[rowsIndex].getBackground()==Color.GREEN){
                        System.out.println(Index);
                        boardsList.get(Index).setNumbersState(rowsIndex,1);       // 1 para numeros seleccionados     
                        ticketsButtonsArray[rowsIndex].setBackground(Color.LIGHT_GRAY);
                    }else{
                            boardsList.get(Index).setNumbersState(rowsIndex,0);    // 0 para numeros disponibles
                            ticketsButtonsArray[rowsIndex].setBackground(Color.GREEN);                                                   
                    }
                }
            }
        }
    }
     
public void setEnabledButtons(int index){
    
    ticketsButtonsArray[index].setEnabled(false);
}
       
       public void changeStateButton(int posButton, int state){ // edit
           if(state == 3){
               ticketsButtonsArray[posButton].setBackground(Color.YELLOW);
           }else if(state == 2){
               ticketsButtonsArray[posButton].setBackground(Color.RED);
           }else{
           ticketsButtonsArray[posButton].setBackground(Color.GREEN);
       }
           ticketsButtonsArray[posButton].setSelected(false);
       }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ticketsPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFFrom = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFTo = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTBRandomNum = new javax.swing.JToggleButton();
        jLRandomNum = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLClientName = new javax.swing.JLabel();
        jLClientID = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLPhoneNumber = new javax.swing.JLabel();
        jLPayMethod = new javax.swing.JLabel();
        qrPanel = new javax.swing.JPanel();
        jLBuyDate = new javax.swing.JLabel();
        genWin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Numeros del Talonario");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Estado de numeros");

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Numeros Disponibles");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Numeros reservados");

        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Numeros pagados");

        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setText("Pagar o reservar numeros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ticketsPanel.setLayout(new java.awt.GridLayout(25, 0, 5, 5));
        jScrollPane1.setViewportView(ticketsPanel);

        jLabel5.setText("Seleccionar numeros por rango");

        jLabel6.setText("Del");

        jTFFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFFromActionPerformed(evt);
            }
        });

        jLabel7.setText("al");

        jTFTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFToActionPerformed(evt);
            }
        });

        jButton3.setText("Seleccionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTBRandomNum.setText("Obtener numero aleatorio");
        jTBRandomNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBRandomNumActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Informacion del propietario");

        jLClientName.setText("Nombre: ");

        jLClientID.setText("ID: ");

        jLPhoneNumber.setText("Numero de telefono: ");

        jLPayMethod.setText("Forma de pago:");

        javax.swing.GroupLayout qrPanelLayout = new javax.swing.GroupLayout(qrPanel);
        qrPanel.setLayout(qrPanelLayout);
        qrPanelLayout.setHorizontalGroup(
            qrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        qrPanelLayout.setVerticalGroup(
            qrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        jLBuyDate.setText("Fecha de compra: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLClientName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(175, 175, 175))
                            .addComponent(jLClientID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(qrPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLBuyDate))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11)))
                                .addGap(113, 113, 113)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLClientID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLPhoneNumber)
                .addGap(12, 12, 12)
                .addComponent(jLPayMethod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLBuyDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(qrPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        genWin.setText("GENERAR GANADOR");
        genWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genWinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jTBRandomNum)
                        .addGap(30, 30, 30)
                        .addComponent(jLRandomNum, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(191, 191, 191))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(99, 99, 99)
                                .addComponent(genWin)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFTo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLRandomNum, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addComponent(genWin)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jTFTo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTBRandomNum, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
            
    for (int i = 0; i < rows; i++) {
        if (ticketsButtonsArray[i].getBackground()==Color.LIGHT_GRAY) {
                    boardsList.get(Index).setNumbersState(i,0);
ticketsButtonsArray[i].setBackground(Color.GREEN);
        }
    }
        boardWindow.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here

       boolean isNumSelected=false;
       boolean reserveNums=false;

                for (rowsIndex = 0; rowsIndex  < rows; rowsIndex ++) {
          
                if (boardsList.get(Index). getNumberState(rowsIndex)==2&& !selectedNumsList.isEmpty()){
                    
                    reserveNums=true;
                    break;
                    
                }
            
                    if (boardsList.get(Index). getNumberState(rowsIndex)==1&&selectedNumsList.isEmpty()) {
                        
                        isNumSelected=true;
                        break;
                    }                  
                }
                
                if (reserveNums==true) {
                    System.out.println("reserveNums true");
                       BuyTickets newBoard = new BuyTickets();
                       newBoard.getListIndex(Index,reserveNums);
                       newBoard.setLocationRelativeTo(null);
                       newBoard.setVisible(true);
                 }

                else if (isNumSelected==true) {
                                        System.out.println("reserveNums false");

                       BuyTickets newBoard = new BuyTickets();
                       newBoard.getListIndex(Index,reserveNums);
                       newBoard.setLocationRelativeTo(null);
                       newBoard.setVisible(true);
                 }
                
    }//GEN-LAST:event_jButton2ActionPerformed

    public void selectNumbersByRange(){
        
        int from=Integer.parseInt(jTFFrom.getText());
        int to=Integer.parseInt(jTFTo.getText());
        boolean notAvailable=false;
        
           String[] options = {"OK", "Cancelar"};
       
        if (from<=to ) {
              for (int i = from; i <= to; i++) {
            if (ticketsButtonsArray[i].getBackground()!=Color.GREEN) {
       notAvailable=true;
               int opt = JOptionPane.showOptionDialog(null,"Uno de los numeros en el rango seleccionado no se encuentra disponible \n\nIngrese un rango diferente", "¡AVISO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, null);
                break; 
            }
        }
              if (notAvailable==false) {
                
                   for (int i = from; i <= to; i++) {
            if (ticketsButtonsArray[i].getBackground()==Color.GREEN) {
                ticketsButtonsArray[i].setBackground(Color.LIGHT_GRAY);
                boardsList.get(Index).setNumbersState(i, 1);
                
            }
            
        }
            }
              
}
             
    }
    
    
       public void generateRandomNum(){

           int randomNum;
           for (int i = 0; i < rows; i++) {
               randomNum=(int) (Math.random() *rows);
               if (ticketsButtonsArray[randomNum].getBackground()==Color.GREEN) {
                   ticketsButtonsArray[randomNum].setBackground(Color.LIGHT_GRAY);
                boardsList.get(Index).setNumbersState(randomNum, 1);
                  jLRandomNum.setText(""+randomNum);
                  jTBRandomNum.setSelected(false);

                   break;
               }
               
           }
           

}
    
    private void jTFFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFFromActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         selectNumbersByRange();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTBRandomNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTBRandomNumActionPerformed
generateRandomNum();

    }//GEN-LAST:event_jTBRandomNumActionPerformed

    private void genWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genWinActionPerformed
int numberNums = 100;
    JFrame frame = new JFrame("Winner Animation");
    frame.setName("Ganador del Talonario");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    frame.setSize(400, 400);
    WinnerAnimation animationPanel = new WinnerAnimation();
    frame.setLocationRelativeTo(null);
// Aquí se crea una instancia de la clase WinnerAnimation
    frame.getContentPane().add(animationPanel, BorderLayout.CENTER);
    frame.setVisible(true);

    // Iniciar el temporizador de cuenta regresiva
    animationPanel.startCountdown(numberNums);         // TODO add your handling code here:
    }//GEN-LAST:event_genWinActionPerformed

    private void jTFToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFToActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BoardTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardTickets().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton genWin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLBuyDate;
    private javax.swing.JLabel jLClientID;
    private javax.swing.JLabel jLClientName;
    private javax.swing.JLabel jLPayMethod;
    private javax.swing.JLabel jLPhoneNumber;
    private javax.swing.JLabel jLRandomNum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jTBRandomNum;
    private javax.swing.JTextField jTFFrom;
    private javax.swing.JTextField jTFTo;
    private javax.swing.JPanel qrPanel;
    private javax.swing.JPanel ticketsPanel;
    // End of variables declaration//GEN-END:variables
}
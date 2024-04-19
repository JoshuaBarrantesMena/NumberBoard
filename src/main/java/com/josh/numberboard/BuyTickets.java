/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.josh.numberboard;

import static com.josh.numberboard.BoardsMenu.boardsList;
import static com.josh.numberboard.BoardTickets.clientsList;
import static com.josh.numberboard.BoardsMenu.view;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Joshuar
 */
public class BuyTickets extends javax.swing.JFrame {
    /**
     * Creates new form BuyTickets
     */
    public static int listIndex;
    int clientListIndex;
    int numAmount;
    int selectedNums=0;
    boolean areReserveNums;
    String clientName;
    String clientPhoneNumber;
    String clientID;
    String payMethod;
     
    public static ArrayList<Integer> selectedNumsList=new ArrayList<>();
    private DefaultListModel listModel=new DefaultListModel();
 
    public void getListIndex(int num, boolean reserveNums){
        numAmount= boardsList.get(num).getNumAmount();
        listIndex=num;
        jLBoardName.setText("Talonario: "+boardsList.get(listIndex).getName());
        listModel = new DefaultListModel();
        jLiNumbersSelected.setModel(listModel);
        areReserveNums=reserveNums;
        
        if (reserveNums) {
            for (int i = 0; i < selectedNumsList.size(); i++) {
                selectedNums++;
                listModel.addElement("Numero: "+selectedNumsList.get(i));
            }
            jTFTotalAmount.setText(""+ selectedNums*boardsList.get(listIndex).getNumPrice());
            
         }else{
            for (int i = 0; i < numAmount; i++) {
                if ( boardsList.get(listIndex).getNumberState(i)==1) {
                    selectedNums++;
                    listModel.addElement("Numero: "+ i);        
                    selectedNumsList.add(i);
                }
            }
            
            jTFTotalAmount.setText(""+ selectedNums*boardsList.get(listIndex).getNumPrice());
         }    
    }

    public BuyTickets() {
        initComponents();
        
    }
    


    public void createClient(){
        clientName= jTFClientName.getText();
        clientPhoneNumber= jTFClientPhone.getText();
        clientID= jTFClientID.getText();
   
        Clients newClient= new Clients(clientName,clientID,clientPhoneNumber);
        clientsList.add(newClient);
    
    }

    public void setVisibleFields(){

        if (areReserveNums) {
            verifyReserveNumID();
        }else if (verifyID()==true) {
            
            jLClientName.setVisible(true);   
            jTFClientName.setVisible(true);
    
            jLClientPhone.setVisible(true);
            jTFClientPhone.setVisible(true);
    
            jLPayMethod.setVisible(true);
            jCBPayMethod.setVisible(true);
            jLTotalAmount.setVisible(true);
            jTFTotalAmount.setVisible(true);
            jTFClientName.setText(clientsList.get(clientListIndex).getName());
            jTFClientPhone.setText(clientsList.get(clientListIndex).getPhoneNumber());
            jBPay.setEnabled(true);
            jBReservation.setEnabled(true);
            
        }else{
            String[] options = {"OK", "Cancelar"};
            int opt = JOptionPane.showOptionDialog(null,"No se encontro el ID ingresado\n\n¿Desea registrar un cliente nuevo?", "¡AVISO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, null);
        
            if(opt == 0){

                jLClientName.setVisible(true);   
                jTFClientName.setVisible(true);
    
                jLClientPhone.setVisible(true);
                jTFClientPhone.setVisible(true);
    
                jLPayMethod.setVisible(true);
                jCBPayMethod.setVisible(true);
                jLTotalAmount.setVisible(true);
                jTFTotalAmount.setVisible(true);
                jBPay.setEnabled(true);
                jBReservation.setEnabled(true);
            }
        }
    }
    
    public void verifyReserveNumID(){
    
        boolean aux=true;
        for (int i = 0; i < selectedNumsList.size(); i++) {
            if (!boardsList.get(listIndex).getNumID(selectedNumsList.get(i)).equals(jTFClientID.getText())) {
                aux=false;
                break;
            }
        }
        
        if (aux) {
            areReserveNums=false;
            setVisibleFields();
         }else{
            String[] options = {"OK", "Cancelar"};
            JOptionPane.showOptionDialog(null,"El ID ingresado no coincide con el de los numeros reservados\n\n¿Desea ingresar un ID diferente?", "¡AVISO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, null);
            
        }
    }

    public boolean verifyID(){

        if (!clientsList.isEmpty()) {

            for (int i = 0; i < clientsList.size(); i++) {
                   
                if (clientsList.get(i).getID().equals(jTFClientID.getText())) {
                    clientListIndex=i;
                    return true;
                }
            }
        }

        return false;
    }



    public void payNumbers(){ 
        java.util.Date fechaSeleccionada = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String fechaString = sdf.format(fechaSeleccionada);
        clientID=jTFClientID.getText();
        payMethod=jCBPayMethod.getSelectedItem().toString();
    
        if (!verifyID()) {
            createClient();
        }

        for (int i = 0; i < selectedNums; i++) {
            Boards auxBoard = boardsList.get(listIndex);
            auxBoard.setNumbersState(selectedNumsList.get(i),3);
            auxBoard.setNumberID(selectedNumsList.get(i),jTFClientID.getText());
            auxBoard.setNumberPayMethod(selectedNumsList.get(i),payMethod);
            auxBoard.setDateNum(selectedNumsList.get(i), fechaString);
            boardsList.set(listIndex, auxBoard);
            boardsList.get(listIndex).sendDatabaseValues(selectedNumsList.get(i));
        
            String data = "Numero: " + selectedNumsList.get(i) + "\nClientID: " + clientID + "\nFecha de Compra: " + fechaString + "\nMetodo de Pago: " + payMethod;
            BufferedImage qrNumber =boardsList.get(listIndex).generateNumQrImage(selectedNumsList.get(i), data);
            boardsList.get(listIndex).setImageQR(selectedNumsList.get(i), qrNumber);
            view.changeStateButton(selectedNumsList.get(i), 3); 
    
        }
        
        selectedNumsList.clear();
        this.dispose();        
    }

    public void reserveNumbers(){ 
    
        if (!verifyID()) {
            createClient();
        }
        
        for (int i = 0; i < selectedNums; i++) {
        
            Boards auxBoard = boardsList.get(listIndex);
            auxBoard.setNumbersState(selectedNumsList.get(i),2);
            view.changeStateButton(selectedNumsList.get(i), 2); 
            auxBoard.setNumberID(selectedNumsList.get(i),jTFClientID.getText());
            boardsList.set(listIndex, auxBoard);
            boardsList.get(listIndex).sendDatabaseValues(selectedNumsList.get(i));

        }
        selectedNumsList.clear();
        this.dispose();
    }

    public void resetNumbers(){

        for (int i = 0; i < selectedNums; i++) {
        
            boardsList.get(listIndex).setNumbersState(selectedNumsList.get(i),0);
            view.changeStateButton(selectedNumsList.get(i), 0); 
        }
        selectedNumsList.clear();
        this.dispose();
    }

        public void addElementList(String newValue){
        listModel.addElement(newValue);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLBoardName = new javax.swing.JLabel();
        jLClientName = new javax.swing.JLabel();
        jTFClientName = new javax.swing.JTextField();
        jBPay = new javax.swing.JButton();
        jCBPayMethod = new javax.swing.JComboBox<>();
        jLClientID = new javax.swing.JLabel();
        jTFClientID = new javax.swing.JTextField();
        jLClientPhone = new javax.swing.JLabel();
        jTFClientPhone = new javax.swing.JTextField();
        jLPayMethod = new javax.swing.JLabel();
        jLTotalAmount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFTotalAmount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLiNumbersSelected = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jBReservation = new javax.swing.JButton();
        jBClientRegistration = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLPayMethod1 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comprar o Reservar Numeros");

        jLBoardName.setText("Talonario: XXXXXX");

        jLClientName.setText("Nombre: ");
        jLClientName.setVisible(false);

        jTFClientName.setVisible(false);
        jTFClientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFClientNameActionPerformed(evt);
            }
        });

        jBPay.setEnabled(false);
        jBPay.setText("Pagar");
        jBPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPayActionPerformed(evt);
            }
        });

        jCBPayMethod.setVisible(false);
        jCBPayMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Sinpe", "Tarjeta" }));
        jCBPayMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBPayMethodActionPerformed(evt);
            }
        });

        jLClientID.setText("Cedula: ");

        jTFClientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFClientIDActionPerformed(evt);
            }
        });

        jLClientPhone.setVisible(false);
        jLClientPhone.setText("Telefono");

        jTFClientPhone.setVisible(false);

        jLPayMethod.setVisible(false);
        jLPayMethod.setText("Metodo de pago:");

        jLTotalAmount.setVisible(false);
        jLTotalAmount.setText("Total a pagar:");

        jTFTotalAmount.setVisible(false);
        jTFTotalAmount.setEnabled(false);
        jTFTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTotalAmountActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jLiNumbersSelected);

        jLabel2.setText("Numeros seleccionados");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jBReservation.setEnabled(false);
        jBReservation.setText("Reservar");
        jBReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBReservationActionPerformed(evt);
            }
        });

        jBClientRegistration.setText("Deplegar datos");
        jBClientRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBClientRegistrationActionPerformed(evt);
            }
        });

        jLPayMethod.setVisible(false);
        jLPayMethod1.setText("Fecha de Pago");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLClientName, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLClientID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLClientPhone, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLPayMethod)
                    .addComponent(jLTotalAmount))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(97, 97, 97))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCBPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLPayMethod1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTFClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBClientRegistration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(45, 45, 45))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(253, 253, 253)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLBoardName)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jBPay, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jBReservation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLBoardName)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLClientID)
                            .addComponent(jTFClientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBClientRegistration))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLClientName))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLClientPhone))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLPayMethod)
                            .addComponent(jCBPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTotalAmount)
                            .addComponent(jTFTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBPay)
                            .addComponent(jButton1)
                            .addComponent(jBReservation)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLPayMethod1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFClientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFClientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFClientNameActionPerformed

    private void jCBPayMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPayMethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBPayMethodActionPerformed

    private void jTFClientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFClientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFClientIDActionPerformed

    private void jBClientRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBClientRegistrationActionPerformed
        // TODO add your handling code here:
        setVisibleFields();        
    }//GEN-LAST:event_jBClientRegistrationActionPerformed

    private void jTFTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTotalAmountActionPerformed

    private void jBPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPayActionPerformed

        payNumbers();
    }//GEN-LAST:event_jBPayActionPerformed

    private void jBReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBReservationActionPerformed
        // TODO add your handling code here:
        reserveNumbers();
    }//GEN-LAST:event_jBReservationActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        resetNumbers();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BuyTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuyTickets().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBClientRegistration;
    private javax.swing.JButton jBPay;
    private javax.swing.JButton jBReservation;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBPayMethod;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLBoardName;
    private javax.swing.JLabel jLClientID;
    private javax.swing.JLabel jLClientName;
    private javax.swing.JLabel jLClientPhone;
    private javax.swing.JLabel jLPayMethod;
    private javax.swing.JLabel jLPayMethod1;
    private javax.swing.JLabel jLTotalAmount;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jLiNumbersSelected;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFClientID;
    private javax.swing.JTextField jTFClientName;
    private javax.swing.JTextField jTFClientPhone;
    private javax.swing.JTextField jTFTotalAmount;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}

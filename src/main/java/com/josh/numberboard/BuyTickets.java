/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.josh.numberboard;

import static com.josh.numberboard.BoardsMenu.boardsList;
import static com.josh.numberboard.BoardsMenu.view;
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
     
 public static  ArrayList<Integer> selectedNumsList=new ArrayList<>();
 public static ArrayList<Clients>clientsList=new ArrayList<>();
 private    DefaultListModel listModel=new DefaultListModel();
 
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
         }
         else{
        
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
            System.out.println("linea 90");
        verifyReserveNumID();
    }
  else  if (verifyID()==true) {
        System.out.println("asdfa");
          jLClientName.setVisible(true);   
        jTFClientName.setVisible(true);
    
        jLClientPhone.setVisible(true);
        jTFClientPhone.setVisible(true);
    
        jLPayMethod.setVisible(true);
        jCBPayMethod.setVisible(true);
            jTFClientName.setText(clientsList.get(clientListIndex).getName());
          jTFClientPhone.setText(clientsList.get(clientListIndex).getPhoneNumber());
            
        }
    
    else{
        String[] options = {"OK", "Cancelar"};
        int opt = JOptionPane.showOptionDialog(null,"No se encontro el ID ingresado\n\n¿Desea registrar un cliente nuevo?", "¡AVISO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, null);
        
        if(opt == 0){

        jLClientName.setVisible(true);   
        jTFClientName.setVisible(true);
    
        jLClientPhone.setVisible(true);
        jTFClientPhone.setVisible(true);
    
        jLPayMethod.setVisible(true);
        jCBPayMethod.setVisible(true);
        }
    }
    

}
public void verifyReserveNumID(){
    
    boolean aux=true;
             for (int i = 0; i < selectedNumsList.size(); i++) {
                 System.out.println("Linea 131");
                 System.out.println( jTFClientID.getText());
                if (!boardsList.get(listIndex).getNumID(selectedNumsList.get(i)).equals(jTFClientID.getText())) {//verifica si todos los numeros tienen mismo ID
                    System.out.println("Linea 133");
                     aux=false;
                             break;
                }
         }
          if (aux) {
                            areReserveNums=false;
              setVisibleFields();
              
         }
          else{

                   String[] options = {"OK", "Cancelar"};
         int opt = JOptionPane.showOptionDialog(null,"El ID ingresado no coincide con el de los numeros reservados\n\n¿Desea ingresar un ID diferente?", "¡AVISO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, null);
        
              if (opt==1) {

              }

          }
}

public   boolean verifyID(){


   if (!clientsList.isEmpty()) {

        for (int i = 0; i < clientsList.size(); i++) {
                   
        if (clientsList.get(i).getID().equals(jTFClientID.getText())) {
            System.out.println("Cliente encontrado");
            clientListIndex=i;
           return true;
        }
      }
    }

    return false;
}

public void payNumbers(){ // 3 = pagado
    
        payMethod=jCBPayMethod.getSelectedItem().toString();
        if (!verifyID()) {
                createClient();
    }

    for (int i = 0; i < selectedNums; i++) {
        
        boardsList.get(listIndex).setNumbersState(selectedNumsList.get(i),3);
        boardsList.get(listIndex).setNumberID(selectedNumsList.get(i),clientID);
        
      //  view.setEnabledButtons(selectedNumsList.get(i));
        view.changeStateButton(selectedNumsList.get(i), 3); //edit
    }
    
        selectedNumsList.clear();

            this.dispose();
}

public void reserveNumbers(){ // 1 = reservado
    
                if (!verifyID()) {
                    createClient();
                }
    for (int i = 0; i < selectedNums; i++) {
        
        boardsList.get(listIndex).setNumbersState(selectedNumsList.get(i),2);
        view.changeStateButton(selectedNumsList.get(i), 2); // edit
                boardsList.get(listIndex).setNumberID(selectedNumsList.get(i),jTFClientID.getText());

        System.out.println("ID asignado: "+boardsList.get(listIndex).getNumID(selectedNumsList.get(i)));
    }
    selectedNumsList.clear();
            this.dispose();
}

public void resetNumbers(){
  
  
  
  
  
    
    for (int i = 0; i < selectedNums; i++) {
        
        boardsList.get(listIndex).setNumbersState(selectedNumsList.get(i),0);
        view.changeStateButton(selectedNumsList.get(i), 0); // edit
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
        jCBIDList = new javax.swing.JComboBox<>();
        jBClientRegistration = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBoardName.setText("Talonario: XXXXXX");

        jLClientName.setText("Nombre: ");
        jLClientName.setVisible(false);

        jTFClientName.setVisible(false);
        jTFClientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFClientNameActionPerformed(evt);
            }
        });

        jBPay.setText("Pagar");
        jBPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPayActionPerformed(evt);
            }
        });

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

        jLPayMethod.setText("Metodo de pago:");

        jLTotalAmount.setText("Total a pagar:");

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

        jBReservation.setText("Reservar");
        jBReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBReservationActionPerformed(evt);
            }
        });

        jCBIDList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBIDList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBIDListActionPerformed(evt);
            }
        });

        jBClientRegistration.setText("Deplegar datos");
        jBClientRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBClientRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLClientName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLClientID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLClientPhone, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBClientRegistration))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCBIDList, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTFClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(151, 151, 151)
                            .addComponent(jBPay)
                            .addGap(12, 12, 12)
                            .addComponent(jBReservation)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLTotalAmount)
                                .addGap(18, 18, 18)
                                .addComponent(jTFTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLBoardName)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLPayMethod)
                            .addGap(30, 30, 30)
                            .addComponent(jCBPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(140, 140, 140))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLClientID)
                            .addComponent(jTFClientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBClientRegistration))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLClientName)
                            .addComponent(jCBIDList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLBoardName)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLClientPhone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLPayMethod)
                            .addComponent(jCBPayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTotalAmount)
                            .addComponent(jTFTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBPay)
                    .addComponent(jButton1)
                    .addComponent(jBReservation))
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

    private void jCBIDListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBIDListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBIDListActionPerformed

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
    private javax.swing.JComboBox<String> jCBIDList;
    private javax.swing.JComboBox<String> jCBPayMethod;
    private javax.swing.JLabel jLBoardName;
    private javax.swing.JLabel jLClientID;
    private javax.swing.JLabel jLClientName;
    private javax.swing.JLabel jLClientPhone;
    private javax.swing.JLabel jLPayMethod;
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

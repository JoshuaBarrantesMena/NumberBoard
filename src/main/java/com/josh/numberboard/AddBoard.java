/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.josh.numberboard;
import static  com.josh.numberboard.BoardsMenu.boardsList;
import static com.josh.numberboard.BoardsMenu.listModel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Josh
 */
public class AddBoard extends javax.swing.JFrame {
    /**
     * Creates new form AddBoard
     */
    public AddBoard() {
        initComponents();
        setImageLabel(fontLabel, "src/main/java/com/josh/resources/LandscapeWP.png");
    }

    private void setImageLabel(JLabel label, String imageRute){
        ImageIcon font = new ImageIcon(imageRute);
        Icon image = new ImageIcon(font.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(image);
        this.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fontPanel = new javax.swing.JPanel();
        Owner = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        Numbers = new javax.swing.JLabel();
        Value = new javax.swing.JLabel();
        Prize = new javax.swing.JLabel();
        Description = new javax.swing.JLabel();
        BoardDesc = new javax.swing.JScrollPane();
        BoardDescText = new javax.swing.JTextArea();
        BoardOwner = new javax.swing.JTextField();
        BoardName = new javax.swing.JTextField();
        BoardNumbers = new javax.swing.JTextField();
        BoardPrice = new javax.swing.JTextField();
        BoardPrize = new javax.swing.JTextField();
        ConfirmButton = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        fontLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Nuevo Talonario");
        setAlwaysOnTop(true);

        fontPanel.setPreferredSize(new java.awt.Dimension(360, 440));
        fontPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Owner.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Owner.setText("Propietario:");
        fontPanel.add(Owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, -1));

        Date.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Date.setText("Fecha Limite:");
        fontPanel.add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 90, -1));

        name.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        name.setText("Nombre:");
        fontPanel.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        Numbers.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Numbers.setText("Cantidad de numeros:");
        fontPanel.add(Numbers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, -1));
        Numbers.getAccessibleContext().setAccessibleName("Valor:");

        Value.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Value.setText("Valor por numero:");
        fontPanel.add(Value, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 140, -1));

        Prize.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Prize.setText("Premio:");
        fontPanel.add(Prize, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 140, -1));

        Description.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Description.setText("Descripcion:");
        fontPanel.add(Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, -1));

        BoardDescText.setColumns(20);
        BoardDescText.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BoardDescText.setLineWrap(true);
        BoardDescText.setRows(5);
        BoardDescText.setWrapStyleWord(true);
        BoardDescText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BoardDescTextKeyTyped(evt);
            }
        });
        BoardDesc.setViewportView(BoardDescText);

        fontPanel.add(BoardDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 370, 100));

        BoardOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoardOwnerActionPerformed(evt);
            }
        });
        BoardOwner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BoardOwnerKeyTyped(evt);
            }
        });
        fontPanel.add(BoardOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 220, -1));

        BoardName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoardNameActionPerformed(evt);
            }
        });
        BoardName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BoardNameKeyTyped(evt);
            }
        });
        fontPanel.add(BoardName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 220, -1));

        BoardNumbers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BoardNumbersKeyTyped(evt);
            }
        });
        fontPanel.add(BoardNumbers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, -1));

        BoardPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BoardPriceKeyTyped(evt);
            }
        });
        fontPanel.add(BoardPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 150, -1));

        BoardPrize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BoardPrizeKeyTyped(evt);
            }
        });
        fontPanel.add(BoardPrize, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 180, -1));

        ConfirmButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ConfirmButton.setText("Confirmar");
        ConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmButtonActionPerformed(evt);
            }
        });
        fontPanel.add(ConfirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 90, 30));
        fontPanel.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 130, 30));
        fontPanel.add(fontLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmButtonActionPerformed

        String name = BoardName.getText();
        String owner = BoardOwner.getText();
        int numAmount = Integer.parseInt(BoardNumbers.getText());
        int numPrice = Integer.parseInt(BoardPrice.getText());
        String prize = BoardPrize.getText();
        String boardDesc = BoardDescText.getText();
        java.util.Date fechaSeleccionada = jDateChooser1.getDate();
        String fechaString = null;

        if (fechaSeleccionada != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fechaString = sdf.format(fechaSeleccionada);
            String qrData = "Nombre: " + name + "\nFecha Límite: " + fechaString + "\nPremio: " + prize;
        
            BufferedImage qrImage = Boards.generateQR(qrData);
            Boards newBoard = new Boards(name, owner, numAmount, numPrice, prize, boardDesc, fechaString,qrImage);     
            boardsList.add(newBoard);

            listModel.addElement(newBoard.getName());        
            this.dispose();
            BoardName.setText("");
            BoardNumbers.setText("");
            BoardOwner.setText("");
            BoardPrize.setText("");
            BoardPrice.setText("");
            BoardDescText.setText("");
        }                                             
    }//GEN-LAST:event_ConfirmButtonActionPerformed

    private void BoardNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BoardNameKeyTyped
        if(BoardName.getText().length() >= 32){
            evt.consume();
        }
    }//GEN-LAST:event_BoardNameKeyTyped

    private void BoardOwnerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BoardOwnerKeyTyped
        if(BoardOwner.getText().length() >= 32){
            evt.consume();
        }
    }//GEN-LAST:event_BoardOwnerKeyTyped

    private void BoardDescTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BoardDescTextKeyTyped
        if(BoardDescText.getText().length() >= 256){
            evt.consume();
        }
    }//GEN-LAST:event_BoardDescTextKeyTyped

    private void BoardPrizeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BoardPrizeKeyTyped
        if(BoardPrize.getText().length() >= 24){
            evt.consume();
        }
    }//GEN-LAST:event_BoardPrizeKeyTyped

    private void BoardPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BoardPriceKeyTyped
        isNumber(evt);
    }//GEN-LAST:event_BoardPriceKeyTyped

    private void BoardNumbersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BoardNumbersKeyTyped
        isNumber(evt);
    }//GEN-LAST:event_BoardNumbersKeyTyped

    private void BoardOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoardOwnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoardOwnerActionPerformed

    private void BoardNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoardNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoardNameActionPerformed
    
    private void isNumber(java.awt.event.KeyEvent evt){
        int key = evt.getKeyChar();
        boolean isNum = key >= 48 && key <= 57;
        
        if(!isNum){
            evt.consume();
        }
    }
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
            java.util.logging.Logger.getLogger(AddBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBoard().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane BoardDesc;
    private javax.swing.JTextArea BoardDescText;
    private javax.swing.JTextField BoardName;
    private javax.swing.JTextField BoardNumbers;
    private javax.swing.JTextField BoardOwner;
    private javax.swing.JTextField BoardPrice;
    private javax.swing.JTextField BoardPrize;
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Description;
    private javax.swing.JLabel Numbers;
    private javax.swing.JLabel Owner;
    private javax.swing.JLabel Prize;
    private javax.swing.JLabel Value;
    private javax.swing.JLabel fontLabel;
    private javax.swing.JPanel fontPanel;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}


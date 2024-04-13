/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.josh.numberboard;

import ConexionSQLDB.DataBaseConnect;
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.WriterException;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.WriterException;
import static com.josh.numberboard.Boards.displayQRImage;
import java.awt.image.BufferedImage;

/**
 *
 * @author Josh
 */
public class BoardsMenu extends javax.swing.JFrame {

    
 public static   DefaultListModel listModel=new DefaultListModel();
    
  public static  ArrayList<Boards> boardsList= new ArrayList<>();
    
    /**
     * Creates new form BoardsMenu
     */
    public BoardsMenu() {
        initComponents();
        listModel = new DefaultListModel();
        ListBoard.setModel(listModel);
        setImageLabel(fontLabel, "src/main/java/com/josh/resources/Skywallpaper.png");

        //
        /*
        try(Connection conn = DataBaseConnect.getConnection()){
                CallableStatement sv = conn.prepareCall("{call GETBOARD(?)}");
                sv.registerOutParameter(1, Types.REF_CURSOR);
                sv.execute();
                
                ResultSet rs = (ResultSet) sv.getObject(1);
                while(rs.next()){
                    
                    Boards auxBoard = new Boards();
                    
                    auxBoard.setID(rs.getInt("BOARD_ID"));
                    auxBoard.setName(rs.getString("NAME"));
                    auxBoard.setOwner(rs.getString("OWNER"));
                    auxBoard.setNumAmount(rs.getInt("NUM_AMOUNT"));
                    auxBoard.setWinAmount(rs.getInt("WIN_AMOUNT"));
                    auxBoard.setNumPrice(rs.getInt("NUM_PRICE"));
                    auxBoard.setPrize(rs.getString("PRIZE"));
                    auxBoard.setBoardDesc(rs.getString("BOARD_DESC"));
                    auxBoard.setLimitDate(rs.getString("LIMIT_DATE"));
                    
                  //  boardList.add(auxBoard);
                    listModel.addElement(rs.getString("NAME"));
                }
                
        } catch (SQLException ex){
            System.out.println(ex);
        }
        */
    }
    
    private void setImageLabel(JLabel label, String imageRute){
        ImageIcon font = new ImageIcon(imageRute);
        Icon image = new ImageIcon(font.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(image);
        this.repaint();
    }

    public BoardsMenu syncWindow(){
        return this;
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

        fontPanel = new javax.swing.JPanel();
        DeleteBoard = new javax.swing.JButton();
        createBoard = new javax.swing.JButton();
        OpenBoard = new javax.swing.JButton();
        ScrollListBoard = new javax.swing.JScrollPane();
        ListBoard = new javax.swing.JList<>();
        ShowInfo = new javax.swing.JButton();
        fontLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fontPanel.setMinimumSize(new java.awt.Dimension(640, 420));
        fontPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeleteBoard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        DeleteBoard.setText("Eliminar Tablonario");
        DeleteBoard.setEnabled(false);
        DeleteBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBoardActionPerformed(evt);
            }
        });
        fontPanel.add(DeleteBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 170, 40));

        createBoard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        createBoard.setText("Nuevo Tablonario");
        createBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBoardActionPerformed(evt);
            }
        });
        fontPanel.add(createBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 170, 40));

        OpenBoard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        OpenBoard.setText("Abrir Tablonario");
        OpenBoard.setEnabled(false);
        OpenBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenBoardActionPerformed(evt);
            }
        });
        fontPanel.add(OpenBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 170, 40));

        ListBoard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ListBoard.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ListBoard.setOpaque(false);
        ListBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListBoardMouseClicked(evt);
            }
        });
        ScrollListBoard.setViewportView(ListBoard);

        fontPanel.add(ScrollListBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 340));

        ShowInfo.setText("VER QR");
        ShowInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowInfoActionPerformed(evt);
            }
        });
        fontPanel.add(ShowInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 120, 40));
        fontPanel.add(fontLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fontPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBoardActionPerformed
        AddBoard newBoard = new AddBoard();
        newBoard.setLocationRelativeTo(null);
        newBoard.setVisible(true);
    }//GEN-LAST:event_createBoardActionPerformed

    private void ListBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListBoardMouseClicked
        if(ListBoard.getSelectedIndex() != -1){
            OpenBoard.setEnabled(true);
            DeleteBoard.setEnabled(true);
        }
    }//GEN-LAST:event_ListBoardMouseClicked

    private void DeleteBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBoardActionPerformed
        String[] options = {"OK", "Cancelar"};
        int opt = JOptionPane.showOptionDialog(null,"Esta a punto de eliminar el tablonario seleccionado\n\n¿Desea eliminarlo?", "¡AVISO!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null , options, null);
        
        if(opt == 0){
           // boardsList.get(ListBoard.getSelectedIndex()).deleteBoardDatabase(ListBoard.getSelectedIndex());
            boardsList.remove(ListBoard.getSelectedIndex());
            listModel.remove(ListBoard.getSelectedIndex());
            OpenBoard.setEnabled(false);
            DeleteBoard.setEnabled(false);
        }
    }//GEN-LAST:event_DeleteBoardActionPerformed

    private void ShowInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowInfoActionPerformed
            
         int selectedIndex = ListBoard.getSelectedIndex();

    if (selectedIndex != -1) { // Si se ha seleccionado un elemento válido
        // Obtener el objeto Boards correspondiente
        Boards selectedBoard = boardsList.get(selectedIndex);

        // Obtener el QR asociado al objeto Boards
        BufferedImage qrImage = selectedBoard.getQrImage();

        if (qrImage != null) { // Si se ha generado un QR válido
            // Mostrar el QR en una nueva ventana
            displayQRImage(qrImage,30 ,30);
        } else {
            // Mostrar un mensaje de error si no se ha generado un QR válido
            JOptionPane.showMessageDialog(this, "No se ha generado un código QR para este elemento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Mostrar un mensaje si no se ha seleccionado ningún elemento
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un elemento de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  
            
    }//GEN-LAST:event_ShowInfoActionPerformed

    private void OpenBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenBoardActionPerformed
  
     BoardTickets view=new BoardTickets();
     
        System.out.println( boardsList.get(ListBoard.getSelectedIndex()).getNumAmount());
        
             view.initRows( ListBoard.getSelectedIndex());

      
        view.setVisible(true);

        this.dispose();        // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_OpenBoardActionPerformed

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
            java.util.logging.Logger.getLogger(BoardsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardsMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBoard;
    private javax.swing.JList<String> ListBoard;
    private javax.swing.JButton OpenBoard;
    private javax.swing.JScrollPane ScrollListBoard;
    private javax.swing.JButton ShowInfo;
    private javax.swing.JButton createBoard;
    private javax.swing.JLabel fontLabel;
    private javax.swing.JPanel fontPanel;
    // End of variables declaration//GEN-END:variables

    static class boards {

        public boards() {
        }
    }
}

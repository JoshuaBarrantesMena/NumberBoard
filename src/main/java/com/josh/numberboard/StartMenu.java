/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.josh.numberboard;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 *
 * @author Josh
 */
public class StartMenu extends javax.swing.JFrame {

    public static BoardsMenu boardWindow;
    /**
     * Creates new form StartMenu
     */
    public StartMenu() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        setImageLabel(fontLabel, "src/main/java/com/josh/resources/Skywallpaper.png");
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

        voidPanel = new javax.swing.JPanel();
        Start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fontLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        voidPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Start.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Start.setLabel("Empezar");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        voidPanel.add(Start, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 140, 70));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Crea Tablonarios para eventos\n");
        voidPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 280, 100));
        voidPanel.add(fontLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voidPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voidPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        SplashScreen splashScreen = new SplashScreen();
    
  
        Timer timer = new Timer(10, new ActionListener() {
        private int progress = 0;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            progress++;
            
            splashScreen.setProgress(progress);

            if (progress == 100) {
                ((Timer) e.getSource()).stop();

                boardWindow = new BoardsMenu();
                boardWindow.setLocationRelativeTo(null);
                boardWindow.setVisible(true);

                splashScreen.dispose();
               
                dispose();
            }
        }
    });

    timer.start();
    }//GEN-LAST:event_StartActionPerformed

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
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Start;
    private javax.swing.JLabel fontLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel voidPanel;
    // End of variables declaration//GEN-END:variables
}

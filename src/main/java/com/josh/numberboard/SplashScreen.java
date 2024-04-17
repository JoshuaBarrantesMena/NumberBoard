/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josh.numberboard;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SplashScreen extends JWindow {
    private JLabel splashLabel;
    private CenteredProgressBar progressBar;
    private Timer timer;

    public SplashScreen() {
        
        ImageIcon icon = new ImageIcon("src/main/java/com/josh/resources/ChargeWallpaper.png");
        splashLabel = new JLabel(icon);

       
        progressBar = new CenteredProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        
        timer = new Timer(10, new ActionListener() {
            private int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                
                progress++;
               
                progressBar.setValue(progress);
                
                if (progress == 100) {
                    timer.stop();
                    setVisible(false);
                    dispose(); 
                }
            }
        });

        
        setSize(icon.getIconWidth(), icon.getIconHeight() + progressBar.getPreferredSize().height);

        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(splashLabel, BorderLayout.CENTER);
        getContentPane().add(progressBar, BorderLayout.SOUTH);

        
        timer.start();

        setVisible(true);
    }

   
    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }
}

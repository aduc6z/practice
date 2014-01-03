/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.ui;

import com.sample.controller.AppController;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 */
public class TimeCounterDialog extends javax.swing.JFrame {

    /**
     * Creates new form Popup
     */
    public TimeCounterDialog() {
        initComponents();
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
        elapsedTimeLabel = new javax.swing.JLabel();
        pauseResumeButton = new javax.swing.JToggleButton();
        nextButton = new javax.swing.JButton();
        hideButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Time");

        elapsedTimeLabel.setText("Output");

        pauseResumeButton.setText("Pause/Resume");
        pauseResumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseResumeButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        hideButton.setText("Hide");
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pauseResumeButton)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(elapsedTimeLabel)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(hideButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elapsedTimeLabel))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseResumeButton)
                    .addComponent(nextButton))
                .addGap(18, 18, 18)
                .addComponent(hideButton)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pauseResumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseResumeButtonActionPerformed
        controller.toggleRunningState();
        nextButton.setEnabled(false);
    }//GEN-LAST:event_pauseResumeButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        this.controller.start();      
        nextButton.setEnabled(false);
        pauseResumeButton.setEnabled(true);
        this.setVisible(false);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_hideButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TimeCounterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimeCounterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimeCounterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimeCounterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TimeCounterDialog dialog = new TimeCounterDialog();
                dialog.setVisible(true);
                AppController controller = new AppController(dialog);
                dialog.setController(controller);
                dialog.createSystemTray();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel elapsedTimeLabel;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton nextButton;
    private javax.swing.JToggleButton pauseResumeButton;
    // End of variables declaration//GEN-END:variables

   
    String getTimeInString(long timeInMilliSecond) {
        long timeInSeconds = timeInMilliSecond / 1000;
        long timeInMinutes = timeInSeconds / 60;
        String seconds = String.valueOf(timeInSeconds % 60);
        seconds = (seconds.length() >=2 ? "" : "0") + seconds;
        return String.valueOf(timeInMinutes) + ":" + String.valueOf(seconds);
    }
    
    public void update(long elapsedTime, String status) {
        elapsedTimeLabel.setText(getTimeInString(elapsedTime));
        placeHolder.setLabel(status + " --- " + getTimeInString(elapsedTime));
    }
    
    AppController controller;

    public void setController(AppController controller) {
        this.controller = controller;
    }

    public void enableNext() {
        nextButton.setEnabled(true);
        pauseResumeButton.setEnabled(false);        
    }
    
    PopupMenu popupMenu;
    MenuItem placeHolder = new MenuItem();
    
    void createSystemTray() {
        if (!SystemTray.isSupported()) {
            System.err.println("System does not support tray!");
            return;
        }
        popupMenu = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("/resources/index.jpeg", "tray icon"));
        trayIcon.setImageAutoSize(true);
        final SystemTray tray = SystemTray.getSystemTray();
        
        MenuItem configItem = new MenuItem("Configure");
        MenuItem nextItem = new MenuItem("Next");       
        MenuItem pauseResumeItem = new MenuItem("Pause/Resume");
        MenuItem exitItem = new MenuItem("Exit");
        nextItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nextButtonActionPerformed(e);
            }
        });
        pauseResumeItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pauseResumeButtonActionPerformed(e);
            }
        });
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popupMenu.add(placeHolder);
        popupMenu.add(nextItem);
        popupMenu.add(pauseResumeItem);
        popupMenu.add(configItem);
        popupMenu.add(exitItem);
        trayIcon.setPopupMenu(popupMenu);
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(TimeCounterDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     protected static Image createImage(String path, String description) {
        URL imageURL = TimeCounterDialog.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            Image image = null;
            try {
                BufferedImage bImage = ImageIO.read(TimeCounterDialog.class.getResource(path));
                double width = new TrayIcon(bImage).getSize().getWidth();
                image = bImage.getScaledInstance((int) width, -1, Image.SCALE_SMOOTH);
            } catch (IOException ex) {
                Logger.getLogger(TimeCounterDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            return image;
        }
    }
}
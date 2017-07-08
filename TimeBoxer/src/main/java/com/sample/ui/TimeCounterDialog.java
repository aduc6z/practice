
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ui;

import com.sample.controller.AppController;
import com.sample.model.AppState;
import com.sample.model.StateChangeListener;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 */
public class TimeCounterDialog extends javax.swing.JFrame implements StateChangeListener {

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

        setTitle("W");
        setAlwaysOnTop(true);
        setResizable(false);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(74, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(pauseResumeButton)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(elapsedTimeLabel)
                                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(elapsedTimeLabel))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pauseResumeButton)
                                        .addComponent(nextButton))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pauseResumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseResumeButtonActionPerformed
        controller.pauseResumeClick();
        nextButton.setEnabled(false);
        updateSystemTrayImage();
        pauseResumeButton.setSelected(!appState.isTimerRunning());
        nextItem.setEnabled(!pauseResumeButton.isSelected());
        restartItem.setEnabled(!pauseResumeButton.isSelected());
    }//GEN-LAST:event_pauseResumeButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        this.controller.nextClick();
        updateSystemTrayImage();
        nextButton.setEnabled(false);
        nextItem.setEnabled(true);
        pauseResumeButton.setEnabled(true);
        this.setVisible(false);
    }//GEN-LAST:event_nextButtonActionPerformed

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
                AppState appState = new AppState(dialog);
                AppController controller = new AppController(appState);
                dialog.controller = controller;
                dialog.appState = appState;
                dialog.createSystemTray();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel elapsedTimeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton nextButton;
    private javax.swing.JToggleButton pauseResumeButton;
    // End of variables declaration//GEN-END:variables

    private String getTimeInString(long timeInMilliSecond) {
        long timeInSeconds = timeInMilliSecond / 1000;
        String seconds = String.valueOf(timeInSeconds % 60);
        seconds = (seconds.length() >= 2 ? "" : "0") + seconds;
        long timeInMinutes = timeInSeconds / 60;
        return String.valueOf(timeInMinutes) + ":" + String.valueOf(seconds);
    }

    private void updateTitle() {
//        System.out.println("Update title");
//        System.out.println("isTrayOnFocus = " + isTrayOnFocus);
//        System.out.println("this.isVisible() = " + this.isVisible());
        long elapsedTime = appState.getRemainingTime();
        String status = appState.getState().toString();
        String label = status + " --- " + getTimeInString(elapsedTime);
        if (this.isVisible()) {
            elapsedTimeLabel.setText(getTimeInString(elapsedTime));
        }
        if (trayIcon != null) {
            trayIcon.setToolTip(label);
            placeHolder.setLabel(label);
        }
    }

    AppController controller;
    AppState appState;

    public void setController(AppController controller) {
        this.controller = controller;
    }

    public void enableNext() {
        nextButton.setEnabled(true);
        pauseResumeButton.setEnabled(false);
    }

    PopupMenu popupMenu;
    MenuItem placeHolder = new MenuItem("-----");
    TrayIcon trayIcon;
    MenuItem configItem = new MenuItem("Configure");
    MenuItem nextItem = new MenuItem("Next");
    MenuItem pauseResumeItem = new MenuItem("Pause/Resume");
    MenuItem restartItem = new MenuItem("Restart");
    MenuItem exitItem = new MenuItem("Exit");

    boolean isTrayOnFocus = false;

    private void createSystemTray() {
        if (!SystemTray.isSupported()) {
            System.err.println("System does not support tray!");
            return;
        }
        final SystemTray tray = SystemTray.getSystemTray();
        if (trayIcon != null) {
            tray.remove(trayIcon);
        }
        trayIcon = createTrayIcon(getResourcePath(this.appState.getState()), "tray icon");
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(TimeCounterDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateSystemTrayImage() {
        if (!SystemTray.isSupported()) {
            System.err.println("System does not support tray!");
            return;
        }
        Image image = createImage(getResourcePath(appState.getState()), "");
        trayIcon.setImage(image);
    }

    private String getResourcePath(AppState.StateEnum state) {
        String resourcePath = null;
        switch (state) {
            case REST:
                resourcePath = "/resources/standby.jpeg";
                break;
            case WORKING:
                resourcePath = "/resources/index.jpeg";
                break;
            case PAUSE:
                resourcePath = "/resources/stop.png";
                break;
            default:
                throw new RuntimeException("Unsupported appState!");
        }
        return resourcePath;
    }

    private TrayIcon createTrayIcon(String resourcePath, String trayName) {
        System.out.println("Create icon from " + resourcePath);
        trayIcon = new TrayIcon(createImage(resourcePath, trayName));
        trayIcon.setImageAutoSize(true);
        registerMenuListener(nextItem, pauseResumeItem, exitItem);
        registerMouseEventHandlerForSystemTray(trayIcon);
        popupMenu = new PopupMenu();
        popupMenu.add(placeHolder);
        popupMenu.add(nextItem);
        popupMenu.add(pauseResumeItem);
//      popupMenu.add(configItem);
        popupMenu.add(restartItem);
        popupMenu.add(exitItem);
        trayIcon.setPopupMenu(popupMenu);
        return trayIcon;
    }

    private void registerMenuListener(MenuItem nextItem, MenuItem pauseResumeItem, MenuItem exitItem) {
        nextItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextButtonActionPerformed(e);
                System.out.println("Call next button");
            }
        });
        pauseResumeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseResumeButtonActionPerformed(e);
            }
        });
        exitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                restartButtonActionPerformed(e);
            }

        });
    }

    private void restartButtonActionPerformed(ActionEvent e) {
        controller.restartClick();
    }

    private static Image createImage(String path, String description) {
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

    /**
     * Handle double click
     *
     * @param trayIcon
     */
    private void registerMouseEventHandlerForSystemTray(TrayIcon trayIcon) {
        trayIcon.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                updateTitle();
                if (e.getClickCount() > 1) {
                    showDialog();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered---");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
            }
        });
    }

    /**
     * Customized method to ensure the dialog is brought to front
     * even when there is focus stealing
     */
    public void showDialog() {
        setVisible(true);
        toFront();
        requestFocus();
        repaint();
    }

    public void handleActiveStateChange() {
//        System.out.println("Handle active state change");
//        System.out.println("appState = " + appState);
        updateSystemTrayImage();
    }

    public void handleTimerToggle() {
//        System.out.println("Handle timer toggle");
//        System.out.println("appState = " + appState);
        updateSystemTrayImage();
    }

    public void handleTimeChange() {
//        System.out.println("handle time change");
        updateTitle();
//        System.out.println("appState = " + appState);
    }
}

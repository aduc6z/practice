/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.controller;

import com.sample.ui.TimeCounterDialog;

/**
 *
 * 
 */
public class AppController {
    AppTimer controlTimer;
    int currentState = 1;
    Boolean running = true;
    TimeCounterDialog popup;
    
    public AppController(TimeCounterDialog popupDialog) {
        this.popup = popupDialog;
        controlTimer = new AppTimer(this, currentState);
        controlTimer.start();
        System.out.println("Finished initialized controller");
    }
    
      
    public void stop() {
        controlTimer.cancel();
        currentState++;
        popup.setVisible(true);
        popup.enableNext();
    }
    
    public void start() {
        controlTimer = new AppTimer(this, currentState);
        controlTimer.start();
        popup.setVisible(true);
    }
    
    public void toggleRunningState() {
        running = !running;
    }

    void updateTime(long elapsedTime) {
        popup.update(elapsedTime);
    }

    public Boolean isRunning() {
        return running;
    }    
    
}
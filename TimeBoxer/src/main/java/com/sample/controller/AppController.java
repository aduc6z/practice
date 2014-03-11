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
    int currentState;
    Boolean running;
    TimeCounterDialog popup;
    
    public AppController(TimeCounterDialog popupDialog) {
        currentState = 1;
        running = true;
        this.popup = popupDialog;
        controlTimer = new AppTimer(this, currentState);
        controlTimer.start();
        System.out.println("Finished initialized controller");
    }    
    
    public void stop() {
        swithState();        
        popup.showDialog();
        popup.enableNext();        
        running = false;
    }
    
    void swithState() {
        currentState++;
        popup.setTitle(currentState % 2 == 0 ? "R" : "W");
        controlTimer.cancel();
    }
           
    public void start() {
        if (running) swithState();
        controlTimer = new AppTimer(this, currentState);
        controlTimer.start(); 
        running = true;
    }
    
    public void toggleRunningState() {
        running = !running;
    }

    void updateTime(long elapsedTime) {
        popup.update(elapsedTime, currentState % 2 == 0 ? "R" : "W");
    }

    public Boolean isRunning() {
        return running;
    }
   
}
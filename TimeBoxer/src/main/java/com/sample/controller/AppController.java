/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.controller;

import com.sample.model.AppState;
import com.sample.ui.UIActionListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * 
 */
public class AppController implements UIActionListener {
    AppState appState;
    Timer controlTimer;
    long sleepStep = 1000;

    public AppController(AppState appState) {
        this.appState = appState;
        System.out.println("Finished initialized controller");
    }    

    public void pauseResumeClick() {
        appState.switchRunningState();
    }

    public void restartClick() {
        appState.resetTime();
    }

    public void nextClick() {
        appState.changeToNextActiveState();
        startNewCounterTask();
    }

    private void startNewCounterTask() {
        if (controlTimer != null) {
            controlTimer.cancel();
        }
        controlTimer = new Timer();
        controlTimer.schedule(new TimeCounter(), 0, sleepStep);
    }

    class TimeCounter extends TimerTask {

        @Override
        public void run() {
//            System.out.println("Task execute:");
//            System.out.println("appState = " + appState);
            if (appState.getState() != AppState.StateEnum.PAUSE) {
                appState.reduceRemainingTime(sleepStep);
            }
        }
    }

}
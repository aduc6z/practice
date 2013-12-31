/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.controller;

import java.util.TimerTask;

/**
 *
 * 
 */
public class AppTimer extends java.util.Timer {  
    
    final int REST = 0;
    final int WORK = 1;
    final int state;
    AppController controller;
    final long sleepStep = 1000; // in milliseconds
    final int WORK_TIME = 25;
    final int REST_TIME = 5;
    final boolean measureInMinutes = true;
    
    public AppTimer(AppController controller, int state) {
        this.controller = controller;        
        this.state = state;
    }
        
    class TimeCounter extends TimerTask {
        
        long totalTime, sleepStep;
        long elapsedTime;
        
        public TimeCounter(long totalTime, long sleepStep) {
            this.totalTime = totalTime;
            this.sleepStep = sleepStep;          
        }
        
        @Override
        public void run() {
//            System.out.println("Current elapsed time: " + elapsedTime);
            controller.updateTime(elapsedTime);
            if (controller.isRunning()) {
                elapsedTime += sleepStep;
            }
            if (elapsedTime > totalTime) {
                controller.stop();
            }
        }        
    }
        
    TimerTask getTask(int state) {
        long totalTime = 0;
        switch(state % 2) {
            case REST:
                totalTime = REST_TIME * 1000 * (measureInMinutes ? 60 : 1);
                break;
            case WORK:
                totalTime = WORK_TIME * 1000 * (measureInMinutes ? 60 : 1);
                break;
            default:
                throw new RuntimeException("Not supported state! state = " + state);
        }        
        return new TimeCounter(totalTime, sleepStep);
    }
    
    public void start() {
        TimerTask task = getTask(state);
        this.schedule(task, 0, sleepStep);
    }
    
}
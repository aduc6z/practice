/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.controller;

import java.util.TimerTask;

public class AppTimer extends java.util.Timer {  
    
    public final static int REST = 0;
    public final static int WORK = 1;
    public final static int PAUSE = 2;
    final int state;
    AppController controller;
    final long sleepStep = 1000; // in milliseconds
    final int WORK_TIME = 1;
    final int REST_TIME = 1;
    final boolean measureInMinutes = true;
    
    public AppTimer(AppController controller, int state) {
        this.controller = controller;        
        this.state = state;
    }
        
    class TimeCounter extends TimerTask {
        
        long sleepStep;
        long elapsedTime = 0;
        
        public TimeCounter(long totalTime, long sleepStep) {
            this.elapsedTime = totalTime;
            this.sleepStep = sleepStep;          
        }
        
        @Override
        public void run() {
            if (controller.isRunning()) {
                elapsedTime -= sleepStep;
            }
            if (elapsedTime <= 0) {            
               controller.stop();
               elapsedTime = 0;
            }
            controller.updateTime(elapsedTime);
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
        this.schedule(task, sleepStep, sleepStep);
    }
    
}
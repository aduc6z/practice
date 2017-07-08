package com.sample.model;

import com.sample.ui.TimeCounterDialog;

/**
 * Created by dn5970 on 7/7/17.
 */
public class AppState {

    public enum StateEnum {
        WORKING(25), REST(5), PAUSE(0);

        int time;

        StateEnum(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }

    final boolean measureInMinutes = true; // false = measure in seconds

    boolean timerRunning;
    long remainingTime;
    StateEnum state;

    StateChangeListener stateChangeListener;

    public AppState(StateChangeListener stateChangeListener) {
        this.stateChangeListener = stateChangeListener;
        timerRunning = true;
        state = StateEnum.WORKING;
        resetTime();
    }

    public void switchRunningState() {
        timerRunning = !timerRunning;
        stateChangeListener.handleTimerToggle();
    }

    public void changeToNextActiveState() {
        switch (state) {
            case WORKING:
                state = StateEnum.REST;
                break;
            case REST:
                state = StateEnum.WORKING;
                break;
            default:
                System.out.println("Invalid state");
        }
        resetTime();
        stateChangeListener.handleActiveStateChange();
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public StateEnum getState() {
        if (!timerRunning) return StateEnum.PAUSE;
        return state;
    }

    public void resetTime() {
        remainingTime = state.getTime() * (measureInMinutes ? 60 * 1000 : 1000);
    }

    public void reduceRemainingTime(long step) {
//        System.out.println("Reduce remaining time");
        if (remainingTime > step) {
            remainingTime -= step;
            stateChangeListener.handleTimeChange();
        } else {
            remainingTime = 0;
            switchRunningState();
        }
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    @Override
    public String toString() {
        return "AppState{" +
                "measureInMinutes=" + measureInMinutes +
                ", timerRunning=" + timerRunning +
                ", remainingTime=" + remainingTime +
                ", state=" + state +
                '}';
    }
}
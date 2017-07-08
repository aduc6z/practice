package com.sample.model;

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
    long startTime;
    StateEnum state;
    long pauseTime;
    long pausePoint;

    StateChangeListener stateChangeListener;

    public AppState(StateChangeListener stateChangeListener) {
        this.stateChangeListener = stateChangeListener;
        timerRunning = true;
        state = StateEnum.WORKING;
        resetTime();
        pauseTime = 0;
    }

    public void switchRunningState() {
        timerRunning = !timerRunning;
        pauseTime = timerRunning ? 0 : System.currentTimeMillis();
        System.out.println("Switch running state");
        System.out.println("pauseTime = " + pauseTime);
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
        startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getElapsedTime() {
        System.out.println("pauseTime = " + pauseTime);
        return state.getTime() * 60 * 1000 - (System.currentTimeMillis() - startTime)
                + pauseTime;
    }

    @Override
    public String toString() {
        return "AppState{" +
                "measureInMinutes=" + measureInMinutes +
                ", timerRunning=" + timerRunning +
                ", startTime=" + startTime +
                ", state=" + state +
                '}';
    }
}
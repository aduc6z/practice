package com.sample.model;

/**
 * Created by dn5970 on 7/7/17.
 */
public interface StateChangeListener {

    void handleActiveStateChange();

    void handleTimerToggle();

    void handleTimeChange();
}

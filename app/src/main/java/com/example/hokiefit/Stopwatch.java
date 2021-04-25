package com.example.hokiefit;

import android.widget.Button;

/**
 * Stopwatch class
 * Keeps track of time and can increment by seconds
 */
public class Stopwatch {
    private Time time;

    /**
     * constructor that makes a new stopwatch at 0
     */
    public Stopwatch() {
        time = new Time(0, 0, 0);
    }


    public Time getTime() {
        return time;
    }

    public void calc() {
        time.incrementSecond();
    }

    public void reset() {
        time.reset();
    }

}

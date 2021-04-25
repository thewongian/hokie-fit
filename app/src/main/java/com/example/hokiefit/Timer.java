package com.example.hokiefit;



public class Timer {
    private Time time;
    private Time ogTime;

    public Timer(Time time) {
        this.time = time;
        ogTime = time.copy();
    }

    public Timer (int h, int m, int s) {
        time = new Time(h, m, s);
        ogTime = time.copy();

    }

    public boolean isDone() {
        return time.isZero();
    }

    public void decrement() {
        time.decrementSecond();
    }
    public void increment() {
        time.incrementSecond();
    }

    public void reset() {
        time = ogTime.copy();
    }
}

package com.example.hokiefit;

/**
 * Stopwatch class
 * Keeps track of time and can increment by seconds
 */
public class Stopwatch {

    private int sec;
    private int min;
    private int restSec;
    private int restMin;

    public Stopwatch() {
        this(0, 0, 0, 30);
    }

    public Stopwatch(int min, int sec, int restMin, int restSec) {
        this.sec = sec;
        this.min = min;
        this.restSec = restSec;
        this.restMin = restMin;
    }

    public int getSec() {
        return sec;
    }

    public int getMin() {
        return min;
    }

    public int getRestSec() {
        return restSec;
    }

    public int getRestMin() {
        return restMin;
    }

    public void decrementRest() {
        restSec--;
        if (restSec == 0 && restMin == 0) {
            restSec = 1;
            return;
        }
        if (restSec < 0) {
            if (restMin != 0) {
                restSec = 59;
                restMin--;
            }
            else {
                restSec = 0;
            }
        }
    }

    public void reset() {
        sec = 0;
        min = 0;
    }

    public void incrementRest() {
        restSec++;
        if (restSec == 60) {
            if (restMin != 99) {
                restSec = 0;
                restMin++;
            }
            else {
                restSec = 59;
            }
            if (restMin > 99) {
                restMin = 99;
            }

        }
    }

    public void setRestSec(int restSec) {
        this.restSec = restSec;
    }

    public void setRestMin(int restMin) {
        this.restMin = restMin;
    }

    public void incrementTime() {

        sec++;
        if (sec == 60) {
            sec = 0;
            min++;
            if (min > 99) {
                min = 99;
            }
        }

    }


}

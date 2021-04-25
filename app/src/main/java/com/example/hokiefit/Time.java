package com.example.hokiefit;

/**
 * Time class that keeps track of hour minute and second
 */
public class Time {
    private int hour;
    private int minute;
    private int second;

    /**
     * Constructor of Time
     * @param hour
     * hour of the time
     * @param minute
     * minute of the time
     * @param second
     * second of the time
     */
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * gets hours
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * gets minutes
     * @return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * gets seconds
     * @return second
     */
    public int getSecond() {
        return second;
    }

    /**
     * increment the time by 1 second
     */
    public void incrementSecond() {
        second++;
        if (second == 60) {
            second = 0;
            minute++;

        }
        if (minute == 60) {
            minute = 0;
            hour++;
        }
    }

    public void decrementSecond() {
        second--;
        if (second == -1) {
            second = 59;
            minute--;
        }
        if (minute == -1) {
            minute = 0;
            hour --;
        }
    }

    /**
     * resets the time
     */
    public void reset() {
        second = 0;
        minute = 0;
        hour = 0;
    }

    public Time copy() {
        return new Time(hour, minute, second);
    }


    public boolean isZero() {
        return second == 0 && minute == 0 && hour == 0;
    }
    /**
     *
     * @return time in time form
     */
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    /**
     * attempts to parse time from string
     * @param s
     * string being parsed
     * @return a time object parsed from string
     */
    public static Time parseTime(String s) {
        if (s.length() != 8) {
            throw new IllegalArgumentException("Invalid time parameter");
        }
        else {
            int h = Integer.parseInt(s.substring(0, 2));
            int min = Integer.parseInt(s.substring(3, 5));
            int sec = Integer.parseInt(s.substring(6, 8));

            return (new Time(h, min, sec));
        }
    }
}

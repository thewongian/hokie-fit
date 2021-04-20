package com.example.hokiefit;

public class WorkoutTimer {
    private int sets;
    private int workMin;
    private int workSec;
    private int restMin;
    private int restSec;

    public WorkoutTimer(int sets, int workMin, int workSec, int restMin, int restSec) {
        this.sets = sets;
        this.workMin = workMin;
        this.workSec = workSec;
        this.restMin = restMin;
        this.restSec = restSec;
    }

    public WorkoutTimer(int sets, int work, int rest) {
        this.sets = sets;
        this.workMin = work / 60;
        this.workSec = work % 60;
        this.restMin = rest / 60;
        this.restMin = work % 60;
    }

    public int getSets() {
        return sets;
    }

    public int getWorkMin() {
        return workMin;
    }

    public int getWorkSec() {
        return workSec;
    }

    public int getRestMin() {
        return restMin;
    }

    public int getRestSec() {
        return restSec;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("Sets: %d\n", sets));
        buffer.append(String.format("Work: %d:%d\n", workMin, workSec));
        buffer.append(String.format("Rest: %d:%d\n", restMin, restSec));
        return buffer.toString();
    }
}

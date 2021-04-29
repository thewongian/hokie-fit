package com.example.hokiefit;

public class WorkoutTimer {
    private int sets;
    private int workMin;
    private int workSec;
    private int restMin;
    private int restSec;

    public WorkoutTimer() {
        this(4, 10, 0, 0, 30);
    }
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

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setWorkMin(int workMin) {
        this.workMin = workMin;
    }

    public void setWorkSec(int workSec) {
        this.workSec = workSec;
    }

    public void setRestMin(int restMin) {
        this.restMin = restMin;
    }

    public void setRestSec(int restSec) {
        this.restSec = restSec;
    }

    public void incrementSet() {
        sets++;
    }
    public void decrementSet() {
        sets--;
    }
    public void decrementWork() {
        workSec--;
        if (workSec == -1) {
            if (workMin > 0) {
                workSec = 59;
            }
            else {
                workSec = 0;
            }
            workMin--;
            if (workMin < 0) {
                workMin = 0;
            }
        }
    }
    public void incrementWork() {
        workSec++;
        if(workSec == 60) {
            if (workMin < 99) {
                workSec = 0;
            }
            else {
                workSec = 59;
            }
            workMin++;
            if (workMin > 99) {
                workMin = 99;
            }
        }
    }
    public void decrementRest() {
        restSec--;
        if (restSec == -1) {
            if (restMin > 0) {
                restSec = 59;
            }
            else {
                restSec = 0;
            }
            restMin--;
            if (restMin < 0) {
                restMin = 0;
            }
        }
    }
    public void incrementRest() {
        restSec++;
        if(restSec == 60) {
            if (restMin < 99) {
                restSec = 0;
            }
            else {
                restSec = 59;
            }
            restMin++;
            if (restMin > 99) {
                restMin = 99;
            }
        }
    }


    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("Sets: %d\n", sets));
        buffer.append(String.format("Work: %02d:%02d\n", workMin, workSec));
        buffer.append(String.format("Rest: %02d:%02d\n", restMin, restSec));
        return buffer.toString();
    }
}

package com.example.hokiefit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TimerFragment.TimerFragmentListener, StopwatchFragment.StopwatchFragmentListener,
        StopwatchRest.StopwatchRestListener, TimerSelectionFragment.TimerSelectionFragmentListener, FitnessTimer.FitnessTimerListener {
    WorkoutTimer workoutTimer;
    Stopwatch stopwatch;
    FragmentManager fm;
    boolean end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new FitnessFragment()).commit();
    }

    @Override
    public void updateTimer(WorkoutTimer timer) {
        workoutTimer = timer;
    }

    @Override
    public void rest(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;


        FragmentTransaction transaction = fm.beginTransaction();

        transaction.add(R.id.container, new StopwatchRest()).addToBackStack("Rest").commit();
        // start rest Fragment for stopwatch

    }

    @Override
    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    @Override
    public boolean end() {
        return end;
    }

    @Override
    public void startWorkout() {
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.add(R.id.container, new FitnessTimer()).addToBackStack("Rest").commit();
    }

    @Override
    public WorkoutTimer getTimer() {
        return workoutTimer;
    }

    @Override
    public void onWorkFinish() {
        //notification that work finished

    }

    @Override
    public void onRestFinish() {
        //notification that rest finished
    }
}
package com.example.hokiefit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TimerFragment.TimerFragmentListener, StopwatchFragment.StopwatchFragmentListener, StopwatchRest.StopwatchRestListener {
    WorkoutTimer workoutTimer;
    Stopwatch stopwatch;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new FitnessFragment()).commit();
    }

    @Override
    public void startWorkout(WorkoutTimer timer) {
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
    public void onRestEnd(boolean end) {
        //play sound notification
        if (end) {
            //end stopwatch
        }
        else {
            //keep it going


        }
    }
}
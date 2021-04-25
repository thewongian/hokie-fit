package com.example.hokiefit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TimerFragment.TimerFragmentListener {
    WorkoutTimer workoutTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void updateWorkoutTimer(WorkoutTimer timer) {
        workoutTimer = timer;
    }
}
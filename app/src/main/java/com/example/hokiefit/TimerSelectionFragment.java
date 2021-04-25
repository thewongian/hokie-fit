package com.example.hokiefit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 *
 */
public class TimerSelectionFragment extends Fragment implements TimerFragment.TimerFragmentListener {

    WorkoutTimer workoutTimer;
    Button start, presets;
    public TimerSelectionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer_selection, container, false);
        start = (Button) view.findViewById(R.id.start);
        presets = (Button) view.findViewById(R.id.presets);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void updateWorkoutTimer(WorkoutTimer timer) {
        workoutTimer = timer;
    }
}
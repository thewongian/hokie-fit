package com.example.hokiefit;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 *
 */
public class TimerSelectionFragment extends Fragment implements View.OnClickListener {

    WorkoutTimer workoutTimer;
    Button start, presets;
    TimerSelectionFragmentListener listener;
    public TimerSelectionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer_selection, container, false);
        start = (Button) view.findViewById(R.id.start);
        presets = (Button) view.findViewById(R.id.presets);
        start.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (TimerSelectionFragmentListener) context;

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            listener.startWorkout();
        }
    }

    public interface TimerSelectionFragmentListener {
        void startWorkout();
    }
}
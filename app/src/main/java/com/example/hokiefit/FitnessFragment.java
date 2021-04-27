package com.example.hokiefit;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * .
 */
public class FitnessFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private SwitchCompat stopwatchMode;
    private FragmentManager fm;

    public FitnessFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness, container, false);

        //initialize views

        stopwatchMode = (SwitchCompat) view.findViewById(R.id.stopwatchMode);
        stopwatchMode.setOnCheckedChangeListener(this);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.workoutTimerFragment, new TimerSelectionFragment()).commit();
        return view;
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment;
        if (isChecked) {
            //make it stopwatch mode
            fragment = new StopwatchFragment();
        }
        else {
            //make it not stopwatch mode lul
            fragment = new TimerSelectionFragment();

        }
        transaction.replace(R.id.workoutTimerFragment, fragment).commit();


        ;
    }
}
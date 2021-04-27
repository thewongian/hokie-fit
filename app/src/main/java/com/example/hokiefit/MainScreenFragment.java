package com.example.hokiefit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainScreenFragment extends Fragment implements SensorEventListener {
    Button pdmReset;
    TextView walkNum;

    private int nSteps = 0;
    private int nCounterSteps = 0;

    private SensorManager sensorManager;
    private Sensor stepCountSensor;


    private View view;


    public MainScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        //Displays warning message when no sensor is detected
        if(stepCountSensor == null){
            Toast.makeText(getContext(),"ERROR:NO SENSOR DETECTED",Toast.LENGTH_SHORT).show();
        }

        pdmReset = view.findViewById(R.id.btn_resetPdm);
        walkNum = view.findViewById(R.id.tv_stpCount);

        Button breakfastSearch = (Button)view.findViewById(R.id.btn_breakfastSearch);
        Button lunchSearch = (Button)view.findViewById(R.id.btn_lunchSearch);
        Button dinnerSearch = (Button)view.findViewById(R.id.btn_dinnerSearch);
        Button otherMealSearch = (Button)view.findViewById(R.id.btn_otherMealSearch);

        breakfastSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_placeholder, new SearchScreenFragment());
                transaction.commit();
            }
        });

        lunchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_placeholder, new SearchScreenFragment());
                transaction.commit();
            }
        });

        dinnerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_placeholder, new SearchScreenFragment());
                transaction.commit();
            }
        });

        otherMealSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_placeholder, new SearchScreenFragment());
                transaction.commit();
            }
        });

        // Reset pedometer
        pdmReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nSteps = 0;
                nCounterSteps = 0;
                walkNum.setText(Integer.toString(nSteps));

            }
        });


        return view;
    }

    public void onStart() {
        super.onStart();
        if(stepCountSensor !=null){
            sensorManager.registerListener(this,stepCountSensor,SensorManager.SENSOR_DELAY_GAME);
        }
    }
    public void onStop(){
        super.onStop();
        if(sensorManager!=null){
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){

            if (nCounterSteps < 1) {
                nCounterSteps = (int) event.values[0];
            }

            nSteps = (int) event.values[0] - nCounterSteps;
            walkNum.setText(Integer.toString(nSteps));
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
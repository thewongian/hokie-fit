package com.example.hokiefit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Main Screen Fragment
 */
public class MainScreenFragment extends Fragment implements SensorEventListener {
    Button pdmReset;
    TextView walkNum;

    private int nSteps = 0;
    private int nCounterSteps = 0;

    private int maxCal = 2400;
    private int maxCarb = 306;
    private int maxPro = 108;
    private int maxFats = 88;
    private int calorieCheck;

    private SensorManager sensorManager;
    private Sensor stepCountSensor;

    ArrayList<Meal> mealList = new MealList().getMealList();

    private UserData userData;
    private View view;
    private String[] passingArray;


    public MainScreenFragment() {
        this.userData = new UserData();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            nSteps = savedInstanceState.getInt("pdmSave", 0);
        }

        view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        //Displays warning message when no sensor is detected
        if(stepCountSensor == null){
            Toast.makeText(getContext(),"ERROR:NO SENSOR DETECTED",Toast.LENGTH_SHORT).show();
        }

        pdmReset = view.findViewById(R.id.btn_resetPdm);
        walkNum = view.findViewById(R.id.tv_stpCount);

        Button breakfastSearch = view.findViewById(R.id.btn_breakfastSearch);
        Button lunchSearch = view.findViewById(R.id.btn_lunchSearch);
        Button dinnerSearch = view.findViewById(R.id.btn_dinnerSearch);
        Button otherMealSearch = view.findViewById(R.id.btn_otherMealSearch);

        ProgressBar calorieProgress = view.findViewById(R.id.pb_MainCalories);
        ProgressBar carbProgress = view.findViewById(R.id.pb_Carb);
        ProgressBar proProgress = view.findViewById(R.id.pb_Protein);
        ProgressBar fatsProgress = view.findViewById(R.id.pb_Fats);
        TextView calorieTextView = view.findViewById(R.id.tv_RemainingCalories);
        TextView msgCalories = view.findViewById(R.id.mainUserCalorieTextView);

        breakfastSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SearchBreakfastFragment searchFrag = new SearchBreakfastFragment();
                searchFrag.setArguments(bundle);
                transaction.replace(R.id.container, searchFrag);
                transaction.commit();
            }
        });

        lunchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new SearchLunchFragment());
                transaction.commit();
            }
        });

        dinnerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new SearchDinnerFragment());
                transaction.commit();
            }
        });

        otherMealSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new SearchOtherMealFragment());
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

        int calorieRemainder = maxCal - userData.getTotalCalories();

        // When Calorie is positive
        if (calorieRemainder >= 0) {
            calorieTextView.setText(Integer.toString(calorieRemainder));
        }

        // When Calorie is not positive
        else {
            calorieTextView.setText(Integer.toString(Math.abs(calorieRemainder)));
            calorieTextView.setTextColor(Color.parseColor("#CA002A"));
            msgCalories.setText("Over Daily Calorie");
        }

        calorieProgress.setProgress(userData.getTotalCalories());
        carbProgress.setProgress(userData.getTotalCarbs());
        proProgress.setProgress(userData.getTotalProteins());
        fatsProgress.setProgress(userData.getTotalFats());

        if (userData.getTotalCalories() > maxCal) {
            Toast.makeText(getContext(),"Warning: You have exceeded your daily calorie intake",Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pdmSave", nSteps);
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
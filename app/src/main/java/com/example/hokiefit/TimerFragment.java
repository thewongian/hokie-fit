package com.example.hokiefit;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 */
public class TimerFragment extends Fragment implements View.OnClickListener {


    Button setMinus, setPlus, workMinus, workPlus, restMinus, restPlus;
    EditText sets, workMin, workSec, restMin, restSec;
    WorkoutTimer timer;
    Toast toast;
    TimerFragmentListener listener;
    public TimerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        //initialize views
        setMinus = (Button) view.findViewById(R.id.setMinus);
        setPlus = (Button) view.findViewById(R.id.setPlus);
        workMinus = (Button) view.findViewById(R.id.workMinus);
        workPlus = (Button) view.findViewById(R.id.workPlus);
        restMinus = (Button) view.findViewById(R.id.restMinus);
        restPlus = (Button) view.findViewById(R.id.restPlus);




        sets = (EditText) view.findViewById(R.id.sets);
        workMin = (EditText) view.findViewById(R.id.workMin);
        workSec = (EditText) view.findViewById(R.id.workSec);
        restMin = (EditText) view.findViewById(R.id.restMin);
        restSec = (EditText) view.findViewById(R.id.restSec);


        setMinus.setOnClickListener(this);
        setPlus.setOnClickListener(this);
        workMinus.setOnClickListener(this);
        workPlus.setOnClickListener(this);
        restMinus.setOnClickListener(this);
        restPlus.setOnClickListener(this);

        sets.setOnClickListener(this);
        workMin.setOnClickListener(this);
        workSec.setOnClickListener(this);
        restMin.setOnClickListener(this);
        restSec.setOnClickListener(this);

        //initialize member variable with default values
        timer = new WorkoutTimer();
        listener.startWorkout(timer);


        return view;
    }

    public void setTimer(WorkoutTimer workoutTimer) {
        timer = workoutTimer;
        sets.setText(workoutTimer.getSets());
        workMin.setText(String.format("%02d", workoutTimer.getWorkMin()));
        workSec.setText(String.format("%02d", workoutTimer.getWorkSec()));
        restMin.setText(String.format("%02d", workoutTimer.getRestMin()));
        restSec.setText(String.format("%02d", workoutTimer.getRestSec()));
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        listener = (TimerFragmentListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temp;
        switch(v.getId()) {
            case R.id.setMinus:
                if (Integer.parseInt(sets.getText().toString()) > 1) {
                    timer.decrementSet();
                    sets.setText(String.format("%d", timer.getSets()));

                }
                break;
            case R.id.setPlus:
                timer.incrementSet();
                sets.setText(String.format("%d", timer.getSets()));
                break;
            case R.id.workMinus:
                timer.decrementWork();
                workMin.setText(String.format("%02d", timer.getWorkMin()));
                workSec.setText(String.format("%02d", timer.getWorkSec()));
                break;
            case R.id.workPlus:
                timer.incrementWork();
                workMin.setText(String.format("%02d", timer.getWorkMin()));
                workSec.setText(String.format("%02d", timer.getWorkSec()));
                break;
            case R.id.restMinus:
                timer.decrementRest();
                restMin.setText(String.format("%02d", timer.getRestMin()));
                restSec.setText(String.format("%02d", timer.getRestSec()));
                break;
            case R.id.restPlus:
                timer.incrementRest();
                restMin.setText(String.format("%02d", timer.getRestMin()));
                restSec.setText(String.format("%02d", timer.getRestSec()));
                break;
            case R.id.sets:
                temp = timer.getSets();
                timer.setSets(Integer.parseInt(sets.getText().toString()));
                if (timer.getSets() < 1) {
                    sets.setText(String.format("%d", temp));
                    toast.setText("Too few sets! Minimum of 1 set");
                    toast.show();
                    timer.setSets(temp);

                }
                else {
                    sets.setText(String.format("%d", timer.getSets()));
                }
                break;

            case R.id.workMin:
                temp = timer.getWorkMin();
                timer.setWorkMin(Integer.parseInt(workMin.getText().toString()));
                if (timer.getWorkMin() > 99) {
                    workMin.setText(String.format("%02d", temp));
                    toast.setText("Maximum work minutes is 99");
                    toast.show();
                    timer.setWorkMin(temp);
                }
                else if (timer.getWorkMin() < 1 && timer.getWorkSec() < 1) {
                    workMin.setText(String.format("%02d", temp));
                    toast.setText("Minimum work time must be at least 1 second");
                    toast.show();
                    timer.setWorkMin(temp);
                }
                else {
                    workMin.setText(String.format("%02d", timer.getWorkMin()));
                }
                break;
            case R.id.workSec:
                temp = timer.getWorkSec();
                timer.setWorkSec(Integer.parseInt(workSec.getText().toString()));
                if (timer.getWorkSec() > 59) {
                    workSec.setText(String.format("%02d", temp));
                    toast.setText("Maximum work seconds is 59");
                    toast.show();
                    timer.setWorkSec(temp);
                }
                else if (timer.getWorkMin() < 1 && timer.getWorkSec() < 1) {
                    workSec.setText(String.format("%02d", temp));
                    toast.setText("Minimum work time must be at least 1 second");
                    toast.show();
                    timer.setWorkSec(temp);
                }
                else {
                    workSec.setText(String.format("%02d", timer.getWorkSec()));
                }
                break;
            case R.id.restMin:
                temp = timer.getRestMin();
                timer.setRestMin(Integer.parseInt(restMin.getText().toString()));
                if (timer.getRestMin() > 99) {
                    restMin.setText(String.format("%02d", temp));
                    toast.setText("Maximum rest minutes is 99");
                    toast.show();
                    timer.setRestMin(temp);
                }
                else if (timer.getRestMin() < 1 && timer.getRestSec() < 1) {
                    restMin.setText(String.format("%02d", temp));
                    toast.setText("Minimum rest time must be at least 1 second");
                    toast.show();
                    timer.setRestMin(temp);
                }
                else {
                    restMin.setText(String.format("%02d",timer.getRestMin()));
                }
                break;
            case R.id.restSec:
                temp = timer.getRestSec();
                timer.setRestSec(Integer.parseInt(workSec.getText().toString()));
                if (timer.getRestSec() > 59) {
                    restSec.setText(String.format("%02d", temp));
                    toast.setText("Maximum rest seconds is 59");
                    toast.show();
                    timer.setRestSec(temp);
                }
                else if (timer.getRestMin() < 1 && timer.getRestSec() < 1) {
                    restSec.setText(String.format("%02d", temp));
                    toast.setText("Minimum rest time must be at least 1 second");
                    toast.show();
                    timer.setRestSec(temp);
                }
                else {
                    restSec.setText(String.format("%02d", timer.getRestSec()));
                }
                break;
        }

    }
    public interface TimerFragmentListener {
        void startWorkout(WorkoutTimer timer);
    }

}
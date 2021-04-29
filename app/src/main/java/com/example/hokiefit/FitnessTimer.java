package com.example.hokiefit;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 */
public class FitnessTimer extends Fragment implements View.OnClickListener {

    private static final String TAG = "FitnessTimer";
    boolean running;
    Button pause, end;
    TextView timer, title;
    FitnessTimerListener listener;
    TimerAsyncTask task;
    WorkoutTimer workoutTimer;
    int sets;
    int workoutTime;
    int restTime;
    boolean rest;
    public FitnessTimer() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fitness_timer, container, false);
        pause = view.findViewById(R.id.pause);
        end = view.findViewById(R.id.end);
        timer = view.findViewById(R.id.timerText);
        title = view.findViewById(R.id.timerTitle);

        pause.setOnClickListener(this);
        end.setOnClickListener(this);
        task = new TimerAsyncTask();
        running = true;
        task.execute();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (FitnessTimerListener) context;

        workoutTimer = listener.getTimer();
        sets = workoutTimer.getSets();
        workoutTime = workoutTimer.getWorkMin() * 60 + workoutTimer.getWorkSec();
        restTime = workoutTimer.getRestMin() * 60 + workoutTimer.getRestSec();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pause) {
            if (running) {
                pause.setText("Start");
                running = false;
            }
            else {
                pause.setText("Pause");
                running = true;
                task = new TimerAsyncTask();
                task.execute();
            }
        }
        if (v.getId() == R.id.end) {

            task.cancel(true);
            task = null;

            running = false;
            getActivity().onBackPressed();
        }
    }

    private class TimerAsyncTask extends AsyncTask<Void, Integer, Void> {

        /**
         * Runs when progress is update
         *
         * @param ints one or more ints, its just the time values passed from doInBackground though
         */
        @Override
        protected void onProgressUpdate(Integer... ints) {
            super.onProgressUpdate(ints);
            if (ints[0] == -1) {
                running = false;
                task.cancel(true);
                task = null;
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    getActivity().onBackPressed();
                }

            }
            View view = getView();
            if (rest) {
                if (ints[0] != 0) {
                    if (view != null) {
                        view.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                    title.setText("Rest");
                }

            }
            else {
                if (ints[0] != 0) {
                    if (view != null) {
                        view.setBackgroundColor(getResources().getColor(R.color.red));
                    }
                    title.setText("Work");
                }

            }
            timer.setText(String.format("%02d:%02d", ints[0] / 60, ints[0] % 60));
        }



        protected Void doInBackground(Void... params) {
            while (running) {

                System.out.println(workoutTime);
                if (rest) {

                    restTime--;
                    publishProgress(restTime);
                    if (restTime == 0) {
                        rest = false;
                        listener.onRestFinish();
                        restTime = workoutTimer.getRestMin() * 60 + workoutTimer.getRestSec();
                    }
                }
                else {


                    workoutTime--;
                    publishProgress(workoutTime);
                    if (workoutTime == 0 && sets > 1) {
                        sets--;
                        rest = true;
                        listener.onWorkFinish();
                        workoutTime = workoutTimer.getWorkMin() * 60 + workoutTimer.getWorkSec();
                    }
                    if (workoutTime == 0 && sets == 0) {

                        listener.onWorkFinish();
                        publishProgress(-1);
                    }
                }
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    Log.e(TAG, ExceptionUtils.getStackTrace(e));
                }


            }
            return null;
        }
    }
    public interface FitnessTimerListener {
        WorkoutTimer getTimer();
        void onWorkFinish();
        void onRestFinish();
    }


}
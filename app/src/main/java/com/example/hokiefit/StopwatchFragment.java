package com.example.hokiefit;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "StopwatchFragment";
    Button restMinus, restPlus, start, rest, end;
    TextView time;
    EditText restMin, restSec;
    Stopwatch stopwatch;
    Toast toast;
    boolean running;
    boolean runningDuringRest;
    StopwatchAsyncTask asyncTask;
    int originalRestTime;
    StopwatchFragmentListener mListener;
    public StopwatchFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        restMinus = (Button) view.findViewById(R.id.restMinus2);
        restPlus = (Button) view.findViewById(R.id.restPlus2);
        start = (Button) view.findViewById(R.id.stopwatchStart);
        rest = (Button) view.findViewById(R.id.stopwatchRest);
        end = (Button) view.findViewById(R.id.stopwatchEnd);

        restMinus.setOnClickListener(this);
        restPlus.setOnClickListener(this);
        start.setOnClickListener(this);
        rest.setOnClickListener(this);
        end. setOnClickListener(this);

        time = (TextView) view.findViewById(R.id.stopwatchText);

        restMin = (EditText) view.findViewById(R.id.restMin2);
        restSec = (EditText) view.findViewById(R.id.restSec2);

        restMin.setOnClickListener(this);
        restSec.setOnClickListener(this);
        Stopwatch watch = mListener.getStopwatch();
        if (stopwatch == null) {
            stopwatch = new Stopwatch();
        }
        originalRestTime = Integer.parseInt(restMin.getText().toString()) * 60 + Integer.parseInt(restMin.getText().toString());
        if (runningDuringRest) {
            runningDuringRest = false;
            running = true;
        }

        asyncTask = new StopwatchAsyncTask();
        asyncTask.execute();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (StopwatchFragmentListener) context;



        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.restMinus2:
                stopwatch.decrementRest();
                restMin.setText(String.format("%02d", stopwatch.getRestMin()));
                restSec.setText(String.format("%02d", stopwatch.getRestSec()));
                break;
            case R.id.restPlus2:
                stopwatch.incrementRest();
                restMin.setText(String.format("%02d", stopwatch.getRestMin()));
                restSec.setText(String.format("%02d", stopwatch.getRestSec()));
                break;
            case R.id.stopwatchStart:
                if (!running) {
                    running = true;
                    start.setText("Stop");

                    asyncTask = new StopwatchAsyncTask();
                    asyncTask.execute();

                }
                else {
                    start.setText("Start");
                    running = false;
                }

                break;
            case R.id.stopwatchRest:
                if (running) {
                    running = false;
                    runningDuringRest = true;
                }
                start.setText("Start");
                runningDuringRest = false;
                //start rest
                mListener.rest(stopwatch);
                break;
            case R.id.stopwatchEnd:
                end();


                break;
            case R.id.restMin2:
                int temp = stopwatch.getRestMin();
                stopwatch.setRestMin(Integer.parseInt(restMin.getText().toString()));
                if (stopwatch.getRestMin() > 99) {
                    restMin.setText(String.format("%02d", temp));
                    toast.setText("Maximum rest minutes is 99");
                    toast.show();
                    stopwatch.setRestMin(temp);

                }
                else if (stopwatch.getRestMin() < 0 && stopwatch.getRestSec() < 0) {
                    restMin.setText(String.format("%02d", temp));
                    toast.setText("Minimum rest time must be at least 1 second");
                    toast.show();
                    stopwatch.setRestMin(temp);
                }
                else {
                    restMin.setText(String.format("%02d", stopwatch.getRestMin()));

                }
                break;
            case R.id.restSec2:
                temp = stopwatch.getRestSec();
                stopwatch.setRestSec(Integer.parseInt(restSec.getText().toString()));
                if (stopwatch.getRestSec() > 59) {
                    restSec.setText(String.format("%02d",  temp));
                    toast.setText("Maximum rest seconds is 59");
                    toast.show();
                    stopwatch.setRestSec(temp);

                }
                else if (stopwatch.getRestMin() < 0 && stopwatch.getRestSec() < 0) {
                    restSec.setText(String.format("%02d", temp));
                    toast.setText("Minimum rest time must be at least 1 second");
                    toast.show();
                    stopwatch.setRestSec(temp);
                }
                else {
                    restSec.setText(String.format("%02d", stopwatch.getRestSec()));
                }
                break;

        }





    }

    public void end() {
        running = false;
        //log it in activity log

        stopwatch.reset();

        time.setText(String.format("%02d:%02d", stopwatch.getMin(), stopwatch.getSec()));
    }
    private class StopwatchAsyncTask extends AsyncTask<Void, Integer, Void> {

        /**
         * Runs when progress is update
         *
         * @param ints one or more ints, its just the time values passed from doInBackground though
         */
        @Override
        protected void onProgressUpdate(Integer... ints) {
            super.onProgressUpdate(ints);
            String currTime = String.format("%02d:%02d", ints[0], ints[1]);
            time.setText(currTime);
        }



        protected Void doInBackground(Void... params) {
            while (running) {
                stopwatch.incrementTime();
                publishProgress(stopwatch.getMin(), stopwatch.getSec());
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    Log.e(TAG, ExceptionUtils.getStackTrace(e));
                }


            }
            return null;
        }
    }

    public interface StopwatchFragmentListener {
        void rest(Stopwatch stopwatch);

        Stopwatch getStopwatch();

        boolean end();

    }


}
package com.example.hokiefit;

import android.app.Activity;
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
import android.widget.Toast;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.Executor;

/**

 */
public class StopwatchRest extends Fragment implements View.OnClickListener{
    private static final String TAG = "StopwatchRestFragment";
    TextView time;
    Button stop, end;
    StopwatchRestListener listener;
    Stopwatch stopwatch;
    int rest;
    boolean running;
    MyAsyncTask asyncTask;
    public StopwatchRest() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stopwatch_rest, container, false);
        time = (TextView) view.findViewById(R.id.restTime);
        stop = (Button) view.findViewById(R.id.restStopwatchStop);
        end = (Button) view.findViewById(R.id.restEnd);

        stop.setOnClickListener(this);
        end.setOnClickListener(this);
        if (stopwatch == null) {
            Log.e(TAG, "Stopwatch was null");
        }

        rest = stopwatch.getRestMin() * 60 + stopwatch.getRestSec();

        time.setText(String.format("%02d:%02d", stopwatch.getRestMin(), stopwatch.getRestSec()));
        running = true;
        asyncTask = new MyAsyncTask();
        asyncTask.execute();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        listener = (StopwatchRestListener) context;

        stopwatch = listener.getStopwatch();



    }

    @Override
    public void onClick(View v) {
        boolean rest = false;
        switch (v.getId()) {
            case R.id.restStopwatchStop:
                //start stop
                if (asyncTask.getStatus() != AsyncTask.Status.RUNNING) {
                    running = true;
                    asyncTask = new MyAsyncTask();
                    asyncTask.execute();
                }

                else {
                    running = false;
                }

                break;
            case R.id.restEnd:
                running = false;

                rest = true;



                break;
        }
        if (rest) {
            asyncTask.cancel(true);
            asyncTask = null;

            running = false;
            getActivity().onBackPressed();
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onProgressUpdate(Integer... ints) {
            if (ints[0] == -1) {
                running = false;
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    getActivity().onBackPressed();
                }
            }
            time.setText(String.format("%02d:%02d", ints[0] / 60, ints[0] % 60));
        }
        @Override
        protected Void doInBackground(Void... voids) {
            while (running) {
                rest--;
                if (rest == 0) {
                    publishProgress(-1);
                    running = false;
                    break;
                }
                publishProgress(rest);
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                    Log.e(TAG, ExceptionUtils.getStackTrace(e));
                }

            }
            return null;
        }
    }
    public interface StopwatchRestListener {
        Stopwatch getStopwatch();
    }

}
package com.example.hokiefit;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {
    Button restMinus, restPlus, start, rest, end;
    TextView time;
    EditText restMin, restSec;
    Stopwatch stopwatch;
    Toast toast;
    public StopwatchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        stopwatch = new Stopwatch();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
                //start stop watch
                break;
            case R.id.stopwatchRest:
                //start rest
                break;
            case R.id.stopwatchEnd:
                //end the workout and reset
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
}
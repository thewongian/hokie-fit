package com.example.hokiefit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GoalsFragment extends Fragment {

    private ArrayList<String> goals;
    private ArrayAdapter<String> goalsAdapter;
    private ListView lvGoals;
    private Button addItem;
    private EditText newGoal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.goals_fragment, container, false);
        setRetainInstance(true);

        lvGoals = v.findViewById(R.id.goals_list);
        goals = new ArrayList<String>();
        readItems(getContext());
        addItem = v.findViewById(R.id.goals_add_item);
        newGoal = v.findViewById(R.id.goals_et);
        goalsAdapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, goals);
        lvGoals.setAdapter(goalsAdapter);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalsAdapter.add(newGoal.getText().toString());
                newGoal.setText("");
                writeItems(getContext());
            }
        });

        setupListViewListener(getContext());

        return v;
    }

    private void setupListViewListener(Context context) {
        lvGoals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                goals.remove(i);
                goalsAdapter.notifyDataSetChanged();
                writeItems(context);
                return true;
            }
        });
    }

    private void readItems(Context context) {
        File filesDir = getContext().getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            goals = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            goals = new ArrayList<String>();
        }
    }

    private void writeItems(Context context) {
        File filesDir = context.getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, goals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.hokiefit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Nutrition Search Fragment
 */
public class SearchOtherMealFragment extends Fragment implements OtherMealListAdapter.ItemClickListener {

    MealList mealList = new MealList();
    public Meal[] userMealData = new Meal[4];
    private int mealTarget = 0;

    public int getMealTarget() {
        return mealTarget;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_screen, container, false);

        Button toMainScreen = (Button)view.findViewById(R.id.btn_backToMain);
        SearchView searchView = (SearchView)view.findViewById(R.id.sv_SearchBar);

        // Load bundle if there is one
        if (getArguments() != null) {
            mealTarget = getArguments().getInt("key");
        }

        // Button to main screen
        toMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_placeholder, new MainScreenFragment());
                transaction.commit();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        OtherMealListAdapter listAdapter = new OtherMealListAdapter(userMealData, this);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Search view implementation
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            // Pressing search after typing
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // Searching as user types on search bar
            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return view;
    }

    @Override
    public void onItemClick(Meal meal) {
    }
}
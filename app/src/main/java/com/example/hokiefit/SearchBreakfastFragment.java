package com.example.hokiefit;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

/**
 * Nutrition Search Fragment
 */
public class SearchBreakfastFragment extends Fragment implements BreakfastListAdapter.ItemClickListener {

    MealList mealList = new MealList();
    public Meal[] userMealData = new Meal[4];

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_screen, container, false);

        Button toMainScreen = (Button)view.findViewById(R.id.btn_backToMain);
        SearchView searchView = (SearchView)view.findViewById(R.id.sv_SearchBar);

        // Button to main screen
        toMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new MainScreenFragment());
                transaction.commit();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        BreakfastListAdapter listAdapter = new BreakfastListAdapter(userMealData, this);
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
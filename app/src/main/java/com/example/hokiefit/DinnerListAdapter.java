package com.example.hokiefit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for recycler view
 */
public class DinnerAdapter extends RecyclerView.Adapter implements Filterable {
    ArrayList<Meal> mealList = new MealList().getMealList();
    ArrayList<Meal> mealListAll = new MealList().getMealList();
    private Meal[] userMealList;
    private ItemClickListener clickListener;

    @Override
    public Filter getFilter() {
        return null;
    }

    // Handles clicking interaction
    public interface ItemClickListener {
        public void onItemClick(Meal meal);
    }

    public DinnerAdapter(Meal[] list, ItemClickListener clickListener) {
        this.userMealList = list;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_meal, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();

                clickListener.onItemClick(userMealList[0] = mealList.get(pos));

                Bundle bundle = new Bundle();
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                MainScreenFragment mainScreenFragment = new MainScreenFragment();
                mainScreenFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_placeholder, mainScreenFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mealName, mealServer, mealCalories;
        private ImageView mealImage;

        public ListViewHolder(View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tv_mealName);
            mealServer = itemView.findViewById(R.id.tv_mealServer);
            mealCalories = itemView.findViewById(R.id.tv_mealCalories);
            mealImage = itemView.findViewById(R.id.iv_mealPic);
            itemView.setOnClickListener(this);
        }


        public void bindView(int position) {
            mealName.setText(mealList.get(position).getName());
            mealServer.setText(mealList.get(position).getServer());
            mealCalories.setText(String.valueOf(mealList.get(position).getCalories() + " Calories"));
            mealImage.setImageResource(MealList.mealPic[mealList.get(position).getNum()]);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

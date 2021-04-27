package com.example.hokiefit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {
    com.example.hokiefit.MealList mealList = new com.example.hokiefit.MealList();
    com.example.hokiefit.Meal[] mealTable = mealList.getMealArray();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_meal, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mealTable.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mealName;
        private ImageView mealImage;


        public ListViewHolder(View itemView) {
            super(itemView);
            mealName = (TextView) itemView.findViewById(R.id.tv_mealName);
            //mealServer = (TextView) itemView.findViewById(R.id.tv_mealServer);
            mealImage = (ImageView) itemView.findViewById(R.id.iv_mealPic);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mealName.setText(mealTable[position].getName());
            //mealServer.setText(mealTable[position].getName());
            mealImage.setImageResource(com.example.hokiefit.MealList.mealPic[position]);
        }

        public void onClick(View view) {

        }
    }
}

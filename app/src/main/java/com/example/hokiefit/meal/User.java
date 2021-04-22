package com.example.hokiefit.meal;

import com.example.hokiefit.meal.Meal;

import java.util.List;

public class User {
    private int UserID;
    private int currentWeight;
    private int weightGoal;
    private int calorieLimit;
    // 0: protein, 1: carbs, 2: fats
    private float[] nutritionGoals = new float[3];
    private int height;
    private int dob;
    private String name;
    private String username;
    private List<Meal> mealList;

    public User(int userID, String name, String username) {
        UserID = userID;
        this.name = name;
        this.username = username;
    }

    public int getUserID() {
        return UserID;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getWeightGoal() {
        return weightGoal;
    }

    public int getCalorieLimit() {
        return calorieLimit;
    }

    public float[] getNutritionGoals() {
        return nutritionGoals;
    }

    public int getHeight() {
        return height;
    }

    public int getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public List<Meal> getMealList() {
        return mealList;
    }
}

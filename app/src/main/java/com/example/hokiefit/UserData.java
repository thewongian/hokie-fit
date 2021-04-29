package com.example.hokiefit;

public class UserData {

    public static Meal[] userMealList = new Meal[4];
    private Meal[] mealData;

    public UserData() {
        mealData = userMealList;
    }

    public int getTotalCalories() {
        int totalCal = 0;
        for (Meal meal: mealData) {
            if(meal != null) {
                totalCal = totalCal + meal.getCalories();
            }
        }
        return totalCal;
    }

    public int getTotalCarbs() {
        int totalCarbs = 0;
        for (Meal meal: mealData) {
            if (meal != null){
                totalCarbs = (int) (totalCarbs + meal.getCarbs());
            }
        }
        return totalCarbs;
    }

    public int getTotalProteins() {
        int totalProteins = 0;
        for (Meal meal: mealData) {
            if (meal != null){
                totalProteins = (int) (totalProteins + meal.getProteins());
            }
        }
        return totalProteins;
    }

    public int getTotalFats() {
        int totalFats = 0;
        for (Meal meal: mealData) {
            if (meal != null){
                totalFats = (int) (totalFats + meal.getProteins());
            }
        }
        return totalFats;
    }
}

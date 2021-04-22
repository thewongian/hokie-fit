package com.example.hokiefit.meal;

public class Food {
    private String name;
    private int calories;
    private float protein;
    private float carbs;
    private float fats;


    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public Food(String name, int calories, float protein, float carbs, float fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public float getProtein() {
        return protein;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getFats() {
        return fats;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public String toString() {
        return String.format("%s\nCalories: %d\nProtein (g): %.2f\nCarbs (g): %.2f\nFats (g): %.2f", name, calories, protein, carbs, fats);
    }
}

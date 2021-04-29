package com.example.hokiefit;

/**
 * Meal class for nutritional data
 */
public class Meal {
    private String name, server;
    private final double carbs, proteins, fats;
    private int calories, number;

    public Meal(String name, String server, double carbs, double proteins, double fats, int num) {
        this.name = name;
        this.server = server;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.calories = (int)Math.round((carbs * 4) + (proteins * 4) + (fats * 9));
        this.number = num;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public int getCalories() {
        return calories;
    }

    public int getNum() {
        return number;
    }

}

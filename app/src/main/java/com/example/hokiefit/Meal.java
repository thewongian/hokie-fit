package com.example.hokiefit;

public class Meal {
    private String name, server;
    private final double carbs, proteins, fats, calories;

    public Meal(String name, String server, double carbs, double proteins, double fats) {
        this.name = name;
        this.server = server;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.calories = (carbs * 4) + (proteins * 4) + (fats * 9);
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

    public double getCalories() {
        return calories;
    }

}

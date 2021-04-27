package com.example.hokiefit;

/**
 * Store food nutritional data as arraylist
 */
public class MealList {

    Meal mealOne = new Meal("Chicken Shawarma", "D2", 27.6,18.4,16.2 );
    Meal mealTwo = new Meal("Maryland Crab Stuffed Ravioli", "D2", 20.6, 7, 13.6);
    Meal mealThree = new Meal("Cheese Quesadilla", "Tazon", 68.8, 24.7, 40.3);
    Meal mealFour = new Meal("Blue Ridge Barbeque Pork", "Blue Ridge BBQ", 5.2, 23.4, 24.3);
    Meal mealFive = new Meal("Vegetable Stirfry", "Wan", 74.4, 25.6, 25);
    Meal mealSix = new Meal("Tandoori Chicken", "Variabowl", 4.6, 22.3, 17.5);
    Meal mealSeven = new Meal("Bacon Cheese Burger", "Pops", 45.2, 31.4, 49.5);
    Meal mealEight = new Meal("Philly Cheese Steak", "Pops", 32.4, 24.3, 33.1);
    Meal mealNine = new Meal("Frank's Deli Meat Sub", "Frank's", 58.9, 31.7, 50);
    Meal mealTen = new Meal("Wine Marinated Flank Steak", "Dish", 0.8, 16.2, 15.8);

    private Meal[] mealArray = {mealOne, mealTwo, mealThree, mealFour, mealFive, mealSix, mealSeven, mealEight, mealNine, mealTen};

    public Meal[] getMealArray() {
        return mealArray.clone();
    }

    public static int[] mealPic = new int[]{
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger,
            R.drawable.hamburger
    };
}

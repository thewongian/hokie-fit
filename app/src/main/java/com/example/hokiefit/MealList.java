package com.example.hokiefit;

import java.util.ArrayList;

/**
 * Store food nutritional data as arraylist
 */
public class MealList {

    private Meal[] mealArray;

    Meal mealOne = new Meal("Chicken Shawarma", "D2", 27.6,18.4,16.2, 0);
    Meal mealTwo = new Meal("Maryland Crab Stuffed Ravioli", "D2", 20.6, 7, 13.6,1);
    Meal mealThree = new Meal("Cheese Quesadilla", "Tazon", 68.8, 24.7, 40.3,2);
    Meal mealFour = new Meal("Barbeque Pork", "Blue Ridge BBQ", 5.2, 23.4, 24.3,3);
    Meal mealFive = new Meal("Vegetable Stir Fry", "Wan", 74.4, 25.6, 25,4);
    Meal mealSix = new Meal("Tandoori Chicken", "Variabowl", 4.6, 22.3, 17.5,5);
    Meal mealSeven = new Meal("Bacon Cheese Burger", "Pops", 45.2, 31.4, 49.5,6);
    Meal mealEight = new Meal("Philly Cheese Steak", "Pops", 32.4, 24.3, 33.1,7);
    Meal mealNine = new Meal("Frank's Deli Meat Sub", "Frank's", 58.9, 31.7, 50,8);
    Meal mealTen = new Meal("Wine Marinated Flank Steak", "Dish", 0.8, 16.2, 15.8,9);

    ArrayList<Meal> mealList;

    public MealList() {
        mealArray = new Meal[]{mealOne, mealTwo, mealThree, mealFour, mealFive, mealSix, mealSeven, mealEight, mealNine, mealTen};
        mealList = new ArrayList<Meal>();

        for (int i = 0; i < 10; i++) {
            mealList.add(mealArray[i]);
        }
    }

    public ArrayList<Meal> getMealList() {
        return mealList;
    }

    public static int[] mealPic = new int[]{
            R.drawable.chicken_shawarma,
            R.drawable.maryland_crab_stuffed_ravioli,
            R.drawable.cheese_quesadilla,
            R.drawable.blue_ridge_barbeque_pork,
            R.drawable.vegetable_stir_fry,
            R.drawable.tandoori_chicken,
            R.drawable.bacon_cheese_burger,
            R.drawable.philly_cheese_steak,
            R.drawable.deli_meat_sub,
            R.drawable.red_wine_marinated_flank_steak
    };
}

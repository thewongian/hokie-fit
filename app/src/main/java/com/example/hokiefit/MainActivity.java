package com.example.hokiefit;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity implements TimerFragment.TimerFragmentListener, StopwatchFragment.StopwatchFragmentListener,
        StopwatchRest.StopwatchRestListener, TimerSelectionFragment.TimerSelectionFragmentListener, FitnessTimer.FitnessTimerListener, NavigationView.OnNavigationItemSelectedListener {
    WorkoutTimer workoutTimer;
    Stopwatch stopwatch;
    FragmentManager fm;
    boolean end;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProfileFragment profileFragment;
    private GoalsFragment goalsFragment;
    private FitnessFragment fitnessFragment;
    private MainScreenFragment mainScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MainScreenFragment());
        fragmentTransaction.commit();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        profileFragment = new ProfileFragment();
        goalsFragment = new GoalsFragment();
        fitnessFragment = new FitnessFragment();
        mainScreenFragment = new MainScreenFragment();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void updateTimer(WorkoutTimer timer) {
        workoutTimer = timer;
    }

    @Override
    public void rest(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;


        FragmentTransaction transaction = fm.beginTransaction();

        transaction.add(R.id.container, new StopwatchRest()).addToBackStack("Rest").commit();
        // start rest Fragment for stopwatch

    }

    @Override
    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    @Override
    public boolean end() {
        return end;
    }

    @Override
    public void startWorkout() {
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.add(R.id.container, new FitnessTimer()).addToBackStack("Rest").commit();
    }

    @Override
    public WorkoutTimer getTimer() {
        return workoutTimer;
    }

    @Override
    public void onWorkFinish() {
        //notification that work finished

    }

    @Override
    public void onRestFinish() {
        //notification that rest finished


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MainScreenFragment());
        fragmentTransaction.commit();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        profileFragment = new ProfileFragment();
        goalsFragment = new GoalsFragment();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.toString()) {
            case "Profile":
                drawerLayout.closeDrawer(Gravity.LEFT);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, profileFragment)
                        .commit();

                break;
            case "My Goals":
                drawerLayout.closeDrawer(Gravity.LEFT);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, goalsFragment)
                        .commit();
                break;

            case "Nutrition":
                drawerLayout.closeDrawer(Gravity.LEFT);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, mainScreenFragment)
                        .commit();
                break;
            case "Fitness":
                drawerLayout.closeDrawer(Gravity.LEFT);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fitnessFragment)
                        .commit();
                break;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
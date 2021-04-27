package com.example.hokiefit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Button to display fragment expansion functionality
         * TODO: Remove button from this class and XML
         * TODO: Set animation for opening the fragment
         */
        Button temp = findViewById(R.id.expand_settings_frag);
        ConstraintLayout mainLayout = findViewById(R.id.main_layout);
        // set up settings fragment
        SettingsFragment settingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                )
                .replace(R.id.container, settingsFragment)
                .hide(settingsFragment)
                .commit();

        View.OnClickListener ClickListener = v -> {
            /*
             * display the fragment on button click (no toggle functionality)
             */
            if (v.getId() == temp.getId()) {
                getSupportFragmentManager().beginTransaction()
                        .show(settingsFragment)
                        .commit();
            }
            if (v.getId() == mainLayout.getId() && v.getId() != settingsFragment.getId()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(settingsFragment)
                        .commit();
            }
        };

        temp.setOnClickListener(ClickListener);
        mainLayout.setOnClickListener(ClickListener);
    }
}
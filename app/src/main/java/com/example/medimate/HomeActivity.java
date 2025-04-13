package com.example.medimate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    String[] item = {
            "You should go get a pregnancy test",
            "You should go get a mammography"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Calendar Button
        Button clicker = findViewById(R.id.button1);
        clicker.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Calendar.class);
            startActivity(intent);
        });

        // Dropdown Toggle
        Button dropdownButton = findViewById(R.id.dropdownButton);
        LinearLayout dropdownLayout = findViewById(R.id.dropdownLayout);
        dropdownLayout.setVisibility(View.GONE); // hidden by default

        dropdownButton.setOnClickListener(v -> {
            if (dropdownLayout.getVisibility() == View.GONE) {
                dropdownLayout.setVisibility(View.VISIBLE);
            } else {
                dropdownLayout.setVisibility(View.GONE);
            }
        });

        // Populate dropdown items
        LinearLayout recommendationContainer = findViewById(R.id.recommendationContainer);
        recommendationContainer.removeAllViews();

        for (String option : item) {
            Button recBtn = new Button(this);
            recBtn.setText(option);
            recBtn.setBackgroundResource(R.drawable.button);
            recBtn.setOnClickListener(view -> {
                dropdownButton.setText(option);
                Toast.makeText(this, "Selected: " + option, Toast.LENGTH_SHORT).show();
                dropdownLayout.setVisibility(View.GONE);
            });
            recommendationContainer.addView(recBtn);
        }

        // ✅ Fix: bind the newEventBtn before setting listener
        Button newEventBtn = findViewById(R.id.newEventButton);
        newEventBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, WeekViewActivity.class);
            intent.putExtra("openNewEvent", true); // pass flag
            startActivity(intent);
        });

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

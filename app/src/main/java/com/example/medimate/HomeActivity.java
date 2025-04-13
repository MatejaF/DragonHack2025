package com.example.medimate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
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

        // Recommendations Dropdown Button
        Button dropdownButton = findViewById(R.id.dropdownButton);
        dropdownButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(HomeActivity.this, dropdownButton);
            for (String option : item) {
                popupMenu.getMenu().add(option);
            }

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                String selectedItem = menuItem.getTitle().toString();
                dropdownButton.setText(selectedItem);
                Toast.makeText(HomeActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                return true;
            });

            popupMenu.show();
        });

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

package com.example.medimate;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medimate.databinding.ActivityHomepageBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(v -> {});
        binding.button2.setOnClickListener(v -> {});
        binding.button3.setOnClickListener(v -> {});
        binding.button4.setOnClickListener(v -> {});
    }
}
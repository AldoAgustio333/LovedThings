package com.example.things.Activity.PaymentMethode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.things.databinding.ActivityDanaBinding;

public class DanaActivity extends AppCompatActivity {

    private ActivityDanaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDanaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}
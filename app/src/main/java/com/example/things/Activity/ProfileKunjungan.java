package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.things.databinding.ActivityProfileKunjunganBinding;

public class ProfileKunjungan extends AppCompatActivity {

    private ActivityProfileKunjunganBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileKunjunganBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String nama = intent.getStringExtra(DetailProduk.EXTRA_NAMA);
        String email = intent.getStringExtra(DetailProduk.EXTRA_EMAIL);
        String nohp = intent.getStringExtra(DetailProduk.EXTRA_NOHP);
        String img = intent.getStringExtra(DetailProduk.EXTRA_IMG);
        String alamat = intent.getStringExtra(DetailProduk.EXTRA_ALAMAT);

        binding.addAlamat.setText(alamat);
        binding.adddHp.setText(nohp);
        binding.addEmail.setText(email);
        binding.nama.setText(nama);
        Glide.with(binding.imgUser).load(img).centerCrop().into(binding.imgUser);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.things.Fragment.Dashboard;
import com.example.things.Fragment.ProdukTerjualFragment;
import com.example.things.Fragment.TambahProduk;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityPenjualanBinding;

public class PenjualanActivity extends AppCompatActivity {

    private ActivityPenjualanBinding binding;
    SessionManager sessionManager;
    private TambahProduk tambahProduk = new TambahProduk();
    private ProdukTerjualFragment produkTerjualFragment = new ProdukTerjualFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPenjualanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, tambahProduk).commit();
        sessionManager = new SessionManager(this);

        AllButton();

    }

    private void AllButton() {
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PenjualanActivity.this, SearchActtivity.class);
                startActivity(intent);
            }
        });

        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PenjualanActivity.this, DaftarChatActivity.class);
                startActivity(intent);
            }
        });

        binding.btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PenjualanActivity.this, KeranjangActivity.class);
                startActivity(intent);
            }
        });
        binding.btnProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTabs( binding.btnProduk);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, tambahProduk).commit();
            }
        });

        binding.btnTerjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTabs( binding.btnTerjual);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, produkTerjualFragment).commit();
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void switchTabs(TextView selectedTextView) {
        // Reset background pada kedua TextView
        binding.btnProduk.setBackgroundResource(R.drawable.btn_fill2);
        binding.btnTerjual.setBackgroundResource(R.drawable.btn_fill2);

        // Terapkan animasi slide ke TextView yang dipilih
        selectedTextView.setBackgroundResource(R.drawable.btn_fill);
    }
}
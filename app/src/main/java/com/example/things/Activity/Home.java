package com.example.things.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.things.Fragment.Dashboard;
import com.example.things.Presenter.DaftarChatPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityHomeBinding;


public class Home extends AppCompatActivity{

    SessionManager sessionManager;
    private ActivityHomeBinding binding;
    private Dashboard dashboard = new Dashboard();
//    private Pencarian pencarian = new Pencarian();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, dashboard).commit();
        sessionManager = new SessionManager(this);



        String type = sessionManager.getUserDetail().get(SessionManager.TYPE);
        String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);

        ALlButton(nama);


        if (sessionManager.isLogin() == false){
            //kita membuat sebuah method
            moveToLogin();
        }

        if (type.equals("user"))
        {
//            binding.tamplateBottomNav.setVisibility(View.GONE);
        }

        binding.btnPembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PesananActivity.class);
                startActivity(intent);
            }
        });



    }

    private void ALlButton(String nama) {
        binding.btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Home.this, nama, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SearchActtivity.class);
                startActivity(intent);
            }
        });
        binding.btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, KeranjangActivity.class);
                startActivity(intent);
            }
        });
        binding.btnPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PenjualanActivity.class);
                startActivity(intent);
            }
        });
        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, DaftarChatActivity.class);
                startActivity(intent);
//                Toast.makeText(Home.this, "Maaf layanan ini belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void moveToLogin() {
        Intent intent = new Intent(Home.this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

}
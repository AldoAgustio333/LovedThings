package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.things.Adapter.KategoriAdapter;
import com.example.things.Model.KategoriModel;
import com.example.things.R;
import com.example.things.databinding.ActivityKategoriBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    private ActivityKategoriBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKategoriBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inisialisasiDaftarKategori();
        AllButton();
    }

    private void AllButton() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriActivity.this, SearchActtivity.class);
                startActivity(intent);
            }
        });
    }

    private void inisialisasiDaftarKategori() {
        // Misalkan, definisikan array kategori Anda
        KategoriModel[] daftarKategori = new KategoriModel[] {
                new KategoriModel("Atasan", "Wanita", R.drawable.atasancewe),
                new KategoriModel("Bawahan", "Wanita", R.drawable.bawahancw),
                new KategoriModel("Sepatu/Sandal", "Wanita", R.drawable.sepatucw),
                new KategoriModel("Tas", "Wanita", R.drawable.tascw),
                new KategoriModel("Aksesoris", "Wanita", R.drawable.aksesoriscw),
                new KategoriModel("Atasan", "Pria", R.drawable.atasancowo),
                new KategoriModel("Bawahan", "Pria", R.drawable.bawahancowo),
                new KategoriModel("Sepatu/Sandal", "Pria", R.drawable.sepatucowo),
                new KategoriModel("Tas", "Pria", R.drawable.tascowo),
                new KategoriModel("Aksesoris", "Pria", R.drawable.jamcowo)
                // tambahkan kategori lainnya
        };

        // Inisialisasi adapter untuk pria dan wanita
        KategoriAdapter adapterPria = new KategoriAdapter(getApplicationContext(), filterByType(Arrays.asList(daftarKategori), "Pria"));
        KategoriAdapter adapterWanita = new KategoriAdapter(getApplicationContext(), filterByType(Arrays.asList(daftarKategori), "Wanita"));


        binding.recKategoriPria.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recKategoriWanita.setLayoutManager(new GridLayoutManager(this, 2));

        binding.recKategoriPria.setAdapter(adapterPria);
        binding.recKategoriWanita.setAdapter(adapterWanita);
    }

    private List<KategoriModel> filterByType(List<KategoriModel> asList, String type) {
        List<KategoriModel> filteredList = new ArrayList<>();

        for (KategoriModel model : asList) {
            if (model.getType().equals(type)) {
                filteredList.add(model);
            }
        }

        return filteredList;
    }
}
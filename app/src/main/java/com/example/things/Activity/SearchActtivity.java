package com.example.things.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Interface.SearchInterface;
import com.example.things.Model.ProdukModel;
import com.example.things.Presenter.SearchPresenter;
import com.example.things.databinding.ActivitySearchActtivityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActtivity extends AppCompatActivity implements SearchInterface {

    private ActivitySearchActtivityBinding binding;
    private SearchPresenter presenter;
    ProdukAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchActtivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new SearchPresenter(this);

        AllButton();


    }

    private void AllButton() {
        binding.addSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Nothing to do here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Handle text changes, perform search
                String searchText = charSequence.toString().trim();
                searchData(searchText);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Nothing to do here
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void searchData(String searchText) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProdukModel> searchResults = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProdukModel produkModel = dataSnapshot.getValue(ProdukModel.class);

                    // Cek apakah produkModel tidak null
                    if (produkModel != null) {
                        // Cek apakah produkModel sesuai dengan kriteria pencarian
                        if (produkModel.getKategori() != null && produkModel.getKategori().toLowerCase().contains(searchText.toLowerCase())) {
                            searchResults.add(produkModel);
                        } else if (produkModel.getUkuran() != null && produkModel.getUkuran().toLowerCase().contains(searchText.toLowerCase())) {
                            searchResults.add(produkModel);
                        } else if (produkModel.getMerek() != null && produkModel.getMerek().toLowerCase().contains(searchText.toLowerCase())) {
                            searchResults.add(produkModel);
                        }
                    }
                }

                // Handle hasil pencarian
                handleSearchResults(searchResults);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
}

    private void handleSearchResults(ArrayList<ProdukModel> searchResults) {
        adapter = new ProdukAdapter(getApplicationContext(), searchResults);

        binding.recSearch.setAdapter(adapter);
        binding.recSearch.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateSearchResults(ArrayList<ProdukModel> searchResults) {

    }
}
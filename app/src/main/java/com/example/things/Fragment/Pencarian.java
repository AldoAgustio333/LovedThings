package com.example.things.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Model.ProdukModel;
import com.example.things.R;
import com.example.things.databinding.FragmentPencarianBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pencarian extends Fragment {

    static FragmentPencarianBinding binding;
    private ProdukAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Periksa apakah ada argumen yang dikirimkan
        if (getArguments() != null) {
            String searchQuery = getArguments().getString("search_query");
            if (searchQuery != null && !searchQuery.isEmpty()) {
                performSearch(searchQuery);
            }
        }
    }

    private void performSearch(String searchQuery) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        // Query untuk mencari data berdasarkan kata kunci pada kategori
        Query query = productsRef.orderByChild("kategori")
                .startAt(searchQuery)
                .endAt(searchQuery + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProdukModel> searchResults = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProdukModel produkModel = dataSnapshot.getValue(ProdukModel.class);
                    searchResults.add(produkModel);
                }

                displaySearchResults(searchResults);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPencarianBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        return view;
    }


    public void displaySearchResults(ArrayList<ProdukModel> searchResults) {
        if (adapter == null) {
            adapter = new ProdukAdapter(getContext(), searchResults);
            binding.recKategoriPeria.setAdapter(adapter);
        } else {
            adapter.clearData(); // Bersihkan data sebelum menambahkan yang baru
            adapter.addData(searchResults);
            adapter.notifyDataSetChanged();
        }
    }
}
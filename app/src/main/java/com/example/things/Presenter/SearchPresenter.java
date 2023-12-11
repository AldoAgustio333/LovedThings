package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.SearchInterface;
import com.example.things.Model.ProdukModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchPresenter {

    SearchInterface view;

    public SearchPresenter(SearchInterface view) {
        this.view = view;
    }

    public void searchData(String searchText) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        // Query untuk mencari data berdasarkan kata kunci pada kategori
        Query query = productsRef.orderByChild("kategori")
                .startAt(searchText)
                .endAt(searchText + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ProdukModel> searchResults = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProdukModel produkModel = dataSnapshot.getValue(ProdukModel.class);
                    searchResults.add(produkModel);
                }

                // Update tampilan dengan hasil pencarian
                view.updateSearchResults(searchResults);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}

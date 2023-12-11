package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.DaftarProdukInterface;
import com.example.things.Model.ImageSliderModel;
import com.example.things.Model.ProdukModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DaftarProdukPresenter {

    DaftarProdukInterface view;
    FirebaseDatabase database;

    public DaftarProdukPresenter(DaftarProdukInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance();
    }


    public void All() {
       database.getReference().child("DaftarProduk").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NotNull DataSnapshot snapshot) {
               ArrayList<ProdukModel> produk = new ArrayList<>();

               for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                   ProdukModel model = dataSnapshot.getValue(ProdukModel.class);
                   produk.add(model);
               }

               view.daftarProdukResponse(produk);
           }

           @Override
           public void onCancelled(@NotNull DatabaseError error) {
               view.messageError();
           }
       });

    }

    public void getProductsByCategory(String category) {
        database.getReference().child("DaftarProduk")
                .orderByChild("kategori")
                .equalTo(category)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot snapshot) {
                        ArrayList<ProdukModel> produk = new ArrayList<>();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ProdukModel model = dataSnapshot.getValue(ProdukModel.class);
                            produk.add(model);
                        }

                        view.daftarProdukResponse(produk);
                    }

                    @Override
                    public void onCancelled(@NotNull DatabaseError error) {
                        view.messageError();
                    }
                });
    }
}

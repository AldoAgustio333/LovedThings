package com.example.things.Presenter;

import com.example.things.Interface.ProdukTerjualInterface;
import com.example.things.Model.ProdukModel;
import com.example.things.Model.ProdukTerjualModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProdukTerjualPresenter {
    ProdukTerjualInterface view;
    DatabaseReference database;
    public ProdukTerjualPresenter(ProdukTerjualInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void getlistProduk(String uid) {
        database.child(uid).child("ProdukTerjual").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                ArrayList<ProdukTerjualModel> produk = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProdukTerjualModel model = dataSnapshot.getValue(ProdukTerjualModel.class);
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

package com.example.things.Presenter;

import com.example.things.Interface.TambahProdukInterface;
import com.example.things.Model.ProdukModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TambahProdukPresenter {

    TambahProdukInterface view;
    FirebaseDatabase database;

    public TambahProdukPresenter(TambahProdukInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance();
    }


    public void getlistProduk(String uid) {
//        database.getReference().child("DaftarProduk")
//                .orderByChild("uid")
//                .equalTo(uid)
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NotNull DataSnapshot snapshot) {
//                        ArrayList<ProdukModel> produk = new ArrayList<>();
//
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            ProdukModel model = dataSnapshot.getValue(ProdukModel.class);
//                            produk.add(model);
//                        }
//
//                        view.daftarProdukResponse(produk);
//                    }
//
//                    @Override
//                    public void onCancelled(@NotNull DatabaseError error) {
//                        view.messageError();
//                    }
//                });
        database.getReference("Users").child(uid).child("Penjualan").addValueEventListener(new ValueEventListener() {
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

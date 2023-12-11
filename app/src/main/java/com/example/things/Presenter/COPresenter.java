package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.COInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class COPresenter {

    COInterface view;

    public COPresenter(COInterface view) {
        this.view = view;
    }


    public void chackProduk(String lokasi, String hargaKirim, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        databaseReference.orderByChild("idP").equalTo(idP).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    // Data ditemukan
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Ambil data dari DataSnapshot

                        view.reviewDataProduk(idUser, hargaKirim, lokasi,merek, kategori, des, harga, idP, uidPenjual, imgP, ukuran, namaPenjual, uidPenjual, alamatPenjual, nohpPenjual, fotoPenjual);

                    }

                } else {
                    // Data tidak ditemukan
                    view.dataIsGone(idUser, idP);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }
        });
    }
}

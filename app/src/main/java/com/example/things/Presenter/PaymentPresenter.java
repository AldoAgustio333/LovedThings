package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.PaymentInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentPresenter {

    PaymentInterface view;

    public PaymentPresenter(PaymentInterface view) {
        this.view = view;
    }


    public void findProduk(String jml, String hargaKirim, String method, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        databaseReference.orderByChild("idP").equalTo(idP).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    // Data ditemukan
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Ambil data dari DataSnapshot
                        view.reviewDataProduk(jml, method,hargaKirim, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);

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

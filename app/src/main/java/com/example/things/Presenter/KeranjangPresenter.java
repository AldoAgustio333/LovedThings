package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.KeranjangInterface;
import com.example.things.Model.KeranjangModel;
import com.example.things.Model.ProdukModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KeranjangPresenter {
    KeranjangInterface view;
    DatabaseReference reference;

    public KeranjangPresenter(KeranjangInterface view) {
        this.view = view;
        reference = FirebaseDatabase.getInstance().getReference("Users");
    }


    public void getDataKeranjang(String uid) {
        reference.child(uid).child("Keranjang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<KeranjangModel> keranjang = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        KeranjangModel model = dataSnapshot.getValue(KeranjangModel.class);
                        keranjang.add(model);

                    }
                    view.daftarKeranjang(keranjang);

                } else {
                    // Child "keranjang" tidak ada di bawah child "uid"
                    view.daftarKeranjangEmpty();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Penanganan kesalahan
                System.err.println("Gagal membaca data: " + error.getMessage());
            }
        });
    }

    public void findProduk(String idP, String uid, String uidPenjual, String merek, String kategori, String deskripsi, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String noHpPenjual, String fotoPenjual, String harga) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        databaseReference.orderByChild("idP").equalTo(idP).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    // Data ditemukan
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Ambil data dari DataSnapshot
                        String merek = snapshot.child("merek").getValue(String.class);
                        String des = snapshot.child("deskripsi").getValue(String.class);
                        Long hargaLong = snapshot.child("harga").getValue(Long.class);
                        String harga = String.valueOf(hargaLong);
                        String idP = snapshot.child("idP").getValue(String.class);
                        String uidPenjual = snapshot.child("uid").getValue(String.class);
                        String imgP = snapshot.child("img_produk").getValue(String.class);
                        String kategori = snapshot.child("kategori").getValue(String.class);
                        String ukuran = snapshot.child("ukuran").getValue(String.class);

                        view.reviewDataProduk(merek, kategori, des, harga, idP, uidPenjual, imgP, ukuran, namaPenjual, uidPenjual, alamatPenjual, noHpPenjual, fotoPenjual);

                    }

                } else {
                    // Data tidak ditemukan
                    view.dataIsGone(uid, idP);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }
        });
    }
}

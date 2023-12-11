package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.DetailProdukInterface;
import com.example.things.Model.ProdukModel;
import com.example.things.Model.UserModel;
import com.example.things.Utils.SessionManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DetailProdukPresenter {

    DetailProdukInterface view;
    DatabaseReference database;

    public DetailProdukPresenter(DetailProdukInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }


    public void getUser(String idP, String uid) {

      database.child(uid).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot snapshot) {
              //get all info of user here from snapshot
              String email = ""+snapshot.child("email").getValue();
              String username = ""+snapshot.child("username").getValue();
              String nohp = ""+snapshot.child("nohp").getValue();
              String alamat = ""+snapshot.child("alamat").getValue();
              String type = ""+snapshot.child("userType").getValue();
              String uid = ""+snapshot.child("uid").getValue();
              String profileimage = snapshot.child("profileimage").getValue(String.class);

              view.dataUserResponses(email,username, nohp,type, alamat, uid, profileimage);
          }

          @Override
          public void onCancelled(DatabaseError error) {

          }
      });
    }

    public void findProduk(String idP, String nama, String uidU, String alamatU, String nohpU, String imgU) {
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
                        String uid = snapshot.child("uid").getValue(String.class);
                        String imgP = snapshot.child("img_produk").getValue(String.class);
                        String kategori = snapshot.child("kategori").getValue(String.class);
                        String ukuran = snapshot.child("ukuran").getValue(String.class);

                        view.reviewDataProduk(merek, kategori, des, harga, idP, uid, imgP, ukuran, nama, uidU, alamatU, nohpU, imgU);

                    }

                } else {
                    // Data tidak ditemukan
                    view.dataIsGone();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }
        });
    }

    public void sendDataToChart(String uidUser, String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU) {
        String saveCurrentDate, saveCurrentTime, tgl;

//        database.child(uidUser).child("Keranjang");
        int hargaP = Integer.parseInt(harga);

        // Buat objek untuk dimasukkan ke dalam child "keranjang"
        Map<String, Object> keranjangData = new HashMap<>();
        keranjangData.put("idP", idP);
        keranjangData.put("uid", uidUser);
        keranjangData.put("merek", merek);
        keranjangData.put("kategori", kategori);
        keranjangData.put("deskripsi", des);
        keranjangData.put("harga", hargaP);
        keranjangData.put("img_produk", imgP);
        keranjangData.put("ukuran", ukuran);
        keranjangData.put("namaPenjual", nama);
        keranjangData.put("uidPenjual", uidU);
        keranjangData.put("alamatPenjual", alamatU);
        keranjangData.put("nohpPenjual", nohpU);
        keranjangData.put("fotoPenjual", imgU);

        // Tambahkan data ke dalam child "keranjang"
        database.child(uidUser).child("Keranjang").child(idP).setValue(keranjangData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data berhasil ditambahkan ke dalam keranjang
                        view.dataAddedSuccessfully();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Gagal menambahkan data ke dalam keranjang
                        view.dataAddedFailed();
                    }
                });
    }

    public void findProdukforBuyNow(String idP, String nama, String uidU, String alamatU, String nohpU, String imgU) {
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
                        String uid = snapshot.child("uid").getValue(String.class);
                        String imgP = snapshot.child("img_produk").getValue(String.class);
                        String kategori = snapshot.child("kategori").getValue(String.class);
                        String ukuran = snapshot.child("ukuran").getValue(String.class);

                        view.reviewDataProdukForBuyNow(merek, kategori, des, harga, idP, uid, imgP, ukuran, nama, uidU, alamatU, nohpU, imgU);

                    }

                } else {
                    // Data tidak ditemukan
                    view.dataIsGone();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }
        });

    }

    public void findProdukforChat(String idP, String nama, String uidU, String alamatU, String nohpU, String imgU) {
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
                        String uid = snapshot.child("uid").getValue(String.class);
                        String imgP = snapshot.child("img_produk").getValue(String.class);
                        String kategori = snapshot.child("kategori").getValue(String.class);
                        String ukuran = snapshot.child("ukuran").getValue(String.class);

                        view.reviewDataProdukForCgat(merek, kategori, des, harga, idP, uid, imgP, ukuran, nama, uidU, alamatU, nohpU, imgU);

                    }

                } else {
                    // Data tidak ditemukan
                    view.dataIsGone();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }
        });
    }

    public void findProdukWithIDP(String idProduk) {
    }
}

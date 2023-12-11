package com.example.things.Presenter;

import androidx.annotation.NonNull;

import com.example.things.Interface.MethodPaymentInterface;
import com.example.things.Model.PesananModel;
import com.example.things.Model.ProdukModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class MethodPaymentPresenter {

    MethodPaymentInterface view;
    DatabaseReference database;
    String saveCurrentDate, saveCurrentTime;

    public MethodPaymentPresenter(MethodPaymentInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void findProduk(String totalBayar, String hargaKirim, String method, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("DaftarProduk");

        databaseReference.orderByChild("idP").equalTo(idP).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    // Data ditemukan
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Ambil data dari DataSnapshot
                        // Ambil data dari DataSnapshot
                        view.reviewDataProduk(totalBayar, method,hargaKirim, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);

                    }

                } else {
                    // Data tidak ditemukan
                    view.dataIsGone(idP, idUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }
        });
    }

    public void sendDataToPemesanan(String totalBayar, String nama, String nohp, String hargaKirim, String method, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {

        view.onProgress();
        String saveCurrentDate, saveCurrentTime, tgl;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        String date = saveCurrentTime + "-" + saveCurrentDate;

//        database.child(uidUser).child("Keranjang");
        int hargaP = Integer.parseInt(harga);
        int total = Integer.parseInt(totalBayar);
        int hargaPengiriman = Integer.parseInt(hargaKirim);

        // Buat objek untuk dimasukkan ke dalam child "keranjang"
        Map<String, Object> pesananData = new HashMap<>();
        pesananData.put("idP", idP);
        pesananData.put("uid", idUser);
        pesananData.put("merek", merek);
        pesananData.put("kategori", kategori);
        pesananData.put("deskripsi", des);
        pesananData.put("harga", hargaP);
        pesananData.put("img_produk", imgP);
        pesananData.put("ukuran", ukuran);
        pesananData.put("namaPenjual", namaPenjual);
        pesananData.put("uidPenjual", uidPenjual);
        pesananData.put("alamatPenjual", alamatPenjual);
        pesananData.put("nohpPenjual", nohpPenjual);
        pesananData.put("fotoPenjual", fotoPenjual);
        pesananData.put("total", total);
        pesananData.put("methodPembayaran", method);
        pesananData.put("lokasi", lokasi);
        pesananData.put("status", "Sedang dikirim");
        pesananData.put("tglPemesanan", date);
        pesananData.put("hargaPengiriman", hargaPengiriman);

        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("idn",date);
        hashMap2.put("judulnotif","Admin Kawan Ternak");
        hashMap2.put("logonotif","");
        hashMap2.put("isintif","Teriama kasih pembayaran anda telah berhasil, kurir sedang dalam perjalanan ke alamat mu");
        hashMap2.put("tglnotif", saveCurrentDate);
        hashMap2.put("imgnotifikasi", imgP);

        HashMap<String, Object> hashMap3 = new HashMap<>();
        hashMap3.put("idP", idP);
        hashMap3.put("uid",uidPenjual);
        hashMap3.put("tgl",saveCurrentDate);
        hashMap3.put("kategori",kategori);
        hashMap3.put("merek", merek);
        hashMap3.put("ukuran", ukuran);
        hashMap3.put("status", "Sedang Dikirim");
        hashMap3.put("alamat_pembeli", lokasi);
        hashMap3.put("nohp", nohp);
        hashMap3.put("metode_pembayaran", method);
        hashMap3.put("imageP", imgP);
        hashMap3.put("nama_pembeli", nama);
        hashMap3.put("total_harga", total);

        // Tambahkan data ke dalam child "keranjang"
        database.child(idUser).child("Pesanan").child(idP).setValue(pesananData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data berhasil ditambahkan ke dalam keranjang
                        DatabaseReference notif = FirebaseDatabase.getInstance().getReference("Users").child(idUser);
                        notif.child("Notifikasi").child(saveCurrentDate).setValue(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                database.child(uidPenjual).child("ProdukTerjual").child(idP).setValue(hashMap3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        view.dataAddedSuccessfully(idP);
                                    }
                                });
                                database.child(uidPenjual).child("Penjualan").child(idP).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                            }
                        });
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

    public void deleteProdukFromKeranjang(String uid, String idPP) {
        database.child(uid)
                .child("Keranjang")
                .child(idPP)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        // Handle onComplete
                        DatabaseReference dbDafProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                        dbDafProduk.child(idPP)
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NotNull Task<Void> task) {
                                        // Handle onComplete

                                        view.removeSuccess();
                                    }
                                });
                    }
                });
    }

//    public void senDatatoModelPesanan() {
//        database.child("Pesanan").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                ArrayList<PesananModel> models = new ArrayList<>();
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    PesananModel model = dataSnapshot.getValue(PesananModel.class);
//                    models.add(model);
//                }
//
//                view.daftarProdukResponse(models);
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//                view.messageError();
//            }
//        });
//    }
}

package com.example.things.Presenter;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.things.Interface.FormTambahProdukInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FormTambahProdukPresenter{

    FormTambahProdukInterface view;
    DatabaseReference database;

    public FormTambahProdukPresenter(FormTambahProdukInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void sendDatatoDaftarProduk(String uid, String des, String harga, String kategori, String ukuran, String merek, String idP, String tglupload, byte[] data) {
        view.onProgress();

        int hargaP = Integer.parseInt(harga);

        String saveCurrentDate, saveCurrentTime, tgl;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        String date = saveCurrentTime+saveCurrentDate+uid;

        final String filename = "IMG_"+ String.valueOf(System.currentTimeMillis())+".jpg";
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference profileImageRef = storageRef.child("MediaProduk").child(filename);
        // Mengunggah file ke Firebase Storage
        UploadTask uploadTask = profileImageRef.putBytes(data);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Jika pengunggahan berhasil, dapatkan URL gambar dari Firebase Storage
                profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Dapatkan URL gambar
                        String imageUrl = uri.toString();

                        // Buat objek untuk dimasukkan ke dalam child "keranjang"
                        Map<String, Object> produk = new HashMap<>();
                        produk.put("idP", date);
                        produk.put("uid", uid);
                        produk.put("merek", merek);
                        produk.put("kategori", kategori);
                        produk.put("deskripsi", des);
                        produk.put("harga", hargaP);
                        produk.put("img_produk", imageUrl);
                        produk.put("ukuran", ukuran);
                        produk.put("tglUpload", tglupload);

                        Map<String, Object> itemproduk = new HashMap<>();
                        itemproduk.put("idP", date);
                        itemproduk.put("uid", uid);
                        itemproduk.put("merek", merek);
                        itemproduk.put("kategori", kategori);
                        itemproduk.put("deskripsi", des);
                        itemproduk.put("harga", hargaP);
                        itemproduk.put("img_produk", imageUrl);
                        itemproduk.put("ukuran", ukuran);
                        itemproduk.put("tglUpload", tglupload);

                        database.child(uid).child("Penjualan").child(date).setValue(produk)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Data berhasil ditambahkan ke dalam keranjang
                                        DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                                        databaseProduk.child(date).setValue(itemproduk).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                view.dataAddedSuccessfully(idP);
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
                });
            }
        });


    }

}

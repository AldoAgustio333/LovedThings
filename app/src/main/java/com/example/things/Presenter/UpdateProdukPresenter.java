package com.example.things.Presenter;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.things.Activity.UpdateProdukActivity;
import com.example.things.Interface.UpdateProdukInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateProdukPresenter {
    UpdateProdukInterface view;
    DatabaseReference database;

    public UpdateProdukPresenter(UpdateProdukInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void updateDesProduk(String uid, String idP, String des) {
        view.onProgress();
        database.child(uid).child("Penjualan").child(idP).child("deskripsi").setValue(des).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                databaseProduk.child(idP).child("deskripsi").setValue(des).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                        view.updateisSuccess();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateHargaProduk(String uid, String idP, String hargaU) {
        view.onProgress();
        int hargaP = Integer.parseInt(hargaU);
        database.child(uid).child("Penjualan").child(idP).child("harga").setValue(hargaP).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                databaseProduk.child(idP).child("harga").setValue(hargaU).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                        view.updateisSuccess();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateKategoriProduk(String uid, String idP, String kategoriU) {
        view.onProgress();
        database.child(uid).child("Penjualan").child(idP).child("kategori").setValue(kategoriU).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                databaseProduk.child(idP).child("kategori").setValue(kategoriU).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                        view.updateisSuccess();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateUkuranProduk(String uid, String idP, String size) {
        view.onProgress();
        database.child(uid).child("Penjualan").child(idP).child("ukuran").setValue(size).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                databaseProduk.child(idP).child("ukuran").setValue(size).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                        view.updateisSuccess();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateMerekProduk(String uid, String idP, String merekU) {
        view.onProgress();
        database.child(uid).child("Penjualan").child(idP).child("merek").setValue(merekU).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                databaseProduk.child(idP).child("merek").setValue(merekU).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                        view.updateisSuccess();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateImage(String uid, String idP, byte[] data) {
        view.onProgress();

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

                        database.child(uid).child("Penjualan").child(idP).child("img_produk").setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                DatabaseReference databaseProduk = FirebaseDatabase.getInstance().getReference("DaftarProduk");
                                databaseProduk.child(idP).child("img_produk").setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                                        view.updateisSuccess();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle onFailure
                                view.updateisFailed();
                            }
                        });
                    }
                });
            }
        });
    }

    public void deleteData(String uid, String idP) {
        view.onProgress();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DaftarProduk");
        database.child(idP)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        // Handle onComplete
                        database.child(uid)
                                .child("Penjualan")
                                .child(idP)
                                .removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NotNull Task<Void> task) {
                                        view.removeSuccess();
                                    }
                                });
                    }
                });
    }
}

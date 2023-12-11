package com.example.things.Presenter;

import com.example.things.Adapter.ChatInterface;
import com.example.things.Model.ChatModel;
import com.example.things.Model.KeranjangModel;
import com.example.things.Utils.SessionManager;
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

public class ChatPresenter {

    ChatInterface view;
    DatabaseReference database;
    private boolean isFirstMessageSent = false;

    public ChatPresenter(ChatInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void sendMessage(String des, String uid, String nama, String img, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {
        String saveCurrentDate, saveCurrentTime, jam;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        SimpleDateFormat currentHour = new SimpleDateFormat("HH:mm");
        jam = currentHour.format(calendar.getTime());

        String idm = saveCurrentTime+saveCurrentDate;

        HashMap<String, Object> chat = new HashMap<>();
        chat.put("idP", idP);
        chat.put("idm", idm);
        chat.put("uidUser",uidPenjual);
        chat.put("uidOther",uid);
        chat.put("tgl",saveCurrentDate);
        chat.put("jam",jam);
        chat.put("kategori",kategori);
        chat.put("merek", merek);
        chat.put("ukuran", ukuran);
        chat.put("imageP", imgProduk);
        chat.put("namaUser", nama);
        chat.put("namaOther", namaPenjual);
        chat.put("imgUser", img);
        chat.put("imgOther", imgPenjual);
        chat.put("pesan", pesan);
        chat.put("harga", harga);
        chat.put("deskripsi", des);

//        HashMap<String, Object> chat2 = new HashMap<>();
//        chat2.put("idP", idP);
//        chat2.put("idm", idm);
//        chat2.put("uidUser",uidPenjual);
//        chat2.put("uidOther",uid);
//        chat2.put("tgl",saveCurrentDate);
//        chat2.put("jam",jam);
//        chat2.put("kategori",kategori);
//        chat2.put("merek", merek);
//        chat2.put("ukuran", ukuran);
//        chat2.put("imageP", imgProduk);
//        chat2.put("namaUser", namaPenjual);
//        chat2.put("namaOther", nama);
//        chat2.put("imgUser", imgPenjual);
//        chat2.put("imgOther", img);
//        chat2.put("pesan", pesan);
//        chat2.put("harga", harga);
        HashMap<String, Object> userchat = new HashMap<>();
        userchat.put("uid", uidPenjual);
        userchat.put("tgl",saveCurrentDate);
        userchat.put("jam",jam);
        userchat.put("namaUser", namaPenjual);
        userchat.put("imgUser", imgPenjual);
        userchat.put("pesan", pesan);

        userchat.put("idP", idP);
        userchat.put("deskripsi", des);
        userchat.put("kategori",kategori);
        userchat.put("merek", merek);
        userchat.put("ukuran", ukuran);
        userchat.put("harga", harga);



        HashMap<String, Object> userchat2 = new HashMap<>();
        userchat2.put("uid", uid);
        userchat2.put("tgl",saveCurrentDate);
        userchat2.put("jam",jam);
        userchat2.put("namaUser", nama);
        userchat2.put("imgUser", img);
        userchat2.put("pesan", pesan);

        userchat2.put("idP", idP);
        userchat2.put("deskripsi", des);
        userchat2.put("kategori",kategori);
        userchat2.put("merek", merek);
        userchat2.put("ukuran", ukuran);
        userchat2.put("harga", harga);


        database.child(uid).child("DaftarPesan").child("User").child(uidPenjual).setValue(userchat).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                database.child(uidPenjual).child("DaftarPesan").child("User").child(uid).setValue(userchat2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.child(uid).child("DaftarPesan").child("Pesan").child(uidPenjual).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.child(uidPenjual).child("DaftarPesan").child("Pesan").child(uid).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        view.reviewChat(des, pesan,idP, uid, saveCurrentDate,nama, img, idm, jam, imgPenjual, uidPenjual, imgProduk, namaPenjual, merek, kategori, harga, ukuran);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public void sendMessageOnly(String des, String uid, String nama, String img, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {
        String saveCurrentDate, saveCurrentTime, jam;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        SimpleDateFormat currentHour = new SimpleDateFormat("HH:mm");
        jam = currentHour.format(calendar.getTime());

        String idm = saveCurrentTime+saveCurrentDate;

        HashMap<String, Object> chat = new HashMap<>();
        chat.put("idP", "");
        chat.put("idm", idm);
        chat.put("uidUser",uidPenjual);
        chat.put("uidOther",uid);
        chat.put("tgl",saveCurrentDate);
        chat.put("jam",jam);
        chat.put("kategori","");
        chat.put("merek", "");
        chat.put("ukuran", "");
        chat.put("imageP", "");
        chat.put("namaUser", nama);
        chat.put("namaOther", namaPenjual);
        chat.put("imgUser", img);
        chat.put("imgOther", imgPenjual);
        chat.put("pesan", pesan);
        chat.put("harga", harga);
        chat.put("deskripsi", des);

//        HashMap<String, Object> chat2 = new HashMap<>();
//        chat2.put("idP", idP);
//        chat2.put("idm", idm);
//        chat2.put("uidUser",uidPenjual);
//        chat2.put("uidOther",uid);
//        chat2.put("tgl",saveCurrentDate);
//        chat2.put("jam",jam);
//        chat2.put("kategori",kategori);
//        chat2.put("merek", merek);
//        chat2.put("ukuran", ukuran);
//        chat2.put("imageP", imgProduk);
//        chat2.put("namaUser", namaPenjual);
//        chat2.put("namaOther", nama);
//        chat2.put("imgUser", imgPenjual);
//        chat2.put("imgOther", img);
//        chat2.put("pesan", pesan);
//        chat2.put("harga", harga);

        HashMap<String, Object> userchat = new HashMap<>();
        userchat.put("uid", uidPenjual);
        userchat.put("tgl",saveCurrentDate);
        userchat.put("jam",jam);
        userchat.put("namaUser", namaPenjual);
        userchat.put("imgUser", imgPenjual);
        userchat.put("pesan", pesan);

        userchat.put("idP", "");
        userchat.put("deskripsi", "");
        userchat.put("kategori","");
        userchat.put("merek", "");
        userchat.put("ukuran", "");
        userchat.put("harga", "");

        HashMap<String, Object> userchat2 = new HashMap<>();
        userchat2.put("uid", uid);
        userchat2.put("tgl",saveCurrentDate);
        userchat2.put("jam",jam);
        userchat2.put("namaUser", nama);
        userchat2.put("imgUser", img);
        userchat2.put("pesan", pesan);

        userchat2.put("idP", "");
        userchat2.put("deskripsi", "");
        userchat2.put("kategori","");
        userchat2.put("merek", "");
        userchat2.put("ukuran", "");
        userchat2.put("harga", "");


        database.child(uid).child("DaftarPesan").child("User").child(uidPenjual).setValue(userchat).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                database.child(uidPenjual).child("DaftarPesan").child("User").child(uid).setValue(userchat2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.child(uid).child("DaftarPesan").child("Pesan").child(uidPenjual).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.child(uidPenjual).child("DaftarPesan").child("Pesan").child(uid).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        view.reviewChat(des, pesan,idP, uid, saveCurrentDate,nama, img, idm, jam, imgPenjual, uidPenjual, imgProduk, namaPenjual, merek, kategori, harga, ukuran);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public void getChat(String uid, String idP, String uidPenjual) {
        database.child(uid).child("DaftarPesan").child("Pesan").child(uidPenjual).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                ArrayList<ChatModel> chat = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatModel model = dataSnapshot.getValue(ChatModel.class);
                    chat.add(model);
                }

                view.daftarChatResponse(chat);
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                view.messageError();
            }
        });
    }

    public void cekChatInDatabase(String uid, String des, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {

        database.child(uid).child("DaftarPesan").child("Pesan").child(uidPenjual).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    view.daftarChatEmpty(des, pesan, idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                view.messageError();
            }
        });
    }

    public void sendMessageFirstEmpty(String uid, String des, String uid1, String nama, String img, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String uidPenjual1, String merek, String kategori, String harga, String ukuran) {
        String saveCurrentDate, saveCurrentTime, jam;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        SimpleDateFormat currentHour = new SimpleDateFormat("HH:mm");
        jam = currentHour.format(calendar.getTime());

        String idm = saveCurrentTime+"first"+saveCurrentDate;

        String pesan = "Selamat datang di toko kami! Selama barang masih tersedia di daftar produk Loved Things, produk tersedia dan siap untuk dikirim. Selamat berbelanja!";

        HashMap<String, Object> chat = new HashMap<>();
        chat.put("idP", "");
        chat.put("idm", idm);
        chat.put("uidUser",uid);
        chat.put("uidOther",uidPenjual);
        chat.put("tgl",saveCurrentDate);
        chat.put("jam",jam);
        chat.put("kategori","");
        chat.put("merek", "");
        chat.put("ukuran", "");
        chat.put("imageP", "");
        chat.put("namaUser", namaPenjual);
        chat.put("namaOther", nama);
        chat.put("imgUser", imgPenjual);
        chat.put("imgOther", img);
        chat.put("pesan", pesan);
        chat.put("harga", "");
        chat.put("deskripsi", "");

        HashMap<String, Object> userchat = new HashMap<>();
        userchat.put("uid", uidPenjual);
        userchat.put("tgl",saveCurrentDate);
        userchat.put("jam",jam);
        userchat.put("namaUser", namaPenjual);
        userchat.put("imgUser", imgPenjual);
        userchat.put("pesan", pesan);

        userchat.put("idP", "");
        userchat.put("deskripsi", "");
        userchat.put("kategori","");
        userchat.put("merek", "");
        userchat.put("ukuran", "");
        userchat.put("harga", "");

        HashMap<String, Object> userchat2 = new HashMap<>();
        userchat2.put("uid", uid);
        userchat2.put("tgl",saveCurrentDate);
        userchat2.put("jam",jam);
        userchat2.put("namaUser", nama);
        userchat2.put("imgUser", img);
        userchat2.put("pesan", pesan);

        userchat2.put("idP", "");
        userchat2.put("deskripsi", "");
        userchat2.put("kategori","");
        userchat2.put("merek", "");
        userchat2.put("ukuran", "");
        userchat2.put("harga", "");

        // Periksa apakah pesan pertama kali sudah dikirim
        if (!isFirstMessageSent) {
            // Logika pengiriman pesan pertama kali
            database.child(uid).child("DaftarPesan").child("User").child(uidPenjual).setValue(userchat).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    database.child(uidPenjual).child("DaftarPesan").child("User").child(uid).setValue(userchat2).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            database.child(uid).child("DaftarPesan").child("Pesan").child(uidPenjual).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    database.child(uidPenjual).child("DaftarPesan").child("Pesan").child(uid).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });

            database.child(uidPenjual).child("DaftarPesan").child("Pesan").child(uid).child(idm).setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    view.reviewChat(des, pesan, idP, uid, saveCurrentDate, nama, img, idm, jam, imgPenjual, uidPenjual, imgProduk, namaPenjual, merek, kategori, harga, ukuran);
                    onSendMessageFirstEmptyComplete();  // Panggil ini untuk menandakan pesan pertama kali sudah dikirim
                }
            });

        } else {
            // Logika ketika pesan pertama kali sudah dikirim sebelumnya
        }

        // ... (kode lainnya)

    }
    public void onSendMessageFirstEmptyComplete() {
        isFirstMessageSent = true;
    }
}
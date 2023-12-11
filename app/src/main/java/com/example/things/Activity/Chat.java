package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.things.Adapter.AdapterChat;
import com.example.things.Adapter.AdapterDafPesan;
import com.example.things.Adapter.ChatInterface;
import com.example.things.Model.ChatModel;
import com.example.things.Presenter.ChatPresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityChatBinding;

import java.util.ArrayList;

public class Chat extends AppCompatActivity implements ChatInterface {

    private ActivityChatBinding binding;
    private ChatPresenter presenter;
    AdapterChat adapter;
    String mid, message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String imgPenjual = intent.getStringExtra(DetailProduk.EXTRA_FOTOPENJUAL);
        String imgProduk = intent.getStringExtra(DetailProduk.EXTRA_IMGP);
        String namaPenjual = intent.getStringExtra(DetailProduk.EXTRA_NAMAPENJUAL);
        String uidPenjual = intent.getStringExtra(DetailProduk.EXTRA_UIDPENJUAL);
        String idP = intent.getStringExtra(DetailProduk.EXTRA_IDP);
        String merek = intent.getStringExtra(DetailProduk.EXTRA_MEREK);
        String kategori = intent.getStringExtra(DetailProduk.EXTRA_KATEGORI);
        String harga = "Rp." + intent.getStringExtra(DetailProduk.EXTRA_HARGA);
        String ukuran = intent.getStringExtra(DetailProduk.EXTRA_UKURAN);
        String des = intent.getStringExtra(DetailProduk.EXTRA_DESKRIPSI);

        //ini dari adapter chat
        String uidOther = intent.getStringExtra(AdapterDafPesan.EXTRA_UID_OTHER);
        String username = intent.getStringExtra(AdapterDafPesan.EXTRA_USERNAME);
        String imgUser = intent.getStringExtra(AdapterDafPesan.EXTRA_IMG);


        if (idP == null){
            binding.tamplateChatProduk.setVisibility(View.GONE);
            Glide.with(Chat.this).load(imgPenjual).centerCrop().into(binding.addimgPP);
            binding.addUsername.setText(namaPenjual);
        } else {
            binding.tamplateChatProduk.setVisibility(View.VISIBLE);
            Glide.with(Chat.this).load(imgProduk).centerCrop().into(binding.addImagePrduk);
            Glide.with(Chat.this).load(imgPenjual).centerCrop().into(binding.addimgPP);
            binding.addUsername.setText(namaPenjual);
            binding.addHarga.setText(harga);
            binding.addKategori.setText(kategori);
            binding.addMerek.setText(merek);
            binding.addSize.setText(ukuran);
        }

        presenter = new ChatPresenter(this);

        SessionManager sessionManager = new SessionManager(this);
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        if(uidPenjual == null)
        {
            binding.addUsername.setText(username);
            Glide.with(Chat.this).load(imgUser).centerCrop().into(binding.addimgPP);
            presenter.getChat(uid, idP, uidOther);
            AllButton(des,idP, imgUser, uidOther, imgProduk, username, uid, merek, kategori, harga, ukuran);
        } else {
            presenter.getChat(uid, idP, uidPenjual);
            AllButton(des,idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
        }

    }

    private void AllButton(String des, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tamplateChatProduk.setVisibility(View.GONE);
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesan = binding.addPesan.getText().toString().trim();

                int visibility = binding.tamplateChatProduk.getVisibility();
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                String uid = sessionManager.getUserDetail().get(SessionManager.UID);

                if(visibility == View.VISIBLE){
                    // Periksa apakah pesan tidak kosong sebelum mengirim ke database
                    if (!pesan.isEmpty()) {

                        presenter.cekChatInDatabase(uid, des, pesan, idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
                        kirimPesanKeDatabase(des, pesan, idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
                        binding.addPesan.getText().clear();
                        return;

                    } else {
                        Toast.makeText(Chat.this, "Maaf anda belum membuat pesan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Periksa apakah pesan tidak kosong sebelum mengirim ke database


                    if (!pesan.isEmpty()) {
                        presenter.cekChatInDatabase(uid, des, pesan, idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
                        kirimPesanKeDatabasePesanOnly(des, pesan, idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
                        binding.addPesan.getText().clear();
                    } else {
                        Toast.makeText(Chat.this, "Maaf anda belum membuat pesan", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }

    private void kirimPesanKeDatabasePesanOnly(String des, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {
        SessionManager sessionManager = new SessionManager(this);
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String img = sessionManager.getUserDetail().get(SessionManager.IMG_PROFILE);
        binding.tamplateChatProduk.setVisibility(View.GONE);
        presenter.sendMessageOnly(des, uid, nama, img,pesan,idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
    }

    private void kirimPesanKeDatabase(String des, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {
        SessionManager sessionManager = new SessionManager(this);
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String img = sessionManager.getUserDetail().get(SessionManager.IMG_PROFILE);
        binding.tamplateChatProduk.setVisibility(View.GONE);
        presenter.sendMessage(des, uid, nama, img, pesan,idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
    }

    @Override
    public void reviewChat(String des, String pesan, String idP, String uid, String saveCurrentDate, String nama, String img, String idm, String jam, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String merek, String kategori, String harga, String ukuran) {
        SessionManager sessionManager = new SessionManager(this);
        presenter.getChat(uid,idP, uidPenjual);
    }

    @Override
    public void daftarChatEmpty(String des, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String uidPenjual1, String merek, String kategori, String harga, String ukuran) {

    }

    @Override
    public void daftarChatEmpty(String des, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran) {
        SessionManager sessionManager = new SessionManager(this);
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String img = sessionManager.getUserDetail().get(SessionManager.IMG_PROFILE);
        binding.tamplateChatProduk.setVisibility(View.GONE);
        presenter.sendMessageFirstEmpty(uid,des, uid, nama, img,idP, imgPenjual, uidPenjual, imgProduk, namaPenjual, uidPenjual, merek, kategori, harga, ukuran);
    }

    @Override
    public void daftarChatResponse(ArrayList<ChatModel> chat) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdapterChat(getApplicationContext(), chat);
        binding.recPesanUser.setAdapter(adapter);
        binding.recPesanUser.setLayoutManager(layoutManager);

        // Ketika menambahkan item baru, scroll ke posisi terakhir
        int newPosition = chat.size() - 1;

        adapter.notifyItemInserted(newPosition);
        binding.recPesanUser.scrollToPosition(newPosition);
        binding.recPesanUser.smoothScrollToPosition(binding.recPesanUser.getAdapter().getItemCount());

        adapter.notifyDataSetChanged();

    }

    @Override
    public void updateDaftarChat(String idP) {

    }

    @Override
    public void messageError() {
        Toast.makeText(this, "Maaf pesan gagal dikirim", Toast.LENGTH_SHORT).show();
    }
}
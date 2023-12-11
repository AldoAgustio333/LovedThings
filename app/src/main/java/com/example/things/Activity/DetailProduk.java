package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.things.Adapter.AdapterChat;
import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Interface.DetailProdukInterface;
import com.example.things.Model.UserModel;
import com.example.things.Presenter.DetailProdukPresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityDetailProdukBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailProduk extends AppCompatActivity implements DetailProdukInterface {

    private ActivityDetailProdukBinding binding;
    private DetailProdukPresenter presenter;
    SessionManager sessionManager;

    List<UserModel> model;
    String nama, emailU, imgU,nohpU, alamatU, uidU;

    //user
    public static final String EXTRA_NAMA= "nama";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_ALAMAT = "alamat";
    public static final String EXTRA_IMG = "img";
    public static final String EXTRA_NOHP = "nohp";

    //produk
    public static final String EXTRA_DES = "des";
    public static final String EXTRA_IMGP = "img";
    public static final String EXTRA_MEREK = "merek";
    public static final String EXTRA_KATEGORI = "kategori";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_HARGA = "harga";
    public static final String EXTRA_UKURAN = "ukuran";
    public static final String EXTRA_UID = "uid";
    public static final String EXTRA_IDP = "idP";

    public static final String EXTRA_NAMAPENJUAL = "namaP";
    public static final String EXTRA_UIDPENJUAL = "uidP";
    public static final String EXTRA_ALAMATPENJUAL = "alamatP";
    public static final String EXTRA_NOHPPENJUAL = "nohpP";
    public static final String EXTRA_FOTOPENJUAL = "fotoP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DetailProdukPresenter(this);
        model = new ArrayList<>();
        Intent intent = getIntent();
        String idProduk = intent.getStringExtra(AdapterChat.EXTRA_IDP);

        if(idProduk == null){


        } else {
            presenter.findProdukWithIDP(idProduk);
        }

        String des = intent.getStringExtra(ProdukAdapter.EXTRA_DES);
        String size = intent.getStringExtra(ProdukAdapter.EXTRA_UKURAN);
        String uid = intent.getStringExtra(ProdukAdapter.EXTRA_UID);
        String idP = intent.getStringExtra(ProdukAdapter.EXTRA_IDP);
        String kategori = intent.getStringExtra(ProdukAdapter.EXTRA_KATEGORI);
        String harga = intent.getStringExtra(ProdukAdapter.EXTRA_HARGA);
        String merek = intent.getStringExtra(ProdukAdapter.EXTRA_MEREK);
        String imgP = intent.getStringExtra(ProdukAdapter.EXTRA_IMG);

        binding.addDes.setText(des);
        binding.addMerek.setText(merek);
        binding.addHarga.setText(harga);
        binding.kategori.setText(kategori);
        binding.addUkuan.setText(size);
        binding.addUsername.setText(size);

        Glide.with(binding.imgProduk).load(imgP).centerCrop().into(binding.imgProduk);
        Glide.with(binding.imgUser).load(imgP).centerCrop().into(binding.imgUser);

        presenter.getUser(idP,uid);
        sessionManager = new SessionManager(this);
        String uidUser = sessionManager.getUserDetail().get(SessionManager.UID);



        if(uid.equals(uidUser))
        {
            binding.bottomNav.setVisibility(View.GONE);
            binding.btnChat.setVisibility(View.GONE);
            binding.btnKunjungi.setVisibility(View.GONE);
        } else
            {
            binding.bottomNav.setVisibility(View.VISIBLE);
            binding.btnChat.setVisibility(View.VISIBLE);
            binding.btnKunjungi.setVisibility(View.VISIBLE);
        }

        AllButton(idP, des, harga, size, uid, imgP, merek, kategori);

    }

    private void AllButton(String idP, String des, String harga, String size, String uid, String imgP, String merek, String kategori) {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProduk.this, SearchActtivity.class);
                startActivity(intent);

            }
        });
        binding.btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DetailProduk.this, KeranjangActivity.class);
//                startActivity(intent);
                presenter.findProduk(idP, nama, uidU, alamatU, nohpU, imgU);
            }
        });
        binding.btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProduk.this, KeranjangActivity.class);
                startActivity(intent);
            }
        });
        binding.btnKunjungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProduk.this, ProfileKunjungan.class);
                intent.putExtra(EXTRA_NAMA, nama);
                intent.putExtra(EXTRA_EMAIL, emailU);
                intent.putExtra(EXTRA_NOHP, nohpU);
                intent.putExtra(EXTRA_ALAMAT, alamatU);
                intent.putExtra(EXTRA_IMG, imgU);
                startActivity(intent);
            }
        });
        binding.btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findProdukforBuyNow(idP, nama, uidU, alamatU, nohpU, imgU);
            }
        });
        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findProdukforChat(idP, nama, uidU, alamatU, nohpU, imgU);
            }
        });

    }

    @Override
    public void dataUserResponses(String email, String username, String nohp, String type, String alamat, String uid, String profileimage) {
        nama = username;
        emailU = email;
        nohpU = nohp;
        alamatU = alamat;
        imgU = profileimage;
        uidU = uid;

        binding.addUsername.setText(username);
        binding.addAlamat.setText(alamat);
        if (!isDestroyed()) {
            Glide.with(binding.imgUser).load(profileimage).centerCrop().into(binding.imgUser);
        }

    }

    @Override
    public void reviewDataProduk(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU) {
        sessionManager = new SessionManager(this);
        String uidUser = sessionManager.getUserDetail().get(SessionManager.UID);
        presenter.sendDataToChart(uidUser, merek, kategori, des, harga, idP, uid, imgP, ukuran, nama, uidU, alamatU, nohpU, imgU);

    }

    @Override
    public void dataIsGone() {
        Toast.makeText(this, "Data Telah Habis", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void dataAddedSuccessfully() {
        Intent intent = new Intent(DetailProduk.this, KeranjangActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void dataAddedFailed() {
        Toast.makeText(this, "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reviewDataProdukForBuyNow(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU) {
        Intent intent = new Intent(DetailProduk.this, CheckoutActivity.class);
        intent.putExtra(EXTRA_IMGP, imgP);
        intent.putExtra(EXTRA_MEREK, merek);
        intent.putExtra(EXTRA_KATEGORI, kategori);
        intent.putExtra(EXTRA_DESKRIPSI, des);
        intent.putExtra(EXTRA_HARGA, harga);
        intent.putExtra(EXTRA_UKURAN, ukuran);
        intent.putExtra(EXTRA_UID, uid);
        intent.putExtra(EXTRA_IDP, idP);

        intent.putExtra(EXTRA_NAMAPENJUAL, nama);
        intent.putExtra(EXTRA_UIDPENJUAL, uidU);
        intent.putExtra(EXTRA_ALAMATPENJUAL, alamatU);
        intent.putExtra(EXTRA_NOHPPENJUAL, nohpU);
        intent.putExtra(EXTRA_FOTOPENJUAL, imgU);
        startActivity(intent);
        finish();
    }

    @Override
    public void reviewDataProdukForCgat(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU) {
        Intent intent = new Intent(DetailProduk.this, Chat.class);
        intent.putExtra(EXTRA_IMGP, imgP);
        intent.putExtra(EXTRA_MEREK, merek);
        intent.putExtra(EXTRA_KATEGORI, kategori);
        intent.putExtra(EXTRA_HARGA, harga);
        intent.putExtra(EXTRA_UKURAN, ukuran);
        intent.putExtra(EXTRA_UID, uid);
        intent.putExtra(EXTRA_IDP, idP);
        intent.putExtra(EXTRA_NAMAPENJUAL, nama);
        intent.putExtra(EXTRA_UIDPENJUAL, uidU);
        intent.putExtra(EXTRA_FOTOPENJUAL, imgU);
        intent.putExtra(EXTRA_DESKRIPSI, des);
        startActivity(intent);
    }
}
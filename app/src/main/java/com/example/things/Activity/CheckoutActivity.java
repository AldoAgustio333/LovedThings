package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.things.Interface.COInterface;
import com.example.things.Presenter.COPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityCheckoutBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class CheckoutActivity extends AppCompatActivity implements COInterface {

    private ActivityCheckoutBinding binding;
    private COPresenter presenter;
    SessionManager sessionManager;
    DatabaseReference database;

    //produk
    public static final String EXTRA_IMGP = "img";
    public static final String EXTRA_MEREK = "merek";
    public static final String EXTRA_KATEGORI = "kategori";
    public static final String EXTRA_DESKRIPSI = "deskripsi";
    public static final String EXTRA_HARGA = "harga";
    public static final String EXTRA_UKURAN = "ukuran";
    public static final String EXTRA_UID = "uid";
    public static final String EXTRA_IDP = "idP";

    public static final String EXTRA_LOKASI = "lokasi";
    public static final String EXTRA_HARGAKIRIM = "hargaKirim";

    public static final String EXTRA_NAMAPENJUAL = "namaP";
    public static final String EXTRA_UIDPENJUAL = "uidP";
    public static final String EXTRA_ALAMATPENJUAL = "alamatP";
    public static final String EXTRA_NOHPPENJUAL = "nohpP";
    public static final String EXTRA_FOTOPENJUAL = "fotoP";

    public static final String EXTRA_JML = "jml";

    String jmlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new COPresenter(this);
        sessionManager = new SessionManager(this);
        database = FirebaseDatabase.getInstance().getReference("Users");

        Intent intent = getIntent();
        String idP = intent.getStringExtra(KeranjangActivity.EXTRA_IDP);
        String uid = intent.getStringExtra(KeranjangActivity.EXTRA_UID);
        String uidPenjual = intent.getStringExtra(KeranjangActivity.EXTRA_UIDPENJUAL);
        String kategori = intent.getStringExtra(KeranjangActivity.EXTRA_KATEGORI);
        String harga = intent.getStringExtra(KeranjangActivity.EXTRA_HARGA);
        String fotoPenjual = intent.getStringExtra(KeranjangActivity.EXTRA_FOTOPENJUAL);
        String imgP = intent.getStringExtra(KeranjangActivity.EXTRA_IMGP);
        String des = intent.getStringExtra(KeranjangActivity.EXTRA_DESKRIPSI);
        String alamatPenjual = intent.getStringExtra(KeranjangActivity.EXTRA_ALAMATPENJUAL);
        String nohpPenjual = intent.getStringExtra(KeranjangActivity.EXTRA_NOHPPENJUAL);
        String ukuran = intent.getStringExtra(KeranjangActivity.EXTRA_UKURAN);
        String merek = intent.getStringExtra(KeranjangActivity.EXTRA_MEREK);
        String namaPenjual = intent.getStringExtra(KeranjangActivity.EXTRA_NAMAPENJUAL);

        String namaUser = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String idUser = sessionManager.getUserDetail().get(SessionManager.UID);
        String alamatUser = sessionManager.getUserDetail().get(SessionManager.ALAMAT);
        String nohpUser = sessionManager.getUserDetail().get(SessionManager.NOHP);

        binding.addUsername.setText(namaPenjual);
        binding.addAlamat.setText(alamatPenjual);
        binding.addKategori.setText(kategori);
        binding.addMerek.setText(merek);
        binding.addTotalHarga.setText(harga);
        binding.addNama.setText(namaUser);
        binding.adddHp.setText(nohpUser);
        binding.addLokasi.setText(alamatUser);
        Glide.with(CheckoutActivity.this).load(imgP).centerCrop().into(binding.imgProduk);
        Glide.with(CheckoutActivity.this).load(fotoPenjual).centerCrop().into(binding.imgUser);

        binding.addSize.setText("Size"+" "+ukuran);

        int hargaP = Integer.parseInt(harga);
        int hargaPengiriman = 12000;

        int jml = hargaP + hargaPengiriman;

        String hargaKirim = String.valueOf(hargaPengiriman);
        jmlString = String.valueOf(jml);

        binding.addTotal.setText(jmlString);

        String getLokasi = binding.addLokasi.getText().toString();

        if(getLokasi.isEmpty()){
            binding.btnPembayaran.setBackground(getDrawable(R.drawable.btn_fill2));
        } else {
            binding.btnPembayaran.setBackground(getDrawable(R.drawable.btn_fill));
        }

        AllButton(getLokasi, hargaKirim, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga, jmlString);
    }

    private void AllButton(String getLokasi, String hargaKirim, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga, String jmlString) {
        binding.btnPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lokasi = binding.addLokasi.getText().toString();
                if (lokasi.isEmpty())
                {
                    binding.addLokasi.requestFocus();
                    binding.addLokasi.setError("Maaf alamat anda tidak boleh kosong!");
                } else {
                    presenter.chackProduk(lokasi, hargaKirim, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);
                }
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void reviewDataProduk(String idUser, String hargaKirim, String lokasi, String merek, String kategori, String des, String harga, String idP, String uidPenjual, String imgP, String ukuran, String namaPenjual, String penjual, String alamatPenjual, String nohpPenjual, String fotoPenjual) {

        Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);
        intent.putExtra(EXTRA_IMGP, imgP);
        intent.putExtra(EXTRA_MEREK, merek);
        intent.putExtra(EXTRA_KATEGORI, kategori);
        intent.putExtra(EXTRA_DESKRIPSI, des);
        intent.putExtra(EXTRA_HARGA, harga);
        intent.putExtra(EXTRA_UKURAN, des);
        intent.putExtra(EXTRA_UID, idUser);
        intent.putExtra(EXTRA_IDP, idP);

        intent.putExtra(EXTRA_NAMAPENJUAL, namaPenjual);
        intent.putExtra(EXTRA_UIDPENJUAL, uidPenjual);
        intent.putExtra(EXTRA_ALAMATPENJUAL, alamatPenjual);
        intent.putExtra(EXTRA_NOHPPENJUAL, nohpPenjual);
        intent.putExtra(EXTRA_FOTOPENJUAL, fotoPenjual);

        intent.putExtra(EXTRA_HARGAKIRIM, hargaKirim);

        intent.putExtra(EXTRA_LOKASI, lokasi);
        intent.putExtra(EXTRA_JML, jmlString);
        startActivity(intent);
        finish();
    }

    @Override
    public void dataIsGone(String idUser, String idP) {
        database.child(idUser)
                .child("Keranjang")
                .child(idP)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        // Handle onComplete
                        Toast.makeText(CheckoutActivity.this, "Maaf Produk Habis tolong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CheckoutActivity.this, DaftarProduk.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
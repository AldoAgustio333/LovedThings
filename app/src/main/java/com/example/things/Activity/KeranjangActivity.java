package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.things.Adapter.KeranjangAdapter;
import com.example.things.Interface.KeranjangInterface;
import com.example.things.Interface.itemClick.RecyclerKeranjang;
import com.example.things.Model.KeranjangModel;
import com.example.things.Presenter.KeranjangPresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityKeranjangBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KeranjangActivity extends AppCompatActivity implements KeranjangInterface, RecyclerKeranjang {

    private ActivityKeranjangBinding binding;
    private KeranjangPresenter presenter;
    private KeranjangAdapter adapter;
    DatabaseReference database;

    SessionManager sessionManager;

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

    //send To chat activity
    public static final String Keranjang_EXTRA_IMGP = "img";
    public static final String Keranjang_EXTRA_MEREK = "merek";
    public static final String Keranjang_EXTRA_KATEGORI = "kategori";
    public static final String Keranjang_EXTRA_DESKRIPSI = "deskripsi";
    public static final String Keranjang_EXTRA_HARGA = "harga";
    public static final String Keranjang_EXTRA_UKURAN = "ukuran";
    public static final String Keranjang_EXTRA_UID = "uid";
    public static final String Keranjang_EXTRA_IDP = "idP";

    public static final String Keranjang_EXTRA_NAMAPENJUAL = "namaP";
    public static final String Keranjang_EXTRA_UIDPENJUAL = "uidP";
    public static final String Keranjang_EXTRA_ALAMATPENJUAL = "alamatP";
    public static final String Keranjang_EXTRA_NOHPPENJUAL = "nohpP";
    public static final String Keranjang_EXTRA_FOTOPENJUAL = "fotoP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKeranjangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance().getReference("Users");

        sessionManager = new SessionManager(this);
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        presenter = new KeranjangPresenter(this);

        presenter.getDataKeranjang(uid);

        AllButton();
    }

    private void AllButton() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void daftarKeranjang(ArrayList<KeranjangModel> keranjang) {
        adapter = new KeranjangAdapter(getApplicationContext(), keranjang);
        adapter.setListener(KeranjangActivity.this); // Inisialisasi listener
        binding.recKeranjang.setAdapter(adapter);
        binding.recKeranjang.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void daftarKeranjangEmpty() {
        Toast.makeText(this, "Maaf saat ini anda tidak memiliki barang di keranjang", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reviewDataProduk(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String namaPenjual, String uidPenjual, String alamatPenjual, String noHpPenjual, String fotoPenjual) {

        Intent intent = new Intent(KeranjangActivity.this, CheckoutActivity.class);
        intent.putExtra(EXTRA_IMGP, imgP);
        intent.putExtra(EXTRA_MEREK, merek);
        intent.putExtra(EXTRA_KATEGORI, kategori);
        intent.putExtra(EXTRA_DESKRIPSI, des);
        intent.putExtra(EXTRA_HARGA, harga);
        intent.putExtra(EXTRA_UKURAN, des);
        intent.putExtra(EXTRA_UID, uid);
        intent.putExtra(EXTRA_IDP, idP);

        intent.putExtra(EXTRA_NAMAPENJUAL, namaPenjual);
        intent.putExtra(EXTRA_UIDPENJUAL, uidPenjual);
        intent.putExtra(EXTRA_ALAMATPENJUAL, alamatPenjual);
        intent.putExtra(EXTRA_NOHPPENJUAL, noHpPenjual);
        intent.putExtra(EXTRA_FOTOPENJUAL, fotoPenjual);
        startActivity(intent);
        finish();
    }

    @Override
    public void dataIsGone(String uid, String idP) {
        database.child(uid)
                .child("Keranjang")
                .child(idP)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        // Handle onComplete
                        Toast.makeText(KeranjangActivity.this, "Maaf Produk Habis tolong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KeranjangActivity.this, DaftarProduk.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }

    @Override
    public void onItemClick(int position, List<KeranjangModel> models) {
        KeranjangModel model = models.get(position);

    }

    @Override
    public void onDeleteItemClick(int position, List<KeranjangModel> models) {
        KeranjangModel model = models.get(position);
        String uid = model.getUid();
        String idP = model.getIdP();
        if (!isFinishing() && !isDestroyed()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hapus");
            builder.setMessage("Anda yakin untuk menghapus data ini?");
            builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    database.child(uid)
                            .child("Keranjang")
                            .child(idP)
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NotNull Task<Void> task) {
                                    // Handle onComplete
                                    Toast.makeText(KeranjangActivity.this, "Hapus Berhasil", Toast.LENGTH_SHORT).show();
                                    models.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                            });
                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    @Override
    public void onCHeckOutItemClick(int position, List<KeranjangModel> models) {
        KeranjangModel model = models.get(position);
        String idP = model.getIdP();
        String uid = model.getUid();
        String uidPenjual = model.getUidPenjual();
        String merek = model.getMerek();
        String kategori = model.getKategori();
        String deskripsi = model.getDeskripsi();
        String imgP = model.getImg_produk();
        String ukuran = model.getUkuran();
        String namaPenjual = model.getNamaPenjual();
        String alamatPenjual = model.getAlamatPenjual();
        String noHpPenjual = model.getNohpPenjual();
        String fotoPenjual = model.getFotoPenjual();
        String harga = String.valueOf(model.getHarga());
        presenter.findProduk(idP, uid, uidPenjual, merek, kategori, deskripsi, imgP, ukuran, namaPenjual, alamatPenjual, noHpPenjual, fotoPenjual, harga);
    }

    @Override
    public void onVhatItemClick(int position, List<KeranjangModel> models) {
        KeranjangModel model = models.get(position);
        Intent intent = new Intent(KeranjangActivity.this, DaftarChatActivity.class);
        startActivity(intent);
        finish();
    }
}
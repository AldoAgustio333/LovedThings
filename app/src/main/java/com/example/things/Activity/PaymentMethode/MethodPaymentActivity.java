package com.example.things.Activity.PaymentMethode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.things.Activity.DaftarProduk;
import com.example.things.Activity.DetailProduk;
import com.example.things.Activity.PaymentActivity;
import com.example.things.Activity.PesananActivity;
import com.example.things.Interface.MethodPaymentInterface;
import com.example.things.Presenter.MethodPaymentPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityGopayBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class MethodPaymentActivity extends AppCompatActivity implements MethodPaymentInterface {

    MethodPaymentPresenter presenter;
    SessionManager sessionManager;
    DatabaseReference database;
    private ActivityGopayBinding binding;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGopayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);
        presenter = new MethodPaymentPresenter(this);
        database = FirebaseDatabase.getInstance().getReference("Users");

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        Intent intent = getIntent();
        String idP = intent.getStringExtra(PaymentActivity.EXTRA_IDP);
        String uid = intent.getStringExtra(PaymentActivity.EXTRA_UID);
        String uidPenjual = intent.getStringExtra(PaymentActivity.EXTRA_UIDPENJUAL);
        String kategori = intent.getStringExtra(PaymentActivity.EXTRA_KATEGORI);
        String harga = intent.getStringExtra(PaymentActivity.EXTRA_HARGA);
        String fotoPenjual = intent.getStringExtra(PaymentActivity.EXTRA_FOTOPENJUAL);
        String imgP = intent.getStringExtra(PaymentActivity.EXTRA_IMGP);
        String des = intent.getStringExtra(PaymentActivity.EXTRA_DESKRIPSI);
        String alamatPenjual = intent.getStringExtra(PaymentActivity.EXTRA_ALAMATPENJUAL);
        String nohpPenjual = intent.getStringExtra(PaymentActivity.EXTRA_NOHPPENJUAL);
        String ukuran = intent.getStringExtra(PaymentActivity.EXTRA_UKURAN);
        String merek = intent.getStringExtra(PaymentActivity.EXTRA_MEREK);
        String namaPenjual = intent.getStringExtra(PaymentActivity.EXTRA_NAMAPENJUAL);

        String lokasi = intent.getStringExtra(PaymentActivity.EXTRA_LOKASI);
        String hargaKirim = intent.getStringExtra(PaymentActivity.EXTRA_HARGAKIRIM);
        String jml = intent.getStringExtra(PaymentActivity.EXTRA_JML);
        String method = intent.getStringExtra(PaymentActivity.EXTRA_METHOD);



        String namaUser = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String idUser = sessionManager.getUserDetail().get(SessionManager.UID);
        String alamatUser = sessionManager.getUserDetail().get(SessionManager.ALAMAT);
        String nohpUser = sessionManager.getUserDetail().get(SessionManager.NOHP);

        if(method.equals("Dana"))
        {
            binding.logo.setBackground(getDrawable(R.drawable.dana));
            binding.bgTombolBayar.setBackgroundColor(getResources().getColor(R.color.birudana));
            binding.namaLogo.setText("Dana");
        }

        binding.addTotalHarga.setText("Rp."+jml);

        int getHarga = Integer.parseInt(jml);

        int total = 300000 - getHarga;

        String totalBayar = String.valueOf(total);
        binding.jmlTotal.setText("Rp."+totalBayar);

        AllButton(totalBayar, hargaKirim, method, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);
    }

    private void AllButton(String totalBayar, String hargaKirim, String method, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.bgTombolBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findProduk(totalBayar, hargaKirim, method, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);
            }
        });
    }

    @Override
    public void reviewDataProduk(String totalBayar, String method, String hargaKirim, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {
        String nohp = sessionManager.getUserDetail().get(SessionManager.NOHP);
        String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        presenter.sendDataToPemesanan(totalBayar,nama, nohp, hargaKirim, method, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);
    }

    @Override
    public void dataIsGone(String idP, String idUser) {
        database.child(idUser)
                .child("Keranjang")
                .child(idP)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        // Handle onComplete
                        Toast.makeText(MethodPaymentActivity.this, "Maaf Produk Habis", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MethodPaymentActivity.this, DaftarProduk.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    public void dataAddedSuccessfully(String idP) {
//        Toast.makeText(this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show();
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        Intent intent = getIntent();
        String idPP = intent.getStringExtra(DetailProduk.EXTRA_IDP);
        presenter.deleteProdukFromKeranjang(uid, idPP);
    }

    @Override
    public void dataAddedFailed() {
        Toast.makeText(this, "Pembayaran Gagal", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void removeSuccess() {
        dialog.dismiss();
        Toast.makeText(this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MethodPaymentActivity.this, PesananActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onProgress() {
        dialog.show();
    }
}
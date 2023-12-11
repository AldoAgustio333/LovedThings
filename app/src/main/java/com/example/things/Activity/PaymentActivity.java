package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.things.Activity.PaymentMethode.MethodPaymentActivity;
import com.example.things.Interface.PaymentInterface;
import com.example.things.Presenter.PaymentPresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityPaymentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class PaymentActivity extends AppCompatActivity implements PaymentInterface {

    private PaymentPresenter presenter;
    private ActivityPaymentBinding binding;
    SessionManager sessionManager;
    RadioButton radioButton;
    String method;
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
    public static final String EXTRA_METHOD = "metode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);
        presenter = new PaymentPresenter(this);
        database = FirebaseDatabase.getInstance().getReference("Users");

        Intent intent = getIntent();
        String idP = intent.getStringExtra(CheckoutActivity.EXTRA_IDP);
        String uid = intent.getStringExtra(CheckoutActivity.EXTRA_UID);
        String uidPenjual = intent.getStringExtra(CheckoutActivity.EXTRA_UIDPENJUAL);
        String kategori = intent.getStringExtra(CheckoutActivity.EXTRA_KATEGORI);
        String harga = intent.getStringExtra(CheckoutActivity.EXTRA_HARGA);
        String fotoPenjual = intent.getStringExtra(CheckoutActivity.EXTRA_FOTOPENJUAL);
        String imgP = intent.getStringExtra(CheckoutActivity.EXTRA_IMGP);
        String des = intent.getStringExtra(CheckoutActivity.EXTRA_DESKRIPSI);
        String alamatPenjual = intent.getStringExtra(CheckoutActivity.EXTRA_ALAMATPENJUAL);
        String nohpPenjual = intent.getStringExtra(CheckoutActivity.EXTRA_NOHPPENJUAL);
        String ukuran = intent.getStringExtra(CheckoutActivity.EXTRA_UKURAN);
        String merek = intent.getStringExtra(CheckoutActivity.EXTRA_MEREK);
        String namaPenjual = intent.getStringExtra(CheckoutActivity.EXTRA_NAMAPENJUAL);

        String hargaKirim = intent.getStringExtra(CheckoutActivity.EXTRA_HARGAKIRIM);

        String lokasi = intent.getStringExtra(CheckoutActivity.EXTRA_LOKASI);
        String jml = intent.getStringExtra(CheckoutActivity.EXTRA_JML);


        String namaUser = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String idUser = sessionManager.getUserDetail().get(SessionManager.UID);
        String alamatUser = sessionManager.getUserDetail().get(SessionManager.ALAMAT);
        String nohpUser = sessionManager.getUserDetail().get(SessionManager.NOHP);


        AllButton(jml, hargaKirim, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);

    }

    private void AllButton(String jml, String hargaKirim, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.tamplatePayment.getVisibility() == View.GONE)
                {
                    binding.btnDropDown.setScaleY(-1);
                    binding.tamplatePayment.setVisibility(View.VISIBLE);
                } else {
                    binding.btnDropDown.setScaleY(1);
                    binding.tamplatePayment.setVisibility(View.GONE);
                }
            }
        });

        binding.btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idTerpilihH = binding.radiongrup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(idTerpilihH);
                method = radioButton.getText().toString();
                if(method == null)
                {
                    Toast.makeText(PaymentActivity.this, "Tolong pilih metode pembayaran", Toast.LENGTH_SHORT).show();
                } else if(method.equals("QRIS")){
                    Toast.makeText(PaymentActivity.this, "Pembayaran ini belum tersedia", Toast.LENGTH_SHORT).show();
                }else {
                    presenter.findProduk(jml, hargaKirim,method, lokasi, idP, idUser, uidPenjual, merek, kategori, des, imgP, ukuran, namaPenjual, alamatPenjual, nohpPenjual, fotoPenjual, harga);
                }
            }
        });

    }

    @Override
    public void reviewDataProduk(String jml, String method, String hargaKirim, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga) {

        if (PaymentActivity.this.method.equals("Dana"))
        {
            Intent intent = new Intent(PaymentActivity.this, MethodPaymentActivity.class);
            intent.putExtra(EXTRA_IMGP, imgP);
            intent.putExtra(EXTRA_MEREK, merek);
            intent.putExtra(EXTRA_KATEGORI, kategori);
            intent.putExtra(EXTRA_DESKRIPSI, des);
            intent.putExtra(EXTRA_HARGA, harga);
            intent.putExtra(EXTRA_UKURAN, ukuran);
            intent.putExtra(EXTRA_UID, idUser);
            intent.putExtra(EXTRA_IDP, idP);

            intent.putExtra(EXTRA_NAMAPENJUAL, namaPenjual);
            intent.putExtra(EXTRA_UIDPENJUAL, uidPenjual);
            intent.putExtra(EXTRA_ALAMATPENJUAL, alamatPenjual);
            intent.putExtra(EXTRA_NOHPPENJUAL, nohpPenjual);
            intent.putExtra(EXTRA_FOTOPENJUAL, fotoPenjual);

            intent.putExtra(EXTRA_HARGAKIRIM, hargaKirim);

            intent.putExtra(EXTRA_LOKASI, lokasi);
            intent.putExtra(EXTRA_JML, jml);

            intent.putExtra(EXTRA_METHOD, this.method);
            startActivity(intent);

        } else if (PaymentActivity.this.method.equals("Gopay")) {

            Intent intent = new Intent(PaymentActivity.this, MethodPaymentActivity.class);
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
            intent.putExtra(EXTRA_JML, jml);

            intent.putExtra(EXTRA_METHOD, this.method);
            startActivity(intent);

        } else {
            Toast.makeText(PaymentActivity.this, "Pembayaran ini belum tersedia", Toast.LENGTH_SHORT).show();
        }
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
                        Toast.makeText(PaymentActivity.this, "Maaf Produk Habis", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PaymentActivity.this, DaftarProduk.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
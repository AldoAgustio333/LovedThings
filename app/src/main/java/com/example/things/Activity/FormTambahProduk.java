package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.things.Adapter.AdapterTambahProduk;
import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Interface.FormTambahProdukInterface;
import com.example.things.Presenter.FormTambahProdukPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityFormTambahProdukBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class FormTambahProduk extends AppCompatActivity implements FormTambahProdukInterface {

    private ActivityFormTambahProdukBinding binding;
    private FormTambahProdukPresenter presenter;
    private SessionManager sessionManager;
    ArrayList<String> spinnerKategori, spinnerUkuran, spinnerMerek;
    ArrayAdapter<String> adapterKategori, adapterUkuran, adapterMerek;

    String[] kategori = {"Pilih", "Atasan Wanita", "Atasan Pria", "Bawahan Wanita", "Bawahan Pria", "Sepatu/Sandal Wanita", "Sepatu/Sandal Pria", "Tas Wanita", "Tas Pria", "Aksesoris Wanita", "Aksesoris Pria"};

//    String[] ukuran = {"Pilih", "S", "M", "L", "XL", "XXL", "30", "31", "32", "33", "34", "35", "37", "38", "39", "40", "41", "42", "43"};
//
//    String[] merek = {"Pilih", "Cole", "Nevada", "Cardinal", "Details", "Bombboogie", "American Jeans", "Converse", "Jackson", "Nevada Sport", "Trave Time", "Connexion", "H&M", "Pull&bear"};

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormTambahProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inisialisasi();


        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        AllButton();

    }

    private void inisialisasi() {
        presenter = new FormTambahProdukPresenter(this);
        sessionManager = new SessionManager(this);

        spinnerKategori = new ArrayList<>();

        adapterKategori = new ArrayAdapter<String>(this, R.layout.spinner_item,kategori);

        adapterKategori.setDropDownViewResource(R.layout.spinner_item);
    }

    private void AllButton() {
        binding.addKategori.setAdapter(adapterKategori);

        binding.btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormTambahProduk.this, KeranjangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormTambahProduk.this, SearchActtivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);

        binding.addImageProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String des = binding.addDes.getText().toString();
                    String harga = String.valueOf(binding.addHarga.getText().toString());
                    String kategori = binding.addKategori.getSelectedItem().toString();
                    String ukuran = binding.addUkuan.getText().toString();
                    String merek = binding.addMerek.getText().toString();

                    Calendar calendar = Calendar.getInstance();
                    Locale locale = new Locale ("in","ID");
                    String tglupload = new SimpleDateFormat("dd/MM/yyyy",locale).format(Calendar.getInstance().getTime());
                    String timeStamp2 = new SimpleDateFormat("HH:mm",locale).format(Calendar.getInstance().getTime());


                    if (binding.addImageProduk.getDrawable() != null) {
                        binding.addImageProduk.setDrawingCacheEnabled(true);
                        binding.addImageProduk.buildDrawingCache();
                        Bitmap bitmap = ((BitmapDrawable) binding.addImageProduk.getDrawable()).getBitmap();

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        String idP = uid+timeStamp2+tglupload;

                        if(TextUtils.isEmpty(des)){
                            binding.addDes.setError("Tolong Diisi");
                            binding.addDes.requestFocus();
                        } else if (TextUtils.isEmpty(harga)){
                            binding.addHarga.setError("Tolong Diisi");
                            binding.addHarga.requestFocus();
                        } else if (kategori.equals("Pilih")){
                            binding.addKategori.requestFocus();
                            Toast.makeText(FormTambahProduk.this, "Maaf Kategori belum dipilih", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(ukuran) && TextUtils.isEmpty(merek)){
                            binding.addUkuan.requestFocus();
                            binding.addUkuan.setText("-");
                            binding.addMerek.requestFocus();
                            binding.addMerek.setText("No Brand");
                        } else {
                            presenter.sendDatatoDaftarProduk(uid, des, harga, kategori, ukuran, merek, idP, tglupload, data);
                        }
                    } else {
                        Toast.makeText(FormTambahProduk.this, "Tolog masukan foto produk anda", Toast.LENGTH_SHORT).show();
                        return;
                    }
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
                // Foto dari kamera
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                binding.addImageProduk.setImageBitmap(imageBitmap);
                Glide.with(binding.addImageProduk).load(imageBitmap).centerCrop().into(binding.addImageProduk);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                // Foto dari galeri
                Uri selectedImage = data.getData();
//                binding.addImageProduk.setImageURI(selectedImage);
                binding.imgbg.setVisibility(View.GONE);
                Glide.with(binding.addImageProduk).load(selectedImage).centerCrop().into(binding.addImageProduk);
            }
        }
    }

//    private void uploadImageToFirebaseStorage(Bitmap imageBitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        data = baos.toByteArray();
//    }

    @Override
    public void dataAddedSuccessfully(String idP) {
        dialog.dismiss();
        Intent intent = new Intent(FormTambahProduk.this, PenjualanActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void dataAddedFailed() {
        dialog.dismiss();
    }

    @Override
    public void onProgress() {
        dialog.show();
    }
}
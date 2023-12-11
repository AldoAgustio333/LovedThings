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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.things.Adapter.AdapterTambahProduk;
import com.example.things.Interface.UpdateProdukInterface;
import com.example.things.Presenter.UpdateProdukPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityUpdateProdukBinding;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class UpdateProdukActivity extends AppCompatActivity implements UpdateProdukInterface {

    private ActivityUpdateProdukBinding binding;
    private UpdateProdukPresenter presenter;
    private SessionManager sessionManager;
    ArrayList<String> spinnerKategori, spinnerUkuran, spinnerMerek;
    ArrayAdapter<String> adapterKategori, adapterUkuran, adapterMerek;

    String[] kategori = {"Pilih", "Atasan Wanita", "Atasan Pria", "Bawahan Wanita", "Bawahan Pria", "Sepatu/Sandal Wanita", "Sepatu/Sandal Pria", "Tas Wanita", "Tas Pria", "Aksesoris Wanita", "Aksesoris Pria"};

    String[] ukuran = {"Pilih", "S", "M", "L", "XL", "XXL", "30", "31", "32", "33", "34", "35", "37", "38", "39", "40", "41", "42", "43"};

    String[] merek = {"Pilih", "Cole", "Nevada", "Cardinal", "Details", "Bombboogie", "American Jeans", "Converse", "Jackson", "Nevada Sport", "Trave Time", "Connexion", "H&M", "Pull&bear"};

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        String desU = intent.getStringExtra(AdapterTambahProduk.EXTRA_DES);
        String size = intent.getStringExtra(AdapterTambahProduk.EXTRA_UKURAN);
        String uid = intent.getStringExtra(AdapterTambahProduk.EXTRA_UID);
        String idP = intent.getStringExtra(AdapterTambahProduk.EXTRA_IDP);
        String kategoriU = intent.getStringExtra(AdapterTambahProduk.EXTRA_KATEGORI);
        String hargaU = intent.getStringExtra(AdapterTambahProduk.EXTRA_HARGA);
        String merekU = intent.getStringExtra(AdapterTambahProduk.EXTRA_MEREK);
        String imgP = intent.getStringExtra(AdapterTambahProduk.EXTRA_IMG);

        Glide.with(binding.addImageProduk).load(imgP).into(binding.addImageProduk);
        int hargaP = Integer.parseInt(hargaU);


        Toast.makeText(this, kategoriU, Toast.LENGTH_SHORT).show();
        binding.addHarga.setText(hargaU);

        binding.addDes.setText(desU);
        binding.addMerek.setText(merekU);
        binding.addUkuan.setText(size);

        presenter = new UpdateProdukPresenter(this);
        sessionManager = new SessionManager(this);

        spinnerKategori = new ArrayList<>();
        adapterKategori = new ArrayAdapter<String>(this, R.layout.spinner_item,kategori);
        adapterKategori.setDropDownViewResource(R.layout.spinner_item);
        binding.addKategori.setAdapter(adapterKategori);

        // Temukan indeks item yang sesuai dengan nilai dari activity lain
        int index = -1;
        for (int i = 0; i < kategori.length; i++) {
            if (kategori[i].equals(kategoriU)) {
                index = i;
                break;
            }
        }
        // Atur posisi item yang dipilih pada Spinner
        if (index != -1) {
            binding.addKategori.setSelection(index);
        }


        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        AllButton(idP, desU, hargaU, size, kategoriU, merekU);

    }



    private void AllButton(String idP, String desU, String hargaU, String size, String kategoriU, String merekU) {

        binding.btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProdukActivity.this, KeranjangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProdukActivity.this, SearchActtivity.class);
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
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteData(uid, idP);
            }
        });
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


                        if(TextUtils.isEmpty(des)){
                            binding.addDes.setError("Tolong Diisi");
                            binding.addDes.requestFocus();
                        } else if (!des.equals(desU)){
                            presenter.updateDesProduk(uid, idP, des);
                        }

                        if (TextUtils.isEmpty(harga)){
                            binding.addHarga.setError("Tolong Diisi");
                            binding.addHarga.requestFocus();
                        } else if (!harga.equals(hargaU)){
                            presenter.updateHargaProduk(uid, idP, hargaU);
                        }

                        if (kategori.equals("Pilih")){
                            binding.addKategori.requestFocus();
                            Toast.makeText(UpdateProdukActivity.this, "Tolong Pilih Kategori", Toast.LENGTH_SHORT).show();
                        } else if (!kategori.equals(kategoriU)){
                            presenter.updateKategoriProduk(uid, idP, kategori);
                        }

                        if (TextUtils.isEmpty(ukuran)){
                            binding.addUkuan.requestFocus();
                            binding.addUkuan.setError("Tolong Diisi");
                        } else if (!ukuran.equals(size)){
                            presenter.updateUkuranProduk(uid, idP, ukuran);
                        }

                        if (TextUtils.isEmpty(merek)){
                            binding.addMerek.requestFocus();
                            Toast.makeText(UpdateProdukActivity.this, "Tolong Pilih Merek", Toast.LENGTH_SHORT).show();
                        }else if (!merek.equals(hargaU)){
                            presenter.updateMerekProduk(uid, idP, merek);
                        }

                        if (binding.addImageProduk.getDrawable().equals(data)){
                            Toast.makeText(UpdateProdukActivity.this, "Tolong Pilih Merek", Toast.LENGTH_SHORT).show();
                        }else if(!binding.addImageProduk.getDrawable().equals(data)){
                            presenter.updateImage(uid, idP, data);
                        }

                    } else {
                        Toast.makeText(UpdateProdukActivity.this, "Tolog masukan foto produk anda", Toast.LENGTH_SHORT).show();
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
                Glide.with(binding.addImageProduk).load(imageBitmap).into(binding.addImageProduk);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                // Foto dari galeri
                Uri selectedImage = data.getData();
//                binding.addImageProduk.setImageURI(selectedImage);
                Glide.with(binding.addImageProduk).load(selectedImage).centerCrop().into(binding.addImageProduk);
            }
        }
    }

    @Override
    public void onProgress() {
        dialog.show();
    }

    @Override
    public void removeSuccess() {
        Toast.makeText(this, "Data produk berhasil dihapus", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void updateisSuccess() {
        dialog.dismiss();
        Toast.makeText(this, "Data berhasil di update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateisFailed() {
        dialog.dismiss();
        Toast.makeText(this, "Data gagal di update", Toast.LENGTH_SHORT).show();
    }
}

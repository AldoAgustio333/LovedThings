package com.example.things.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.things.Adapter.KategoriAdapter;
import com.example.things.Adapter.KategoriAdapter2;
import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Interface.DaftarProdukInterface;
import com.example.things.Model.ImageSliderModel;
import com.example.things.Model.KategoriModel;
import com.example.things.Model.KategoriModel2;
import com.example.things.Model.ProdukModel;
import com.example.things.Presenter.DaftarProdukPresenter;
import com.example.things.R;
import com.example.things.databinding.ActivityDaftarProdukBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaftarProduk extends AppCompatActivity implements DaftarProdukInterface {

    private ActivityDaftarProdukBinding binding;
    private DaftarProdukPresenter presenter;
    private ProdukAdapter adapter;

    KategoriAdapter2 adapter2;
    List<KategoriModel2> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaftarProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String kategori = intent.getStringExtra(KategoriAdapter.EXTRA_KATEGORI);
        String type = intent.getStringExtra(KategoriAdapter.EXTRA_TYPE);

        presenter = new DaftarProdukPresenter(this);

        inisialisasiDaftarKategori();

        ArrayList<KategoriModel2> array = new ArrayList<>();
        String cek = kategori + " " + type;

        AllButton(cek);


        adapter2 = new KategoriAdapter2(getApplicationContext(), array);
//        adapter2.selected(cek);
        int selectedItem = getSelectedItemIndex(cek, array);
        // Set item yang dipilih pada adapter
        adapter2.setSelectedItem(selectedItem);

    }

    private void AllButton(String cek) {
        DefaultConditionKategori(cek);
        CondisionKategoriOnClick(cek);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void CondisionKategoriOnClick(String cek) {
        binding.txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("All");
                presenter.All();
            }
        });
        binding.txtAtasanCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Atasan Wanita");
                presenter.getProductsByCategory("Atasan Wanita");
            }
        });

        binding.txtBawahanCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Bawahan Wanita");
                presenter.getProductsByCategory("Bawahan Wanita");
            }
        });
        binding.txtSepatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Sepatu/Sandal Wanita");
                presenter.getProductsByCategory("Sepatu/Sandal Wanita");
            }
        });
        binding.txtTasCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Tas Wanita");
                presenter.getProductsByCategory("Tas Wanita");
            }
        });
        binding.txtAksesorisCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Aksesoris Wanita");
                presenter.getProductsByCategory("Aksesoris Wanita");
            }
        });
        binding.txtAtasanCowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Atasan Pria");
                presenter.getProductsByCategory("Atasan Pria");
            }
        });
        binding.txtBawahanCowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Bawahan Pria");
                presenter.getProductsByCategory("Bawahan Pria");
            }
        });
        binding.txtSepatuPria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Sepatu/Sandal Pria");
                presenter.getProductsByCategory("Sepatu/Sandal Pria");
            }
        });
        binding.txtTasCowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                DefaultConditionKategori("Tas Pria");
                presenter.getProductsByCategory("Tas Pria");
            }
        });
        binding.txtAksesorisCowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
                binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill));
                DefaultConditionKategori("Aksesoris Pria");
                presenter.getProductsByCategory("Aksesoris Pria");
            }
        });
    }

    private void DefaultConditionKategori(String cek) {
        if (cek.equals("Atasan Wanita")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Atasan Wanita");
        } else if (cek.equals("Bawahan Wanita")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Bawahan Wanita");
        } else if (cek.equals("Sepatu/Sandal Wanita")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Sepatu/Sandal Wanita");
        } else if (cek.equals("Tas Wanita")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Tas Wanita");
        } else if (cek.equals("Aksesoris Wanita")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Aksesoris Wanita");
        } else if (cek.equals("Atasan Pria")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Atasan Pria");
        } else if (cek.equals("Bawahan Pria")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Bawahan Pria");
        } else if (cek.equals("Sepatu/Sandal Pria")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Sepatu/Sandal Pria");
        } else if (cek.equals("Tas Pria")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.getProductsByCategory("Tas Pria");
        } else if (cek.equals("Aksesoris Pria")){
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill));
            presenter.getProductsByCategory("Aksesoris Pria");
        } else {
            binding.txtAll.setBackground(getDrawable(R.drawable.btn_fill));
            binding.txtAtasanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatu.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCW.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAtasanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtBawahanCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtSepatuPria.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtTasCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            binding.txtAksesorisCowo.setBackground(getDrawable(R.drawable.btn_fill2));
            presenter.All();
        }
    }

    private int getSelectedItemIndex(String cek, ArrayList<KategoriModel2> array) {
        // Temukan indeks item yang sesuai dengan kategori dan jenisKelamin
        int index = 0;
        for (KategoriModel2 model : array) {
            if (model.getNama().equals(cek)){
                break;
            }
            index++;
        }
        return index;
    }

    private void inisialisasiDaftarKategori() {
        // Misalkan, definisikan array kategori Anda
        KategoriModel2[] daftarKategori = new KategoriModel2[] {
                new KategoriModel2("All"),
                new KategoriModel2("Atasan Wanita"),
                new KategoriModel2("Bawahan Wanita"),
                new KategoriModel2("Sepatu/Sandal Wanita"),
                new KategoriModel2("Tas Wanita"),
                new KategoriModel2("Aksesoris Wanita"),
                new KategoriModel2("Atasan Pria"),
                new KategoriModel2("Bawahan Pria"),
                new KategoriModel2("Sepatu/Sandal Pria"),
                new KategoriModel2("Tas Pria"),
                new KategoriModel2("Aksesoris Pria")
                // tambahkan kategori lainnya
        };

        // Inisialisasi adapter untuk pria dan wanita
        KategoriAdapter2 adapter = new KategoriAdapter2(getApplicationContext(), Arrays.asList(daftarKategori));
    }

    @Override
    public void daftarProdukResponse(ArrayList<ProdukModel> produk) {

//        adapter.clearData();

        adapter = new ProdukAdapter(getApplicationContext(), produk);

        binding.recyclerProduk.setAdapter(adapter);
        binding.recyclerProduk.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void messageError() {
        Toast.makeText(this, "Maaf sistem gagal memuat data", Toast.LENGTH_SHORT).show();
    }
}
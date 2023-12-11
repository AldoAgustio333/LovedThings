package com.example.things.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.things.Adapter.AdapterTambahProduk;
import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Interface.TambahProdukInterface;
import com.example.things.Interface.itemClick.OnTambahProdukClickListener;
import com.example.things.Model.ProdukModel;
import com.example.things.Presenter.TambahProdukPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.FragmentPencarianBinding;
import com.example.things.databinding.FragmentTambahProdukBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TambahProduk extends Fragment implements TambahProdukInterface {

   private FragmentTambahProdukBinding binding;
    private AdapterTambahProduk adapterTambahProduk;
    private List<ProdukModel> produkList;
    TambahProdukPresenter presenter;
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTambahProdukBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        presenter = new TambahProdukPresenter(this);
        sessionManager = new SessionManager(getContext());

        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        presenter.getlistProduk(uid);

        return view;
    }

    @Override
    public void daftarProdukResponse(ArrayList<ProdukModel> produk) {
        adapterTambahProduk = new AdapterTambahProduk(getContext(), produk);
        binding.recPenjualan.setAdapter(adapterTambahProduk);
        binding.recPenjualan.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterTambahProduk.notifyDataSetChanged();
    }

    @Override
    public void messageError() {

    }
}
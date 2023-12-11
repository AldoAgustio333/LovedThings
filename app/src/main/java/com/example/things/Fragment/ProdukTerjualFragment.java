package com.example.things.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.things.Activity.KeranjangActivity;
import com.example.things.Adapter.AdapterProdukTerjual;
import com.example.things.Adapter.AdapterTambahProduk;
import com.example.things.Adapter.KeranjangAdapter;
import com.example.things.Interface.ProdukTerjualInterface;
import com.example.things.Model.ProdukTerjualModel;
import com.example.things.Presenter.ProdukTerjualPresenter;
import com.example.things.Presenter.TambahProdukPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.FragmentProdukTerjualBinding;

import java.util.ArrayList;


public class ProdukTerjualFragment extends Fragment implements ProdukTerjualInterface {

    private FragmentProdukTerjualBinding binding;
    private ProdukTerjualPresenter presenter;
    private AdapterProdukTerjual adapter;
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProdukTerjualBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        presenter = new ProdukTerjualPresenter(this);
        sessionManager = new SessionManager(getContext());

        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        presenter.getlistProduk(uid);

        return view;
    }

    @Override
    public void daftarProdukResponse(ArrayList<ProdukTerjualModel> produk) {
        adapter = new AdapterProdukTerjual(getContext(), produk);
        binding.recPenjualan.setAdapter(adapter);
        binding.recPenjualan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void messageError() {

    }
}
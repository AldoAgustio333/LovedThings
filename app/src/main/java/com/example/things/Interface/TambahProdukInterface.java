package com.example.things.Interface;

import com.example.things.Model.ProdukModel;

import java.util.ArrayList;

public interface TambahProdukInterface {
    void daftarProdukResponse(ArrayList<ProdukModel> produk);

    void messageError();
}

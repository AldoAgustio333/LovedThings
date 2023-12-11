package com.example.things.Interface;

import com.example.things.Model.ProdukModel;

import java.util.ArrayList;

public interface DaftarProdukInterface {
    void daftarProdukResponse(ArrayList<ProdukModel> produk);

    void messageError();
}

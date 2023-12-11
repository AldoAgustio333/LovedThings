package com.example.things.Interface;

import com.example.things.Model.ProdukTerjualModel;

import java.util.ArrayList;

public interface ProdukTerjualInterface {
    void daftarProdukResponse(ArrayList<ProdukTerjualModel> produk);

    void messageError();
}

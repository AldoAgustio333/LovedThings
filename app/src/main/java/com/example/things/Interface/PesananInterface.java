package com.example.things.Interface;

import com.example.things.Model.PesananModel;

import java.util.ArrayList;

public interface PesananInterface {
    void daftarProdukResponse(ArrayList<PesananModel> models);

    void messageError();
}

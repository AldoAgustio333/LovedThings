package com.example.things.Interface;

import com.example.things.Model.KeranjangModel;

import java.util.ArrayList;

public interface KeranjangInterface {
    void daftarKeranjang(ArrayList<KeranjangModel> keranjang);

    void daftarKeranjangEmpty();

    void reviewDataProduk(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String namaPenjual, String uidPenjual, String alamatPenjual, String noHpPenjual, String fotoPenjual);

    void dataIsGone(String uid, String idP);
}

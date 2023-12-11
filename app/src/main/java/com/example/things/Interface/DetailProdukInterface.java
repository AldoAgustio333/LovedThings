package com.example.things.Interface;

public interface DetailProdukInterface {
    void dataUserResponses(String email, String username, String nohp, String type, String alamat, String uid, String profileimage);

    void reviewDataProduk(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU);

    void dataIsGone();

    void dataAddedSuccessfully();

    void dataAddedFailed();

    void reviewDataProdukForBuyNow(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU);

    void reviewDataProdukForCgat(String merek, String kategori, String des, String harga, String idP, String uid, String imgP, String ukuran, String nama, String uidU, String alamatU, String nohpU, String imgU);
}

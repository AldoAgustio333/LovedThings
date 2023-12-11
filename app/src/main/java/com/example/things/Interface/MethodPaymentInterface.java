package com.example.things.Interface;

public interface MethodPaymentInterface {
    void reviewDataProduk(String totalBayar, String method, String hargaKirim, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga);

    void dataIsGone(String idP, String idUser);

    void dataAddedSuccessfully(String idP);

    void dataAddedFailed();

    void removeSuccess();

    void onProgress();
}

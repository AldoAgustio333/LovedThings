package com.example.things.Interface;

public interface PaymentInterface {

    void reviewDataProduk(String jml, String method, String hargaKirim, String lokasi, String idP, String idUser, String uidPenjual, String merek, String kategori, String des, String imgP, String ukuran, String namaPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String harga);

    void dataIsGone(String idUser, String idP);
}

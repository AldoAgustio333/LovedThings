package com.example.things.Model;

public class ProdukModel {
    String idP, deskripsi, kategori, merek, ukuran, img_produk, uid, tglUpload;
    int harga;


    // Konstruktor default tanpa argumen
    public ProdukModel() {
        // Diperlukan oleh Firebase untuk deserialisasi
    }

    public ProdukModel(String idP, String deskripsi, String kategori, String merek, String ukuran, String img_produk, String uid, String tglUpload, int harga) {
        this.idP = idP;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.merek = merek;
        this.ukuran = ukuran;
        this.img_produk = img_produk;
        this.uid = uid;
        this.tglUpload = tglUpload;
        this.harga = harga;
    }

    public String getTglUpload() {
        return tglUpload;
    }

    public void setTglUpload(String tglUpload) {
        this.tglUpload = tglUpload;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getImg_produk() {
        return img_produk;
    }

    public void setImg_produk(String img_produk) {
        this.img_produk = img_produk;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

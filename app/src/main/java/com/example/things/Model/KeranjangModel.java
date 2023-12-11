package com.example.things.Model;

public class KeranjangModel {

    String idP, uid, merek, kategori, deskripsi, img_produk, ukuran, namaPenjual, uidPenjual, alamatPenjual, nohpPenjual, fotoPenjual, tglUpload;
    int harga;

    public KeranjangModel() {
    }

    public KeranjangModel(String idP, String uid, String merek, String kategori, String deskripsi, String img_produk, String ukuran, String namaPenjual, String uidPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String tglUpload, int harga) {
        this.idP = idP;
        this.uid = uid;
        this.merek = merek;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.img_produk = img_produk;
        this.ukuran = ukuran;
        this.namaPenjual = namaPenjual;
        this.uidPenjual = uidPenjual;
        this.alamatPenjual = alamatPenjual;
        this.nohpPenjual = nohpPenjual;
        this.fotoPenjual = fotoPenjual;
        this.tglUpload = tglUpload;
        this.harga = harga;
    }

    public String getTglUpload() {
        return tglUpload;
    }

    public void setTglUpload(String tglUpload) {
        this.tglUpload = tglUpload;
    }

    public String getFotoPenjual() {
        return fotoPenjual;
    }

    public void setFotoPenjual(String fotoPenjual) {
        this.fotoPenjual = fotoPenjual;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImg_produk() {
        return img_produk;
    }

    public void setImg_produk(String img_produk) {
        this.img_produk = img_produk;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getNamaPenjual() {
        return namaPenjual;
    }

    public void setNamaPenjual(String namaPenjual) {
        this.namaPenjual = namaPenjual;
    }

    public String getUidPenjual() {
        return uidPenjual;
    }

    public void setUidPenjual(String uidPenjual) {
        this.uidPenjual = uidPenjual;
    }

    public String getAlamatPenjual() {
        return alamatPenjual;
    }

    public void setAlamatPenjual(String alamatPenjual) {
        this.alamatPenjual = alamatPenjual;
    }

    public String getNohpPenjual() {
        return nohpPenjual;
    }

    public void setNohpPenjual(String nohpPenjual) {
        this.nohpPenjual = nohpPenjual;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}

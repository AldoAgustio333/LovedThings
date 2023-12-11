package com.example.things.Model;

public class ChatModel {

    String idP, idm, uidUser, uidOther, tgl, jam, kategori, merek, ukuran, imageP, namaUser, namaOther, imgUser, imgOther, pesan, harga;


    public ChatModel() {
    }

    public ChatModel(String idP, String idm, String uidUser, String uidOther, String tgl, String jam, String kategori, String merek, String ukuran, String imageP, String namaUser, String namaOther, String imgUser, String imgOther, String pesan, String harga) {
        this.idP = idP;
        this.idm = idm;
        this.uidUser = uidUser;
        this.uidOther = uidOther;
        this.tgl = tgl;
        this.jam = jam;
        this.kategori = kategori;
        this.merek = merek;
        this.ukuran = ukuran;
        this.imageP = imageP;
        this.namaUser = namaUser;
        this.namaOther = namaOther;
        this.imgUser = imgUser;
        this.imgOther = imgOther;
        this.pesan = pesan;
        this.harga = harga;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getIdm() {
        return idm;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }

    public String getUidUser() {
        return uidUser;
    }

    public void setUidUser(String uidUser) {
        this.uidUser = uidUser;
    }

    public String getUidOther() {
        return uidOther;
    }

    public void setUidOther(String uidOther) {
        this.uidOther = uidOther;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
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

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaOther() {
        return namaOther;
    }

    public void setNamaOther(String namaOther) {
        this.namaOther = namaOther;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getImgOther() {
        return imgOther;
    }

    public void setImgOther(String imgOther) {
        this.imgOther = imgOther;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}

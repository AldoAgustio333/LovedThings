package com.example.things.Model;

public class UserChatModel {
    String uid, tgl, pesan, namaUser, jam, imgUser, harga, deskripsi, kategori, merek, ukuran;
    
    public UserChatModel() {
    }

    public UserChatModel(String uid, String tgl, String pesan, String namaUser, String jam, String imgUser) {
        this.uid = uid;
        this.tgl = tgl;
        this.pesan = pesan;
        this.namaUser = namaUser;
        this.jam = jam;
        this.imgUser = imgUser;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}

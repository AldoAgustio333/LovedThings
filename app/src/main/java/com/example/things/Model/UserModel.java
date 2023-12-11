package com.example.things.Model;

public class UserModel {
    String email, nama, nohp, alamat, img;

    public UserModel() {
    }

    public UserModel(String email, String nama, String nohp, String alamat, String img) {
        this.email = email;
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

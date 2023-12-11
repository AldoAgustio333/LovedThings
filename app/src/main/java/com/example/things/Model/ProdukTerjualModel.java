package com.example.things.Model;

public class ProdukTerjualModel {
    String idP, uid, tgl, kategori, merek, ukuran, status, alamat_pemebeli,  nohp, metode_pembayaran, imageP,nama_pembeli;
    int total_harga;

    public ProdukTerjualModel() {
    }

    public ProdukTerjualModel(String idP, String uid, String tgl, String kategori, String merek, String ukuran, String status, String alamat_pemebeli, String nohp, String metode_pembayaran, String imageP, String nama_pembeli, int total_harga) {
        this.idP = idP;
        this.uid = uid;
        this.tgl = tgl;
        this.kategori = kategori;
        this.merek = merek;
        this.ukuran = ukuran;
        this.status = status;
        this.alamat_pemebeli = alamat_pemebeli;
        this.nohp = nohp;
        this.metode_pembayaran = metode_pembayaran;
        this.imageP = imageP;
        this.nama_pembeli = nama_pembeli;
        this.total_harga = total_harga;
    }

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    public String getNama_pembeli() {
        return nama_pembeli;
    }

    public void setNama_pembeli(String nama_pembeli) {
        this.nama_pembeli = nama_pembeli;
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

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamat_pemebeli() {
        return alamat_pemebeli;
    }

    public void setAlamat_pemebeli(String alamat_pemebeli) {
        this.alamat_pemebeli = alamat_pemebeli;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getMetode_pembayaran() {
        return metode_pembayaran;
    }

    public void setMetode_pembayaran(String metode_pembayaran) {
        this.metode_pembayaran = metode_pembayaran;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }
}

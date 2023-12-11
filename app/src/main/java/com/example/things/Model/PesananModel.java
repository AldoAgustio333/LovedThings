package com.example.things.Model;

public class PesananModel {

    String idP, uid, merek, kategori, deskripsi, img_produk, ukuran, namaPenjual, uidPenjual, alamatPenjual, nohpPenjual, fotoPenjual, lokasi, methodPembayaran, status, tglPemesanan;
    int harga, total, hargaPengiriman;

    public PesananModel() {
    }

    public PesananModel(String idP, String uid, String merek, String kategori, String deskripsi, String img_produk, String ukuran, String namaPenjual, String uidPenjual, String alamatPenjual, String nohpPenjual, String fotoPenjual, String lokasi, String methodPembayaran, String status, String tglPemesanan, int harga, int total, int hargaPengiriman) {
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
        this.lokasi = lokasi;
        this.methodPembayaran = methodPembayaran;
        this.status = status;
        this.tglPemesanan = tglPemesanan;
        this.harga = harga;
        this.total = total;
        this.hargaPengiriman = hargaPengiriman;
    }

    public int getHargaPengiriman() {
        return hargaPengiriman;
    }

    public void setHargaPengiriman(int hargaPengiriman) {
        this.hargaPengiriman = hargaPengiriman;
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

    public String getFotoPenjual() {
        return fotoPenjual;
    }

    public void setFotoPenjual(String fotoPenjual) {
        this.fotoPenjual = fotoPenjual;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getMethodPembayaran() {
        return methodPembayaran;
    }

    public void setMethodPembayaran(String methodPembayaran) {
        this.methodPembayaran = methodPembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglPemesanan() {
        return tglPemesanan;
    }

    public void setTglPemesanan(String tglPemesanan) {
        this.tglPemesanan = tglPemesanan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

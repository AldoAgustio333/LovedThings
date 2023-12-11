package com.example.things.Adapter;

import com.example.things.Model.ChatModel;

import java.util.ArrayList;

public interface ChatInterface {
    void daftarChatEmpty(String des, String pesan, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String penjual, String merek, String kategori, String harga, String ukuran);

    void daftarChatResponse(ArrayList<ChatModel> chat);
    void updateDaftarChat(String idP);

    void messageError();

    void reviewChat(String des, String pesan, String idP, String uid, String saveCurrentDate, String nama, String img, String idm, String jam, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String merek, String kategori, String harga, String ukuran);

    void daftarChatEmpty(String des, String idP, String imgPenjual, String uidPenjual, String imgProduk, String namaPenjual, String uidPenjual1, String merek, String kategori, String harga, String ukuran);
}

package com.example.things.Interface;

import com.example.things.Model.UserChatModel;

import java.util.ArrayList;

public interface DaftarChatInterface {
    void daftarProdukResponse(ArrayList<UserChatModel> modelChat);

    void messageError();
}

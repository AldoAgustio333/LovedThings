package com.example.things.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class KeranjangVIewHolder extends RecyclerView.ViewHolder {

    public TextView addUsername, addAlamat, addKategori, addMerek, addSize, addHarga, addTotal, btnCO;
    public ImageView imgUser, btnChat, btnDelete, imgProduk;

    public KeranjangVIewHolder(@NotNull View itemView) {
        super(itemView);
        addUsername = itemView.findViewById(R.id.addUsername);
        addAlamat = itemView.findViewById(R.id.addAlamat);
        addKategori = itemView.findViewById(R.id.addKategori);
        addMerek = itemView.findViewById(R.id.addMerek);
        addSize = itemView.findViewById(R.id.addSize);
        addHarga = itemView.findViewById(R.id.addHarga);
        addTotal = itemView.findViewById(R.id.addTotal);
        btnCO = itemView.findViewById(R.id.btnCO);
        imgProduk = itemView.findViewById(R.id.imgProduk);
        btnChat = itemView.findViewById(R.id.btnChat);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        imgUser = itemView.findViewById(R.id.imgUser);
    }
}

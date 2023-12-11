package com.example.things.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class ProdukTerjualViewHolder extends RecyclerView.ViewHolder {

    public TextView addKategori, addMerek, addSize, addStatus, addTanggal, addPembeli, addNohp, addTotalHarga, addMethod;
    public ImageView imgProduk;

    public ProdukTerjualViewHolder(@NotNull View itemView) {
        super(itemView);
        addKategori = itemView.findViewById(R.id.addKategori);
        addMerek = itemView.findViewById(R.id.addMerek);
        addSize = itemView.findViewById(R.id.addSize);
        addTanggal = itemView.findViewById(R.id.addTanggal);
        addStatus = itemView.findViewById(R.id.addStatus);
        addPembeli = itemView.findViewById(R.id.addPembeli);
        addNohp = itemView.findViewById(R.id.addNohp);
        addTotalHarga = itemView.findViewById(R.id.addTotalHarga);
        addMethod = itemView.findViewById(R.id.addMethod);
        imgProduk = itemView.findViewById(R.id.imgProduk);

    }
}

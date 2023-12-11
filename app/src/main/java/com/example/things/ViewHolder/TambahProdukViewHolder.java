package com.example.things.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class TambahProdukViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout btnTambah;


    public TambahProdukViewHolder(@NotNull View itemView) {
        super(itemView);

        btnTambah = itemView.findViewById(R.id.btnTambah);
    }
}

package com.example.things.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class KategoriHolder extends RecyclerView.ViewHolder {

    public TextView txtKategori;

    public KategoriHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        txtKategori = itemView.findViewById(R.id.txtKategori);
    }
}

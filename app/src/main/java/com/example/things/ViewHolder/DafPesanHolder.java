package com.example.things.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class DafPesanHolder extends RecyclerView.ViewHolder {

    public TextView addWaktu, addNama,addPesan;
    public ImageView addPP;

    public DafPesanHolder(@NotNull View itemView) {
        super(itemView);

        addWaktu = itemView.findViewById(R.id.addWaktu);
        addNama = itemView.findViewById(R.id.addUsername);
        addPesan = itemView.findViewById(R.id.addPesan);
        addPP = itemView.findViewById(R.id.imgUser);
    }
}

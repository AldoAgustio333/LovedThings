package com.example.things.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderItem2 extends RecyclerView.ViewHolder {

    public ImageView imgBarang;
    public TextView txtBarang, addSize, addHarga;

    public ViewHolderItem2(@NotNull View itemView) {
        super(itemView);
        imgBarang = itemView.findViewById(R.id.imgBarang);
        txtBarang = itemView.findViewById(R.id.txtBarang);
        addSize = itemView.findViewById(R.id.addSize);
        addHarga = itemView.findViewById(R.id.addHarga);
    }
}

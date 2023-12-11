package com.example.things.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderItem1 extends RecyclerView.ViewHolder {

    public ImageView imgBarang;
    public TextView txtBarang;

    public ViewHolderItem1(@NotNull View itemView) {
        super(itemView);
        imgBarang = itemView.findViewById(R.id.imgBarang);
        txtBarang = itemView.findViewById(R.id.txtBarang);
    }
}

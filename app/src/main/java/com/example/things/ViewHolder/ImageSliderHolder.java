package com.example.things.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.things.R;

import org.jetbrains.annotations.NotNull;

public class ImageSliderHolder extends RecyclerView.ViewHolder {

    public ImageView imgIklan;

    public ImageSliderHolder(@NotNull View itemView) {
        super(itemView);
        imgIklan = itemView.findViewById(R.id.img);
    }
}

package com.example.things.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Model.ImageSliderModel;
import com.example.things.R;
import com.example.things.ViewHolder.ImageSliderHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderHolder> {

    private final List<ImageSliderModel> imageUrls;

    public ImageSliderAdapter(ArrayList<ImageSliderModel> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NotNull
    @Override
    public ImageSliderHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ImageSliderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imgslider, parent, false));

    }

    @Override
    public void onBindViewHolder(@NotNull ImageSliderHolder holder, int position) {
        ImageSliderModel imageUrl = imageUrls.get(position);

        // Menggunakan Glide untuk memuat gambar dari URL
        Glide.with(holder.imgIklan)
                .load(imageUrl.getUrl())
                .into(holder.imgIklan);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}

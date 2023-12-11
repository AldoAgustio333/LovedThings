package com.example.things.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Model.ProdukModel;
import com.example.things.Model.ProdukTerjualModel;
import com.example.things.R;
import com.example.things.ViewHolder.ProdukTerjualViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterProdukTerjual extends RecyclerView.Adapter<ProdukTerjualViewHolder> {

    Context context;
    List<ProdukTerjualModel> models;

    public AdapterProdukTerjual(Context context, List<ProdukTerjualModel> models) {
        this.context = context;
        this.models = models;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ProdukTerjualViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ProdukTerjualViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk_terjual, parent,false));
    }

    @Override
    public void onBindViewHolder(@NotNull ProdukTerjualViewHolder holder, int position) {
        ProdukTerjualModel model = models.get(position);
        String total = String.valueOf(model.getTotal_harga());

        holder.addKategori.setText(model.getKategori());
        holder.addMerek.setText(model.getMerek());
        holder.addMethod.setText(model.getMetode_pembayaran());
        holder.addSize.setText(model.getUkuran());
        holder.addNohp.setText(model.getNohp());
        holder.addTanggal.setText(model.getTgl());
        holder.addStatus.setText(model.getStatus());
        holder.addTotalHarga.setText("Rp."+total);
        holder.addPembeli.setText(model.getNama_pembeli());

        Glide.with(holder.imgProduk).load(model.getImageP()).centerCrop().into(holder.imgProduk);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

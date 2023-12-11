package com.example.things.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Activity.DetailProduk;
import com.example.things.Model.ProdukModel;
import com.example.things.R;
import com.example.things.ViewHolder.ViewHolderItem2;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ViewHolderItem2> {

    Context context;
    List<ProdukModel> models;

    public static final String EXTRA_IDP= "idP";
    public static final String EXTRA_UID = "uid";
    public static final String EXTRA_KATEGORI = "kategori";
    public static final String EXTRA_MEREK = "merek";
    public static final String EXTRA_UKURAN = "ukuran";
    public static final String EXTRA_IMG = "img_produk";
    public static final String EXTRA_DES = "deskripsi";
    public static final String EXTRA_HARGA = "harga";


    // Tambahkan metode addData untuk menambahkan data ke adapter
    public void addData(List<ProdukModel> newData) {
        models.addAll(newData);
        notifyDataSetChanged();
    }

    public ProdukAdapter(Context context, List<ProdukModel> models) {
        this.context = context;
        this.models = models;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolderItem2 onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolderItem2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang2, parent,false));
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolderItem2 holder, int position) {
        ProdukModel model = models.get(position);
        String harga = String.valueOf(model.getHarga());

        holder.txtBarang.setText(model.getMerek());
        holder.addHarga.setText(String.valueOf(model.getHarga()));
        holder.addSize.setText(model.getUkuran());

        Glide.with(holder.imgBarang).load(model.getImg_produk()).into(holder.imgBarang);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProduk.class);
                intent.putExtra(EXTRA_IMG, model.getImg_produk());
                intent.putExtra(EXTRA_DES, model.getDeskripsi());
                intent.putExtra(EXTRA_HARGA, harga);
                intent.putExtra(EXTRA_IDP, model.getIdP());
                intent.putExtra(EXTRA_UID, model.getUid());
                intent.putExtra(EXTRA_UKURAN, model.getUkuran());
                intent.putExtra(EXTRA_KATEGORI, model.getKategori());
                intent.putExtra(EXTRA_MEREK, model.getMerek());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void clearData() {
        models.clear();
        notifyDataSetChanged();
    }

}

package com.example.things.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Activity.DaftarProduk;
import com.example.things.Model.KategoriModel;
import com.example.things.Model.KategoriModel2;
import com.example.things.R;
import com.example.things.ViewHolder.KategoriHolder;
import com.example.things.ViewHolder.ViewHolderItem1;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KategoriAdapter2 extends RecyclerView.Adapter<KategoriHolder> {
    private int selectedItem = 0; // Menyimpan indeks item yang dipilih
    Context context;
    List<KategoriModel2> model;

    public KategoriAdapter2(Context context, List<KategoriModel2> model) {
        this.context = context;
        this.model = model;
    }

    @NotNull
    @Override
    public KategoriHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new KategoriHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull KategoriHolder holder, int position) {
        KategoriModel2 getmodel = model.get(position);

        holder.txtKategori.setText(getmodel.getNama());


        // Set latar belakang berdasarkan status item yang dipilih
        if (position == selectedItem) {
            holder.txtKategori.setBackgroundResource(R.drawable.btn_fill);
        } else {
            holder.txtKategori.setBackgroundResource(R.drawable.btn_fill2);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set item yang dipilih ke posisi yang baru
                setSelectedItem(position);

                // Tambahkan logika lain yang sesuai dengan kebutuhan
                // ...
            }
        });
    }


    @Override
    public int getItemCount() {
        if(model != null){
            return model.size();
        } return 0;
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged(); // Memberi tahu adapter bahwa data telah berubah
    }
}

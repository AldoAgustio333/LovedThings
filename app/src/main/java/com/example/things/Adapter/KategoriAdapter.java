package com.example.things.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Activity.DaftarProduk;
import com.example.things.Model.KategoriModel;
import com.example.things.R;
import com.example.things.ViewHolder.ViewHolderItem1;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<ViewHolderItem1> {

    Context context;
    List<KategoriModel> model;
    public static final String EXTRA_KATEGORI= "kategori";
    public static final String EXTRA_TYPE= "type";

    public KategoriAdapter(Context context, List<KategoriModel> model) {
        this.context = context;
        this.model = model;
    }

    @NotNull
    @Override
    public ViewHolderItem1 onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolderItem1(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolderItem1 holder, int position) {
        KategoriModel getmodel = model.get(position);

        wanita(getmodel, holder);
        pria(getmodel, holder);


    }

    private void pria(KategoriModel getmodel, ViewHolderItem1 holder) {
        if(getmodel.getType().equals("Pria")){
            holder.txtBarang.setText(getmodel.getNama());
            Glide.with(holder.imgBarang).load(getmodel.getGambarResID()).into(holder.imgBarang);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DaftarProduk.class);
                    intent.putExtra(EXTRA_KATEGORI, getmodel.getNama());
                    intent.putExtra(EXTRA_TYPE, getmodel.getType());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    private void wanita(KategoriModel getmodel, ViewHolderItem1 holder) {
        if(getmodel.getType().equals("Wanita")){
            holder.txtBarang.setText(getmodel.getNama());
            Glide.with(holder.imgBarang).load(getmodel.getGambarResID()).into(holder.imgBarang);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DaftarProduk.class);
                    intent.putExtra(EXTRA_KATEGORI, getmodel.getNama());
                    intent.putExtra(EXTRA_TYPE, getmodel.getType());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(model != null){
            return model.size();
        } return 0;
    }
}

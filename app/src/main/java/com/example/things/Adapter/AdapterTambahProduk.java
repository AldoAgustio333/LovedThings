//package com.example.things.Adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.things.Activity.DetailProduk;
//import com.example.things.Interface.itemClick.OnTambahProdukClickListener;
//import com.example.things.Model.ProdukModel;
//import com.example.things.R;
//import com.example.things.ViewHolder.TambahProdukViewHolder;
//import com.example.things.ViewHolder.ViewHolderItem2;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.List;
//
//public class AdapterTambahProduk extends RecyclerView.Adapter<ViewHolderItem2> {
//
//    Context context;
//    List<ProdukModel> models;
//
//    public static final String EXTRA_IDP= "idP";
//    public static final String EXTRA_UID = "uid";
//    public static final String EXTRA_KATEGORI = "kategori";
//    public static final String EXTRA_MEREK = "merek";
//    public static final String EXTRA_UKURAN = "ukuran";
//    public static final String EXTRA_IMG = "img_produk";
//    public static final String EXTRA_DES = "deskripsi";
//    public static final String EXTRA_HARGA = "harga";
//
//    public AdapterTambahProduk(Context context, List<ProdukModel> models) {
//        this.context = context;
//        this.models = models;
//    }
//
//    @NotNull
//    @Override
//    public ViewHolderItem2 onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        return new ViewHolderItem2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang2, parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull ViewHolderItem2 holder, int position) {
//        ProdukModel model = models.get(position);
//        String harga = String.valueOf(model.getHarga());
//
//        holder.txtBarang.setText(model.getMerek());
//        holder.addHarga.setText(String.valueOf(model.getHarga()));
//        holder.addSize.setText(model.getUkuran());
//
//        Glide.with(holder.imgBarang).load(model.getImg_produk()).into(holder.imgBarang);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailProduk.class);
//                intent.putExtra(EXTRA_IMG, model.getImg_produk());
//                intent.putExtra(EXTRA_DES, model.getDeskripsi());
//                intent.putExtra(EXTRA_HARGA, harga);
//                intent.putExtra(EXTRA_IDP, model.getIdP());
//                intent.putExtra(EXTRA_UID, model.getUid());
//                intent.putExtra(EXTRA_UKURAN, model.getUkuran());
//                intent.putExtra(EXTRA_KATEGORI, model.getKategori());
//                intent.putExtra(EXTRA_MEREK, model.getMerek());
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return models.size();
//    }
//
//    public void clearData() {
//        models.clear();
//        notifyDataSetChanged();
//    }
//}

package com.example.things.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Activity.DetailProduk;
import com.example.things.Activity.FormTambahProduk;
import com.example.things.Activity.UpdateProdukActivity;
import com.example.things.Interface.itemClick.OnTambahProdukClickListener;
import com.example.things.Model.ProdukModel;
import com.example.things.R;
import com.example.things.ViewHolder.TambahProdukViewHolder;
import com.example.things.ViewHolder.ViewHolderItem2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterTambahProduk extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_PRODUCT = 0;
    private static final int VIEW_TYPE_ADD_ITEM = 1;


    public static final String EXTRA_IDP= "idP";
    public static final String EXTRA_UID = "uid";
    public static final String EXTRA_KATEGORI = "kategori";
    public static final String EXTRA_MEREK = "merek";
    public static final String EXTRA_UKURAN = "ukuran";
    public static final String EXTRA_IMG = "img_produk";
    public static final String EXTRA_DES = "deskripsi";
    public static final String EXTRA_HARGA = "harga";

    Context context;
    List<ProdukModel> models;

    public AdapterTambahProduk(Context context, List<ProdukModel> models) {
        this.context = context;
        // Tambahkan satu item dummy di posisi pertama
        this.models = new ArrayList<>();
        this.models.add(new ProdukModel());
        this.models.addAll(models);
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_PRODUCT:
                return new ViewHolderItem2(inflater.inflate(R.layout.item_barang2, parent, false));
            case VIEW_TYPE_ADD_ITEM:
                return new ViewHolderAddItem(inflater.inflate(R.layout.item_tambah_produk, parent, false));
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (position >= 0 && position < models.size()) {
            if (holder.getItemViewType() == VIEW_TYPE_PRODUCT) {
                ProdukModel model = models.get(position);
                String harga = String.valueOf(model.getHarga());

                ViewHolderItem2 itemHolder = (ViewHolderItem2) holder;
                itemHolder.txtBarang.setText(model.getMerek());
                itemHolder.addHarga.setText(harga);
                itemHolder.addSize.setText(model.getUkuran());

                Glide.with(itemHolder.imgBarang).load(model.getImg_produk()).into(itemHolder.imgBarang);

                itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UpdateProdukActivity.class);
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
            } else if (holder.getItemViewType() == VIEW_TYPE_ADD_ITEM) {
                ViewHolderAddItem addItemHolder = (ViewHolderAddItem) holder;
                addItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, FormTambahProduk.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ?  VIEW_TYPE_ADD_ITEM : VIEW_TYPE_PRODUCT;
    }

    public void clearData() {
        models.clear();
        notifyDataSetChanged();
    }

    // ViewHolder for product item
    public static class ViewHolderItem2 extends RecyclerView.ViewHolder {
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

    // ViewHolder for "add item" view
    public static class ViewHolderAddItem extends RecyclerView.ViewHolder {
        public ViewHolderAddItem(@NotNull View itemView) {
            super(itemView);
        }
    }
}



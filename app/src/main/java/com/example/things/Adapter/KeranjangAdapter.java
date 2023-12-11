package com.example.things.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.things.Interface.itemClick.RecyclerKeranjang;
import com.example.things.Model.KeranjangModel;
import com.example.things.R;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.KeranjangViewHolder> {

    Context context;
    List<KeranjangModel> models;
    RecyclerKeranjang listener;

    public KeranjangAdapter(Context context, List<KeranjangModel> models) {
        this.context = context;
        this.models = models;
        notifyDataSetChanged();
    }

    // Tambahkan setter untuk inisialisasi listener
    public void setListener(RecyclerKeranjang listener) {
        this.listener = listener;
    }

    @NotNull
    @Override
    public KeranjangAdapter.KeranjangViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new KeranjangAdapter.KeranjangViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull KeranjangAdapter.KeranjangViewHolder holder, int position) {
        KeranjangModel model = models.get(position);
        String harga = String.valueOf(model.getHarga());

        holder.addAlamat.setText(model.getAlamatPenjual());
        holder.addHarga.setText(harga);
        holder.addKategori.setText(model.getKategori());
        holder.addMerek.setText(model.getMerek());
        holder.addSize.setText(model.getUkuran());
        holder.addUsername.setText(model.getNamaPenjual());
        holder.addTotal.setText(harga);

        Glide.with(holder.imgUser).load(model.getFotoPenjual()).centerCrop().into(holder.imgUser);
        Glide.with(holder.imgProduk).load(model.getImg_produk()).centerCrop().into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        if(models != null){
            return models.size();
        } return 0;
    }

    public class KeranjangViewHolder extends RecyclerView.ViewHolder{

        public TextView addUsername, addAlamat, addKategori, addMerek, addSize, addHarga, addTotal, btnCO;
        public ImageView imgUser, btnChat, btnDelete, imgProduk;

        public KeranjangViewHolder(@NotNull View itemView) {
            super(itemView);

            addUsername = itemView.findViewById(R.id.addUsername);
            addAlamat = itemView.findViewById(R.id.addAlamat);
            addKategori = itemView.findViewById(R.id.addKategori);
            addMerek = itemView.findViewById(R.id.addMerek);
            addSize = itemView.findViewById(R.id.addSize);
            addHarga = itemView.findViewById(R.id.addHarga);
            addTotal = itemView.findViewById(R.id.addTotal);
            btnCO = itemView.findViewById(R.id.btnCO);
            imgProduk = itemView.findViewById(R.id.imgProduk);
            btnChat = itemView.findViewById(R.id.btnChat);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            imgUser = itemView.findViewById(R.id.imgUser);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onDeleteItemClick(position, models);

                    }
                }
            });

            btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onVhatItemClick(position, models);

                    }
                }
            });

            btnCO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onCHeckOutItemClick(position, models);

                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION  && listener != null) {

                        listener.onItemClick(position, models);

                    }
                }
            });

        }
    }
}

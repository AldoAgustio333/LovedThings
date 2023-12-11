package com.example.things.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.things.Interface.itemClick.RecPesanan;
import com.example.things.Model.PesananModel;
import com.example.things.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.PesananHolder> {

    Context context;
    List<PesananModel> models;
    RecPesanan listener;

    public PesananAdapter(Context context, List<PesananModel> models) {
        this.context = context;
        this.models = models;
        notifyDataSetChanged();
    }

    public void setListener(RecPesanan listener) {
        this.listener = listener;
    }


    @NotNull
    @Override
    public PesananAdapter.PesananHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new PesananAdapter.PesananHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent,false));
    }

    @Override
    public void onBindViewHolder(@NotNull PesananAdapter.PesananHolder holder, int position) {
        PesananModel model = models.get(position);
        String harga = String.valueOf(model.getHarga());
        String total = String.valueOf(model.getTotal());
        String hargaKiri = String.valueOf(model.getHargaPengiriman());

        holder.addAlamat.setText(model.getAlamatPenjual());
        holder.addUsername.setText(model.getNamaPenjual());
        holder.addStatus.setText(model.getStatus());
        holder.addKategori.setText(model.getKategori());
        holder.addMerek.setText(model.getMerek());
        holder.addTotalHarga.setText(harga);
        holder.addTotalPengiriman.setText(hargaKiri);
        holder.addTotal.setText(total);

        String getstatus = model.getStatus();
        if(getstatus.equals("Sudah Diterima"))
        {
            holder.btnSelesai.setVisibility(View.GONE);
        } else {
            holder.btnSelesai.setVisibility(View.VISIBLE);
        }

////        holder.addalamatPesanan.setText(model.getLokasi());

        Glide.with(holder.imgUser).load(model.getFotoPenjual()).into(holder.imgUser);
        Glide.with(holder.imgProduk).load(model.getImg_produk()).into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        if(models != null){
            return models.size();
        } return 0;
    }

    public class PesananHolder extends RecyclerView.ViewHolder {
        public TextView addUsername, addAlamat, addStatus, btnSelesai, addKategori, addMerek, addSize, addTotalHarga, addTotalPengiriman, addalamatPesanan, addTotal;
        public ImageView imgProduk, imgUser, imgPengiriman;

        public PesananHolder(@NotNull View itemView) {
            super(itemView);
            addAlamat = itemView.findViewById(R.id.addAlamat);
            addUsername = itemView.findViewById(R.id.addUsername);
            addStatus = itemView.findViewById(R.id.addStatus);
            addKategori = itemView.findViewById(R.id.addKategori);
            addMerek = itemView.findViewById(R.id.addMerek);
            addSize = itemView.findViewById(R.id.addSize);
            addTotalHarga = itemView.findViewById(R.id.addTotalHarga);
            addTotalPengiriman = itemView.findViewById(R.id.addTotalPengiriman);
            addalamatPesanan = itemView.findViewById(R.id.addalamatPesanan);
            addTotal = itemView.findViewById(R.id.addTotal);
            imgProduk = itemView.findViewById(R.id.imgProduk);
            imgPengiriman = itemView.findViewById(R.id.imgPengiriman);
            imgUser = itemView.findViewById(R.id.imgUser);
            btnSelesai = itemView.findViewById(R.id.btnSelesai);

            btnSelesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onbtnSelsaiCLickListener(position, models);
                    }
                }
            });
        }
    }

    public void clearData() {
        models.clear();
        notifyDataSetChanged();
    }

}

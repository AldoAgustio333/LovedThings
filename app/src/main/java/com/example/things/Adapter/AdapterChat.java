package com.example.things.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Activity.DetailProduk;
import com.example.things.Interface.itemClick.RecPesan;
import com.example.things.Model.ChatModel;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ChatHolder> {

    Context context;
    List<ChatModel> models;
    RecPesan listener;
    public static final String EXTRA_UID = "uid";
    public static final String EXTRA_IDP = "idP";

    public AdapterChat(Context context, List<ChatModel> models) {
        this.context = context;
        this.models = models;
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public ChatHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull ChatHolder holder, int position) {
        ChatModel model = models.get(position);

        holder.pesan.setText(model.getPesan());
        holder.addJamKanan.setText(model.getJam());
        holder.addJamKiri.setText(model.getJam());
        holder.addTglKanan.setText(model.getTgl());
        holder.addTglKiri.setText(model.getTgl());
        holder.pesan.setText(model.getPesan());

        holder.addSize.setText(model.getUkuran());
        holder.addMerek.setText(model.getMerek());
        holder.addKategori.setText(model.getKategori());
        holder.addHarga.setText(model.getHarga());

        Glide.with(context).load(model.getImageP()).centerCrop().into(holder.imgProduk);

        holder.produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProduk.class);
                intent.putExtra(EXTRA_IDP,model.getIdP());
                intent.putExtra(EXTRA_UID,model.getIdP());
                context.startActivity(intent);
            }
        });

        holder.viewBind(models.get(position));


    }

    @Override
    public int getItemCount() {
        if(models != null){
            return models.size();
        } return 0;
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        public TextView pesan,addTglKiri, addJamKiri, addTglKanan, addJamKanan, textTanggal, btnKeranjang, btnBeli, addKategori, addMerek, addSize, addHarga;
        public LinearLayout cardView, tamplatepesan, linear2, linearTanggal;
        public CardView  cardTanggal, produk;
        public ImageView addImgmassage, imgProduk;
        public RelativeLayout waktuKiri, waktuKanan, inibgpesan;
        DatabaseReference database;

        public ChatHolder(@NotNull View itemView) {
            super(itemView);
            pesan = (TextView) itemView.findViewById(R.id.pesan);
            addTglKiri = (TextView) itemView.findViewById(R.id.addTglKiri);
            addJamKiri = (TextView) itemView.findViewById(R.id.addJamKiri);
            addTglKanan = (TextView) itemView.findViewById(R.id.addTglKanan);
            addJamKanan = (TextView) itemView.findViewById(R.id.addJamKanan);
            textTanggal = (TextView) itemView.findViewById(R.id.textTanggal);
            btnKeranjang = (TextView) itemView.findViewById(R.id.btnKeranjang);
            btnBeli = (TextView) itemView.findViewById(R.id.btnBeli);
            addKategori = (TextView) itemView.findViewById(R.id.addKategori);
            addMerek = (TextView) itemView.findViewById(R.id.addMerek);
            addSize = (TextView) itemView.findViewById(R.id.addSize);
            addHarga = (TextView) itemView.findViewById(R.id.addHarga);

            waktuKanan = (RelativeLayout) itemView.findViewById(R.id.waktuKanan);
            waktuKiri = (RelativeLayout) itemView.findViewById(R.id.waktuKiri);
            linear2 = (LinearLayout) itemView.findViewById(R.id.linear2);
            tamplatepesan = (LinearLayout) itemView.findViewById(R.id.tamplatepesan);
            cardView = (LinearLayout) itemView.findViewById(R.id.cardView);
            linearTanggal = (LinearLayout) itemView.findViewById(R.id.linearTanggal);
            inibgpesan = (RelativeLayout) itemView.findViewById(R.id.inibgpesan);

            produk = (CardView) itemView.findViewById(R.id.produk);

            addImgmassage = (ImageView) itemView.findViewById(R.id.addImgmassage);
            imgProduk = (ImageView) itemView.findViewById(R.id.imgProduk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION  && listener != null) {

                        listener.onItemClick(position, models);

                    }
                }
            });

//            btnKeranjang.setText();
        }

        public void viewBind(ChatModel chatModel) {
            SessionManager sessionManager = new SessionManager(context);
            String uidNow = sessionManager.getUserDetail().get(SessionManager.UID);
            String uid = chatModel.getUidUser();
            String uidOther = chatModel.getUidOther();
            String idm = chatModel.getIdm();
            String idP = chatModel.getIdP();

            // Tambahkan ini untuk mengatur visibilitas produk
            if (TextUtils.isEmpty(idP)) {
                produk.setVisibility(View.GONE);
            } else {
                produk.setVisibility(View.VISIBLE);
            }



            DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");

            if (uidNow.equals(uidOther))
            {


                // Mengatur layout_gravity pada LinearLayout
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tamplatepesan.getLayoutParams();
                layoutParams.gravity = Gravity.END;
                tamplatepesan.setLayoutParams(layoutParams);

                waktuKanan.setVisibility(View.GONE);
                waktuKiri.setVisibility(View.VISIBLE);
                linear2.setGravity(Gravity.END);
                inibgpesan.setBackground(context.getResources().getDrawable(R.drawable.chat_to));

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showDeleteDataDialog(itemView.getContext(), idm);

                        return false;
                    }

                    private void showDeleteDataDialog(Context context, String idm) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Hapus");
                        builder.setMessage("Anda yakin untuk menghapus pesan ini?");
                        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.child(uidNow).child("DaftarPesan").child("Pesan").child(uid).child(idm)
                                        .removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {
                                                // Handle onComplete
                                                Toast.makeText(AdapterChat.this.context, "Pesan Berhasil di Hapus", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }

                });


            }else {
                waktuKanan.setVisibility(View.VISIBLE);
                waktuKiri.setVisibility(View.GONE);
                linear2.setGravity(Gravity.START);
                tamplatepesan.setGravity(Gravity.START);
                inibgpesan.setBackground(context.getResources().getDrawable(R.drawable.chat_me));


            }

        }
    }

}

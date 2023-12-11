package com.example.things.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.things.Activity.Chat;
import com.example.things.Model.UserChatModel;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.ViewHolder.DafPesanHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterDafPesan extends RecyclerView.Adapter<AdapterDafPesan.DafPesanHolder> {

    public Context context;
    public List<UserChatModel> models;
    public static final String EXTRA_UID_OTHER = "uidOther";
    public static final String EXTRA_IMG = "imgUser";
    public static final String EXTRA_USERNAME = "username";

    public AdapterDafPesan(Context context, List<UserChatModel> models) {
        this.context = context;
        this.models = models;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public AdapterDafPesan.DafPesanHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new AdapterDafPesan.DafPesanHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daf_pesan, parent,false));
    }

    @Override
    public void onBindViewHolder(@NotNull AdapterDafPesan.DafPesanHolder holder, int position) {
        UserChatModel model = models.get(position);
        holder.addNama.setText(model.getNamaUser());
        holder.addWaktu.setText(model.getJam());
        holder.addPesan.setText(model.getPesan());

        Glide.with(context).load(model.getImgUser()).centerCrop().into(holder.addPP);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat.class);
                intent.putExtra(EXTRA_UID_OTHER, model.getUid());
                intent.putExtra(EXTRA_IMG, model.getImgUser());
                intent.putExtra(EXTRA_USERNAME, model.getNamaUser());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.viewBind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void clearData() {
        models.clear();
        notifyDataSetChanged();
    }

    public class DafPesanHolder extends RecyclerView.ViewHolder {
        public TextView addWaktu, addNama,addPesan;
        public ImageView addPP;

        public DafPesanHolder(@NotNull View itemView) {
            super(itemView);

            addWaktu = itemView.findViewById(R.id.addWaktu);
            addNama = itemView.findViewById(R.id.addUsername);
            addPesan = itemView.findViewById(R.id.addPesan);
            addPP = itemView.findViewById(R.id.imgUser);
        }

        public void viewBind(UserChatModel userChatModel) {
            SessionManager sessionManager = new SessionManager(context);
            String uidNow = sessionManager.getUserDetail().get(SessionManager.UID);
            String idOther = userChatModel.getUid();
            DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDeleteDataDialog(itemView.getContext(), idOther);

                    return false;
                }

                private void showDeleteDataDialog(Context context, String idOther) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Hapus");
                    builder.setMessage("Anda yakin untuk menghapus pesan ini?");
                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            database.child(uidNow).child("DaftarPesan").child("User").child(idOther)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NotNull Task<Void> task) {
                                            // Handle onComplete
                                            database.child(uidNow).child("DaftarPesan").child("Pesan").child(idOther)
                                                    .removeValue()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NotNull Task<Void> task) {
                                                            // Handle onComplete
                                                            Toast.makeText(context, "Pesan Berhasil di Hapus", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
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
        }
    }
}
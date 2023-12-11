package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.things.Adapter.PesananAdapter;
import com.example.things.Adapter.ProdukAdapter;
import com.example.things.Interface.PesananInterface;
import com.example.things.Interface.itemClick.RecPesanan;
import com.example.things.Model.PesananModel;
import com.example.things.Presenter.PesananPresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityPesananBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PesananActivity extends AppCompatActivity implements PesananInterface, RecPesanan {

    private ActivityPesananBinding binding;
    private PesananPresenter presenter;
    SessionManager sessionManager;
    private PesananAdapter adapter;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPesananBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);
        presenter = new PesananPresenter(this);
        database = FirebaseDatabase.getInstance().getReference("Users");

        String uid = sessionManager.getUserDetail().get(SessionManager.UID);

        presenter.getDataPesanan(uid);

        AlldButton();
    }

    private void AlldButton() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PesananActivity.this, KeranjangActivity.class);
                startActivity(intent);
            }
        });

        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PesananActivity.this, DaftarChatActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PesananActivity.this, SearchActtivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void daftarProdukResponse(ArrayList<PesananModel> models) {
        adapter = new PesananAdapter(getApplicationContext(), models);
        adapter.setListener(PesananActivity.this); // Inisialisasi listener
        binding.recPesanan.setAdapter(adapter);
        binding.recPesanan.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void messageError() {
        Toast.makeText(this, "Pesanan Kosong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onbtnSelsaiCLickListener(int position, List<PesananModel> models) {
        PesananModel model = models.get(position);
        String uid = model.getUid();
        String idP = model.getIdP();
        String uidPenjual = model.getUidPenjual();

        if (!isFinishing() && !isDestroyed()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pesanan sudah diterima");
            builder.setMessage("Apakah pesnaan anda sudah diterima?");
            builder.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    database.child(uid)
                            .child("Pesanan")
                            .child(idP).child("status").setValue("Sudah Diterima").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            database.child(uidPenjual)
                                    .child("ProdukTerjual")
                                    .child(idP).child("status").setValue("Sudah Diterima").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(PesananActivity.this, "Terima kasih pesanan telah selesa silahkan belanja lagi", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                        }
                    });

                }
            });
            builder.setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
package com.example.things.Presenter;

import com.example.things.Interface.PesananInterface;
import com.example.things.Model.PesananModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class PesananPresenter {
    PesananInterface view;
    DatabaseReference database;

    public PesananPresenter(PesananInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void getDataPesanan(String uid) {
        database.child(uid).child("Pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                ArrayList<PesananModel> models = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PesananModel model = dataSnapshot.getValue(PesananModel.class);
                    models.add(model);
                }

                view.daftarProdukResponse(models);
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                view.messageError();
            }
        });
    }

}

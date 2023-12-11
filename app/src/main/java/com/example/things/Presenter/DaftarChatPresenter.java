package com.example.things.Presenter;

import android.util.Log;

import com.example.things.Interface.DaftarChatInterface;
import com.example.things.Model.ChatModel;
import com.example.things.Model.ProdukModel;
import com.example.things.Model.UserChatModel;
import com.example.things.Model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DaftarChatPresenter {

    DaftarChatInterface view;
    FirebaseDatabase database;

    public DaftarChatPresenter(DaftarChatInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance();
    }

    public void getChat(String uid) {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        database1.getReference().child("Users").child(uid).child("DaftarPesan").child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                ArrayList<UserChatModel> modelChat = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserChatModel model = dataSnapshot.getValue(UserChatModel.class);
                    modelChat.add(model);
                }
                view.daftarProdukResponse(modelChat);
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                view.messageError();
            }
        });

    }
}

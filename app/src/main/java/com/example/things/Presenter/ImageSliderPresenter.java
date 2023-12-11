package com.example.things.Presenter;

import com.example.things.Interface.ImageSliderInterface;
import com.example.things.Model.ImageSliderModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ImageSliderPresenter {

    ImageSliderInterface view;
    FirebaseDatabase firebaseDatabase;

    public ImageSliderPresenter(ImageSliderInterface view) {
        this.view = view;
        firebaseDatabase = FirebaseDatabase.getInstance();
    }


    public void getIklan() {

        firebaseDatabase.getReference().child("Iklan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                ArrayList<ImageSliderModel> imageUrls = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ImageSliderModel model = dataSnapshot.getValue(ImageSliderModel.class);
                    imageUrls.add(model);
                }

                view.IklanResponses(imageUrls);
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                view.messageError();
            }
        });
    }
}

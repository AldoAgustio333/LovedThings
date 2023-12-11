package com.example.things.Presenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.things.Activity.ProfileActivity;
import com.example.things.Interface.ProfileInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ProfilePresenter {
    ProfileInterface view;
    DatabaseReference database;

    public ProfilePresenter(ProfileInterface view) {
        this.view = view;
        database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void updateUsername(String uid, String namaU) {
        database.child(uid).child("username").setValue(namaU).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda

                view.updateUsernameisSuccess(namaU);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateEmail(String uid, String emailU) {
        database.child(uid).child("email").setValue(emailU).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    user.updateEmail(emailU)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Berhasil memperbarui alamat email di Firebase Authentication
                                        view.updateEmailisSuccess(emailU);
                                    } else {
                                        view.updateEmailFaliled(emailU);
                                    }
                                }
                            });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateAlamat(String uid, String alamatU) {
        database.child(uid).child("alamat").setValue(alamatU).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                view.updateAlamatisSuccess(alamatU);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }

    public void updateNoHP(String uid, String nohpU) {
        database.child(uid).child("nohp").setValue(nohpU).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                view.updateNoHPisSuccess(nohpU);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle onFailure
                view.updateisFailed();
            }
        });
    }
}

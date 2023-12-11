package com.example.things.Presenter;

import com.bumptech.glide.Glide;
import com.example.things.Interface.StartInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class LoginPresenter {

    public StartInterface view;
    private FirebaseAuth firebaseAuth;

    public LoginPresenter(StartInterface view) {
        this.view = view;
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void Login (String email, String password) {
        view.onProgress();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.doneProgres();
                        view.massageBerhasil();
                    } else {
                        view.doneProgres();
                        view.massageGagal(task.getException().getMessage());
                    }
                });
    }

    public void onDestroy() {
        view = null;
    }

    public void getDataUser(DatabaseReference reference) {
        reference.child(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //get all info of user here from snapshot
                        String email = ""+snapshot.child("email").getValue();
                        String username = ""+snapshot.child("username").getValue();
                        String nohp = ""+snapshot.child("nohp").getValue();
                        String alamat = ""+snapshot.child("alamat").getValue();
                        String type = ""+snapshot.child("userType").getValue();
                        String uid = ""+snapshot.child("uid").getValue();
                        String profileimage = snapshot.child("profileimage").getValue(String.class);

                        view.dataUserResponses(email,username, nohp,type, alamat, uid, profileimage);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }
}

package com.example.things.Presenter;

import com.example.things.Interface.StartInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RegisterPresenter {
    StartInterface view;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String saveCurrentDate, saveCurrentTime, tglnotifikasi;


    public RegisterPresenter(StartInterface startInterface) {
        this.view = startInterface;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Register(String email, String password, String nama, String nohp) {
        view.onProgress();
        //create user firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        view.doneProgres();

                        String userId = authResult.getUser().getUid();

                        Calendar calendar = Calendar.getInstance();

                        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MMM/yyyy");
                        saveCurrentDate = currentDate.format(calendar.getTime());


                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                        saveCurrentTime = currentTime.format(calendar.getTime());

                        tglnotifikasi = saveCurrentDate + saveCurrentTime;

                        //timestrap
                        long timestamp = System.currentTimeMillis();

                        //setup data to add in db
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("uid",userId);
                        hashMap.put("email",email);
                        hashMap.put("nama",nama);
                        hashMap.put("nohp",nohp);
                        hashMap.put("profileimage",""); //add empty, will do leter
                        hashMap.put("userType", "user"); // possible value are user, admin: will make admin manually in firebase realtime database by changing this value
                        hashMap.put("timestamp", timestamp);
                        hashMap.put("alamat", "");

                        HashMap<String, Object> hashMap2 = new HashMap<>();
                        hashMap2.put("idn",tglnotifikasi);
                        hashMap2.put("judulnotif","Admin Kawan Ternak");
                        hashMap2.put("logonotif","");
                        hashMap2.put("isintif","Selamat saat ini anda telah bergabung dengan aplikasi Kawan Ternak");
                        hashMap2.put("tglnotif", saveCurrentDate);
                        hashMap2.put("imgnotifikasi", "");

                        //set data db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                        ref.child(userId)
                                .setValue(hashMap)
                                .addOnSuccessListener(aVoid -> view.SavingSuccess())
                                .addOnFailureListener(e -> view.SavingFailed(e.getMessage()));

                        DatabaseReference notif = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                        notif.child("Notifikasi").child("01:").setValue(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                view.onProgress();
                            }
                        });
                    }
                })
                .addOnFailureListener(e -> view.SavingFailed(e.getMessage()));
    }


}

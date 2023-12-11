package com.example.things.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.things.Interface.StartInterface;
import com.example.things.Presenter.LoginPresenter;
import com.example.things.Presenter.RegisterPresenter;
import com.example.things.Presenter.RegisterPresenterSaveDB;
import com.example.things.Presenter.ResetPasswordPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class StartActivity extends AppCompatActivity implements StartInterface {

    private ActivityMainBinding binding;
    private LoginPresenter startPresenter;
    private RegisterPresenter registerPresenter;
    private ResetPasswordPresenter resetPasswordPresenter;
    FirebaseAuth firebaseAuth;
    private RegisterPresenterSaveDB registerPresenterSaveDB;
    boolean addpassVisible;
    //Progress dialog
    private ProgressDialog progressDialog;
    String saveCurrentDate, saveCurrentTime, tglnotifikasi;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setup firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        //setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Tolong Tunggu");
        progressDialog.setCanceledOnTouchOutside(false);

        startPresenter = new LoginPresenter(this);
        registerPresenter = new RegisterPresenter(this);
        resetPasswordPresenter = new ResetPasswordPresenter(this);
//        registerPresenterSaveDB = new RegisterPresenterSaveDB(this);

        sessionManager = new SessionManager(this);
        // Cek apakah pengguna sudah login
        if (sessionManager.isLogin()) {
            navigateToDashboard();
        }

        AllButton();

    }

    private void navigateToDashboard() {
        Intent intent = new Intent(StartActivity.this, Home.class);
        startActivity(intent);
        finish();
    }

    private void AllButton() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        StartActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_login, (RelativeLayout) findViewById(R.id.bottomlogin)
                        );
                EditText addemail = (EditText) bottomSheetView.findViewById(R.id.addemail);
                EditText addpass = (EditText) bottomSheetView.findViewById(R.id.addpass);
                bottomSheetView.findViewById(R.id.btnclose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String addEmail = addemail.getText().toString().trim();
                        String addPass = addpass.getText().toString().trim();
                        if (TextUtils.isEmpty(addEmail)) {
                            addemail.requestFocus();
                            addemail.setError("Email Tidak Boleh Kosong");
                        } else if (TextUtils.isEmpty(addPass)) {
                            addpass.requestFocus();
                            addpass.setError("Password Tidak Boleh Kosong");
                        }else {
                            String email = addemail.getText().toString();
                            String password = addpass.getText().toString();
                            startPresenter.Login(email,password);
                        }
                    }
                });
                addpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                addpass.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        final int Right=2;
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            if(event.getRawX()>=addpass.getRight()-addpass.getCompoundDrawables()[Right].getBounds().width())
                            {
                                int selection=addpass.getSelectionEnd();
                                if(addpassVisible)
                                {
                                    //set drawable image here
                                    addpass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_view, 0);
                                    //for hide password
                                    addpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                    addpassVisible = false;
                                } else {
                                    //set drawable image here
                                    addpass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_view_fill, 0);
                                    //for show password
                                    addpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                    addpassVisible = true;
                                }
                                addpass.setSelection(selection);
                                return  true;
                            }
                        }
                        return false;
                    }
                });
                bottomSheetView.findViewById(R.id.btnLupaPass).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(StartActivity.this,R.style.BottomSheetDialogTheme);
                        View bottomSheetView1 = LayoutInflater.from(getApplicationContext())
                                .inflate(
                                        R.layout.modal_addemail, (RelativeLayout) findViewById(R.id.bottomAddEmail)
                                );
                        EditText addemail = bottomSheetView1.findViewById(R.id.addEmail);
                        bottomSheetView1.findViewById(R.id.btnclose).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomSheetDialog1.dismiss();
                            }
                        });
                        bottomSheetView1.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String addEmail = addemail.getText().toString();
                                if(TextUtils.isEmpty(addEmail)){
                                    addemail.requestFocus();
                                    addemail.setError("Email Tidak Boleh Kosong");
                                } else {
                                    bottomSheetDialog1.dismiss();
                                    resetPasswordPresenter.AddEmail(addEmail);
//                                    Test(addEmail);
                                }
                            }
                        });
                        bottomSheetDialog1.setContentView(bottomSheetView1);
                        bottomSheetDialog1.show();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        StartActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_register, (RelativeLayout) findViewById(R.id.bottomregister)
                        );
                EditText addnama = (EditText) bottomSheetView.findViewById(R.id.addNama);
                EditText addemail = (EditText) bottomSheetView.findViewById(R.id.addemail);
                EditText addhp = (EditText) bottomSheetView.findViewById(R.id.addNohp);
                EditText addpass = (EditText) bottomSheetView.findViewById(R.id.addpass);
                EditText addverif = (EditText) bottomSheetView.findViewById(R.id.addverif);
                bottomSheetView.findViewById(R.id.btnclose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                addpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                addpass.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        final int Right=2;
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            if(event.getRawX()>=addpass.getRight()-addpass.getCompoundDrawables()[Right].getBounds().width())
                            {
                                int selection=addpass.getSelectionEnd();
                                if(addpassVisible)
                                {
                                    //set drawable image here
                                    addpass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_view, 0);
                                    //for hide password
                                    addpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                    addpassVisible = false;
                                } else {
                                    //set drawable image here
                                    addpass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_view_fill, 0);
                                    //for show password
                                    addpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                    addpassVisible = true;
                                }
                                addpass.setSelection(selection);
                                return  true;
                            }
                        }
                        return false;
                    }
                });
                addverif.setTransformationMethod(PasswordTransformationMethod.getInstance());
                addverif.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        final int Right=2;
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            if(event.getRawX()>=addverif.getRight()-addverif.getCompoundDrawables()[Right].getBounds().width())
                            {
                                int selection=addverif.getSelectionEnd();
                                if(addpassVisible)
                                {
                                    //set drawable image here
                                    addverif.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_view, 0);
                                    //for hide password
                                    addverif.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                    addpassVisible = false;
                                } else {
                                    //set drawable image here
                                    addverif.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_view_fill, 0);
                                    //for show password
                                    addverif.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                    addpassVisible = true;
                                }
                                addverif.setSelection(selection);
                                return  true;
                            }
                        }
                        return false;
                    }
                });

                bottomSheetView.findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String addEmail = addemail.getText().toString().trim();
                        String addPass = addpass.getText().toString().trim();
                        String addNama = addnama.getText().toString().trim();
                        String addVerif = addverif.getText().toString().trim();
                        String addNohp = addhp.getText().toString().trim();
                        if (TextUtils.isEmpty(addEmail)) {
                            addemail.requestFocus();
                            addemail.setError("Email Tidak Boleh Kosong");
                        } else if (TextUtils.isEmpty(addPass)) {
                            addpass.requestFocus();
                            addpass.setError("Password Tidak Boleh Kosong");
                        }else if (TextUtils.isEmpty(addNama)) {
                            addnama.requestFocus();
                            addnama.setError("Nama Tidak Boleh Kosong");
                        }else if (TextUtils.isEmpty(addVerif)) {
                            addverif.requestFocus();
                            addverif.setError("Verfikasi Password Tidak Boleh Kosong");
                        }else if (TextUtils.isEmpty(addNohp)) {
                            addhp.requestFocus();
                            addhp.setError("No.Hp Tidak Boleh Kosong");
                        }else if (addNohp.length() >= 13) {
                            addhp.requestFocus();
                            addhp.setError("Isi nomor dengan 12 angka");
                        }else {
                            String email = addemail.getText().toString();
                            String password = addpass.getText().toString();
                            String nama = addnama.getText().toString();
                            String nohp = addhp.getText().toString();
                            Register(email,password, nama,nohp);
                        }
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

    private void Test(String addEmail) {
        firebaseAuth.sendPasswordResetEmail(addEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(StartActivity.this, "Check email kamu", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StartActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Register(String email, String password, String nama, String nohp) {
        //show progress dialog
        progressDialog.setMessage("Pembuatan Akun....");
        progressDialog.show();

        //create user firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //account creating success, now add in firebase realtime database
                        createDB(email,nama, nohp);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //account creating failed
                        progressDialog.dismiss();
                        Toast.makeText(StartActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createDB(String email, String nama, String nohp) {
        progressDialog.setMessage("Saving User Info...");
        sessionManager = new SessionManager(StartActivity.this);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MMM/yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        tglnotifikasi = saveCurrentDate + saveCurrentTime;

        //timestrap
        long timestamp = System.currentTimeMillis();

        //get current user uid, since user is registered so we can get now
        String uid = firebaseAuth.getUid();

        //setup data to add in db
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("email",email);
        hashMap.put("username",nama);
        hashMap.put("nohp",nohp);
        hashMap.put("profileimage",""); //add empty, will do leter
        hashMap.put("userType", "user"); // possible value are user, admin: will make admin manually in firebase realtime database by changing this value
        hashMap.put("timestamp", timestamp);
        hashMap.put("alamat", "-");

        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("idn",tglnotifikasi);
        hashMap2.put("judulnotif","Admin Kawan Ternak");
        hashMap2.put("logonotif","");
        hashMap2.put("isintif","Selamat saat ini anda telah bergabung dengan aplikasi Kawan Ternak");
        hashMap2.put("tglnotif", saveCurrentDate);
        hashMap2.put("imgnotifikasi", "");

        String type = "user";
        //set data db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//        databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //data added to db
                        Toast.makeText(StartActivity.this, "Akun Sedang dibuat", Toast.LENGTH_SHORT).show();
                        //since user akun is created so stat dasboard of user
                        sessionManager.createLoginSession(uid, nama, email, nohp, type);
                        startActivity(new Intent(StartActivity.this, Home.class));
                        finish();
                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //data failed adding to db
                        progressDialog.dismiss();
                        Toast.makeText(StartActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        DatabaseReference notif = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        notif.child("Notifikasi").child("01:").setValue(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public void massageBerhasil() {
//        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        sessionManager = new SessionManager(StartActivity.this);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        startPresenter.getDataUser(reference);
//        reference.child(firebaseAuth.getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot snapshot) {
//                        //get all info of user here from snapshot
//                        String email = ""+snapshot.child("email").getValue();
//                        String username = ""+snapshot.child("username").getValue();
//                        String nohp = ""+snapshot.child("nohp").getValue();
//                        String alamat = ""+snapshot.child("alamat").getValue();
//                        String type = ""+snapshot.child("userType").getValue();
//                        String uid = ""+snapshot.child("uid").getValue();
//                        String profileimage = snapshot.child("profileimage").getValue(String.class);
//
//                        dataUserResponse(email,username, nohp,type, alamat, uid, profileimage);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//
//                    }
//                });

    }

    private void dataUserResponse(String email, String username, String nohp, String type, String alamat, String uid, String profileimage) {
        sessionManager = new SessionManager(this);
        sessionManager.createLoginSession(uid,username,email,nohp,type);
        Toast.makeText(this, firebaseAuth.getUid(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StartActivity.this,Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void massageGagal(String message) {
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUserInfo(String email, String password, String nama, String nohp) {
        //show progress dialog
        progressDialog.setMessage("Saving User Info...");
    }

    @Override
    public void createUserIsFailed(Exception e) {
        Toast.makeText(StartActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgress() {
        //show progress dialog
        progressDialog.setMessage("Pembuatan Akun....");
        progressDialog.show();
    }

    @Override
    public void doneProgres() {
        progressDialog.dismiss();
    }

    @Override
    public void SavingSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "Data telah Disimpan", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StartActivity.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void SavingFailed(String message) {
        Toast.makeText(StartActivity.this, "Data gagal disimpan karena ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ResetIsSuccess() {
        Toast.makeText(StartActivity.this, "Cek email kamu untuk merubah password", Toast.LENGTH_SHORT).show();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                StartActivity.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.modal_login, (RelativeLayout) findViewById(R.id.bottomlogin)
                );
        EditText addemail = (EditText) bottomSheetView.findViewById(R.id.addemail);
        EditText addpass = (EditText) bottomSheetView.findViewById(R.id.addpass);
        bottomSheetView.findViewById(R.id.btnclose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addEmail = addemail.getText().toString().trim();
                String addPass = addpass.getText().toString().trim();
                if (TextUtils.isEmpty(addEmail)) {
                    addemail.requestFocus();
                    addemail.setError("Email Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(addPass)) {
                    addpass.requestFocus();
                    addpass.setError("Password Tidak Boleh Kosong");
                }else {
                    String email = addemail.getText().toString();
                    String password = addpass.getText().toString();
                    startPresenter.Login(email,password);
                }
            }
        });
        bottomSheetView.findViewById(R.id.btnLupaPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(StartActivity.this,R.style.BottomSheetDialogTheme);
                View bottomSheetView1 = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_addemail, (RelativeLayout) findViewById(R.id.bottomAddEmail)
                        );
                EditText addemail = bottomSheetView1.findViewById(R.id.addEmail);
                bottomSheetView1.findViewById(R.id.btnclose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog1.dismiss();
                    }
                });
                bottomSheetView1.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String addEmail = addemail.getText().toString();
                        if(TextUtils.isEmpty(addEmail)){
                            addemail.requestFocus();
                            addemail.setError("Email Tidak Boleh Kosong");
                        } else {
                            bottomSheetDialog1.dismiss();
                            resetPasswordPresenter.AddEmail(addEmail);
                            Test(addEmail);
                        }
                    }
                });
                bottomSheetDialog1.setContentView(bottomSheetView1);
                bottomSheetDialog1.show();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void ResetIsFailed() {
        Toast.makeText(StartActivity.this, "Maaf reset password gagal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataUserResponses(String email, String username, String nohp, String type, String alamat, String uid, String profileimage) {
        sessionManager = new SessionManager(this);
        sessionManager.createLoginSession(uid,username,email,nohp,type);

        Intent intent = new Intent(StartActivity.this,Home.class);
        startActivity(intent);
        finish();
    }

    public void SavingFailed(Exception e) {
        Toast.makeText(StartActivity.this, "Data gagal disimpan karena :"+e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startPresenter.onDestroy();
    }
}
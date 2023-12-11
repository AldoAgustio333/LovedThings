package com.example.things.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.things.Interface.ProfileInterface;
import com.example.things.Presenter.ProfilePresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity implements ProfileInterface {
    private FirebaseAuth firebaseAuth;
    private ActivityProfileBinding binding;
    int PERMISSION_DATA = 20;
    int ACCESS_DATA = 40;
    Bitmap bitmap;
    Uri capturedImgURL;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;

    ProfilePresenter presenter;

    SessionManager sessionManager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        sessionManager = new SessionManager(this);
        presenter = new ProfilePresenter(this);

        String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        String email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        String nohp = sessionManager.getUserDetail().get(SessionManager.NOHP);
        String alamat = sessionManager.getUserDetail().get(SessionManager.ALAMAT);
        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        String img = sessionManager.getUserDetail().get(SessionManager.IMG_PROFILE);


        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        binding.addAlamat.setText(alamat);
        binding.addHp.setText(nohp);
        binding.addUsername.setText(nama);
        binding.addEmail.setText(email);

        Glide.with(binding.imgUser).load(img).centerCrop().into(binding.imgUser);

        AllButton();
    }


    private void AllButton() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnChangePP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutSession();
                firebaseAuth.signOut();
                signOutUser();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);
                String email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
                String alamat = sessionManager.getUserDetail().get(SessionManager.ALAMAT);
                String nohp = sessionManager.getUserDetail().get(SessionManager.NOHP);

                String uid = sessionManager.getUserDetail().get(SessionManager.UID);
                String namaU = binding.addUsername.getText().toString();
                String alamatU = binding.addAlamat.getText().toString();
                String nohpU = binding.addHp.getText().toString();
                String emailU = binding.addEmail.getText().toString();

                if (namaU.isEmpty()){
                    binding.addUsername.requestFocus();
                    binding.addUsername.setError("Username Tidak boleh kosong");
                } else if (!namaU.equals(nama)){
                    presenter.updateUsername(uid, namaU);
                }

                if (emailU.isEmpty()){
                    binding.addEmail.requestFocus();
                    binding.addEmail.setError("Email Tidak boleh kosong");
                } else if (!emailU.equals(email)){
                    presenter.updateEmail(uid, emailU);
                }

                if (alamatU.isEmpty()){
                    binding.addAlamat.requestFocus();
                    binding.addAlamat.setError("Email Tidak boleh kosong");
                } else if (!alamatU.equals(alamat)){
                    presenter.updateAlamat(uid, alamatU);
                }

                if (nohpU.isEmpty()){
                    binding.addHp.requestFocus();
                    binding.addHp.setError("Email Tidak boleh kosong");
                } else if (!nohpU.equals(nohp)){
                    presenter.updateNoHP(uid, nohpU);
                }


            }
        });
    }

    private void signOutUser() {
        Intent intent = new Intent(ProfileActivity.this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    // Fungsi untuk membuka kamera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // Fungsi untuk membuka galeri
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Mengambil gambar dari kamera
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                // Simpan atau unggah gambar ke Firebase Storage
                uploadImageToFirebaseStorage(imageBitmap);
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                // Mengambil gambar dari galeri
                Uri selectedImage = data.getData();
                try {
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    // Simpan atau unggah gambar ke Firebase Storage
                    uploadImageToFirebaseStorage(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void uploadImageToFirebaseStorage(Bitmap bitmap) {
        // Mendapatkan referensi Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        // Mengubah gambar menjadi byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        // Menentukan nama file di Firebase Storage
        final String filename = "IMG_"+ String.valueOf(System.currentTimeMillis())+".jpg";

        // Membuat referensi untuk file di Firebase Storage
        StorageReference profileImageRef = storageRef.child("ProfilUser").child(filename);

        // Mengunggah file ke Firebase Storage
        UploadTask uploadTask = profileImageRef.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Jika pengunggahan berhasil, dapatkan URL gambar dari Firebase Storage
                profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Dapatkan URL gambar
                        String imageUrl = uri.toString();
                        // Update URL gambar di Firebase Realtime Database
                        updateProfileImageInDatabase(imageUrl);
                    }
                });
            }
        });
    }

    private void updateProfileImageInDatabase(String imageUrl) {
        dialog.show();
        // Mendapatkan UID pengguna saat ini dari Firebase Authentication
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            // Mendapatkan referensi ke database pengguna
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);
            // Update URL gambar di database pengguna
            userRef.child("profileimage").setValue(imageUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // Jika berhasil, perbarui sesi manajer dan gambar profil
                    sessionManager.updateImageUserData(imageUrl);
                    // Gantilah 'imageViewProfile' dengan ID yang sesuai dari ImageView Anda
                    Glide.with(ProfileActivity.this).load(imageUrl).centerCrop().into(binding.imgUser);
                    dialog.dismiss();
                    Toast.makeText(ProfileActivity.this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle onFailure
                            dialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Gagal memperbarui profil", Toast.LENGTH_SHORT).show();
                        }
            });
        }
    }


    @Override
    public void updateisFailed() {
        Toast.makeText(this, "Maaf update data gagal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateNoHPisSuccess(String nohpU) {
        sessionManager.updateUserNoHP(nohpU);
        Toast.makeText(this, "No HP berhasil di update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAlamatisSuccess(String alamatU) {
        sessionManager.updateUserAlamat(alamatU);
        Toast.makeText(this, "Alamat berhasil di update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateEmailisSuccess(String emailU) {
        sessionManager.updateUserEmail(emailU);
        Toast.makeText(this, "Email berhasil di update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUsernameisSuccess(String namaU) {
        sessionManager.updateUserUsername(namaU);
        Toast.makeText(this, "Username berhasil di update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateEmailFaliled(String emailU) {
        Toast.makeText(this, "Email Gagal di update", Toast.LENGTH_SHORT).show();
    }
}
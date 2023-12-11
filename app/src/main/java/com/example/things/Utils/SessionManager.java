package com.example.things.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //ini data yang disimpan di session
    public static final String IS_LOGIN = "isLogin";
    public static final String UID = "uid";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String ALAMAT = "alamat";
    public static final String NOHP = "nohp";
    public static final String TYPE = "userType";
    public static final String IMG_PROFILE = "profileimage";

    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = context.getSharedPreferences(IS_LOGIN, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //disini kenapa login data ? karena data yang akan di simpan ada di  LoginData
    public void createLoginSession(String uid, String nama, String email, String nohp, String type) {
        //pas kita klik login kita akan membuat login session
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USERNAME, nama);
        editor.putString(UID, uid);
        editor.putString(NOHP, nohp);
        editor.putString(EMAIL, email);
        editor.putString(TYPE, type);
        editor.commit();
    }

    //cara memanggil data dari sesi ini
    public HashMap<String, String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        //kita kasih key uid dll lalu kita panggil sharedreference tadi, di dalam get string
        // ada user id dan defaulte idnya itu null atau tidak  ada
        user.put(UID, sharedPreferences.getString(UID, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(NOHP, sharedPreferences.getString(NOHP, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        user.put(IMG_PROFILE, sharedPreferences.getString(IMG_PROFILE, null));
        user.put(TYPE, sharedPreferences.getString(TYPE, null));
        //kita  kembaliakan datanya
        return user;
    }

    public String getUsername() {
        return sharedPreferences.getString(USERNAME, "");
    }

    //buat sesi  loggout
    public void logoutSession(){
        editor.putBoolean(IS_LOGIN, false);
        editor.clear();
        editor.commit();
    }

    //fungsi yang berguna untuk membuat user tidak masuk lagi ke dalam login activity seblum di logout
    public boolean isLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    public void updateImageUserData(String imageUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IMG_PROFILE, imageUrl);
        editor.commit();
    }

    public void updateUserData(String emailU, String nohpU, String alamatU, String namaU) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, namaU);
        editor.putString(EMAIL, emailU);
        editor.putString(NOHP, nohpU);
        editor.putString(ALAMAT, alamatU);
        editor.commit();
    }

    public void updateUserNoHP(String nohpU) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NOHP, nohpU);
        editor.commit();
    }

    public void updateUserAlamat(String alamatU) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALAMAT, alamatU);
        editor.commit();
    }

    public void updateUserEmail(String emailU) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, emailU);
        editor.commit();
    }

    public void updateUserUsername(String namaU) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, namaU);
        editor.commit();
    }
}

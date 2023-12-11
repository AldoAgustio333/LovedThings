//package com.example.things.Activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.things.Adapter.AdapterDafPesan;
//import com.example.things.Interface.DaftarChatInterface;
//import com.example.things.Model.UserChatModel;
//import com.example.things.Presenter.DaftarChatPresenter;
//import com.example.things.Utils.SessionManager;
//import com.example.things.databinding.ActivityDaftarChatBinding;
//
//import java.util.ArrayList;
//
//public class DaftarChatActivity extends AppCompatActivity implements DaftarChatInterface {
//
//    private ActivityDaftarChatBinding binding;
//
//    private DaftarChatPresenter presenter;
//    SessionManager sessionManager;
//    private AdapterDafPesan adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityDaftarChatBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        presenter = new DaftarChatPresenter(this);
//        sessionManager = new SessionManager(this);
//
//        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
//        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
//        presenter.getChat(uid);
//
//    }
//
//    @Override
//    public void daftarProdukResponse(ArrayList<UserChatModel> modelChat) {
//        adapter = new AdapterDafPesan(getApplicationContext(), modelChat);
//        binding.recDafPesan.setAdapter(adapter);
//        binding.recDafPesan.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        adapter.notifyDataSetChanged();
//        Toast.makeText(this, "Data berhasil di get", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void messageError() {
//
//    }
//}

package com.example.things.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.things.Adapter.AdapterDafPesan;
import com.example.things.Interface.DaftarChatInterface;
import com.example.things.Model.UserChatModel;
import com.example.things.Presenter.DaftarChatPresenter;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.ActivityDaftarChatBinding;

import java.util.ArrayList;

public class DaftarChatActivity extends AppCompatActivity implements DaftarChatInterface {

    private ActivityDaftarChatBinding binding;

    private DaftarChatPresenter presenter;
    SessionManager sessionManager;
    private AdapterDafPesan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaftarChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DaftarChatPresenter(this);
        sessionManager = new SessionManager(this);

        String uid = sessionManager.getUserDetail().get(SessionManager.UID);
        presenter.getChat(uid);

        AllButton();

    }

    private void AllButton() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void daftarProdukResponse(ArrayList<UserChatModel> modelChat) {
        adapter = new AdapterDafPesan(getApplicationContext(), modelChat);
        binding.recDafPesan.setAdapter(adapter);
        binding.recDafPesan.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();

    }

    @Override
    public void messageError() {
       
    }
}
package com.example.things.Presenter;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.things.Activity.StartActivity;
import com.example.things.Interface.StartInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.io.FileReader;
import java.util.List;

public class ResetPasswordPresenter {

    StartInterface view;
    private FirebaseAuth auth;

    public ResetPasswordPresenter(StartInterface view) {
        this.view = view;
        auth = FirebaseAuth.getInstance();
    }

    public void AddEmail(String addEmail) {
        view.onProgress();
        auth.sendPasswordResetEmail(addEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    view.doneProgres();
                    view.ResetIsSuccess();
                } else {
                    view.doneProgres();
                    view.ResetIsFailed();
                }
            }
        });
    }

    public void ResetPass(String addEmail) {
        view.onProgress();
        auth.sendPasswordResetEmail(addEmail)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        view.doneProgres();
                        view.ResetIsSuccess();
                    } else {
                        view.doneProgres();
                        view.ResetIsFailed();
                    }
                });
    }
}

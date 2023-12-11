package com.example.things.Interface;

public interface StartInterface {

    void massageBerhasil();

    void massageGagal(String message);

    void updateUserInfo(String email, String password, String nama, String nohp);

    void createUserIsFailed(Exception e);

    void onProgress();

    void doneProgres();

    void SavingSuccess();

    void SavingFailed(String message);

    void ResetIsSuccess();

    void ResetIsFailed();

    void dataUserResponses(String email, String username, String nohp, String type, String alamat, String uid, String profileimage);
}

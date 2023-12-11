package com.example.things.Model;

public class KategoriModel2 {
    private String nama;
    private boolean isSelected = false; // Tambahkan properti isSelected

    public KategoriModel2(String nama) {
        this.nama = nama;

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

}

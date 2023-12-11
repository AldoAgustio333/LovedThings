package com.example.things.Model;

public class ImageSliderModel {
    String title, url;

    public ImageSliderModel() {
        // Diperlukan untuk deserialisasi Firebase
    }

    public ImageSliderModel(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

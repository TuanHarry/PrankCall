package com.example.dev97.tuantran.prankcall.Models;

public class ModelDetailMakeCall {
    int image;
    String title;
    String sub;

    public ModelDetailMakeCall(int image, String title, String sub) {
        this.image = image;
        this.title = title;
        this.sub = sub;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}

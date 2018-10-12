package com.example.dev97.tuantran.prankcall.Models;

public class ModelMain {
    int image;
    String content;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content ) {
        this.content = content;
    }

    public ModelMain(int image, String content) {
        this.image = image;
        this.content = content;
    }
}

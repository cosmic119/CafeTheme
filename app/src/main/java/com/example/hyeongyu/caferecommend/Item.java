package com.example.hyeongyu.caferecommend;
public class Item {
    int image;
    String title;

    int getImage() {
        return this.image;
    }
    String getTitle() {
        return this.title;
    }

    Item(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
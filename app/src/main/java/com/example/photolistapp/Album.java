package com.example.photolistapp;

public class Album {
    private int userId;
    private String header;

    public Album(int userId, String header) {
        this.userId = userId;
        this.header = header;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}

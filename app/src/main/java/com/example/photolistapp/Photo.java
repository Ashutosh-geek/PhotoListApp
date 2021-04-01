package com.example.photolistapp;

public class Photo {
    private int albumid;
    private String thumburl,imageurl,tittle;

    public Photo(){

    }

    public Photo(int albumid, String thumburl, String imageurl, String tittle) {
        this.albumid = albumid;
        this.thumburl = thumburl;
        this.imageurl = imageurl;
        this.tittle = tittle;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}

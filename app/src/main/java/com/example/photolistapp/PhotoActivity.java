package com.example.photolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {
private ImageView photoimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Intent intent = getIntent();
        String imageurl = intent.getStringExtra("imageurl");
        photoimage = findViewById(R.id.album_photo);
        Picasso.get().load(imageurl).into(photoimage);
    }
}

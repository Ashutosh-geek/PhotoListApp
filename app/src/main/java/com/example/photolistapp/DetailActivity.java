package com.example.photolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements itemClickListener {
private List<Album> albumList;
private RecyclerView recyclerView;
private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("Albums");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.album_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumList = new ArrayList<>();

        fetchData();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url = "https://jsonplaceholder.typicode.com/albums";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray= new JSONArray(response);
                            for(int i = 0;i<=jsonArray.length()-1;i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject jsonObject1 = jsonArray.getJSONObject(1);
                                //  String image = jsonObject1.getString("albumId");
                                //   Toast.makeText(MainActivity.this, image, Toast.LENGTH_SHORT).show();


                                String tittle = jsonObject.getString("title");
                                int userId = jsonObject.getInt("id");
                                Album album = new Album(userId,tittle);
                                albumList.add(album);


                            }
                          albumAdapter = new AlbumAdapter(DetailActivity.this,albumList);
                            albumAdapter.setOnItemClickListener(DetailActivity.this);
                          recyclerView.setAdapter(albumAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   Toast.makeText(CovidCountries.this, "error", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(DetailActivity.this,MainActivity.class);
        Album album = albumList.get(position);
        intent.putExtra("userId",album.getUserId());
        startActivity(intent);

    }
}

package com.example.photolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements itemClickListener {
    EditText editsearch;
RecyclerView recyclerView;
    public static List<Photo> photoList = new ArrayList<>();
    int userId;
    MyAdapter adapter;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
 userId = intent.getIntExtra("userId",0);
 id = String.valueOf(userId);
        editsearch = (EditText)findViewById(R.id.ed_search);
      recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
          editsearch.addTextChangedListener(new TextWatcher() {
            @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              adapter.getFilter().filter(s);
              adapter.notifyDataSetChanged();
          }

            @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {

              }
          });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url = "https://jsonplaceholder.typicode.com/photos?albumId="+id;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray= new JSONArray(response);
                            photoList.clear();
                            for(int i = 0;i<=jsonArray.length()-1;i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject jsonObject1 = jsonArray.getJSONObject(1);
                              //  String image = jsonObject1.getString("albumId");
                             //   Toast.makeText(MainActivity.this, image, Toast.LENGTH_SHORT).show();
                                String imageurl = jsonObject.getString("url");
                                String thumburl = jsonObject.getString("thumbnailUrl");
                                String tittle = jsonObject.getString("title");
                                int albumid = jsonObject.getInt("albumId");
                                Photo photo = new Photo(albumid,thumburl,imageurl,tittle);
                                photoList.add(photo);

                            }
                            adapter = new MyAdapter(MainActivity.this,photoList);
                            adapter.setOnItemClickListener(MainActivity.this);
                            recyclerView.setAdapter(adapter);

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
Intent intent = new Intent(MainActivity.this,PhotoActivity.class);
Photo photo = photoList.get(position);
intent.putExtra("imageurl",photo.getImageurl());
startActivity(intent);

    }
}

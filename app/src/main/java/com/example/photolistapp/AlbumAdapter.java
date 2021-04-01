package com.example.photolistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter .HomeViewHolder> {
    private Context context;
    private List<Album> albumList;
    private itemClickListener clickListener;

    public void setOnItemClickListener(itemClickListener listener){
        clickListener=listener;
    }

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;

    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_add, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, int position) {
   holder.header.setText(albumList.get(position).getHeader());
   holder.albumid.setText("" +albumList.get(position).getUserId());

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public TextView header,albumid;


        public AdapterView.OnItemClickListener itemClickListener;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
          header = itemView.findViewById(R.id.album_header);
          albumid = itemView.findViewById(R.id.album_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }
}
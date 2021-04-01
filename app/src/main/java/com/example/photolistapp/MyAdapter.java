package com.example.photolistapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter .HomeViewHolder> {
    private Context context;
    private List<Photo> photoList;
    private List<Photo> photoListFiltered;
    private itemClickListener clickListener;

    public void setOnItemClickListener(itemClickListener listener){
        clickListener=listener;
    }



    public MyAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
        this.photoListFiltered = photoList;

    }
@NonNull

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = photoList.size();
                    filterResults.values = photoList;
                } else {
                    List<Photo> resultsModel = new ArrayList<>();
                    String search = constraint.toString().toLowerCase();
                    for (Photo itemsModel : photoList) {
                        if (itemsModel.getTittle().toLowerCase().contains(search)) {
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;

                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                photoListFiltered= (List<Photo>) results.values;
                MainActivity.photoList=(List<Photo>) results.values;
                notifyDataSetChanged();

            }

        };
        return filter;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_data, parent, false);

        return new HomeViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, int position) {
    holder.name.setText(photoListFiltered.get(position).getTittle());
       // SvgLoader.pluck().with((Activity) context).load(countriesList.get(position).getFlagurl(),holder.flagImage);
     holder.albumid.setText("AlbumId: " +photoListFiltered.get(position).getAlbumid());
        Picasso.get().load(photoListFiltered.get(position).getThumburl()).into(holder.Image);
    }


    public int getCount() {
        return photoListFiltered.size();

    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return photoListFiltered.size();
    }


    @Nullable

    public Photo getItem(int position) {
        return photoListFiltered.get(position);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public TextView name,albumid;
        public ImageView Image;
        public AdapterView.OnItemClickListener itemClickListener;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
          name = itemView.findViewById(R.id.tittle);
       Image = itemView.findViewById(R.id.thumb_image);
       albumid = itemView.findViewById(R.id.album_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }


}

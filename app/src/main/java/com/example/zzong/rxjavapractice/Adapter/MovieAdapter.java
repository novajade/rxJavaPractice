package com.example.zzong.rxjavapractice.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zzong.rxjavapractice.Model.MovieData;
import com.example.zzong.rxjavapractice.Model.items;
import com.example.zzong.rxjavapractice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<items> itemList = new ArrayList<>();

    public MovieAdapter(Context mContext, List<items> itemList){
        this.context = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_layout, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        items mitemList = itemList.get(i);

        movieViewHolder.textViewTitle.setText(mitemList.getTitle());
        movieViewHolder.textViewYear.setText(mitemList.getPubdate());
        movieViewHolder.textViewDirector.setText(mitemList.getDirector());
        movieViewHolder.textViewActor.setText(mitemList.getActor());
        movieViewHolder.ratingBar.setRating(mitemList.getUserrating());
        try{
            Picasso.get()
                    .load(mitemList.getImage().toString())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieViewHolder.imageView);
        }
        catch(Exception e){
            e.printStackTrace();
            Picasso.get()
                    .load(R.drawable.noimage)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieViewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewYear, textViewDirector, textViewActor;
        ImageView imageView;
        RatingBar ratingBar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            textViewDirector = itemView.findViewById(R.id.textViewDirector);
            textViewActor = itemView.findViewById(R.id.textViewActor);
            ratingBar = itemView.findViewById(R.id.userRating);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

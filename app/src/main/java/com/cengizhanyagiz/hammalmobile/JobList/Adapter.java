package com.cengizhanyagiz.hammalmobile.JobList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.Model.PostJob;
import com.cengizhanyagiz.hammalmobile.View.DetalistJobActivity;
import com.cengizhanyagiz.hammalmobile.databinding.DesignJoblistBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.PostHolder> {
    private ArrayList<PostJob> postJobArrayList;

    public Adapter(ArrayList<PostJob> postJobArrayList) {
        this.postJobArrayList = postJobArrayList;
    }
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DesignJoblistBinding designJoblistBinding = DesignJoblistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(designJoblistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.designJoblistBinding.recyclerView0.setText(postJobArrayList.get(position).meslek);
        holder.designJoblistBinding.recyclerView1.setText(postJobArrayList.get(position).isim);
        holder.designJoblistBinding.recyclerView2.setText(postJobArrayList.get(position).adres);
        Picasso.get().load(postJobArrayList.get(position).imageUrl).into(holder.designJoblistBinding.recyclerViewImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalistJobActivity.class);
                intent.putExtra("isim",postJobArrayList.get(holder.getAdapterPosition()).isim);
                intent.putExtra("meslek",postJobArrayList.get(holder.getAdapterPosition()).meslek);
                intent.putExtra("adres",postJobArrayList.get(holder.getAdapterPosition()).adres);
                intent.putExtra("image",postJobArrayList.get(holder.getAdapterPosition()).imageUrl);
                intent.putExtra("deneyim",postJobArrayList.get(position).deneyim);
                intent.putExtra("iletisim",postJobArrayList.get(position).iletisim);
                intent.putExtra("çalışma saat",postJobArrayList.get(position).calismasaat);
                intent.putExtra("yas",postJobArrayList.get(position).yas);
                intent.putExtra("tanit",postJobArrayList.get(position).tanit);
                intent.putExtra("ogretim",postJobArrayList.get(position).ogretim);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        return postJobArrayList.size();

    }

    class PostHolder extends  RecyclerView.ViewHolder{
        DesignJoblistBinding designJoblistBinding;

        public  PostHolder(DesignJoblistBinding designJoblistBinding){
            super(designJoblistBinding.getRoot());
            this.designJoblistBinding=designJoblistBinding;
        }
    }
}

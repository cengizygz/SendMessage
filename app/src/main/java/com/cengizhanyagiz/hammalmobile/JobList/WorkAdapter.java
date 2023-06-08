package com.cengizhanyagiz.hammalmobile.JobList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.Model.PostWorker;
import com.cengizhanyagiz.hammalmobile.View.DetalistWorkerActivity;
import com.cengizhanyagiz.hammalmobile.databinding.DesignWorkerlistBinding;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkHolder> {
    private ArrayList<PostWorker> postWorkerArrayList;

    public WorkAdapter(ArrayList<PostWorker> postWorkerArrayList){
        this.postWorkerArrayList = postWorkerArrayList;
    }

    @NonNull
    @Override
    public WorkAdapter.WorkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DesignWorkerlistBinding designWorkerlistBinding = DesignWorkerlistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new WorkHolder(designWorkerlistBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.WorkHolder holder, int position) {
        holder.designWorkerlistBinding.rvnitelik.setText(postWorkerArrayList.get(position).nitelik);
        holder.designWorkerlistBinding.rvsirketadi.setText(postWorkerArrayList.get(position).sirketAdi);
        holder.designWorkerlistBinding.rvadres.setText(postWorkerArrayList.get(position).adres);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Intent intent = new Intent(holder.itemView.getContext(),);
                Intent intent = new Intent(holder.itemView.getContext(), DetalistWorkerActivity.class);
                intent.putExtra("nitelik",postWorkerArrayList.get(holder.getAdapterPosition()).nitelik);
                intent.putExtra("sirketAdi",postWorkerArrayList.get(holder.getAdapterPosition()).sirketAdi);
                intent.putExtra("adres",postWorkerArrayList.get(holder.getAdapterPosition()).adres);
                intent.putExtra("aciklama",postWorkerArrayList.get(holder.getAdapterPosition()).aciklama);
                intent.putExtra("calismasekli",postWorkerArrayList.get(holder.getAdapterPosition()).calismasekli);
                intent.putExtra("adres",postWorkerArrayList.get(holder.getAdapterPosition()).adres);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return postWorkerArrayList.size();
    }

    public class WorkHolder extends RecyclerView.ViewHolder {
        DesignWorkerlistBinding designWorkerlistBinding;
        public WorkHolder(DesignWorkerlistBinding designWorkerlistBinding) {
            super(designWorkerlistBinding.getRoot());
            this.designWorkerlistBinding=designWorkerlistBinding;
        }

    }

}

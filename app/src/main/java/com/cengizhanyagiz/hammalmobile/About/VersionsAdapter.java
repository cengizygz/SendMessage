package com.cengizhanyagiz.hammalmobile.About;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.R;

import java.util.ArrayList;

public class VersionsAdapter  extends RecyclerView.Adapter<VersionsAdapter.ViewHolder> {


    ArrayList<Versions> arrayList;
    Context context;

    public VersionsAdapter(ArrayList<Versions> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VersionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_item_design,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionsAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(arrayList.get(position).title);
        holder.tv_desc.setText(arrayList.get(position).description);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_desc;
        RelativeLayout rl_title_line;
        RelativeLayout rl_desc_line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_desc=itemView.findViewById(R.id.tv_desc);
            rl_desc_line=itemView.findViewById(R.id.rl_desc_line);
            rl_title_line=itemView.findViewById(R.id.rl_title_line);
        }
    }
}

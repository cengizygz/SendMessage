package com.cengizhanyagiz.hammalmobile.Language;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.R;

import java.util.List;

public class VersionsAdapter extends RecyclerView.Adapter<VersionsAdapter.ViewHolder> {
    List<Versions> versionsList;

    public VersionsAdapter(List<Versions> versionsList) {
        this.versionsList = versionsList;
    }

    @NonNull
    @Override
    public VersionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionsAdapter.ViewHolder holder, int position) {
        Versions versions = versionsList.get(position);
        holder.codeNametxt.setText(versions.getCodeName());
        holder.versionTxt.setText(versions.getVersion());

        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codeNametxt, versionTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            codeNametxt = itemView.findViewById(R.id.code_name);
            versionTxt= itemView.findViewById(R.id.version);

            linearLayout=itemView.findViewById(R.id.linear_layout);
            expandableLayout=itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Versions versions = versionsList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

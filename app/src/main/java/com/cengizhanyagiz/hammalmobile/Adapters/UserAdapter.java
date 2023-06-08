package com.cengizhanyagiz.hammalmobile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.Fragment.UserDetalistFragment;
import com.cengizhanyagiz.hammalmobile.Model.Kullanicilar;
import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.Utils.ChangeFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    public UserAdapter(List<String> userKeysList, Activity activity, Context context) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference();
    }

    //layout tanımlaması yapılacak
    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userlayout,parent,false);
        return new ViewHolder(view);
    }
    // viewlara setleme yapılacak
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

      //  holder.username.setText(userKeysList.get(position).toString());
        reference.child("Kullanicilar").child(userKeysList.get(position).toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Kullanicilar kl = snapshot.getValue(Kullanicilar.class);
                Boolean userState = Boolean.parseBoolean(snapshot.child("state").getValue().toString());
                if(!kl.getIsim().equals("null")){
                    holder.username.setText(kl.getIsim());
                }
                if(userState == true){
                    holder.user_state_img.setImageResource(R.drawable.online_icon);
                }
                else{
                    holder.user_state_img.setImageResource(R.drawable.offline_icon);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.userAnaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment= new ChangeFragment(context);
                changeFragment.changeWithParameter(new UserDetalistFragment(),userKeysList.get(position));
            }
        });


    }
    //adepteri olusturacak liste size
    @Override
    public int getItemCount() {
        return userKeysList.size();
    }
    //view tanımlama işlemi
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView username;
        LinearLayout userAnaLayout;
        CircleImageView userimage,user_state_img;
        ViewHolder(View itemView){
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.username);
            userimage=(CircleImageView) itemView.findViewById(R.id.userimage);
            user_state_img=(CircleImageView) itemView.findViewById(R.id.user_state_img);
            userAnaLayout= (LinearLayout) itemView.findViewById(R.id.userAnaLayout);
        }
    }
}

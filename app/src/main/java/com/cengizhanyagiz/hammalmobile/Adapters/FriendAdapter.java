package com.cengizhanyagiz.hammalmobile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.Model.Kullanicilar;
import com.cengizhanyagiz.hammalmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    String userId;

    public FriendAdapter(List<String> userKeysList, Activity activity, Context context) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference();
        auth= FirebaseAuth.getInstance();
        firebaseUser= auth.getCurrentUser();
        userId= firebaseUser.getUid();
    }

    //layout tanımlaması yapılacak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_layout,parent,false);
        return new ViewHolder(view);
    }
    // viewlara setleme yapılacak
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      //  holder.username.setText(userKeysList.get(position).toString());
        reference.child("Kullanicilar").child(userKeysList.get(position).toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userName = snapshot.child("isim").getValue().toString();
                Boolean stateUser = Boolean.parseBoolean(snapshot.child("state").getValue().toString());
                if(stateUser==true){
                    holder.friend_state_img.setImageResource(R.drawable.online_icon);
                }else{
                    holder.friend_state_img.setImageResource(R.drawable.offline_icon);
                }


                    holder.friend_text.setText(userName);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        TextView friend_text;
        CircleImageView friend_state_img;

        ViewHolder(View itemView){
            super(itemView);
            friend_text=(TextView) itemView.findViewById(R.id.friend_text);
            friend_state_img=(CircleImageView) itemView.findViewById(R.id.friend_state_img);

        }
    }

}

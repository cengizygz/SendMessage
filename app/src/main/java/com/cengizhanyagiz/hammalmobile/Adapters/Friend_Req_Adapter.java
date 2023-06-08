package com.cengizhanyagiz.hammalmobile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.google.firebase.firestore.FieldValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Friend_Req_Adapter extends RecyclerView.Adapter<Friend_Req_Adapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    String userId;

    public Friend_Req_Adapter(List<String> userKeysList, Activity activity, Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.friend_req_layout,parent,false);
        return new ViewHolder(view);
    }
    // viewlara setleme yapılacak
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      //  holder.username.setText(userKeysList.get(position).toString());
        reference.child("Kullanicilar").child(userKeysList.get(position).toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Kullanicilar kl = snapshot.getValue(Kullanicilar.class);

                    holder.friend_req_name.setText(kl.getIsim());

                    holder.friend_req_ekle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            kabulEt(userId,userKeysList.get(position));
                        }
                    });

                    holder.friend_req_sil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            redEt(userId,userKeysList.get(position));
                        }
                    });
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
        TextView friend_req_name;
        CircleImageView userimage;
        Button friend_req_ekle,friend_req_sil;
        ViewHolder(View itemView){
            super(itemView);
            friend_req_name=(TextView) itemView.findViewById(R.id.friend_req_name);
            userimage=(CircleImageView) itemView.findViewById(R.id.userimage);
            friend_req_ekle=(Button) itemView.findViewById(R.id.friend_req_ekle);
            friend_req_sil=(Button) itemView.findViewById(R.id.friend_req_sil);
        }
    }
    private void kabulEt(String userId, String otherId) {

        DateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        final String reportDate = date.format(today);
        reference.child("Arkadaslar").child(userId).child(otherId).child("date").setValue(reportDate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference.child("Arkadaslar").child(otherId).child(userId).child("date").setValue(reportDate).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(context, "Arkadaşlık İsteğini Kabul Ettiniz", Toast.LENGTH_SHORT).show();
                            redEt(userId,otherId);
                        }
                    }
                })    ;
            }
        });
    }
    private void redEt(String userId, String otherId) {
        reference.child("Arkadaslik_Istek").child(userId).child(otherId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                reference.child("Arkadaslik_Istek").child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Arkadaşlık İsteğini İptal Ettiniz", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

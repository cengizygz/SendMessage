package com.cengizhanyagiz.hammalmobile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cengizhanyagiz.hammalmobile.Model.MessageModel;
import com.cengizhanyagiz.hammalmobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    String userId;
    List<MessageModel> messageModelList;
    Boolean state;
    int view_type_sent=1,view_type_recieved=2;

    public MessageAdapter(List<String> userKeysList, Activity activity, Context context,List<MessageModel> messageModelList) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference();
        auth= FirebaseAuth.getInstance();
        firebaseUser= auth.getCurrentUser();
        userId= firebaseUser.getUid();
        this.messageModelList= messageModelList;
        state=false;
    }

    //layout tanımlaması yapılacak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(viewType==view_type_sent)
        {
                view = LayoutInflater.from(context).inflate(R.layout.message_sent_layout,parent,false);
            return new ViewHolder(view);
        }else{
                view = LayoutInflater.from(context).inflate(R.layout.message_received_layout,parent,false);
            return new ViewHolder(view);
        }

    }
    // viewlara setleme yapılacak
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.messageText.setText(messageModelList.get(position).getText());


    }



    //adepteri olusturacak liste size
    @Override
    public int getItemCount() {
        return messageModelList.size();
    }



    //view tanımlama işlemi
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView messageText;


        ViewHolder(View itemView){
            super(itemView);
            if(state==true) {
                messageText = (TextView) itemView.findViewById(R.id.message_send_text);
            }
            else {
                messageText = (TextView) itemView.findViewById(R.id.message_received_text);
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModelList.get(position).getFrom().equals(userId))
        {
            state=true;
            return view_type_sent;
        }else{
            state=false;
            return view_type_recieved;
        }
    }
}

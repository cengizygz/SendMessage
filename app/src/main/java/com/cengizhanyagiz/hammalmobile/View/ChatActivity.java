package com.cengizhanyagiz.hammalmobile.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cengizhanyagiz.hammalmobile.Adapters.MessageAdapter;
import com.cengizhanyagiz.hammalmobile.Fragment.UserDetalistFragment;
import com.cengizhanyagiz.hammalmobile.Model.MessageModel;
import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.Utils.ChangeFragment;
import com.cengizhanyagiz.hammalmobile.Utils.GetDate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    TextView chat_username_textview;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FloatingActionButton sendMessageButton;
    EditText messageTextEditText;
    List<MessageModel> messageModelList;
    RecyclerView chat_recy_view;
    MessageAdapter messageAdapter;
    List<String> keylist;
    ImageView back_btn;
    ChangeFragment changeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        action();
        loadMessage();
    }
    public String getUserName(){

        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("userName");
        return userName;
    }

    public String getId(){
        String id = getIntent().getExtras().getString("id").toString();
        return id;
    }
    public void tanimla(){
        chat_username_textview = (TextView) findViewById(R.id.chat_username_textview);
        chat_username_textview.setText(getUserName());
        auth= FirebaseAuth.getInstance();
        firebaseUser= auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference();
        sendMessageButton= (FloatingActionButton) findViewById(R.id.sendMessageButton);
        messageTextEditText= (EditText) findViewById(R.id.messageTextEditText);
        messageModelList= new ArrayList<>();
        keylist= new ArrayList<>();
        chat_recy_view = (RecyclerView) findViewById(R.id.chat_recy_view);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(ChatActivity.this,1);
        chat_recy_view.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(keylist,ChatActivity.this,ChatActivity.this,messageModelList);
        chat_recy_view.setAdapter(messageAdapter);
        back_btn=(ImageView) findViewById(R.id.back_btn);

    }
    public void action(){


        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String message = messageTextEditText.getText().toString();
                messageTextEditText.setText("");
                    sendMessage(firebaseUser.getUid(),getId(),"text", GetDate.getDate(),false,message);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment.change(new UserDetalistFragment());
            }
        });
    }
    public void sendMessage(String userId,String otherId,String textType,String date,Boolean seen,String messageText)
    {
       String mesajId = reference.child("Mesajlar").child(userId).child(otherId).push().getKey();
        Map messageMap = new HashMap();
        messageMap.put("type",textType);
        messageMap.put("time",date);
        messageMap.put("seen",seen);
        messageMap.put("text",messageText);
        messageMap.put("from",userId);

        reference.child("Mesajlar").child(userId).child(otherId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference.child("Mesajlar").child(otherId).child(userId).child(mesajId).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });
    }
        public void  loadMessage(){
        reference.child("Mesajlar").child(firebaseUser.getUid()).child(getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageModel messageModel = snapshot.getValue(MessageModel.class);
                messageModelList.add(messageModel);
                messageAdapter.notifyDataSetChanged();
                keylist.add(snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }



}
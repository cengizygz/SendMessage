package com.cengizhanyagiz.hammalmobile.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cengizhanyagiz.hammalmobile.MainActivity;
import com.cengizhanyagiz.hammalmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText input_email,input_name,input_date,input_password;
    AppCompatButton registerButon;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private EditText yas,isim,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tanımla();
    }
    public void tanımla() {
        input_email =(EditText) findViewById(R.id.input_email);
        input_date =(EditText) findViewById(R.id.input_date);
        input_name =(EditText) findViewById(R.id.input_name);
        input_password =(EditText) findViewById(R.id.input_password);
        registerButon =(AppCompatButton) findViewById(R.id.registerButon);
        auth = FirebaseAuth.getInstance();



        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
                String pass = input_password.getText().toString();
                if(!email.equals("")&&!pass.equals("")){
                    input_email.setText("");
                    input_password.setText("");
                    KayıtOl(email,pass);
                }else{
                    Toast.makeText(getApplicationContext(),"Bilgileri boş giremezsiniz...",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    
    public void KayıtOl(String email,String pass){
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   String yas = input_date.getText().toString();
                    String isim = input_name.getText().toString();
                    // veritabanı bilgi kayıt
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    reference = firebaseDatabase.getReference().child("Kullanicilar").child(auth.getUid());
                    Map  map = new HashMap();
                    map.put("resim","null");
                    map.put("isim",isim);
                    map.put("yas",yas);
                    map.put("meslek","null");
                    map.put("email",email);
                    reference.setValue(map);


                    //auth girişi
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Kayıt Olurken Bir Problem Oldu", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public  void registerBack(View view){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
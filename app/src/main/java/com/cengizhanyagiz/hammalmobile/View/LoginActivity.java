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

import com.cengizhanyagiz.hammalmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText input_email,input_password;
    private AppCompatButton loginButon;
    FirebaseAuth auth;
    TextView hesabinYokMuBtn;
    private FirebaseUser user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tanimla();
        auth = FirebaseAuth.getInstance();

    }

    public void tanimla(){
        input_email=(EditText) findViewById(R.id.input_email);
        input_password=(EditText) findViewById(R.id.input_password);
        loginButon=(AppCompatButton) findViewById(R.id.loginButon);

        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
                String pass = input_password.getText().toString();
                if(!email.equals("")&&!pass.equals("")){
                    giris(email,pass);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Boş Giremezsiniz", Toast.LENGTH_LONG).show();
                }
            }
        });

        hesabinYokMuBtn = (TextView)findViewById(R.id.hesabinYokMuBtn);

        hesabinYokMuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Kullanıcı kayıtlı ise

    }

    public void giris(String email,String pass){

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,BottomNavigationActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Email veya Şifre Hatalı", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
package com.cengizhanyagiz.hammalmobile.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cengizhanyagiz.hammalmobile.Fragment.HomeFragment;
import com.cengizhanyagiz.hammalmobile.Fragment.MessageFragment;
import com.cengizhanyagiz.hammalmobile.Fragment.NotificationFragment;
import com.cengizhanyagiz.hammalmobile.Fragment.ProfileFragment;
import com.cengizhanyagiz.hammalmobile.Fragment.SettingsFragment;
import com.cengizhanyagiz.hammalmobile.MainActivity;
import com.cengizhanyagiz.hammalmobile.Utils.ChangeFragment;
import com.cengizhanyagiz.hammalmobile.databinding.ActivityBottomNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.cengizhanyagiz.hammalmobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BottomNavigationActivity extends AppCompatActivity  {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private ChangeFragment changeFragment;
    ActivityBottomNavigationBinding binding;
    AppCompatButton editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);


      //  replaceFragment(new HomeFragment());


        changeFragment = new ChangeFragment(BottomNavigationActivity.this);
        changeFragment.change(new HomeFragment());
        tanimla();
        kontrol();
        binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


        switch (item.getItemId()) {
            case R.id.home:
                changeFragment.change(new HomeFragment());
                return true;
            case R.id.profile:
                changeFragment.change( new ProfileFragment());
                return true;
            case R.id.message:
                changeFragment.change( new MessageFragment());
                return true;
            case R.id.setting:
                changeFragment.change( new SettingsFragment());
                return true;
        }

        return false;
        });


    }

   /* private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relativelayout,fragment);
        fragmentTransaction.commit();
    }*/

   /* public void cik(){
        auth.signOut();
        Intent intent= new Intent(BottomNavigationActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }*/
    public void singoutBtn (View view){

        auth.signOut();

        Intent intent = new Intent(BottomNavigationActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference reference=firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(false);
    }
    public void tanimla(){
        auth= FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference reference=firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference reference=firebaseDatabase.getReference().child("Kullanicilar");
        reference.child(user.getUid()).child("state").setValue(true);
    }

    public void kontrol(){
        if(user==null){
            Intent intent= new Intent(BottomNavigationActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }else{
            FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
            DatabaseReference reference=firebaseDatabase.getReference().child("Kullanicilar");
            reference.child(user.getUid()).child("state").setValue(true);
        }
    }
    public  void  editworkerbtn (View view){
        Intent intent = new Intent(BottomNavigationActivity.this, WorkerAddActivity.class);
        startActivity(intent);
        finish();

    }



    public  void  editJobbtn (View view){
        Intent intent = new Intent(BottomNavigationActivity.this, JobAddActivity.class);
        startActivity(intent);
        finish();
    }
    public  void  languageBtn (View view){
        Intent intent = new Intent(BottomNavigationActivity.this, LanguageActivity.class);
        startActivity(intent);
        finish();
    }
    public void aboutBtn (View view){

        Intent intent = new Intent(BottomNavigationActivity.this, AboutActivity.class);
        startActivity(intent);
        finish();
    }
    public void islistbtn(View view){
        Intent intent = new Intent(BottomNavigationActivity.this, JobListActivity.class);
        startActivity(intent);
        finish();
    }
    public void iscilistbtn(View view){
        Intent intent = new Intent(BottomNavigationActivity.this, WorkerListActivity.class);
        startActivity(intent);
        finish();
    }
}
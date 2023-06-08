package com.cengizhanyagiz.hammalmobile.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cengizhanyagiz.hammalmobile.Model.Kullanicilar;
import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.Utils.ChangeFragment;
import com.cengizhanyagiz.hammalmobile.View.BottomNavigationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;
    View view;
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText kullaniciIsmi,profilemail,profilyas,profilmeslek;
    CircleImageView profile_image;
    AppCompatButton profil_guncelle;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);
        tanimle();
        bilgileriGetir();
        return view;
    }


    public void tanimle(){
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Kullanicilar").child(user.getUid());

            //View tanımlama işlemleri
        kullaniciIsmi = (EditText) view.findViewById(R.id.kullaniciIsmi);
        profilemail = (EditText) view.findViewById(R.id.profilemail);
        profilyas = (EditText) view.findViewById(R.id.profilyas);
        profilmeslek = (EditText) view.findViewById(R.id.profilmeslek);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        profil_guncelle= (AppCompatButton) view.findViewById(R.id.profil_guncelle);
     //   profil_arkadaslar= (AppCompatButton)view.findViewById(R.id.profil_arkadaslar);
    //    profil_istekler= (AppCompatButton)view.findViewById(R.id.profil_istekler);

        profil_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle();
            }
        });
      /*
        profil_arkadaslar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment = new ChangeFragment(getContext());
                changeFragment.change(new ArkadaslarFragment());
            }
        });
        profil_istekler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment = new ChangeFragment(getContext());
                changeFragment.change(new NotificationFragment());
            }
        });
        */

    }


    public void bilgileriGetir(){

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Kullanicilar kl = snapshot.getValue(Kullanicilar.class);

                kullaniciIsmi.setText(kl.getIsim());
                profilemail.setText(kl.getEmail());
                profilyas.setText(kl.getYas());
                profilmeslek.setText(kl.getMeslek());
                if(!kl.getResim().equals("null")){

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


    }

    public  void guncelle(){
        String isim =kullaniciIsmi.getText().toString();
        String email =profilemail.getText().toString();
        String yas =profilyas.getText().toString();
        String meslek =profilmeslek.getText().toString();

        reference = database.getReference().child("Kullanicilar").child(auth.getUid());
        Map map = new HashMap();

        map.put("isim",isim);
        map.put("yas",yas);
        map.put("meslek",meslek);
        map.put("email",email);
        map.put("resim","null");


        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    ChangeFragment fragment = new ChangeFragment(getContext());
                    fragment.change(new ProfileFragment());
                Toast.makeText(getContext(), "Bilgiler Başarılıyla Güncellendi", Toast.LENGTH_LONG).show();
            }else{
                    Toast.makeText(getContext(), "Bilgiler Güncellenmedi", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
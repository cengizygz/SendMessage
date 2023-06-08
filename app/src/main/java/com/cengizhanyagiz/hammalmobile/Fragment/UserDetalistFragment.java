package com.cengizhanyagiz.hammalmobile.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cengizhanyagiz.hammalmobile.Model.Kullanicilar;
import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.Utils.ShowToastMessage;
import com.cengizhanyagiz.hammalmobile.View.ChatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserDetalistFragment extends Fragment {


    View view;
    String otherId,userId,kontrol="",begeniKontrol="";
    TextView userProfileIsim,userProfileIsim2,userProfileYas,userProfileMeslek,userProfileArkadasText,userProfileTakipText;
    ImageView userProfileTakipEtImage,userProfileMesajImage,userProfileArkadasEkleImage;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference,reference_Arkadaslik;
    FirebaseAuth auth;
    FirebaseUser user;
    ShowToastMessage showToastMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_detalist, container, false);
        tanimla();
        action();
        getBegeniText();
        getArkadasText();
        return view;
    }

    public void tanimla() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        reference_Arkadaslik=firebaseDatabase.getReference().child("Arkadaslik_Istek");
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        userId= user.getUid();

        otherId = getArguments().getString("userid");

        userProfileIsim2 = (TextView)view.findViewById(R.id.userProfileIsim2);
        userProfileIsim = (TextView)view.findViewById(R.id.userProfileIsim);
        userProfileYas = (TextView)view.findViewById(R.id.userProfileYas);
        userProfileMeslek = (TextView)view.findViewById(R.id.userProfileMeslek);


        userProfileArkadasText = (TextView)view.findViewById(R.id.userProfileArkadasText);
        userProfileTakipText = (TextView)view.findViewById(R.id.userProfileTakipText);


        userProfileTakipEtImage = (ImageView) view.findViewById(R.id.userProfileTakipEtImage);
        userProfileMesajImage = (ImageView) view.findViewById(R.id.userProfileMesajImage);
        userProfileArkadasEkleImage = (ImageView) view.findViewById(R.id.userProfileArkadasEkleImage);

        reference_Arkadaslik.child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userId)){
                    kontrol="istek";
                    userProfileArkadasEkleImage.setImageResource(R.drawable.arkadas_sil);
                }
                else
                {

                    userProfileArkadasEkleImage.setImageResource(R.drawable.arkadas_ekle);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("Arkadaslar").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(otherId))
                {
                    kontrol = "arkadas";
                    userProfileArkadasEkleImage.setImageResource(R.drawable.user_delete);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ////////////////////////////////////////////////////////
        reference.child("Arkadaslar").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userId))
                {
                    kontrol = "arkadas";
                    userProfileArkadasEkleImage.setImageResource(R.drawable.user_delete);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /////////////////////////////////////////////////////////
        reference.child("Begeniler").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userId))
                {
                    begeniKontrol= "begendi";
                    userProfileTakipEtImage.setImageResource(R.drawable.takip_off);
                }else
                {
                    userProfileTakipEtImage.setImageResource(R.drawable.takip_ok);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        showToastMessage= new ShowToastMessage(getContext());
    }
    public void action(){

        reference.child("Kullanicilar").child(otherId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Kullanicilar kl = snapshot.getValue(Kullanicilar.class);
                userProfileIsim.setText("İsim : "+kl.getIsim());
                userProfileYas.setText("Yaş : "+kl.getYas());
                userProfileMeslek.setText("Meslek : "+kl.getMeslek());
                userProfileIsim2.setText(kl.getIsim());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        userProfileArkadasEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kontrol.equals("istek"))
                {

                    arkadasIptalEt(otherId,userId);
                }
                else if(kontrol.equals("arkadas"))
                {
                    arkadasCikar(otherId,userId);
                }
                else
                {
                    arkadasEkle(otherId, userId);
                }
            }
        });
        userProfileTakipEtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(begeniKontrol.equals("begendi"))
                {
                    begeniIptal(userId,otherId);
                }
                else
                {
                    begen(userId,otherId);
                }
            }
        });

        userProfileMesajImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("userName",userProfileIsim2.getText().toString());
                intent.putExtra("id",otherId);
                startActivity(intent);
            }
        });
    }
    public void begeniIptal(String userId, String otherId)
    {
        reference.child("Begeniler").child(otherId).child(userId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                userProfileTakipEtImage.setImageResource(R.drawable.takip_ok);
                begeniKontrol= "";
                Toast.makeText(getContext(),    "Beğeni İptal Edildi", Toast.LENGTH_SHORT).show();
                getBegeniText();
            }
        });
    }


    public void arkadasCikar(String otherId,String userId){
        reference.child("Arkadaslar").child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                reference.child("Arkadaslar").child(userId).child(otherId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        kontrol="";
                        userProfileArkadasEkleImage.setImageResource(R.drawable.arkadas_ekle);
                        getArkadasText();
                        Toast.makeText(getContext(),     "Arkadaşlıktan Çıkarıldı", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void arkadasEkle(String otherId, String userId)
    {
        reference_Arkadaslik.child(userId).child(otherId).child("tip").setValue("gonderdi").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    reference_Arkadaslik.child(otherId).child(userId).child("tip").setValue("aldi").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                kontrol="istek";
                                userProfileArkadasEkleImage.setImageResource(R.drawable.arkadas_sil);
                                Toast.makeText(getContext(),     "Arkadaşlık İsteği Başarı İle Gönderildi", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(getContext(),     "Bir Problem Oluştu", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getContext(),     "Bir Problem Oluştu", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void arkadasIptalEt(String otherId, String userId){
        reference_Arkadaslik.child(otherId).child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                reference_Arkadaslik.child(userId).child(otherId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                            kontrol="";
                        userProfileArkadasEkleImage.setImageResource(R.drawable.arkadas_ekle);
                        Toast.makeText(getContext(),     "Arkadaşlık İsteği İptal Edildi", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    public void begen(String userId,String otherId)
    {
        reference.child("Begeniler").child(otherId).child(userId).child("tip").setValue("Begendi").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getContext(),     "Profili Beğendiniz", Toast.LENGTH_SHORT).show();
                    userProfileTakipEtImage.setImageResource(R.drawable.takip_off);
                    begeniKontrol="begendi";
                    getBegeniText();
                }
            }
        });
    }
    public void getBegeniText()
    {
       // userProfileTakipText.setText("0 Beğeni");
      //  List<String> begeniList = new ArrayList<>();
        reference.child("Begeniler").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfileTakipText.setText(snapshot.getChildrenCount()+" Beğeni");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getArkadasText()
    {
       // List<String> arkList = new ArrayList<>();
       // userProfileArkadasText.setText("0 Arkadaş");
        reference.child("Arkadaslar").child(otherId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfileArkadasText.setText(snapshot.getChildrenCount()+" Arkadaş");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
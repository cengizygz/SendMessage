package com.cengizhanyagiz.hammalmobile.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cengizhanyagiz.hammalmobile.JobList.Adapter;
import com.cengizhanyagiz.hammalmobile.Model.PostJob;
import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.databinding.ActivityJobListBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class JobListActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    ArrayList<PostJob> postJobArrayList;
    private ActivityJobListBinding binding;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseFirestore= FirebaseFirestore.getInstance();
        getData();
        postJobArrayList = new ArrayList<>();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(postJobArrayList);
        binding.recyclerView.setAdapter(adapter);
    }
    private void getData(){
        firebaseFirestore.collection("Jobseeker").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Toast.makeText(JobListActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                if(value != null){
                    for(DocumentSnapshot snapshot : value.getDocuments()){

                        Map<String,Object> data = snapshot.getData();
                        String adres = (String) data.get("adres");
                        String imageUrl = (String) data.get("imageUrl");
                        String isim = (String) data.get("isim");
                        String meslek = (String) data.get("meslek");

                        ////////////////////////////////////////////////////////
                        String deneyim = (String) data.get("deneyim");
                        String iletisim = (String) data.get("iletisim");
                        String ogretim = (String) data.get("ogretim");
                        String tanit = (String) data.get("tanit");
                        String yas = (String) data.get("yas");
                        String calismasaat = (String) data.get("çalışma saat");

                        PostJob postjob = new PostJob(adres,imageUrl,isim,meslek,deneyim,iletisim,ogretim,tanit,yas,calismasaat);

                        postJobArrayList.add(postjob);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    public void jobback (View view){

        Intent intent = new Intent(JobListActivity.this, BottomNavigationActivity.class);
        startActivity(intent);
        finish();
    }
}
package com.emirhan.topnote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.emirhan.topnote.databinding.ActivityFirstpageBinding;
import com.emirhan.topnote.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class firstpage extends AppCompatActivity {
private FirebaseAuth auth;
private FirebaseFirestore firebaseFirestore;
private ActivityFirstpageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstpageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
auth = FirebaseAuth.getInstance();
firebaseFirestore = FirebaseFirestore.getInstance();
getData();

    }
    private void getData()
    {
firebaseFirestore.collection("notes").addSnapshotListener(new EventListener<QuerySnapshot>() {
    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
        if(error!= null)
        {
            Toast.makeText(firstpage.this, error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
        if(value!= null)
        {
           for(DocumentSnapshot snapshot: value.getDocuments())
           {
               Map<String, Object> data = snapshot.getData();
              String subject = (String) data.get("subject");
               
           }
        }
    }
});
    }
    public void profile (View View)
    {
        Intent otherpage = new Intent(firstpage.this, profile.class);
        startActivity(otherpage);
    }
    public void upload (View View)
    {
        Intent otherpage = new Intent(firstpage.this, upload.class);
        startActivity(otherpage);
    }


}
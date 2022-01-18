package com.emirhan.topnote.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.emirhan.topnote.adapter.notesadapter;
import com.emirhan.topnote.databinding.ActivityFirstpageBinding;
import com.emirhan.topnote.model.notes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class firstpage extends AppCompatActivity {
private FirebaseAuth auth;
private FirebaseFirestore firebaseFirestore;
private ActivityFirstpageBinding binding;
notesadapter NotesAdapter;
ArrayList<notes> notesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        notesArrayList= new ArrayList<>();
        super.onCreate(savedInstanceState);
        binding = ActivityFirstpageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
auth = FirebaseAuth.getInstance();

firebaseFirestore = FirebaseFirestore.getInstance();
getData();
binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
NotesAdapter= new notesadapter(notesArrayList);
binding.recyclerView.setAdapter(NotesAdapter);
    }
    private void getData()
    {
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
firebaseFirestore.collection("notes").orderBy("date", Query.Direction.DESCENDING).whereEqualTo("useremail", email).addSnapshotListener(new EventListener<QuerySnapshot>() {
    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

        if(value!= null)
        {
           for(DocumentSnapshot snapshot: value.getDocuments())
           {
               Map<String, Object> data = snapshot.getData();
              String subject = (String) data.get("subject");
               String words = (String) data.get("words");
               String downloadurl = (String) data.get("downloadurl");

              notes notes = new notes(subject, words, downloadurl);
notesArrayList.add(notes);

           }
           NotesAdapter.notifyDataSetChanged();
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
package com.emirhan.topnote.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.emirhan.topnote.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {
private FirebaseAuth auth;
    private ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        binding.email.setText("@"+email);

    }
    public void home (View View)
    {
        Intent otherpage = new Intent(profile.this, firstpage.class);
        startActivity(otherpage);
    }
    public void signout (View View)
    {
auth.signOut();
        Intent otherpage = new Intent(profile.this, login.class);
        startActivity(otherpage);
        finish();
    }
    public void change (View View)
    {
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
       auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               Toast.makeText(profile.this, "Please check your mail box",Toast.LENGTH_LONG).show();
           }
       });
    }
}
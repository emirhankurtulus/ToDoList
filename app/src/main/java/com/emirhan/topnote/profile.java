package com.emirhan.topnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.emirhan.topnote.databinding.ActivityProfileBinding;
import com.emirhan.topnote.databinding.ActivityUploadBinding;
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
}
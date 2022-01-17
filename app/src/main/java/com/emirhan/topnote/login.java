package com.emirhan.topnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emirhan.topnote.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
    }
    public void sign (View View)
    {
        String email = binding.mail.getText().toString();
        String password = binding.password.getText().toString();

        if(email.equals("") || password.equals("") )
        {
binding.error.setText("Email and password cannot be empty");
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent otherpage = new Intent(login.this, firstpage.class);
                    startActivity(otherpage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    binding.error.setText("please enter a valid user");
                }
            });
        }
    }
    public void signup (View View)
    {
        Intent otherpage = new Intent(login.this, signup.class);
        startActivity(otherpage);
    }
}
package com.emirhan.topnote.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.emirhan.topnote.R;
import com.emirhan.topnote.databinding.ActivityLoginBinding;
import com.emirhan.topnote.databinding.ActivitySsssBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ssss extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivitySsssBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssss);

        binding = ActivitySsssBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
    }
    public void send (View View)
    {
        String email = binding.mail.getText().toString();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ssss.this, "Please check your mail box",Toast.LENGTH_LONG).show();
                Intent otherpage = new Intent(ssss.this, login.class);
                startActivity(otherpage);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ssss.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void sign (View View)
    {
        Intent otherpage = new Intent(ssss.this, login.class);
        startActivity(otherpage);
        finish();
    }
}
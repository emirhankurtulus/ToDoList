package com.emirhan.topnote.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            Intent otherpage = new Intent(MainActivity.this, firstpage.class);
            startActivity(otherpage);
            finish();
        } else {
            Intent otherpage = new Intent(MainActivity.this, walkthrough.class);
            startActivity(otherpage);


        }




    }
}




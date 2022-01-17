package com.emirhan.topnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class walkthrough extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

    }
    public void skip (View v)
    {
        Intent otherpage=new Intent(walkthrough.this, walkthrough2.class);
        startActivity(otherpage);
    }
}
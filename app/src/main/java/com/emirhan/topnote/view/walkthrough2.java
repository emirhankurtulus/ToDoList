package com.emirhan.topnote.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emirhan.topnote.R;

public class walkthrough2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough2);
    }
    public void skip (View v)
    {
        Intent otherpage=new Intent(walkthrough2.this, walkthrough3.class);
        startActivity(otherpage);
    }
}
package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class walkthrough3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough3);
    }
    public void sign (View View)
    {
        Intent ikincisayfagecis=new Intent(walkthrough3.this, login.class);
        startActivity(ikincisayfagecis);
    }
}
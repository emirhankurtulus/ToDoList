package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void skip (View View)
    {
        Intent ikincisayfagecis=new Intent(MainActivity.this, walkthrough2.class);
        startActivity(ikincisayfagecis);
    }
}
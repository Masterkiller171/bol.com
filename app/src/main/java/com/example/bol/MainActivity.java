package com.example.bol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bol.GUI.Eventlistener;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_but_search).setOnClickListener(new Eventlistener());
    }

}

package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    TextView welcomeTxt, LuckyNumberTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        welcomeTxt = findViewById(R.id.textView2);
        LuckyNumberTxt = findViewById(R.id.luckynumbertxt);
        share_btn = findViewById(R.id.share_number_btn);

        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        Toast.makeText(this, userName, Toast.LENGTH_LONG).show();
    }
}

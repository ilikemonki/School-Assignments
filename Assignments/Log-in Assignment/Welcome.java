package com.example.hm_1;

// Josue Crandall and Meng Cha
// CECS 453 Mobile Apps
// 2/20/20

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    private TextView mTextViewWelcome;

    private String mUsernameKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Initialize
        mTextViewWelcome = findViewById(R.id.text_view_welcome);
        mUsernameKey = getResources().getString(R.string.username_key);

        //Edit Welcome textview
        mTextViewWelcome.setText("Welcome "+getIntent().getStringExtra(mUsernameKey)+"!");
    }
}

package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

//        read data from previous activity
        Intent data = getIntent();
        ((TextView) findViewById(R.id.msgLabel)).setText(data.getStringExtra("TXT"));
    }

    public void startNewGame(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }

}
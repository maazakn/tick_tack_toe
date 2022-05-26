package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart(View e) {
        String player1Name = ((EditText) findViewById(R.id.player1Name)).getText().toString();
        String player2Name = ((EditText) findViewById(R.id.player2Name)).getText().toString();

        if (player1Name.length() == 0 || player2Name.length() == 0) {
            Toast.makeText(this, "Please Enter Players Names", Toast.LENGTH_LONG).show();
        } else {
//            navigate to new activity
            Intent gameActivity = new Intent(this,GameScreen.class);
            gameActivity.putExtra("player1",player1Name);
            gameActivity.putExtra("player2",player2Name);
            startActivity(gameActivity);
            finish();
        }
    }
}
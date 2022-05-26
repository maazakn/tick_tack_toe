package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GameScreen extends AppCompatActivity {
    String activePlayer = "1", player1Sign = "O", player2Sign = "X";

    int boxSize = 9;
    String[] playerInp = new String[boxSize];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

//        load game data
        prepareGameData();

//        set players Names
        Intent data = getIntent();
        ((TextView) findViewById(R.id.f1)).setText("P1: " + data.getStringExtra("player1")+" S:"+player1Sign);
        ((TextView) findViewById(R.id.f2)).setText("P2: " + data.getStringExtra("player2")+" S:"+player2Sign);

//        label
        updateLabel("Player " + activePlayer + " Turn");
    }

    //   check handler
    int gameProgress = 0;

    public void checkHandle(View v) {
        String sign = getCurrentPlayerSign(activePlayer);

        Button currentBtn = (Button) v;
        currentBtn.setEnabled(false);
        currentBtn.setText(sign);

        activePlayer = nextPlayer(activePlayer);
        updateLabel("Player " + activePlayer + " Will Play");

//        check the game is over or not
        if (gameProgress < boxSize) {
            playerInp[currentPosition(currentBtn.getId())] = sign;
            gameProgress += 1;
        }
        if (gameProgress == boxSize) {
            onGameOver("Ops! Game Over");
        }

//        check for winner
        String winner = checkWinner();
        if (winner != null) {
            if (Objects.equals(winner, player1Sign)) onGameOver("Player 1 Wins");
            else onGameOver("Player 2 Wins");
        }
    }

    private void prepareGameData() {
//        decide who gonna play first
        if (getRandomNum(1, 2) == 2) activePlayer = "2";

//        player symbols
        if (getRandomNum(0, 1) == 1) {
            player1Sign = "X";
            player2Sign = "O";
        }
        Toast.makeText(this, "System decides Player " + activePlayer + " will play First", Toast.LENGTH_SHORT).show();
    }

    private String getCurrentPlayerSign(String currentPlayer) {
        if (Objects.equals(currentPlayer, "1")) return player1Sign;
        return player2Sign;
    }

    private String nextPlayer(String currentPlayer) {
        if (Objects.equals(currentPlayer, "1")) return "2";
        return "1";
    }

    private String checkWinner() {
        for (int index = 0; index < boxSize; index++) {
            String line = null;

            switch (index) {
                case 0:
                    line = playerInp[0] + playerInp[1] + playerInp[2];
                    break;
                case 1:
                    line = playerInp[3] + playerInp[4] + playerInp[5];
                    break;
                case 2:
                    line = playerInp[6] + playerInp[7] + playerInp[8];
                    break;
                case 3:
                    line = playerInp[0] + playerInp[3] + playerInp[6];
                    break;
                case 4:
                    line = playerInp[1] + playerInp[4] + playerInp[7];
                    break;
                case 5:
                    line = playerInp[2] + playerInp[5] + playerInp[8];
                    break;
                case 6:
                    line = playerInp[0] + playerInp[4] + playerInp[8];
                    break;
                case 7:
                    line = playerInp[2] + playerInp[4] + playerInp[6];
                    break;
            }
            //For X winner
            if (Objects.equals(line, "XXX")) {
                return "X";
            }

            // For O winner
            else if (Objects.equals(line, "OOO")) {
                return "O";
            }
        }
        return null;
    }

    private void onGameOver(String msg) {
        Intent gameOver = new Intent(this, GameOver.class);
        gameOver.putExtra("TXT", msg);
        startActivity(gameOver);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    private int currentPosition(int id) {
        switch (id) {
            case R.id.b1:
                return 0;
            case R.id.b2:
                return 1;
            case R.id.b3:
                return 2;
            case R.id.b4:
                return 3;
            case R.id.b5:
                return 4;
            case R.id.b6:
                return 5;
            case R.id.b7:
                return 6;
            case R.id.b8:
                return 7;
            case R.id.b9:
                return 8;
            default:
                return -1;
        }
    }

    private void updateLabel(String txt) {
        ((TextView) findViewById(R.id.label)).setText(txt);
    }

    private int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
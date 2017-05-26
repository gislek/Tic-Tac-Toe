package com.example.gisle.prosjektoppgave;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GameActivity extends Activity {

    private boolean won;
    private boolean gameOver;
    private int turn;
    private int playerTurn;
    private int round;
    private int rounds;
    private int[] moves;
    private int[] wins;
    private int[][] board;
    private String[] playerName;
    private TextView text;
    private GridView gameBoard;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        playerName = new String[] {intent.getStringExtra("p1"), intent.getStringExtra("p2")};
        rounds = intent.getIntExtra("rounds", 3);

        text = (TextView)findViewById(R.id.textViewTurn);
        btn = (Button) findViewById(R.id.buttonNext);

        gameOver = false;
        wins = new int[] {0, 0};
        moves = new int[rounds];
        round = 0;

        startGame();
    }

    public void startGame() {
        board = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
    };
        round++;
        won = false;
        turn = 1;
        playerTurn = 1;
        text.setText(playerName[playerTurn -1]);
        gameBoard = (GridView) findViewById(R.id.gameBoard);
        gameBoard.setAdapter(new BoardAdapter(this));

        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(!won && !gameOver) {
                Button button = (Button)v;
                int row = position / 3;
                int col = position % 3;

                if (board[row][col] == 0) {
                    board[row][col] = playerTurn;
                    if (playerTurn == 1) {
                        button.setText("X");
                        playerTurn = 2;
                        text.setText(playerName[playerTurn -1]);
                    }
                    else {
                        button.setText("O");
                        playerTurn = 1;
                        text.setText(playerName[playerTurn -1]);
                    }
                }
                turn++;
                checkScore(row, col);
                }
            }
        });
    }

    public void checkScore(int row, int col) {
        int value = board[row][col];

        // Check row
        boolean same = true;
        for(int i=0;i<3;i++){
            if (board[row][i] != value) {
                same = false;
            }
        }
        if (same) {
            won = true;
        }

        // Check column
        if(!won){
            same = true;
            for(int i=0;i<3;i++){
                if (board[i][col] != value) {
                    same = false;
                }
            }
            if (same) {
                won = true;
            }
        }

        // Check diagonal lr
        if (!won && row == col) {
            same = true;
            for(int i = 0;i<3;i++){
                if (board[i][i] != value) {
                    same = false;
                }
            }
            if (same) {
                won = true;
            }
        }

        // Check diagonal rl
        if (!won && (row + col == 2)) {
            same = true;
            for(int i = 0;i<3;i++){
                if (board[i][2-i] != value) {
                    same = false;
                }
            }
            if (same) {
                won = true;
            }
        }

        // Draw
        if (!won && turn >= 10) {
            text.setText(R.string.noMoves);
            moves[round-1] = turn-1;
            btn.setVisibility((View.VISIBLE));

            if (round >= rounds){
                gameOver = true;
            }
        }

        // Win
        if (won) {
            Resources res = getResources();
            String txt = res.getString(R.string.win, playerName[value-1]);
            text.setText(txt);

            wins[value-1]++;
            moves[round-1] = turn-1;

            btn.setVisibility((View.VISIBLE));

            if (round >= rounds){
                gameOver = true;
            }
        }


    }

    public void onClick(View v) {
        if (!gameOver) {
            btn.setVisibility(View.INVISIBLE);
            startGame();
        }
        else {
            Intent intent = new Intent("ScoreActivity");
            intent.putExtra("playerNames", playerName);
            intent.putExtra("moves", moves);
            intent.putExtra("wins", wins);
            startActivity(intent);
            finish();
        }
    }
}

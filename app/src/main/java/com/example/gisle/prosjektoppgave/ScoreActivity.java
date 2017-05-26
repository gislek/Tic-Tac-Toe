package com.example.gisle.prosjektoppgave;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        String[] playerName = intent.getStringArrayExtra("playerNames");
        int[] moves = intent.getIntArrayExtra("moves");
        int[] wins = intent.getIntArrayExtra("wins");

        Resources res = getResources();
        String txt;
        TextView textWinner = (TextView)findViewById(R.id.textViewWinner);
        if (wins[0] == wins[1])
            txt = getResources().getString(R.string.noMoves);
        else if (wins[0] > wins[1])
            txt = res.getString(R.string.win, playerName[0]);
        else
            txt = res.getString(R.string.win, playerName[1]);

        textWinner.setText(txt);

        TextView textScore = (TextView)findViewById(R.id.textViewScore);
        txt = res.getString(R.string.victories,playerName[0],wins[0],playerName[1],wins[1]);
        textScore.setText(txt);

        TextView textMoves = (TextView)findViewById(R.id.textViewMoves);
        txt="";
        for(int i=0;i<moves.length;i++){
            txt+=i+1+":"+moves[i]+"\n";
        }

        txt=res.getString(R.string.moves,txt);
        textMoves.setText(txt);

    }

    public void onClick(View v) {
        finish();
    }
}

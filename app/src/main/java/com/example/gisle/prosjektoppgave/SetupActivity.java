package com.example.gisle.prosjektoppgave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SetupActivity extends Activity {

    private TextView text;
    private int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        rounds = 1;
        text = (TextView) findViewById(R.id.textSeekBar);
        SeekBar sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setProgress(0);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress++; // Add the minimum value (1)
                text.setText(Integer.toString(progress));
                rounds = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void onClick(View v) {
        String player1 = ((EditText) findViewById(R.id.editPlayer1)).getText().toString();
        String player2 = ((EditText) findViewById(R.id.editPlayer2)).getText().toString();

        if (!TextUtils.isEmpty(player1) && !TextUtils.isEmpty(player2)) {
            Intent intent = new Intent("GameActivity");
            intent.putExtra("p1", player1);
            intent.putExtra("p2", player2);
            intent.putExtra("rounds", rounds);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, R.string.missingName, Toast.LENGTH_SHORT).show();
        }
    }
}
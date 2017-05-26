package com.example.gisle.prosjektoppgave;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class RulesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Locale locale = getResources().getConfiguration().locale;
        if (locale.getLanguage().equals("no") || locale.getLanguage().equals("nb") || locale.getLanguage().equals("nn")) {
            locale = new Locale("no", "NO");
            //let’s update system configuration
            Configuration config = new Configuration();
            config.locale = locale;
            Resources res = getBaseContext().getResources();
            res.updateConfiguration(config, res.getDisplayMetrics());
        }

            setContentView(R.layout.activity_rules);
    }

    public void onClick(View v) {
        Intent intent = new Intent("SetupActivity");
        startActivity(intent);
        finish();
    }
}

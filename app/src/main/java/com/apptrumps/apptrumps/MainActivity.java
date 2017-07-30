package com.apptrumps.apptrumps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTxtPlayGame;
    private TextView mTxtViewPacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtPlayGame = (TextView) findViewById(R.id.txt_play_game);
        mTxtPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, ViewPacksActivity.class);
                startActivity(intent);
            }
        });

        mTxtViewPacks = (TextView) findViewById(R.id.txt_view_packs);
        mTxtViewPacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, ViewPacksActivity.class);
                startActivity(intent);
            }
        });
    }
}

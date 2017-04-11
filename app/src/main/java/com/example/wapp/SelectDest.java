package com.example.wapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectDest extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "dest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dest);
    }

    public void SelectDest(View view) {
        Intent intent = new Intent(this, Game.class);

        switch(view.getId()) {
            case R.id.button:
                int i =1;
                intent.putExtra(EXTRA_MESSAGE,i);
                startActivity(intent);
                finish();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button5:
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }
}

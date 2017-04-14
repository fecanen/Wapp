package com.example.wapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class Game extends AppCompatActivity {
    private int score;
    TextView Score;
    LinkedList<Destination> destinations;
    private DestinationParser parser;
    Destination destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        int nbrdest = intent.getIntExtra(SelectDest.EXTRA_MESSAGE,0);
        InputStream in = this.getResources().openRawResource(R.raw.lund);
        parser = new DestinationParser();

        try {
            destinations = loadGame(in,nbrdest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        destination = destinations.poll();

        score = 10;
        Score = (TextView) findViewById(R.id.Score);
        Score.setText(String.valueOf(score));
    }

    public void nextLead(View view) {
        if (score > 3) {
            score = score - 2;
            Score.setText(String.valueOf(score));
            playLead(destination.getLead());
        }
    }

    public void checkRight(){
    }

    public LinkedList<Destination> loadGame(InputStream in, int nbr) throws IOException, XmlPullParserException {
        return parser.parse(in,nbr);
    }

    private void playLead(String adress){

    }
}


package com.example.wapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        //How many destinations were clicked
        int nbrdest = intent.getIntExtra(SelectDest.EXTRA_MESSAGE,0);

        //Get the xml inputstream from lund.xml
        InputStream in = this.getResources().openRawResource(R.raw.lund);
        parser = new DestinationParser();
        context = this;

        try {
            //Loading a specific number of destinations to a linked list. Each destination contains
            //a specific amount of leads.
            destinations = loadGame(in,nbrdest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        //destination is the destination currently being played.
        destination = destinations.poll();

        score = 10;
        Score = (TextView) findViewById(R.id.Score);
        Score.setText(String.valueOf(score));
    }

    public void nextLead(View view) {
        if (score > 3) {
            score = score - 2;
            Score.setText(String.valueOf(score));
 //         playLead(destination.getLead());
        }
    }

    public void checkRight(){
    }

    //Parses an XML file with the leads to each destination. The Leads are adressess to sounds that should be played
    public LinkedList<Destination> loadGame(InputStream in, int nbr) throws IOException, XmlPullParserException {
        return parser.parse(in,nbr);
    }

    //Adress is the adress of the sound that should be played.
    private void playLead(String adress){
   //     MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.siren);
   //     mediaPlayer.start();
    }
}


package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SingleGameView extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;

    @Override

    //creates the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_view);
        tournament = administrator.getInstance().getTournament().getInstance();
        TextView teamName1 = (TextView) findViewById(R.id.team1);
        TextView teamName2 = (TextView) findViewById(R.id.team2);
        EditText team1Goals = (EditText) findViewById(R.id.team1Goals);
        EditText team2Goals = (EditText) findViewById(R.id.team2Goals);
        teamName1.setText(tournament.getSingleGameStats().getTeam1().getName());
        teamName2.setText(tournament.getSingleGameStats().getTeam2().getName());
        team1Goals.setText(tournament.getSingleGameStats().getScoreTeam1()+"");
        team2Goals.setText(tournament.getSingleGameStats().getScoreTeam2()+"");
    }

    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("Decide here who won, not like you'd be able to beat either team anyway");
        helpBuilder.setPositiveButton("Jeez why does this app have to be condescending.",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    //onclick method for the info button
    public void infoButtonClick(View view){
        Button infoButton = (Button) findViewById(R.id.infoButton);

        popUpInfo();
    }


    //onclick method for the save button
    public void finishedButton(View view){
        EditText team1Goals = (EditText) findViewById(R.id.team1Goals);
        EditText team2Goals = (EditText) findViewById(R.id.team2Goals);
        int score1 = Integer.parseInt(team1Goals.getText().toString());
        int score2 = Integer.parseInt(team2Goals.getText().toString());
        tournament.getGame(tournament.getSingleGameStats().getName()).setScoreTeam1(score1);
        tournament.getGame(tournament.getSingleGameStats().getName()).setScoreTeam2(score2);
        tournament.getGame(tournament.getSingleGameStats().getName()).getTeam1().setPoints(score1);
        tournament.getGame(tournament.getSingleGameStats().getName()).getTeam2().setPoints(score2);
        startActivity(new Intent(SingleGameView.this, ListOfGames.class));
    }
}

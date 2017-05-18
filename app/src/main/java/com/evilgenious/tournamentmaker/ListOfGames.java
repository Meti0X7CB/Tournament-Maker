package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListOfGames extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;
    @Override

    //when opened, the page shows the games that have finished or are ready to be played
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_games);
        tournament = administrator.getInstance().getTournament().getInstance();

        //start and end round buttons
        Button startRoundButton = (Button) findViewById(R.id.startRoundButton);
        Button endRoundButton = (Button) findViewById(R.id.endRoundButton);

        //disables start and end round button is Round Robin tournament is selected
        if (tournament.getType().equals("RR")) {
            startRoundButton.setEnabled(false);
            endRoundButton.setEnabled(false);
        }

        else{
            if(tournament.getNumRounds()>tournament.getRoundStarted() && tournament.getRoundStarted()==tournament.getRoundEnded()){
                startRoundButton.setEnabled(true);
                startRoundButton.setText("Start Round " + (tournament.getRoundStarted()+1) );
            }
            else{
                startRoundButton.setText("Start Round");
                startRoundButton.setEnabled(false);
            }
            if(tournament.getRoundEnded() == (tournament.getRoundStarted()-1)){
                endRoundButton.setEnabled(true);
                endRoundButton.setText("End Round " + (tournament.getRoundStarted()) );
            }
            else{
                endRoundButton.setText("End Round");
                endRoundButton.setEnabled(false);
            }
        }



        LinearLayout linLayout = (LinearLayout)findViewById(R.id.linearLayoutGames);
        for (int round=0; round<tournament.getNumRounds(); round++){
            TextView textView = new TextView(this);
            textView.setText("Round " + (round+1));
            linLayout.addView(textView);
            //every round except last
            if (round != (tournament.getNumRounds()-1)) {
                for (int i = tournament.getRound(round); i < tournament.getRound(round + 1); i++) {
                    Button myButton = new Button(this);
                    myButton.setText(tournament.getGame(i).getName());
                    myButton.setTransformationMethod(null);//makes sure text doesn't get capitalized automatically
                    tournament.setSingleGameStats(tournament.getGame(i));
                    linLayout.addView(myButton);
                }
            }
            //last round
            else{
                for (int i = tournament.getRound(round); i < tournament.gamesSize(); i++){
                    Button myButton = new Button(this);
                    myButton.setText(tournament.getGame(i).getName());
                    myButton.setTransformationMethod(null);//makes sure text doesn't get capitalized automatically
                    tournament.setSingleGameStats(tournament.getGame(i));
                    linLayout.addView(myButton);
                }
            }
        }
    }

    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("Here you can see list of games in different rounds " +
                "and are able to edit each game to set the number of goals (no ties are allowed!).\n" +
                "if it is knock-out tournament, you'll also be able to start and end rounds");
        helpBuilder.setPositiveButton("Okie dokey",
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

    //onclick method for Home button
    public void HomeButtonClick(View view){
        startActivity(new Intent(ListOfGames.this, HomePage.class));
    }

    //onclick method for Open Game button
    public void openGameButtonClick(View view){
        EditText getGame = (EditText) findViewById(R.id.openGameEditText);
        String gameName = getGame.getText().toString();
        for (int i=0; i<tournament.gamesSize(); i++) {
            if ((tournament.getGame(i).getName().equals(gameName)) && !(tournament.getGame(i).getTeam1()==null)) {
                tournament.setSingleGameStats(tournament.getGame(i));
                startActivity(new Intent(ListOfGames.this, SingleGameView.class));
            }

        }
    }

    //onclick method for Start Round button
    public void startRoundButtonClick(View view){
        Button startRoundButton = (Button) findViewById(R.id.startRoundButton);
        if (tournament.getNumRounds()>tournament.getRoundStarted()){
            tournament.startRound(tournament.getRoundStarted()+1);
        }
        //restarts this activity to refresh page
        startActivity(new Intent(ListOfGames.this, ListOfGames.class));
    }

    //onclick method for End Round button
    public void endRoundButtonClick(View view){
        Button endRoundButton = (Button) findViewById(R.id.endRoundButton);
        if(tournament.getRoundEnded() == (tournament.getRoundStarted()-1)){
            tournament.endRound(tournament.getRoundStarted());
        }
        //restarts this activity to refresh page
        startActivity(new Intent(ListOfGames.this, ListOfGames.class));
    }

}

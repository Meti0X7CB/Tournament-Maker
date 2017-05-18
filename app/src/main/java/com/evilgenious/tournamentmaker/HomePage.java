package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.support.v4.app.ActivityCompat.startActivity;

public class HomePage extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;
    @Override

    //when opened, this page displays tournament information such as fees, prizes, start date, and end date
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tournament = administrator.getInstance().getTournament().getInstance();
        EditText fees_text = (EditText) findViewById(R.id.editFeeText);
        fees_text.setText(tournament.getFees() + "");
        EditText prizes_text = (EditText) findViewById(R.id.editPrizeText);
        prizes_text.setText(tournament.getPrizes() + "");
        EditText startdate_text= (EditText) findViewById(R.id.editStartDate);
        startdate_text.setText(tournament.getStartDate() + "");
        EditText enddate_text= (EditText) findViewById(R.id.editEndDate);
        enddate_text.setText(tournament.getEndDate() + "");

        //prevents user from changing values in the text boxes
        fees_text.setKeyListener(null); //makes the text non-editable
        prizes_text.setKeyListener(null); //makes the text non-editable
        startdate_text.setKeyListener(null); //makes the text non-editable
        enddate_text.setKeyListener(null); //makes the text non-editable
    }

    //displays instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("You can choose what to change in this tournament, if you dare. Muahahahaha!");
        helpBuilder.setPositiveButton("Ok, chill",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    //displays a message for the user once the start button has been pressed.
    //The message says if the tournament started successfully, already
    //started, or if a certain parameter hasn't been met which is needed for the tournament to start
    private void popUpInfoStart() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        String message = tournament.Start();
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage(message);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    //onclick method
    public void infoButtonClick(View view){
        popUpInfo();
    }

    //what to do when teams buttons is clicked
    public void teamsButtonClick(View view){
        startActivity(new Intent(HomePage.this, ListOfTeams.class));
    }

    //what to do when games button is clicked
    public void gamesButtonClick(View view){
        startActivity(new Intent(HomePage.this, ListOfGames.class));
    }
    //what to do when standings button is clicked
    public void standingsButtonClick(View view){
        startActivity(new Intent(HomePage.this, Standings.class));
    }
    //what to do when standings button is clicked
    public void logoutButtonClick(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    //start button
    public void startTournamentButtonClick(View view){
        popUpInfoStart();
        tournament.matchmaker(tournament.getType());
    }
}

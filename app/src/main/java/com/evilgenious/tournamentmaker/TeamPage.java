package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeamPage extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;


    @Override
    //on opening the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_page);
        tournament = administrator.getInstance().getTournament().getInstance();
    }

    //warns the user before deleting the tournament
    private void popUpWarning() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Warning!!!");
        helpBuilder.setMessage("ARE YOU SURE???");
        helpBuilder.setPositiveButton("YES!!! Positive!",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
//close the dialog
                    }
                });

        helpBuilder.setNegativeButton("Nope",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }
    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("If you choose to delete the team, the team will never RESURRECT itself. EVER!!!");
        helpBuilder.setPositiveButton("Blah, blah, blah. What a bore",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    //what to do when info button is pressed
    public void infoButtonClick(View view) {
        Button infoButton = (Button) findViewById(R.id.infoButton);
        popUpInfo();
    }

    //what to do when login button is pressed
    public void showStatsButtonClick(View view) {
        startActivity(new Intent(TeamPage.this, TeamStats.class));
    }
    //what to do when add player buttons is clicked

    //what to do when save button is clicked
    public void saveButtonClick(View view) {
        EditText name_box = (EditText) findViewById(R.id.editTextTeamName);
        EditText captain_box = (EditText) findViewById(R.id.editTextCaptainName);
        String captain = captain_box.getText().toString();
        String team_name = name_box.getText().toString();
        Team newTeam = new Team(team_name, captain);
        tournament.addTeam(newTeam);
        startActivity(new Intent(this, ListOfTeams.class));
    }

    //what to do when cancel button is clicked
    public void cancelButtonClick(View view) {
        startActivity(new Intent(TeamPage.this, ListOfTeams.class));
    }

}

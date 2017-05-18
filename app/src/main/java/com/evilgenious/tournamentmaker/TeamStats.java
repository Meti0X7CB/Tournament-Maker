package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeamStats extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;
    @Override
    //on opening the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stats);
        tournament = administrator.getInstance().getTournament().getInstance();
        EditText teamName = (EditText) findViewById(R.id.teamNameEditText);
        EditText captainName = (EditText) findViewById(R.id.captainNameEditText);
        EditText teamPoints = (EditText) findViewById(R.id.teamPointsEditText);
        teamName.setText(tournament.getTeamStats().getName());
        captainName.setText(tournament.getTeamStats().getCaptain());
        teamPoints.setText(tournament.getTeamStats().getPoints()+"");
        teamPoints.setKeyListener(null); //makes the text non-editable
    }
    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("You can change the team name (it sucks)" +
                " and the captain (sucks even more) before the tournament starts." +
                "Once the games start, you can view the points (doubt this team will get any)");
        helpBuilder.setPositiveButton("Yada, yada, yada.",
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

    //onclick method for the home button
    public void homebuttonClick(View view){
        startActivity(new Intent(TeamStats.this, HomePage.class));
    }

    //onclick method for the delete team button
    public void deleteTeamButtonClick(View view){
        tournament.removeTeam(tournament.getTeamStats());
        startActivity(new Intent(TeamStats.this, ListOfTeams.class));
    }

    //onclick method for the save button
    public void saveButtonClick(View view){
        EditText teamName = (EditText) findViewById(R.id.teamNameEditText);
        EditText captainName = (EditText) findViewById(R.id.captainNameEditText);
        for (int i=0; i<tournament.getNumTeams(); i++){
            if (tournament.getTeam(i).getName().equals(tournament.getTeamStats().getName())){
                tournament.getTeam(i).setName(teamName.getText().toString());
                tournament.getTeam(i).setCaptain(captainName.getText().toString());
            }
        }
        startActivity(new Intent(TeamStats.this, ListOfTeams.class));
    }
}

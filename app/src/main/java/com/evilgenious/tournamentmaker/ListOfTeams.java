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

public class ListOfTeams extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;
    @Override

    //all teams can be seen and called
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_teams);
        tournament = administrator.getInstance().getTournament().getInstance();
        for (int i=0; i<tournament.getNumTeams(); i++){
            Button myButton = new Button(this);
            myButton.setText(tournament.getTeam(i).getName());tournament.setTeamStats(tournament.getTeam(i));
            myButton.setTransformationMethod(null);//makes sure text doesn't get capitalized automatically

            LinearLayout ll = (LinearLayout)findViewById(R.id.mainLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
    }

    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("The list of teams that are better than you by a long shot :P");
        helpBuilder.setPositiveButton("Over my dead body!",
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
        popUpInfo();
    }

    //what to do when standings button is clicked
    public void getStatsButtonClick(View view){
        EditText getStats = (EditText) findViewById(R.id.getStatsEditText);
        String teamName = getStats.getText().toString();
        for (int i=0; i<tournament.getNumTeams(); i++) {
            if (tournament.getTeam(i).getName().equals(teamName)) {
                tournament.setTeamStats(tournament.getTeam(i));
                startActivity(new Intent(ListOfTeams.this, TeamStats.class));
            }

        }
    }

    //onclick method for the home button
    public void homeButtonClick(View view){
        startActivity(new Intent(ListOfTeams.this, HomePage.class));
    }

    //onclick method for the addTeam button
    public void addTeamButtonClick(View view){
        if (!tournament.isStarted()) {
            startActivity(new Intent(ListOfTeams.this, TeamPage.class));
        }
    }
}

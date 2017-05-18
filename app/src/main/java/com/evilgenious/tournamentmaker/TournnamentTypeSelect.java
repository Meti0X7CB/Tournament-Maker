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

public class TournnamentTypeSelect extends AppCompatActivity {
    int numgames;
    Administrator administrator;
    Tournament tournament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournnament_type_select);
        tournament = administrator.getInstance().getTournament().getInstance();
    }

    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("You select a type for your tournament here.\n" +
                "You must have at least 2 teams that are multiple of 2 to start Round Robin \n" +
                "You must have at least 4 teams that is of log 2 that is not zero to start KnockOut\n" +
                "You must have at least 8 games and a number of games of log 2 to start Combination");
        helpBuilder.setPositiveButton("You're not the boss of me!",
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
    public void infoButtonClick(View view) {
        Button infoButton = (Button) findViewById(R.id.infoButton);
        popUpInfo();
    }


    //what to do when login button is pressed
    public void roundRobinButtonClick(View view) {
        EditText numTeamsText = (EditText) findViewById(R.id.numberOfTeamsEditText);
        numgames = Integer.parseInt(numTeamsText.getText().toString());
        if (numgames % 2 == 0 && numgames > 0) {
            tournament.setType("RR");
            tournament.setRequiredSize(Integer.parseInt(numTeamsText.getText().toString()));
            startActivity(new Intent(TournnamentTypeSelect.this, HomePage.class));
        }
    }

    //what to do when login button is pressed
    public void knockoutButtonClick(View view) {
        EditText numTeamsText = (EditText) findViewById(R.id.numberOfTeamsEditText);
        numgames = Integer.parseInt(numTeamsText.getText().toString());
        if (numgames > 0 && ((Math.log10(numgames) / Math.log10(2)) == (int) (Math.log10(numgames) / Math.log10(2)))) {
            tournament.setType("K");
            tournament.setRequiredSize(Integer.parseInt(numTeamsText.getText().toString()));
            startActivity(new Intent(TournnamentTypeSelect.this, HomePage.class));
        }
    }

    //what to do when login button is pressed
    public void combinationButtonClick(View view) {
        tournament.setType("M");
        if (numgames >= 8 && ((Math.log10(numgames) / Math.log10(2)) == (int) (Math.log10(numgames) / Math.log10(2))) ) {
            startActivity(new Intent(TournnamentTypeSelect.this, HomePage.class));
        }
    }

}

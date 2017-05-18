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

public class Standings extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;

    @Override
    //creation of the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        tournament = administrator.getInstance().getTournament().getInstance();
        TextView standings = (TextView) findViewById(R.id.standingsText);
        standings.setText(tournament.standings());
    }
    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("The list of teams from the supreme overlord to the feeble peasant");
        helpBuilder.setPositiveButton("And I'm Darth Sidious so I win",
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

    //onclick method for the home button
    public void homeButtonClick(View view){
        startActivity(new Intent(Standings.this, HomePage.class));
    }

}

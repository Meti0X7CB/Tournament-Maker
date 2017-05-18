package com.evilgenious.tournamentmaker;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class TournamentInitial extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    Tournament tournament;

    //on opening the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_initial);
        tournament = administrator.getInstance().getTournament().getInstance();
    }


    //
    private void popUpWarning() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Warning!!!");
        helpBuilder.setMessage("ARE YOU SURE?\n" +
                "(It will start a new tournament and erase previous data)");
        helpBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        tournament.reset();
                        startActivity(new Intent(TournamentInitial.this, setTournamentValues.class));

                    }
                });

        helpBuilder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

    }

    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("If you choose to start new tournament, the previous edited tournmant will be raised with no mercy");
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

    //what to do when info button is pressed
    public void infoButtonClick(View view){
        Button infoButton = (Button) findViewById(R.id.infoButton);

        popUpInfo();
    }

    public void startButtonClick(View view) {
        if (tournament.getType() != null) {
            popUpWarning();
        }
        else{
            startActivity(new Intent(TournamentInitial.this, setTournamentValues.class));
        }

    }

    public void openButtonClick(View view) {
        if (tournament.getType()!=null) {
            startActivity(new Intent(TournamentInitial.this, HomePage.class));
        }
    }
}


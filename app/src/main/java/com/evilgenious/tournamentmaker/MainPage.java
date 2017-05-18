package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    //creates the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("You can choose which to edit next." +
                "enter the tournament if you actually want to live in the nightmare" +
                "or view your profile to maybe make yourself into a better person");
        helpBuilder.setPositiveButton("they both sound bad...",
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
        View.OnClickListener myListener1 = new View.OnClickListener(){
            public void onClick(View v){
                popUpInfo();
            }
        };
        infoButton.setOnClickListener(myListener1);
    }

    //what to do when login button is pressed
    public void tournamentButtonClick(View view){
        startActivity(new Intent(MainPage.this, TournamentInitial.class));
    }

    //what to do when admin profile button is pressed
    public void profileButtonClick(View view){
        startActivity(new Intent(MainPage.this, AdminProfile.class));
    }

}

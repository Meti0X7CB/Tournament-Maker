package com.evilgenious.tournamentmaker;

import java.io.Serializable;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity{

    //instance variables
    Administrator administrator;

    //creates the login age
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        administrator=administrator.getInstance();

    }


    //instructions on how to use the page
    private void popUpInfo() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("If this is the first time you're using this app, " +
                "please enter 'admin' for both username and password to login. ");
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
    private void popUpInfo2() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("If this is the first time you're using this app, please " +
                "enter 'admin' for both username and password to login. Otherwise, your username or password is wrong. ");
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
    //Greets the new admin
    private void welcomeAdmin() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Welcome to your worst nightmare!");
        // helpBuilder.setMessage("Welcome" + loginbox.getText().toString());
        helpBuilder.setPositiveButton("Alright you little sh*t",
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
    public void infoButtonClick(View view) {
        popUpInfo();
    }

    //what to do when login button is pressed
    public void loginButtonClick(View view) {
        EditText loginbox = (EditText) findViewById(R.id.loginEditText);
        EditText passwordbox = (EditText) findViewById(R.id.passwordEditText);
        if (administrator.getName() == null) {
            //check if the user use the default username and password
            if (loginbox.getText().toString().equals("admin") && passwordbox.getText().toString().equals("admin")) {
                Administrator administrator = new Administrator("admin", "admin");
                startActivity(new Intent(LoginActivity.this, MainPage.class));
            } else {
                popUpInfo();
            }
        } else {
            if (loginbox.getText().toString().equals(administrator.getName()) && passwordbox.getText().toString().equals(administrator.getPassword())) {
                startActivity(new Intent(LoginActivity.this, MainPage.class));
            } else {
                popUpInfo2();
            }

        }


    }}
package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.support.v4.app.ActivityCompat.startActivity;

public class AdminProfile extends AppCompatActivity {

    //instance variable administrator of type administrator
    Administrator administrator;
    @Override

    //when opened, this page creates EditText fields for the username and password
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        administrator=administrator.getInstance();
        EditText changeUN = (EditText) findViewById(R.id.userNameEditText);
        EditText changePW = (EditText) findViewById(R.id.passwordEditText);
        changeUN.setText(administrator.getName());
        changePW.setText(administrator.getPassword());

    }

    //instructions on how to use the page
    private void popUpInfo() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Info");
        helpBuilder.setMessage("You can change your username and password here if you're uncomfortable with them");
        helpBuilder.setPositiveButton("Yeah whatever -_-",
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

    //brings the user back to the profile page
    public void cancelButtonClick(View view){
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        startActivity(new Intent(AdminProfile.this, MainPage.class));
    }

    //submits changes made to the username and/or password
    public void submitButtonClick(View view){
        EditText changeUN = (EditText) findViewById(R.id.userNameEditText);
        EditText changePW = (EditText) findViewById(R.id.passwordEditText);
        String newUserName = changeUN.getText().toString();
        String newPassWord = changePW.getText().toString();
        if(newUserName == "admin" && newPassWord!="admin"){
            //administrator.changeUsername(newPassWord,newUserName);
            administrator.changePassword("admin",newPassWord);}
        if(newUserName !="admin"&& newPassWord =="admin"){
            administrator.changeUsername("admin", newUserName);}
        if(newUserName != "admin" && newPassWord!="admin"){
            administrator.changeAll(newUserName,newPassWord);}
        startActivity(new Intent(AdminProfile.this, MainPage.class));
    }

}
package com.evilgenious.tournamentmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setTournamentValues extends AppCompatActivity {

    //instance variables
    Administrator administrator;
    @Override

    //creates the page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_tournament_values);
    }

    //saves the information on the page
    public void saveButtonClick(View view) {
        double fees;
        String prize;
        String start_date;
        String end_date;
        String location;

        EditText feesText = (EditText) findViewById(R.id.feeValueText);
        fees = Double.parseDouble(feesText.getText().toString());

        EditText prizeText = (EditText) findViewById(R.id.PrizeValueText);
        prize = prizeText.getText().toString();

        EditText startdateText = (EditText) findViewById(R.id.StartDateValue);
        start_date = startdateText.getText().toString();

        EditText enddateText = (EditText) findViewById(R.id.EndDateValue);
        end_date = enddateText.getText().toString();

        EditText locationText = (EditText) findViewById(R.id.LocationValue);
        location = locationText.getText().toString();

        Tournament tournament = administrator.getInstance().getTournament().getInstance();
        tournament.setFees(fees);
        tournament.setPrizes(prize);
        tournament.setStartDate(start_date);
        tournament.setEndDate(end_date);
        tournament.setLocation(location);
        startActivity(new Intent(this, TournnamentTypeSelect.class));

    }
}

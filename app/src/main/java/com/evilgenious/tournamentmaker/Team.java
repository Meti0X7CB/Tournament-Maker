package com.evilgenious.tournamentmaker;

import java.util.ArrayList;

public class Team {
    //instance variables
    private String name;
    private String captain;
    private int points; //Stores points for Round Robin tournament
    private boolean eliminated; //if team is elimited from knock-out
    protected static ArrayList<Game> games;

    //Constructor
    public Team(String teamName, String captainName) {
        name = teamName;
        captain = captainName;
        eliminated = false;
        games = new ArrayList<Game>();
        points = 0;
    }

    //change the team name
    public void setName(String teamName) {
        name = teamName;
    }

    //returns the team name
    public String getName() {
        return name;
    }

    //change the captain name
    public void setCaptain(String captainName) {
        captain = captainName;
    }

    //returns team captain
    public String getCaptain() {
        return captain;
    }

    //adds a new game
    public void addGame(Game newGame) {
        games.add(newGame);
    }

    //returns if the team is eliminated or not
    public boolean isEliminated() {
        return eliminated;
    }

    //set elimination status of the team
    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

    public void removeGame(Game remGame) {
        if (games.isEmpty()) {
            System.out.println("U cannot remove");
        } else {
            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).equals(remGame)) {
                    games.remove(i);
                }
            }
        }
    }

    //sets points for the team
    public void setPoints(int points) {
        this.points = points;
    }

    //return the number of points
    public int getPoints() {
        return points;
    }

    //Prints team name and team captain
    public void display() {
        System.out.println("Team name: " + name);
        System.out.println("Team Captain: " + captain);
    }

}



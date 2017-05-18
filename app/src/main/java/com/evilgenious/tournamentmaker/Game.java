package com.evilgenious.tournamentmaker;

public class Game {

	//instance variables
	private Tournament tournament;
	private String name; //name that identifies this game
	private Team team1; // team 1
	private Team team2; // team 2
	private int scoreTeam1; //game score of team 1
	private int scoreTeam2; //game score of team 2

	//------------------------------------------------------------------------------------

	//constructors
	public Game(Tournament tournament, String name){
		this.tournament = tournament;
		this.name = name;
		team1 = null;
		team2 = null;
		scoreTeam1 = 0;
		scoreTeam2 = 0;
	}
	
	public Game(Tournament tournament, Team team1, Team team2, String name){
		this.tournament = tournament;
		this.name = name;
		this.team1 = team1;
		this.team2 = team2;
		scoreTeam1 = 0;
		scoreTeam2 = 0;
	}

	//------------------------------------------------------------------------------------

	//class methods


	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setTeam1(Team team1){
		this.team1 = team1;
	}
	
	public Team getTeam1(){
		return team1;
	}
	
	public void setTeam2(Team team2){
		this.team2 = team2;
	}
	
	public Team getTeam2(){
		return team2;
	}
	
	public int getScoreTeam1(){
		return scoreTeam1;
	}
	
	public void setScoreTeam1(int newScore){
		scoreTeam1 = newScore;
	}
	
	public int getScoreTeam2(){
		return scoreTeam2;
	}
	
	public void setScoreTeam2(int newScore){
		scoreTeam2 = newScore;
	}

	//displays the game information
	public void display(){
		System.out.println("Team 1: " + team1.getName());
		System.out.println("Team 1 Score: " + scoreTeam1);
		System.out.println("Team 2: " + team2.getName());
		System.out.println("Team 2 Score: " + scoreTeam2);
	}
	
}

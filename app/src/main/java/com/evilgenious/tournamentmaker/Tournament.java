package com.evilgenious.tournamentmaker;
import java.util.ArrayList;

class Tournament {

	//static variabels
	private static Tournament instance = null;


	//instance variables
	private String type; //type of tournament
	private int requiredSize;
	private int size; //number of teams playing
	private double fees;
	private String prizes;
	private String location;
	private String startDate;
	private String endDate;
	private boolean started; //tournament has started or not
	private int roundStarted; //stores which round was last started
	private int roundEnded; //stores which round was last ended
	private Team teamStats;
	private Game singleGameStats;
	private ArrayList<Team> teams; //list of all team participating
	private ArrayList<Game> games;
	private ArrayList<Integer> roundLocations;

	//constructor
	Tournament() {
		teams = new ArrayList<Team>();
		games = new ArrayList<Game>();
		roundLocations = new ArrayList<Integer>();
		size = 0;
		roundStarted = 0;
		roundEnded = 0;
		started = false;
		instance = this;
	}

	//class methods

	//returns true if tournament instance is already created
	public boolean instanceExists(){
		return (!(instance == null));
	}

	//returns the tournament instance
	public static Tournament getInstance(){
		if (instance == null){
			instance = new Tournament();
		}
		return instance;
	}

	//initializes the tournament details
	public void initialize(String type, String location, String startDate, String endDate, int requiredSize){
		this.type = type;
		this.requiredSize = requiredSize;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	//resets the tournament
	public void reset(){
		teams = new ArrayList<Team>();
		games = new ArrayList<Game>();
		roundLocations = new ArrayList<Integer>();
		size = 0;
		roundStarted = 0;
		roundEnded = 0;
		started = false;
	}

	//returns the size of games array
	public int gamesSize(){
		return games.size();
	}

	//calls the methods that create the tournament algorithm and games
	public void matchmaker(String type) {
		if (type.equals("RR")) {
			matchmakerRR(teams);
		}
		else if (type.equals("K")) {
			matchmakerK(teams);
		}
		else {
			matchmakerM(teams);
		}
	}

	//matchmmaking algorithm for Round Robin
	public void matchmakerRR(ArrayList<Team> teams){
		int size = teams.size();
		int numOfRounds = size - 1;
		int numOfGames = size / 2; //number of games per round
		Game newGame;
		Team team1;
		Team team2;
		for (int round=0; round<numOfRounds; round++){
			roundLocations.add(numOfGames*round); //stores location where round X starts in games list
			System.out.println("");
			System.out.println("      Round " + (round+1));
			System.out.println("");
			for (int i=0; i<numOfGames; i++){
				if (i==0){
					team1 = teams.get(0);
				}
				else{
					team1 = teams.get( (((size-1) + (i-1) - (round)) % (size-1)) + 1);
				}
				team2 = teams.get(((2*(size-1) - (i) - (round) - 1) % (size-1)) + 1);
				newGame = new Game(this,team1,team2,"Game " + (games.size()+1));
				games.add(newGame);
				team1.addGame(newGame);
				team2.addGame(newGame);
				System.out.println("Game " + (i + 1));
				System.out.println("Teams: " + team1.getName() + " & " + team2.getName());
			}
		}
	}

	//matchmaking algorithm for knock-out
	public void matchmakerK(ArrayList<Team> teams){
		size = teams.size();
		int rounds = (int) (Math.log(size)/Math.log(2));
		Game newGame;
		int sum = 0; //keeps track of game location in games array
		for (int round=0; round<rounds; round++){
			roundLocations.add(sum); //stores location where round X starts in games list
			sum += size/(Math.pow(2,round+1));
			System.out.println("--------------------");
			System.out.println("      Round " + (round+1));
			System.out.println("--------------------");
			for (int i=0; i<(size/(Math.pow(2,round+1))); i++){
				newGame = new Game(this,"Game " + (games.size()+1));
				games.add(newGame);
				System.out.println("Game " + (i+1));
			}
		}
	}

	//matchmaking algorithm for mixed
	public void matchmakerM(ArrayList<Team> teams){
		//divides teams into 4 pools
		//each of the 4 pools play in a round robin setting
		//half of the teams from each pool will be eliminated
		//remaining teams play in a knock-out format
		int size = teams.size();
		ArrayList<Team> team1 = new ArrayList<Team>();
		ArrayList<Team> team2 = new ArrayList<Team>();
		ArrayList<Team> team3 = new ArrayList<Team>();
		ArrayList<Team> team4 = new ArrayList<Team>();
		//divides teams into 4 pools
		for (int i=0; i<size; i++){
			switch (i%4){
				case 0: team1.add(teams.get(i));
					break;
				case 1: team2.add(teams.get(i));
					break;
				case 2: team3.add(teams.get(i));
					break;
				case 3: team4.add(teams.get(i));
					break;
			}
		}
		for (int i=0; i<4; i++){
			System.out.println();
			System.out.println("--------------------");
			System.out.println("      Pool " + (i+1) + "    ");
			System.out.println("--------------------");
			switch (i){
				case 0: matchmakerRR(team1);
					for (int j=0; j<team1.size();j=j+2){
						int rand = (int) (Math.random()*2 + 1);
						if (rand == 1){
							team1.get(j).setEliminated(true);
						}
						else{
							team1.get(j+1).setEliminated(true);
						}
					}
					break;
				case 1: matchmakerRR(team2);
					for (int j=0; j<team2.size();j=j+2){
						int rand = (int) (Math.random()*2 + 1);
						if (rand == 1){
							team2.get(j).setEliminated(true);
						}
						else{
							team2.get(j+1).setEliminated(true);
						}
					}
					break;
				case 2: matchmakerRR(team3);
					for (int j=0; j<team3.size();j=j+2){
						int rand = (int) (Math.random()*2 + 1);
						if (rand == 1){
							team3.get(j).setEliminated(true);
						}
						else{
							team3.get(j+1).setEliminated(true);
						}
					}
					break;
				case 3: matchmakerRR(team4);
					for (int j=0; j<team4.size();j=j+2){
						int rand = (int) (Math.random()*2 + 1);
						if (rand == 1){
							team4.get(j).setEliminated(true);
						}
						else{
							team4.get(j+1).setEliminated(true);
						}
					}
					break;
			}
			roundLocations.clear();
			games.clear();
		}
		//remaining team will play knock-out
		ArrayList<Team> remainingTeams = new ArrayList<Team>();
		for (int i=0; i<team1.size();i++){
			if (!(team1.get(i).isEliminated())){
				remainingTeams.add(team1.get(i));
			}
		}
		for (int i=0; i<team2.size();i++){
			if (!(team2.get(i).isEliminated())){
				remainingTeams.add(team2.get(i));
			}
		}
		for (int i=0; i<team3.size();i++){
			if (!(team3.get(i).isEliminated())){
				remainingTeams.add(team3.get(i));
			}
		}
		for (int i=0; i<team4.size();i++){
			if (!(team4.get(i).isEliminated())){
				remainingTeams.add(team4.get(i));
			}
		}
		this.teams = remainingTeams;
		this.size = remainingTeams.size();
		matchmakerK(this.teams);
	}

	//starts the round (i.e. adds teams to games)
	public void startRound(int round){
		//if it is the first round
		if (round == 1 && roundStarted == 0){
			roundStarted = round;
			for (int i=0; i<teams.size()/2; i++){
				games.get(i).setTeam1(teams.get(2*i));
				games.get(i).setTeam2(teams.get(2*i + 1));
			}
		}
		//if it is any round other than the first and last round
		else if ( (round>0) && (round<roundLocations.size())
				&& (round == roundStarted+1) && (round == roundEnded +1)){
			roundStarted = round;
			int t = -1; //iterates the team list
			for (int j=roundLocations.get(round-1); j<roundLocations.get(round); j++){
				for(;;){//gets the next team that is not yet eliminated
					t++;
					if (!(teams.get(t).isEliminated())){
						break;
					}
				}
				games.get(j).setTeam1(teams.get(t));
				for(;;){//gets the next team that is not yet eliminated
					t++;
					if (!(teams.get(t).isEliminated())){
						break;
					}
				}
				games.get(j).setTeam2(teams.get(t));
			}
		}
		//if it is the last round
		else if (round == roundLocations.size() && (round == roundStarted+1) && (round == roundEnded +1)){
			roundStarted = round;
			int t = -1; //iterates the team list
			for (int j=roundLocations.get(round-1); j<games.size(); j++){
				for(;;){//gets the next team that is not yet eliminated
					t++;
					if (!(teams.get(t).isEliminated())){
						break;
					}
				}
				games.get(j).setTeam1(teams.get(t));
				for(;;){//gets the next team that is not yet eliminated
					t++;
					if (!(teams.get(t).isEliminated())){
						break;
					}
				}
				games.get(j).setTeam2(teams.get(t));
			}
		}
		//invalid round input
		else{
			System.out.println("Check if the value entered is correct for startRound");
		}
	}

	//ends the round (i.e. decides winners)
	public void endRound(int round){
		//for the last round
		if ((round == roundLocations.size()) && (round==roundStarted) && (round==roundEnded+1)){
			roundEnded = round;
			for (int j=roundLocations.get(round-1); j<games.size(); j++){
				if(games.get(j).getScoreTeam1() < games.get(j).getScoreTeam2()){
					games.get(j).getTeam1().setEliminated(true);
				}
				else{
					games.get(j).getTeam2().setEliminated(true);
				}
			}
		}
		//any round except last round
		else if ( (round>0) && (round<roundLocations.size()) && (round==roundStarted) && (round==roundEnded+1) ){
			roundEnded = round;
			for (int j=roundLocations.get(round-1); j<roundLocations.get(round); j++){
				if(games.get(j).getScoreTeam1() < games.get(j).getScoreTeam2()){
					games.get(j).getTeam1().setEliminated(true);
				}
				else{
					games.get(j).getTeam2().setEliminated(true);
				}
			}
		}
		//invalid round input
		else{
			System.out.println("Check if the value entered is correct for endRound");
		}
	}

	//arranges all team according to points (high to low) and prints all team and points
	public String standings() {
		String standings = "";
		ArrayList<Team> copyTeams = new ArrayList<Team>();
		for (int i=0; i<teams.size(); i++){
			copyTeams.add(teams.get(i));
		}
		Team tempTeam;
		for (int i=0; i<size-1; i++){
			for (int j=0; j<size-1; j++){
				if (copyTeams.get(j).getPoints() < copyTeams.get(j+1).getPoints()){
					tempTeam = copyTeams.get(j+1);
					copyTeams.set(j+1, copyTeams.get(j));
					copyTeams.set(j, tempTeam);
				}
			}
		}
		//displays the points for each team in order
		for (int i=0; i<size; i++){
			standings += (copyTeams.get(i).getName() + ": " + copyTeams.get(i).getPoints() + " Points\n");
		}
		return standings;
	}

	//creates a new team with name and captain and add into teams list
	public void addTeam(String name, String captain) {
		if (teams.contains(name)) {
			System.out.println("This name is already taken.\nPlease choose a different name for your team.");
		}
		else {
			teams.add(new Team(name, captain));
			size++;
		}
	}

	//adds a new team object inside the team
	public void addTeam(Team team){
		boolean alreadyExists = false; //checks if team already exists
		for (int i=0; i<teams.size(); i++){
			if (teams.get(i).getName().equals(team.getName())){
				alreadyExists = true;
			}
		}
		if (!alreadyExists){
			teams.add(team);
			size++;
		}
		else{
			System.out.println("This team already exists. Choose a different name.");
		}
	}

	//removes a team
	public void removeTeam(Team team) {
		teams.remove(team);
		size--;
	}

	//returns a team object according to the team name
	public Team getTeam(String name){
		Team team = null;
		for (int i=0; i<teams.size(); i++){
			if (teams.get(i).getName().equals(name)){
				team = teams.get(i);
			}
		}
		return team;
	}

	//returns a team object according to it's position in the list
	public Team getTeam(int pos){
		Team team = null;
		if ((pos>=0) && (pos<teams.size())){
			team = teams.get(pos);
		}
		else{
			System.out.println("Please choose a valid value for team position in list");
		}
		return team;
	}

	public int getRoundStarted(){
		return roundStarted;
	}

	public int getRoundEnded(){
		return roundEnded;
	}

	public int getNumTeams() {
		return size;
	}

	public void setTeamStats(Team team){
		teamStats = team;
	}

	public Team getTeamStats(){
		return teamStats;
	}

	public void setSingleGameStats(Game game){
		singleGameStats = game;
	}

	public Game getSingleGameStats(){
		return singleGameStats;
	}

	public void setType (String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getPrizes() {
		return prizes;
	}

	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	//returns if the tournament has started or not
	public boolean isStarted() {
		return started;
	}

	//sets the required size for the tournament
	public void setRequiredSize(int requiredSize){
		this.requiredSize = requiredSize;
	}

	//returns a message once the start button is pressed and starts the tournament if the criteria are met
	public String Start() {

		String message = "";

		if (started == true){
			message = "Tournament was already started";
		}
		else if (size == requiredSize){
			this.started = true;
			message = "You have started the tournament";
		}
		else if (size < requiredSize){
			message = "Need " + (requiredSize-size) + " more teams to start";
		}
		else{
			message = "Need to remove " + (size-requiredSize) + " teams to start";
		}
		return message;
	}


	//returns the number of rounds
	public int getNumRounds(){
		return roundLocations.size();
	}

	//return the location of round from round list
	public int getRound(int round){
		int location = 0;
		if (round >= 0 && round < roundLocations.size()){
			location = roundLocations.get(round);
		}
		else {
			System.out.println("This round does not exist; please chose another value");
		}
		return location;
	}

	//returns a game from the games list
	public Game getGame(int game){
		Game returnGame = null;
		if (game >= 0 && game < games.size()){
			returnGame = games.get(game);
		}
		else {
			System.out.println("This game does not exist; please chose another value");
		}
		return returnGame;
	}

	//returns a game according to the game name
	public Game getGame(String name){
		Game returnGame = null;
		for (int i=0; i<games.size(); i++){
			if (games.get(i).getName().equals(name)){
				returnGame = games.get(i);
			}
		}
		return returnGame;
	}

	//ends the tournament
	public void End() {
		if (started == false){
			System.out.println("Tournament has already ended or didn't start yet");
		}
		else{
			started = false;
		}
	}

	//displays the teams
	public String printTeams() {
		String teamsToReturn = "";
		for (int i = 0; i < teams.size(); i++) {
			teamsToReturn += teams.get(i).getName();

		}
		return teamsToReturn;
	}
}
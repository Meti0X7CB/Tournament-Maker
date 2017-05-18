package com.evilgenious.tournamentmaker;

import android.os.Parcel;
import android.os.Parcelable;

public class Administrator{

	//Instance variable that holds the admin's username
	private String username;

	//Instance variable that holds the admin's password
	private String password;

	//Stores tournament
	private Tournament tournament;
	private static Administrator instance = null;

	//--------------------------------------------------------------------------

	//Constructor that initializes the variables
	public Administrator(String username, String password){
		this.username = username;
		this.password = password;
		tournament = tournament.getInstance();
		instance = this;
	}
	//--------------------------------------------------------------------------
	/*
	 * Methods
	 */
	public String getName(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	//Changes the username
	public void changeUsername(String password, String newusername){
		if(password.equals(this.password)){
			this.username = newusername;
		}
	}

	//Changes the password
	public void changePassword(String password, String newpassword){
		if(password.equals(this.password)){
			this.password = newpassword;
		}
	}

	//return tournament
	public Tournament getTournament(){
		return tournament;
	}
	public static Administrator getInstance(){
		if (instance == null){
			instance = new Administrator("admin", "admin");
		}
		return instance;
	}
	public void changeAll(String newusername,String newpassword){
		this.username = newusername;
		this.password = newpassword;
	}

}
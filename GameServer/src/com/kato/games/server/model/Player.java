package com.kato.games.server.model;

import net.smartsocket.serverclients.TCPClient;

import com.google.gson.JsonObject;

public class Player {

	public String username;
	public String firstname;
	public String lastname;
	public int cash;
	public int id;
	public String gender;
	public String fb_id = null;

	public TCPClient client;
	
	public boolean dead= false;
	
	public boolean played=false;
	
	public Player() {
		
	}

}

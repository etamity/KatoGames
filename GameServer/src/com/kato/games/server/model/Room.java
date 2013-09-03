package com.kato.games.server.model;

import java.util.HashMap;

public class Room {
	public HashMap userList = new HashMap();
    public int id = 0;
    public int currentUsersNum = 0;
    public String _status = "Waiting";
    
	public Room(Player player) {
		// TODO Auto-generated constructor stub
		userList.put(player.username, player);
		
	}


}

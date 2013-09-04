package com.kato.games.server.model;

public class Slot {

	private boolean filled=false;
	public Slot() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isFilled(){
		return filled;
	}
	
	public void reload(){
		filled=true;
	}
	
	public void shoot(){
		filled=false;
	}
	

}

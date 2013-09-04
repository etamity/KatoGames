package com.kato.games.server.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class Room {
	public LinkedList<Player> playerList = new LinkedList<Player>();
	public Queue<Player> queue =  new LinkedList<Player>();
	
    public int id = 0;
    public String _status = "Waiting";
    
    public Player hostPlayer=null;
    
    public final int MAX_PLAYER=6;
    
    public Player currentPlayer;
    
    public Gun gun;
    
    public Timer gameTimer;
    
    public int roundTime=10;  // seconds
    
    
    private TimerTask gameTimeTask;
    
    
	public Room(Player player) {
		// TODO Auto-generated constructor stub
		hostPlayer=player;
		currentPlayer=player;
		playerList.add(player);
		gun=new Gun();
		gameTimer=new Timer();
		
		gameTimeTask=new TimerTask(){
			  @Override
			  public void run() {
				  gameLoop();
			  }
		};
		

	}

	
	public void gameLoop(){
		//  SOCKET: NEXT PLAYER	
		
		if (currentPlayer.played==false)
			shoot(currentPlayer);
		
		
		currentPlayer=nextPlayer();
	}
	
	public void join(Player player){
		playerList.add(player);
	}
	public int getPlayerNum(){
		return playerList.size();
	}
	
	
	private void createQueue(){
		queue.clear();
		for (int i=0;i<playerList.size();i++){
			Player player = playerList.get(i);
			player.played=false;
			queue.offer(player);
		}
	}
	
	public Player nextPlayer(){
		Player player =queue.peek();
		if (player==null){
			createQueue();
			player=queue.peek();
		}
			
		return player; 
	}
	
	public void startGame(Player player) {
		// TODO Auto-generated method stub
		playGun(currentPlayer);
	}

	public void endGame(){
		queue.clear();
		for (int i=0;i<playerList.size();i++){
			Player player = playerList.get(i);
			player.played=false;
			player.dead=false;
			queue.offer(player);
		}
	}
	
	public void playGun(Player player){
		gameTimer.cancel();
		gameTimer.schedule(gameTimeTask, roundTime*1000);
		
		// SOCKET: PLAYGUN
	}
	
	public void spin(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void shoot(Player player) {
		// TODO Auto-generated method stub
		
	}

	public boolean reload(int slotID) {
		// TODO Auto-generated method stub
		return false;
	}
}

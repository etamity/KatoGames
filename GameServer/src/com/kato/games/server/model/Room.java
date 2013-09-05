package com.kato.games.server.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import net.smartsocket.Logger;

import com.google.inject.Inject;
import com.kato.games.server.service.GameService;

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
    
    public int roundTime=3;  // seconds
    
    
    private TimerTask gameTimeTask;
    
    @Inject
    private GameService gameService;
    
	public Room(Player player) {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass().getSimpleName()+"  Room");
		hostPlayer=player;
		currentPlayer=player;
		playerList.add(player);
		gun=new Gun();
		gameTimer=new Timer();
		
		

	}

	
	public void gameLoop(){
		//  SOCKET: NEXT PLAYER	
		
		if (currentPlayer.played==false)
			shoot(currentPlayer);
		
		
		currentPlayer=nextPlayer();
		gameService.nextPlayer(currentPlayer);
		System.out.println(this.getClass().getSimpleName()+"  nextPlayer");
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
		
		System.out.println(this.getClass().getSimpleName()+"  startGame");
		playGun(currentPlayer);
		gameService.startGame(player);
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
		//gameTimer.cancel();
		gameTimer.schedule(new TimerTask(){
			  @Override
			  public void run() {
				  gameLoop();
			  }
		}, roundTime*1000);

		// SOCKET: PLAYGUN
	}
	
	public void spin(Player player) {
		// TODO Auto-generated method stub
		gun.spin();
		gun.shoot(player);
		gameService.spin(player);
		System.out.println(this.getClass().getSimpleName()+"  spin");
	}

	public void shoot(Player player) {
		// TODO Auto-generated method stub
		gun.shoot(player);
		gameService.shoot(player);
		System.out.println(this.getClass().getSimpleName()+"  shoot");
	}

	public void reload(int slotID) {
		// TODO Auto-generated method stub
		
		gun.reload(slotID);
	}
}

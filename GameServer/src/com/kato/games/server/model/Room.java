package com.kato.games.server.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Room{
	public LinkedList<Player> playerList = new LinkedList<Player>();
	public Queue<Player> queue =  new LinkedList<Player>();
	
    public int id = 0;
    public String _status = "Waiting";
    
    public Player hostPlayer=null;
    
    public final int MAX_PLAYER=6;
    
    public Player currentPlayer;
    
    public IGun gun;
    
    public Timer gameTimer;
    
    public int roundTime=3;  // seconds
    
    
    public boolean started=false;
    
    private TimerTask gameTimeTask;
    
    private Logger logger;
    
    //@Inject
    //private GameService gameService;
    
	public Room() {
		// TODO Auto-generated constructor stub
		
		logger=LogManager.getLogger("["+Room.class.getSimpleName()+"]");
		
		logger.trace("Create Room") ;
		gameTimer=new Timer();
		gun=new Gun();
		gameTimeTask=new TimerTask(){
			  @Override
			  public void run() {
				  gameLoop();
			  }
		};

	}
	public void join(Player player){
		logger.trace(player.username+" Joined Room") ;
		playerList.add(player);
		queue.offer(player);
		if (hostPlayer==null)
		{
			hostPlayer=player;
		}
		if (currentPlayer==null){
			currentPlayer=nextPlayer();
		}
	
	}


	public void gameLoop(){
		//  SOCKET: NEXT PLAYER	
		logger.trace("Next Player:" + currentPlayer.username) ;
		if (currentPlayer.played==false)
			shoot(currentPlayer);
		
		
		currentPlayer=nextPlayer();

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
		Player player =queue.poll();
		if (player==null){
			createQueue();
			player=queue.poll();
		}
			
		return player; 
	}
	

	public void startGame() {
		// TODO Auto-generated method stub
		logger.trace( "startGame") ;
		if (started!=true)
		{
		playGun(currentPlayer);
		started=true;
		}
		//gameService.startGame(player);
	}


	public void endGame(){
		queue.clear();
		for (int i=0;i<playerList.size();i++){
			Player player = playerList.get(i);
			player.played=false;
			player.dead=false;
			queue.offer(player);
		}
		started=false;
	}

	public void playGun(Player player){
		
		gameTimer.schedule(gameTimeTask, 0,roundTime*1000);
		// SOCKET: PLAYGUN
	}
	

	public void spin(Player player) {
		// TODO Auto-generated method stub
		logger.trace("spin") ;
		gun.spin();
		gun.shoot(player);
		//gameService.spin(player);

	}

	public void shoot(Player player) {
		// TODO Auto-generated method stub
		gun.shoot(player);
		//gameService.shoot(player);

		logger.trace("shoot") ;
	}

	public void reload(int slotID) {
		// TODO Auto-generated method stub
		
		gun.reload(slotID);
	}
}
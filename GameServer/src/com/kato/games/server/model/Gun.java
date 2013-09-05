package com.kato.games.server.model;

import java.util.LinkedList;

import com.google.inject.Inject;
import com.kato.games.server.service.api.IGameService;

public class Gun {

	private LinkedList<Slot> bulletSlots;
	
	public final int MAX_SLOTS=6;
	
	public final int DEFAULT_BULLETNUM=2;
	
	@Inject
	private IGameService gameService;
	public Gun() {
		// TODO Auto-generated constructor stub
		bulletSlots=new LinkedList<Slot>();
		for (int i=0;i<MAX_SLOTS;i++)
		{
			bulletSlots.add(new Slot());
		}
		
		bulletSlots.get(0).reload();
		bulletSlots.get(1).reload();
	}

	public void spin(){
		int spin= (int)Math.random()*MAX_SLOTS;
		LinkedList<Slot> tempSlot =new LinkedList<Slot>() ;
		for (int i=0;i<spin;i++)
		{
			tempSlot.add(bulletSlots.get(i));
			bulletSlots.remove();
		}
		bulletSlots.addAll(tempSlot);
	}

	
	public void reload(int slotID){
		if (slotID<MAX_SLOTS){
			bulletSlots.set(slotID, new Slot());
		}
	}
	
	public void shoot(Player player){
		boolean result=bulletSlots.getFirst().isFilled();
		if (result == true)
			player.dead=true;
		
		gameService.shoot(player);
	

	}
}

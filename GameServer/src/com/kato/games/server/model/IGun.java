package com.kato.games.server.model;

public interface IGun {

	public abstract void spin();

	public abstract void reload(int slotID);

	public abstract void shoot(Player player);

}
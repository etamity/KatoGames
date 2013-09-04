package com.kato.games.server.service.api;

import com.kato.games.server.model.Player;

public interface IGameService {
	public void startGame(Player player);
	public void nextPlayer(Player player);
	public void shoot(Player player);
	public void spin(Player player);
}

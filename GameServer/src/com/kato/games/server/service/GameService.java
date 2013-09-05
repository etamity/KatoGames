package com.kato.games.server.service;

import net.smartsocket.protocols.json.RemoteCall;

import com.kato.games.server.model.Player;
import com.kato.games.server.service.api.IGameService;

public class GameService implements IGameService {

	public GameService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame(Player player) {
		// TODO Auto-generated method stub
		RemoteCall call=new RemoteCall("onSocketConnected",player.json.get("directTo").getAsString());
		call.put("status", "connected");
		call.put("message", "startGame");
		player.client.send(call);
	}

	@Override
	public void nextPlayer(Player player) {
		// TODO Auto-generated method stub
		RemoteCall call=new RemoteCall("onSocketConnected",player.json.get("directTo").getAsString());
		call.put("status", "connected");
		call.put("message", "nextPlayer");
		player.client.send(call);
	}

	@Override
	public void shoot(Player player) {
		// TODO Auto-generated method stub
		RemoteCall call=new RemoteCall("onSocketConnected",player.json.get("directTo").getAsString());
		call.put("status", "connected");
		call.put("message", "shoot");
		player.client.send(call);
	}

	@Override
	public void spin(Player player) {
		// TODO Auto-generated method stub
		RemoteCall call=new RemoteCall("onSocketConnected",player.json.get("directTo").getAsString());
		call.put("status", "connected");
		call.put("message", "spin");
		player.client.send(call);
	}


}

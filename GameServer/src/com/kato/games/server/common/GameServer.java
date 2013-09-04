package com.kato.games.server.common;

import net.smartsocket.Logger;
import net.smartsocket.protocols.json.RemoteCall;
import net.smartsocket.serverclients.TCPClient;
import net.smartsocket.serverextensions.TCPExtension;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.kato.games.server.common.api.IActionProcess;
import com.kato.games.server.common.api.IGameServer;


public class GameServer extends TCPExtension implements IGameServer {
	@Inject
	private IActionProcess actionProcess;
	
	@Inject
	public GameServer(@Named("GameServer.port") int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onConnect(TCPClient client) {
		// TODO Auto-generated method stub
		Logger.log(client.getIpAddress() +"[" +client.getId() +"]" + " connected.");

		
	}

	@Override
	public boolean onDataSpecial(TCPClient client, String methoedName, JsonObject json) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDisconnect(TCPClient client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExtensionReady() {
		// TODO Auto-generated method stub
		Logger.setLogLevel(Logger.DEBUG);
		Logger.log("Game Sever Started.");
		Logger.log("Listening Port...["+ this.getPort()+"]");
	}
	
	public void processMessage(TCPClient client,JsonObject json){
		Logger.log("ACTION: " + json.get("action").getAsString());
		actionProcess.processAction(client, json);

	}
	
	public void socketConnected(TCPClient client,JsonObject json){
		Logger.log("RECIEVED: " + json.get("message").getAsString());
		
		RemoteCall call=new RemoteCall("onSocketConnected",json.get("directTo").getAsString());
		call.put("status", "connected");
		call.put("message", "Socket Connected!");
		client.send(call);
	}
	
	public void log(Object obj){
		Logger.log(obj);
	}

}

package com.kato.games.server.common;

import net.smartsocket.Logger;
import net.smartsocket.protocols.json.RemoteCall;
import net.smartsocket.serverclients.TCPClient;
import net.smartsocket.serverextensions.TCPExtension;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.kato.games.server.model.ActionsConstant;
import com.kato.games.server.service.ILoginService;


public class GameServer extends TCPExtension {
	@Inject
	public ILoginService loginService;
	
	public GameServer(int port) {
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
		Logger.log("Listening Port...");
	}
	
	public void processAction(TCPClient client,JsonObject json){
		Logger.log("ACTION: " + json.get("action").getAsString());
		String action=json.get("action").getAsString();
		
		switch (action)
		{
			case ActionsConstant.Login:
				String username =json.get("username").getAsString();
				String password =json.get("password").getAsString();
				//loginService=new LoginService();
				loginService.login(username, password);
				break;
		}
	}
	
	public void socketConnected(TCPClient client,JsonObject json){
		Logger.log("RECIEVED: " + json.get("message").getAsString());
		
		RemoteCall call=new RemoteCall("onSocketConnected",json.get("directTo").getAsString());
		call.put("status", "connected");
		call.put("message", "Socket Connected!");
		client.send(call);
	}
	
	public static void main(String[] args) {
		new GameServer(9999).start();
	}


}

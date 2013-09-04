package com.kato.games.server.common;
import net.smartsocket.serverclients.TCPClient;
import net.smartsocket.serverextensions.TCPExtension;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.kato.games.server.common.api.IChatServer;


public class ChatServer extends TCPExtension implements IChatServer {

	@Inject
	public ChatServer(@Named("ChatServer.port") int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onConnect(TCPClient client) {
		// TODO Auto-generated method stub
		//Logger.log(client.getIpAddress() +"[" +client.getId() +"]" + " connected.");

	}
	public void socketConnected(TCPClient client,JsonObject json){
		
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
	public void log(Object obj){
		//Logger.log(obj);
	}
	@Override
	public void onExtensionReady() {
		// TODO Auto-generated method stub
		//Logger.setLogLevel(Logger.DEBUG);
		//Logger.log("Chat Sever Started.");
		//Logger.log("Listening Port...");
	}

}

import com.google.gson.JsonObject;

import net.smartsocket.Logger;
import net.smartsocket.serverclients.TCPClient;
import net.smartsocket.serverextensions.TCPExtension;


public class ChatServer extends TCPExtension {

	public ChatServer(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onConnect(TCPClient client) {
		// TODO Auto-generated method stub
		Logger.log(client.getIpAddress() +"[" +client.getId() +"]" + " connected.");

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
	public static void main(String[] args) {
		new ChatServer(9900).start();
	}
	@Override
	public void onExtensionReady() {
		// TODO Auto-generated method stub
		Logger.setLogLevel(Logger.DEBUG);
		Logger.log("Chat Sever Started.");
		Logger.log("Listening Port...");
	}

}

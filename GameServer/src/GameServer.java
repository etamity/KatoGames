
import net.smartsocket.Logger;
import net.smartsocket.protocols.json.RemoteCall;
import net.smartsocket.serverclients.TCPClient;
import net.smartsocket.serverextensions.TCPExtension;

import com.google.gson.JsonObject;


public class GameServer extends TCPExtension {

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
		Logger.log("Sever Start Listening...");
	}

	public void greeting(TCPClient client,JsonObject json){
		Logger.log("Recieved: " + json.get("message").getAsString());
		
		RemoteCall call=new RemoteCall("onGreeting",json.get("directTo").getAsString());
		call.put("serverResponse", "Message Recivied!!!");
		client.send(call);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new GameServer(9999).start();
	}

}

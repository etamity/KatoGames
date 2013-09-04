import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kato.games.server.common.ChatServer;
import com.kato.games.server.module.ChatServerModule;


public class ChatSeverApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new ChatServerModule());
		ChatServer chatServer= injector.getInstance(ChatServer.class);
		chatServer.start();
	}

}

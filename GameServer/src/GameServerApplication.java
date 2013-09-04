import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kato.games.server.common.ChatServer;
import com.kato.games.server.common.GameServer;
import com.kato.games.server.module.GameServerModule;


public class GameServerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Injector injector = Guice.createInjector(new GameServerModule());
		GameServer gameServer= injector.getInstance(GameServer.class);
		gameServer.start();
		//GameServer.main(args);
	}
}

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kato.games.server.common.GameServer;
import com.kato.games.server.module.GameServerModule;


public class ServerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChatServer.main(args);
		//GameServer.main(args);
		Injector injector = Guice.createInjector(new GameServerModule());
		//ILoginService loginService=injector.getInstance(LoginService.class);
		//ILoginService loginService=injector.getInstance(LoginService.class);
		GameServer gameServer= injector.getInstance(GameServer.class);
		gameServer.start();
		//GameServer.main(args);
	}
}

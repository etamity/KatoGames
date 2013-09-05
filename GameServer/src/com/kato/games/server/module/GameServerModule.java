package com.kato.games.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.kato.games.server.common.ActionProcess;
import com.kato.games.server.common.GameServer;
import com.kato.games.server.common.api.IActionProcess;
import com.kato.games.server.common.api.IGameServer;
import com.kato.games.server.service.GameService;
import com.kato.games.server.service.LoginService;
import com.kato.games.server.service.api.IGameService;
import com.kato.games.server.service.api.ILoginService;

public class GameServerModule extends AbstractModule {

	public GameServerModule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(int.class).annotatedWith(Names.named("GameServer.port")).toInstance(9999);
		bind(ILoginService.class).to(LoginService.class).in(Scopes.SINGLETON);
		bind(IActionProcess.class).to(ActionProcess.class).in(Scopes.SINGLETON);
		bind(IGameService.class).to(GameService.class).in(Scopes.SINGLETON);
		bind(IGameServer.class).to(GameServer.class).in(Scopes.SINGLETON);
	}

}

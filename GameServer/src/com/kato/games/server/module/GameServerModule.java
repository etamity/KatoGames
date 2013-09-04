package com.kato.games.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.kato.games.server.common.GameServer;
import com.kato.games.server.common.IGameServer;
import com.kato.games.server.service.ILoginService;
import com.kato.games.server.service.LoginService;

public class GameServerModule extends AbstractModule {

	public GameServerModule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(int.class).annotatedWith(Names.named("GameServer.port")).toInstance(9999);
		bind(ILoginService.class).to(LoginService.class).in(Scopes.SINGLETON);
		bind(IGameServer.class).to(GameServer.class).in(Scopes.SINGLETON);
	}

}

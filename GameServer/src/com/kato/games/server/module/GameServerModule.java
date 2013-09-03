package com.kato.games.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.kato.games.server.service.ILoginService;
import com.kato.games.server.service.LoginService;

public class GameServerModule extends AbstractModule {

	public GameServerModule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(ILoginService.class).to(LoginService.class).in(Scopes.SINGLETON);
	}

}

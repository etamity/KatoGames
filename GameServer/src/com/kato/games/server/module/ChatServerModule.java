package com.kato.games.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.kato.games.server.common.ActionProcess;
import com.kato.games.server.common.ChatServer;
import com.kato.games.server.common.GameServer;
import com.kato.games.server.common.api.IActionProcess;
import com.kato.games.server.common.api.IChatServer;
import com.kato.games.server.common.api.IGameServer;
import com.kato.games.server.service.LoginService;
import com.kato.games.server.service.api.ILoginService;

public class ChatServerModule extends AbstractModule {

	public ChatServerModule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(int.class).annotatedWith(Names.named("ChatServer.port")).toInstance(9900);
		bind(ILoginService.class).to(LoginService.class).in(Scopes.SINGLETON);
		bind(IActionProcess.class).to(ActionProcess.class).in(Scopes.SINGLETON);
		bind(IGameServer.class).to(GameServer.class).in(Scopes.SINGLETON);
		bind(IChatServer.class).to(ChatServer.class).in(Scopes.SINGLETON);
	}

}

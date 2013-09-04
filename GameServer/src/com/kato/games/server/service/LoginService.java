package com.kato.games.server.service;

import com.google.inject.Inject;
import com.kato.games.server.common.IGameServer;

public class LoginService implements ILoginService {

	@Inject
	private IGameServer gameSever;
	public LoginService() {
		// TODO Auto-generated constructor stub
		//gameSever.log("LoginService");
		System.out.println("LoginService");
	}
	public void login(String username,String password){
		gameSever.log(this.getClass().getSimpleName()+": "+username+" , "+password);
		System.out.println("login: "+username+" , "+password);
	}

}

package com.kato.games.server.service;

import net.smartsocket.Logger;

import com.google.inject.Singleton;

@Singleton
public class LoginService implements ILoginService {

	public LoginService() {
		// TODO Auto-generated constructor stub
		Logger.log("LoginService");
		System.out.println("LoginService");
	}
	public void login(String username,String password){
		Logger.log("login: "+username+" , "+password);
		System.out.println("login: "+username+" , "+password);
	}

}

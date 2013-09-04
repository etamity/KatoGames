package com.kato.games.server.common;

import net.smartsocket.serverclients.TCPClient;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.kato.games.server.common.api.IActionProcess;
import com.kato.games.server.model.ActionsConstant;
import com.kato.games.server.service.api.ILoginService;

public class ActionProcess implements IActionProcess {
	@Inject
	private ILoginService loginService;
	public ActionProcess() {
		// TODO Auto-generated constructor stub
	}
	public void processAction(TCPClient client,JsonObject json){
		String action=json.get("action").getAsString();
		switch (action)
		{
			case ActionsConstant.Login:
				String username =json.get("username").getAsString();
				String password =json.get("password").getAsString();
				//loginService=new LoginService();
				loginService.login(username, password);
				break;
		}
	}

}

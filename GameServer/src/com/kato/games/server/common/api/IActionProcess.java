package com.kato.games.server.common.api;

import net.smartsocket.serverclients.TCPClient;

import com.google.gson.JsonObject;

public interface IActionProcess {
	public void processAction(TCPClient client,JsonObject json);
}

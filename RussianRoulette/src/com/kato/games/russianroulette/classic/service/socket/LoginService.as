package com.kato.games.russianroulette.classic.service.socket
{
	import com.kato.games.russianroulette.classic.model.ActionsConstant;
	
	import net.smartsocket.SmartSocketClient;
	import net.smartsocket.protocols.json.RemoteCall;

	public class LoginService
	{
		public function LoginService()
		{
		}
		
		public function login(username:String,password:String):void{
			var call:RemoteCall=new RemoteCall("processAction","RussianRoulette");
			call.action=ActionsConstant.Login;
			call.username=username;
			call.password=password;
			SmartSocketClient.send(call);
		}
	}
}
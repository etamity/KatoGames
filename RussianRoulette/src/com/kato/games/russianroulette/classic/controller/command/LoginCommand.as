package com.kato.games.russianroulette.classic.controller.command
{
	import com.kato.game.commlibs.core.BaseSignal;
	import com.kato.games.russianroulette.classic.service.socket.LoginService;
	
	import robotlegs.bender.bundles.mvcs.Command;
	
	public class LoginCommand extends Command
	{
		[Inject]
		public var singal:BaseSignal;
		
		[Inject]
		public var loginService:LoginService;
		public function LoginCommand()
		{
			super();
		}
		override public function execute():void{
			var username:String=singal.params.username;
			var password:String=singal.params.password;
			loginService.login(username,password);
		}
	}
}
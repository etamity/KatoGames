package com.kato.games.russianroulette.classic.controller.command
{
	import com.kato.games.russianroulette.classic.model.Game;
	import com.kato.games.russianroulette.classic.service.GameSocketService;
	
	import robotlegs.bender.extensions.commandCenter.api.ICommand;
	
	public class SocketConnetCommand implements ICommand
	{
		[Inject]
		public var socketService:GameSocketService;
		
		[Inject]
		public var game:Game;
		
		public function SocketConnetCommand()
		{
		}
		
		public function execute():void
		{
			socketService.connect(game.host,game.port);
		}
	}
}
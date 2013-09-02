package com.kato.games.russianroulette.classic.controller.command
{
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.games.russianroulette.classic.view.LoginView;
	
	import robotlegs.bender.bundles.mvcs.Command;
	import robotlegs.bender.extensions.contextView.ContextView;
	
	public class SetupViewsCommand extends Command
	{
		[Inject]
		public var contextView:ContextView;
		[Inject]
		public var signalBus:SignalBus;
		
		public function SetupViewsCommand()
		{
			super();
		}
		
		override public function execute():void{
			contextView.view.addChild(new LoginView());
		}
	}
}
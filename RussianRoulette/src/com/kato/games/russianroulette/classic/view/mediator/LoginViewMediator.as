package com.kato.games.russianroulette.classic.view.mediator
{
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.games.russianroulette.classic.controller.signals.SocketEvent;
	import com.kato.games.russianroulette.classic.view.LoginView;
	
	import robotlegs.bender.bundles.mvcs.Mediator;
	
	public class LoginViewMediator extends Mediator
	{
		[Inject]
		public var view:LoginView;
		[Inject]
		public var signalBus:SignalBus;
		public function LoginViewMediator()
		{
			super();
		}
	 	override public function initialize():void{
			view.loginSignal.add(doLoginSignal);
		}
		
		public function doLoginSignal(username:String,password:String):void{
			
			signalBus.dispatch(SocketEvent.LOGIN,{username:username,password:password});
		}
	}
}
package com.kato.games.russianroulette.configs
{
	import com.kato.game.commlibs.core.FlashVars;
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.game.commlibs.core.XMLService;
	import com.kato.games.russianroulette.classic.controller.command.SetupViewsCommand;
	import com.kato.games.russianroulette.classic.controller.command.SocketConnetCommand;
	import com.kato.games.russianroulette.classic.controller.command.StartupCommand;
	import com.kato.games.russianroulette.classic.controller.signals.ApplicationEvent;
	import com.kato.games.russianroulette.classic.controller.signals.SocketEvent;
	import com.kato.games.russianroulette.classic.model.Game;
	import com.kato.games.russianroulette.classic.model.URLSModel;
	import com.kato.games.russianroulette.classic.service.ConfigService;
	import com.kato.games.russianroulette.classic.service.FlashVarsService;
	import com.kato.games.russianroulette.classic.service.GameSocketService;
	import com.kato.games.russianroulette.classic.view.LoginView;
	import com.kato.games.russianroulette.classic.view.mediator.LoginViewMediator;
	
	import robotlegs.bender.extensions.contextView.ContextView;
	import robotlegs.bender.extensions.mediatorMap.api.IMediatorMap;
	import robotlegs.bender.extensions.signalCommandMap.api.ISignalCommandMap;
	import robotlegs.bender.framework.api.IConfig;
	import robotlegs.bender.framework.api.IContext;
	import robotlegs.bender.framework.api.IInjector;
	import robotlegs.bender.framework.api.LogLevel;
	import robotlegs.extensions.facebook.impl.services.init.FacebookInitAPIService;
	
	public class RussianRouletteConfig implements IConfig
	{
		[Inject]
		public var service:FacebookInitAPIService;
		
		[Inject]
		public var mediatorMap:IMediatorMap;
		
		[Inject]
		public var commandMap:ISignalCommandMap;
		
		[Inject]
		public var injector:IInjector;
		
		[Inject]
		public var context:IContext;
		
		[Inject]
		public var contextView:ContextView;
		
		protected var signalBus:SignalBus=new SignalBus();
		
		public function RussianRouletteConfig()
		{
		}
		
		public function configure():void
		{
			context.logLevel=LogLevel.DEBUG;
			mapSingletons();
			mapMediators();
			mapCommands();
			context.afterInitializing(init);
		}
		public function mapSingletons():void
		{
			injector.map(FlashVars).toValue(new FlashVars(contextView));
			injector.map(FlashVarsService).asSingleton();
			injector.map(ConfigService).asSingleton();
			injector.map(XMLService).asSingleton();
			injector.map(Game).asSingleton();
			injector.map(URLSModel).asSingleton();
			injector.map(SignalBus).toValue(signalBus);
			injector.map(GameSocketService).asSingleton();
		}
		public function mapMediators():void{
			mediatorMap.map(LoginView).toMediator(LoginViewMediator);
		}
		
		public function mapCommands():void
		{
			commandMap.mapSignal(signalBus.signal(ApplicationEvent.STARTUP), StartupCommand, true);
			commandMap.mapSignal(signalBus.signal(ApplicationEvent.STARTUP_COMPLETE), SetupViewsCommand, true);
			commandMap.mapSignal(signalBus.signal(SocketEvent.CONNECT), SocketConnetCommand);
		}
		public function init():void
		{
			mediatorMap.mediate(contextView.view);
			signalBus.dispatch(ApplicationEvent.STARTUP);
		}
	}
}
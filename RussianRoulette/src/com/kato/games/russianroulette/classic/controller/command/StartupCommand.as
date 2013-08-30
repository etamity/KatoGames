package com.kato.games.russianroulette.classic.controller.command
{
	import com.kato.game.commlibs.core.FlashVars;
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.games.russianroulette.classic.controller.signals.ApplicationEvent;
	import com.kato.games.russianroulette.classic.model.URLSModel;
	import com.kato.games.russianroulette.classic.service.ConfigService;
	import com.kato.games.russianroulette.classic.service.FlashVarsService;
	
	import flash.events.IEventDispatcher;
	import flash.events.IOErrorEvent;
	
	import robotlegs.bender.bundles.mvcs.Command;
	import robotlegs.bender.framework.api.ILogger;
	import robotlegs.extensions.facebook.impl.events.FacebookEvent;
	
	public class StartupCommand extends Command
	{
		[Inject]
		public var flashVarsService:FlashVarsService;
		[Inject]
		public var configService:ConfigService;
		[Inject]
		public var flashVars:FlashVars;
		[Inject]
		public var signalBus:SignalBus;
		[Inject]
		public var urls:URLSModel;
		[Inject]
		public var eventDispatcher:IEventDispatcher;
		[Inject]
		public var logger:ILogger;
		public function StartupCommand()
		{
			super();
		}
		override public function execute():void {	
			logger.debug("localhost: "+flashVars.localhost);
			if(flashVars.localhost) {
				flashVarsService.load(function ():void{
					configService.load(function ():void{
						eventDispatcher.dispatchEvent(new FacebookEvent(FacebookEvent.API_INIT_REQUEST));
					});
				});
			}
			else{
				configService.load();
			}
			
		}
		private function onStart():void{
			signalBus.dispatch(ApplicationEvent.STARTUP_COMPLETE);
		}
		private function onError(evt:IOErrorEvent):void{
			signalBus.dispatch(ApplicationEvent.SHOWERROR,{target:this,error:evt.text});
		}
	}
}
package com.kato.games.russianroulette.classic.service
{
	import com.kato.game.commlibs.core.IService;
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.game.commlibs.core.XMLService;
	import com.kato.games.russianroulette.classic.controller.signals.ApplicationEvent;
	import com.kato.games.russianroulette.classic.model.Game;
	import com.kato.games.russianroulette.classic.model.URLSModel;
	
	import org.assetloader.signals.ErrorSignal;
	import org.assetloader.signals.LoaderSignal;
	
	import robotlegs.bender.framework.api.ILogger;
	import robotlegs.extensions.facebook.impl.services.init.FacebookInitAPIService;

	public class ConfigService implements IService
	{
		[Inject]
		public var urlsModel:URLSModel;
		[Inject]
		public var xmlService:XMLService;
		[Inject]
		public var logger:ILogger;
		[Inject]
		public var game:Game;
		[Inject]
		public var signalBus:SignalBus;
		
		[Inject]
		public var facebookService:FacebookInitAPIService;
		
		private var _onComplete:Function;
		public function ConfigService()
		{
		}
		public function load(onComplete:Function=null):void{
			_onComplete=onComplete;
			xmlService.loadURL(urlsModel.config,setConfig,showError);
		}
		private function showError(signal:ErrorSignal):void {
			logger.debug(signal.message);
			signalBus.dispatch(ApplicationEvent.SHOWERROR,{target:this,error:signal.message});
		}
		public function setConfig(signal:LoaderSignal, xml:XML):void{
			debug(xml.toXMLString());
			game.facebookAPIKey=xml.@apikey;
			game.facebookSecret=xml.@secret;
			
			facebookService.api_key=game.facebookAPIKey;
			if (_onComplete!=null)
				_onComplete();
		}
		
		private function debug(...args):void {
			logger.debug(args);
		}
	}
}
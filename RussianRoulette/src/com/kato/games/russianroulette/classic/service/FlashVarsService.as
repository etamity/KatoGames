package com.kato.games.russianroulette.classic.service
{
	import com.kato.game.commlibs.core.FlashVars;
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.game.commlibs.core.XMLService;
	import com.kato.games.russianroulette.classic.controller.signals.ApplicationEvent;
	import com.kato.games.russianroulette.classic.model.Game;
	import com.kato.games.russianroulette.classic.model.URLSModel;
	
	import org.assetloader.signals.ErrorSignal;
	import org.assetloader.signals.LoaderSignal;
	
	import robotlegs.bender.framework.api.ILogger;

	public class FlashVarsService
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
		
		[Inject]
		public var flashVars:FlashVars;
		public var signalBus:SignalBus;
		private var _onComplete:Function;
		public function FlashVarsService()
		{
		}
		public function load(onComplete:Function=null):void{
			_onComplete=onComplete;
			xmlService.loadURL(urlsModel.flashvarsUrl,setConfig,showError);
		}
		private function showError(signal:ErrorSignal):void {
			logger.debug(signal.message);
			signalBus.dispatch(ApplicationEvent.SHOWERROR,{target:this,error:signal.message});
		}
		public function setConfig(signal:LoaderSignal, xml:XML):void{
			logger.debug(xml.toXMLString());
			flashVars.lang=xml.@lang;
			flashVars.seats=int(xml.@seats);
			flashVars.waittime=int(xml.@waittime);
			flashVars.game==xml.@game;
			flashVars.host==xml.@host;
			flashVars.port=int(xml.@port);
			
			
			game.seatCount=int(xml.@seats);
			game.waitTime=int(xml.@waittime);
			game.host=xml.@host;
			game.port=int(xml.@port);
			if (_onComplete!=null)
				_onComplete();
		}
	}
}
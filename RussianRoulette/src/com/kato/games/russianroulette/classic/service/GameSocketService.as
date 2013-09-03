package com.kato.games.russianroulette.classic.service
{
	import com.kato.game.commlibs.core.SignalBus;
	import com.kato.games.russianroulette.classic.controller.signals.ApplicationEvent;
	
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	
	import net.smartsocket.SmartSocketClient;
	import net.smartsocket.protocols.json.RemoteCall;
	
	import robotlegs.bender.framework.api.ILogger;

	public class GameSocketService
	{
		private var socket:SmartSocketClient=new SmartSocketClient();
		
		[Inject]
		public var logger:ILogger;
		[Inject]
		public var signalBus:SignalBus;
		public function GameSocketService()
		{
			SmartSocketClient.addDataListener("RussianRoulette",this);
			
			socket.addEventListener(Event.CONNECT,doConnectEvent);
			socket.addEventListener(Event.CLOSE,doCloseEvent);
			socket.addEventListener(IOErrorEvent.IO_ERROR,doOnIOErrorEvent);
		}
		private function doOnIOErrorEvent(evt:IOErrorEvent):void{
			logger.error(evt);
		}
		private function doCloseEvent(evt:Event):void{
			logger.error(evt);
			socket.close();
		}
		public function onSocketConnected(call:RemoteCall):void{
			logger.debug(call);
			if (call.status=="connected")
			signalBus.dispatch(ApplicationEvent.STARTUP_COMPLETE);
			
		}
		private function doConnectEvent(evt:Event):void{
			var call:RemoteCall=new RemoteCall("socketConnected","RussianRoulette");
			call.message="Client Connected!";
			SmartSocketClient.send(call);
		}
		
		public function connect(host:String,port:int):void{
			logger.debug("host:"+host+"   port:"+String(port));
			socket.connect(host,port);
		}
	}
}
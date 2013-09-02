package com.kato.games.russianroulette.classic.service
{
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
		public function GameSocketService()
		{
			SmartSocketClient.addDataListener("GameSocketService",this);
			
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
		public function onGreeting(call:RemoteCall):void{
			logger.debug(call.serverResponse);
		}
		private function doConnectEvent(evt:Event):void{
			var call:RemoteCall=new RemoteCall("greeting","GameSocketService");
			call.message="Client Greeting";
			SmartSocketClient.send(call);
		}
		
		public function connect(host:String,port:int):void{
			logger.debug("host:"+host+"   port:"+String(port));
			socket.connect(host,port);
		}
	}
}
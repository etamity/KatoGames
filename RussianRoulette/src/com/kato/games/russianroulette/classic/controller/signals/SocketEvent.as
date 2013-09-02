package com.kato.games.russianroulette.classic.controller.signals
{
	public class SocketEvent
	{
		public static const SITDOWN:String = "SocketEvent.SITDOWN";
		public static const STANDUP:String = "SocketEvent.STANDUP";
		
		public static const CONNECT:String = "SocketEvent.CONNECT";
		public static const DISCONNECT:String = "SocketEvent.DISCONNECT";
		public static const LOGIN:String = "SocketEvent.LOGIN";
		
		public function SocketEvent()
		{
		}
	}
}
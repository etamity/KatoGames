package com.kato.games.russianroulette.classic.model
{
	public class Game
	{
		public var facebookAPIKey:String;
		public var facebookSecret:String;
		
		public var seatCount:uint=6;
		public var waitTime:uint=10;
		
		public var host:String="localhost";
		public var port:int=9999;
		
		public function Game()
		{
		}
	}
}
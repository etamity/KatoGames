package com.kato.games.russianroulette.classic.model
{
	import com.kato.game.commlibs.core.FlashVars;

	public class URLSModel
	{
		private var _config:String="xml/config.xml";
		private var _flashvars:String="xml/flashvars.xml";
		
		[Inject]
		public var flashVars:FlashVars;
		
		public function URLSModel()
		{
		}
		
		public function get config():String{
			var result:String;
			if (flashVars.localhost){
				result="xml/config.xml";
			}
			return result;
		}
		public function get flashvarsUrl():String{
			var result:String;
			if (flashVars.localhost){
				result="xml/flashvars.xml";
			}
			return result;
		}
	}
}
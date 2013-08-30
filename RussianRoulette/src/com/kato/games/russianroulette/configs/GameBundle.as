package com.kato.games.russianroulette.configs
{
	import robotlegs.bender.bundles.mvcs.MVCSBundle;
	import robotlegs.bender.extensions.signalCommandMap.SignalCommandMapExtension;
	import robotlegs.bender.framework.api.IBundle;
	import robotlegs.bender.framework.api.IContext;
	import robotlegs.extensions.facebook.FacebookExtension;
	
	public class GameBundle implements IBundle
	{
		public function GameBundle()
		{
		}
		
		public function extend(context:IContext):void
		{
			context.install(
				MVCSBundle,
				FacebookExtension,
				SignalCommandMapExtension);
		}
	}
}
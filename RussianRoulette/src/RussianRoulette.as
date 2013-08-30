package
{
	import com.kato.games.russianroulette.configs.GameBundle;
	import com.kato.games.russianroulette.configs.RussianRouletteConfig;
	
	import flash.display.Sprite;
	import flash.events.Event;
	
	import robotlegs.bender.extensions.contextView.ContextView;
	import robotlegs.bender.framework.api.IContext;
	import robotlegs.bender.framework.impl.Context;
	
	//[SWF(width = "990", height = "610", frameRate = "24", backgroundColor = "#4C4C4C")]
	public class RussianRoulette extends Sprite
	{
		protected var context:IContext; 
		public function RussianRoulette()
		{
			addEventListener(Event.ADDED_TO_STAGE,onAddToStage);
		}
		private function onAddToStage(evt:Event):void{
			removeEventListener(Event.ADDED_TO_STAGE,onAddToStage);
			start();
		}
		
		private function start():void{
			context = new Context()
				.install(GameBundle)
				.configure(
					RussianRouletteConfig,
					new ContextView(this)
				);
		}
	}
}
package com.kato.game.commlibs.core
{
	import flash.display.DisplayObjectContainer;
	import flash.events.Event;
	
	import robotlegs.bender.extensions.contextView.ContextView;
	import robotlegs.bender.framework.api.ILogger;

	public dynamic class FlashVars
	{
		private var root:DisplayObjectContainer;

		public var url:String;

		[Inject]
		public var logger:ILogger;

		public function FlashVars(contextView:ContextView)
		{
			root=contextView.view;
			parse(root.stage.loaderInfo.parameters);
			contextView.view.addEventListener(Event.ADDED_TO_STAGE,onAddToStage);
			url=root.stage.loaderInfo.url;
		}
		private function onAddToStage(evt:Event):void
		{
			url = root.stage.loaderInfo.url;
		}
		protected function onParseError(key:String, value:String, error:Error):void
		{
			logger.debug("Error:", [key, value, error.message]);
		}

		private function debug(... args):void
		{
			logger.debug(this, args);
		}

		public function get localhost():Boolean
		{
			var result:Boolean=false;
			if (url!=null)
			if (url.indexOf("file:///") != -1)
			{
				result=true;
			}
			else
			{
				result=false;
			}
			return result;
		}

		protected function parse(params:Object):void
		{
			var types:Array=new Array("array", "boolean", "int", "number", "string", "xml");

			for (var key:String in params)
			{
				var value:String=params[key];
				var keySplit:Array=key.split("_");
				var type:String=keySplit[0].toLowerCase();
				var typeIndex:int=types.indexOf(type);

				if (typeIndex == -1)
				{
					type="string";
				}
				else
				{
					key=keySplit.splice(1).join("_");
				}

				try
				{
					switch (type)
					{
						case "array":
							this[key]=value.split(",");
							break;
						case "boolean":
							this[key]=toBoolean(value);
							break;
						case "int":
							this[key]=int(value);
							break;
						case "number":
							this[key]=Number(value);
							break;
						case "string":
							this[key]=value;
							break;
						case "xml":
							this[key]=new XML(value);
							break;
					}

				}


				catch (error:Error)
				{
					onParseError(key, value, error);
				}
				trace("Key:" + key, "vaule:" + this[key]);
			}
		}

		protected function toBoolean(str:String):Boolean
		{
			str=str.toLowerCase();
			if (str == "1" || str == "true")
			{
				return true;
			}
			return false;
		}

		public function toString():String
		{
			var str:String;
			for (var key:String in this)
			{
				str+=key + ":" + this[key] + " ";
			}
			return str;


		}
	}
}

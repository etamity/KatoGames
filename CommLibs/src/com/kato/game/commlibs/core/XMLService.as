package com.kato.game.commlibs.core
{
	import flash.net.URLRequest;
	import flash.net.URLVariables;
	
	import org.assetloader.base.Param;
	import org.assetloader.core.IParam;
	import org.assetloader.loaders.XMLLoader;
	
	import robotlegs.bender.framework.api.ILogger;
	
	public class XMLService
	{
		protected var params:URLVariables;
		
		[Inject]
		public var logger:ILogger;
		public function XMLService()
		{
			params=new URLVariables();
		}
		
		public function get parameters():Object{
			return params;
		}
		public function loadURL(url:String,onComplete:Function=null,onError:Function=null,noCache:Boolean=true):void
		{
			var rtime:String;
			var urlRequest:URLRequest;
			urlRequest= new URLRequest(url);
			var loader:XMLLoader=new XMLLoader(urlRequest);
			if (noCache){
				var param:IParam=new Param(Param.PREVENT_CACHE, true);
				loader.addParam(param);
			}
			urlRequest.data=params;
			if (onComplete!=null)
				loader.onComplete.add(onComplete);
			if (onError!=null)
				loader.onError.add(onError);
			
			loader.start();
			logger.debug("Loading: "+url,[params]);
		}

	}
}
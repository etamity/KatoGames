package com.kato.game.commlibs.ui
{
	import flash.display.DisplayObject;
	import flash.display.DisplayObjectContainer;
	import flash.display.SimpleButton;
	import flash.filters.ColorMatrixFilter;
	import flash.text.TextField;
	
	import fl.motion.AdjustColor;
	

	public class SMButton
	{
		private var button:SimpleButton;
		public var url:String;
		private var _label:String;
		
		public var upStateText:TextField;
		private var _params:Object=new Object();
		

		public function SMButton(btn:SimpleButton)
		{
			super();
			button=btn;
			var sp:DisplayObjectContainer=button.upState as DisplayObjectContainer;
			if (sp!=null) {
				var n:uint=sp.numChildren;
				for (var i:int=0; i < n; i++) {
					var txt:*= sp.getChildAt(i);
					if (txt is TextField) {
						upStateText= TextField(txt);
						//upStateText.setTextFormat(_textFormat);
						
					}
				}
			}
		}
		
		public function get params():Object{
			return _params;
		}
		public function set params(val:Object):void{
			_params=val;
		}
		public function set label(text:String):void{
			if (text !=null && text!="")
			update(text);
		}
		
		public function get label():String{
			return _label;
		}
		
		public function set enabled(val:Boolean):void{
		
			button.enabled=val;
			button.mouseEnabled=val;
			if (val==false)
			{
			button.alpha= 0.8;
			setEnableFilter(button,false);
		
			}
			else
			{
			button.alpha= 1;
			setEnableFilter(button);
			}
		}
		
	    public function get visible():Boolean{
			return button.visible;
		}
		public function set visible(val:Boolean):void{
			 button.visible=val;
		}
		public function get enabled():Boolean{
			return button.enabled;
		}
		public function get skin():SimpleButton{
			return button;
		}
		public static function setEnableFilter(mc:DisplayObject, enable:Boolean=true):void {
			var colorFilter:AdjustColor = new AdjustColor();
			var mColorMatrix:ColorMatrixFilter;
			var mMatrix:Array           = [];
			
			colorFilter.brightness = 0;
			colorFilter.contrast = 0;
			colorFilter.saturation = 0;
			colorFilter.hue = 0;
			
			if (!enable)
			{
				colorFilter.saturation = -100;
			}
			
			mMatrix = colorFilter.CalculateFinalFlatArray();
			mColorMatrix = new ColorMatrixFilter(mMatrix);
			mc.filters = [mColorMatrix];
		}
		
		private function update(text:String):void {
			_label=text;
			updateText(button.upState, text);
			updateText(button.overState, text);
			updateText(button.downState, text);
			
			
		}
		
		
		public function updateText(o:DisplayObject,text:String):void {
			var sp:DisplayObjectContainer=o as DisplayObjectContainer;
			if (sp!=null) {
				var n:uint=sp.numChildren;
				for (var i:int=0; i < n; i++) {
					var txt:*= sp.getChildAt(i);
					if (txt is TextField) {
						TextField(txt).text=text;
						TextField(txt).mouseEnabled=false;
					}
				}
			}
		}
		
	}
}
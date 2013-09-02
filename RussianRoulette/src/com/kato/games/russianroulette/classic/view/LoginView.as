package com.kato.games.russianroulette.classic.view
{
	import com.kato.game.commlibs.ui.SMButton;
	
	import flash.events.MouseEvent;
	
	import org.osflash.signals.Signal;

	public class LoginView extends LoginAsset
	{
		public var loginSignal:Signal=new Signal();
		public var loginButton:SMButton;
		public function LoginView()
		{
			super();
			loginButton=new SMButton(loginBtn);
			loginButton.skin.addEventListener(MouseEvent.CLICK,doLoginEvent)
		}
		private function doLoginEvent(evt:MouseEvent):void{
			login();
		}
		public function login():void{
			loginSignal.dispatch(this.userNameTxt.text,this.passwordTxt.text);
		}
		
	}
}
package com.kato.games.russianroulette.classic.model
{
	public class Seat
	{
		private var _player:Player;
		private var _id:uint;
		
		private var _empty:Boolean=true;
		public function Seat(id:uint)
		{
			_id=id;
			_empty=true
		}
		
		public function get player():Player{
			return _player;
		}
		
		public function set player(val:Player):void{
			if (_empty==true)
				_player=val;
		}
		
		public function get id():uint{
			return _id;
		}

	}
}
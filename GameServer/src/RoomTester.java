import com.kato.games.server.model.Player;
import com.kato.games.server.model.Room;


public class RoomTester {

	public RoomTester() {
		// TODO Auto-generated constructor stub
	}
	
	public void start(){
		Room room= new Room();
		
		for (int i=0;i<6;i++)
		{
			Player player=new Player();
			player.username="Player "+ String.valueOf(i);
			room.join(player);
		}
			
		room.startGame();
	}
	public static void main(String[] args) {
		new RoomTester().start();
	}
}

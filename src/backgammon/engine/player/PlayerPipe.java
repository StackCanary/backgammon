package backgammon.engine.player;

public class PlayerPipe {
	private Player player1;
	private Player player2;
	boolean flag = true;
	
	public PlayerPipe(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public Player getPlayer() {
		if (flag) {
			flag = false;
			return player1;
		} else {
			flag = true;
			return player2;
		}
		
	}
	
	
}

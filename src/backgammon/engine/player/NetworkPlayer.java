package backgammon.engine.player;

import backgammon.client.config.Config.Side;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;

public class NetworkPlayer implements Player {
	private NetworkT myRole;
	
	public static enum NetworkT {
		client,
		server,
	}
	
	public NetworkPlayer(NetworkT myRole) {
		this.myRole = myRole;
	}
	
	@Override
	public Side getSide() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hasNextMove() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Pair getMove() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(SequenceOfMoves sequencesOfMoves) {
		// TODO Auto-generated method stub
	}
}

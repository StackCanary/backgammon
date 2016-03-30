package backgammon.engine.player;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;
import backgammon.client.socket.Network;
import backgammon.client.socket.Network.NetworkT;
import backgammon.client.socket.NetworkConstants;
import backgammon.client.socket.Protocol;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;

public class NetworkPlayer implements Player {
	private NetworkT myRole;
	private Network myNetworkManager;
	private Protocol myProtocol;
	private Side side;
	private DiceRollHolder myDice;
	private SequenceOfMoves sequences;
	/**
	 * Start as client
	 * @param host
	 * @param port
	 */
	public NetworkPlayer(String host, int port) {
		this.myRole = NetworkT.client;
		myNetworkManager = new Network(host, port);
		myProtocol = new Protocol(myNetworkManager);
		
		
	}
	
	
	/**
	 * Start as server
	 * @param port
	 */
	public NetworkPlayer(int port) {
		this.myRole = NetworkT.server;
		myNetworkManager = new Network(port);
		myProtocol = new Protocol(myNetworkManager);
	}
	
	@Override
	public Side getSide() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SequenceOfMoves getSequenceOfMoves() {
		return myProtocol.getTurn();
	}
	
	@Override
	public Pair getMove() {
		if (!sequences.getMoves().isEmpty()) {
			return sequences.getMoves().remove(0);
		} else {
			sequences = myProtocol.getTurn();
			return null;
		}
	}
	
	@Override
	public void updateThroughSequences(DiceRollHolder holder, SequenceOfMoves sequencesOfMoves) {
		myProtocol.sendTurn(holder, sequencesOfMoves);
	}
	
	@Override
	public void updateThroughMove(DiceRollHolder holder, Pair move) {
		//unsupported through for network Player
	}


	@Override
	public boolean playsIndividualMoves() {
		return false;
	}


	@Override
	public boolean playerReceivesIndividialMove() {
		return false;
	}


	@Override
	public boolean playerReceivesSequences() {
		return true;
	}


	@Override
	public void setSide(Side side) {
		this.side = side;
	}


	@Override
	public DiceRollHolder getDiceRoll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void error() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

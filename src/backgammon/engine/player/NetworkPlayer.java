package backgammon.engine.player;

import java.util.ArrayList;
import java.util.List;

import backgammon.client.config.Config.Side;
import backgammon.engine.board.DiceAndSequencePair;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.Pair;
import backgammon.engine.board.SequenceOfMoves;
import backgammon.network.Network;
import backgammon.network.Protocol;
import backgammon.network.Network.NetworkRole;

public class NetworkPlayer implements Player {
	private NetworkRole myRole;
	private Network myNetworkManager;
	private Protocol myProtocol;
	private Side side;
	private DiceRollHolder myDice;
	private SequenceOfMoves sequences;
	private boolean skip = false;
	
	/**
	 * Start as client
	 * @param host
	 * @param port
	 */
	public NetworkPlayer(String host, int port) {
		this.myRole = NetworkRole.client;
		myNetworkManager = new Network(host, port);
		myProtocol = new Protocol(myNetworkManager);
		
		this.skip = true;
	}
	
	
	/**
	 * Start as server
	 * @param port
	 */
	public NetworkPlayer(int port) {
		this.myRole = NetworkRole.server;
		myNetworkManager = new Network(port);
		myProtocol = new Protocol(myNetworkManager);
	}
	
	@Override
	public Side getSide() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DiceAndSequencePair getDiceAndSequencePair() {
		System.out.println("In network");
		return myProtocol.getTurn();
	}
	
	//Not supported
	@Override
	public Pair getMove() {
		return null;
	}
	
	@Override
	public boolean refuseMove() {
		if (skip) {
			skip = false;
			
			return true;
		} else {
			return false;
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

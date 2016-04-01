package backgammon.client.socket;

import backgammon.client.socket.Network.NetworkRole;
import backgammon.client.socket.NetworkConstants.ClientProtocol;
import backgammon.client.socket.NetworkConstants.ServerProtocol;
import backgammon.engine.board.DiceAndSequencePair;
import backgammon.engine.board.DiceRollEngine;
import backgammon.engine.board.DiceRollHolder;
import backgammon.engine.board.SequenceOfMoves;

/*
 * Make a class that sends the init stuffs and processes the messages
 */
public class Protocol {
	private Network network;
	public Protocol(Network network) {
		this.network = network;
		if (network.myRole == NetworkRole.client) { init_as_client(); } else { init_as_server(); }
	}

	
	public void init_as_client() {
		try {
			network.sendMessage(ClientProtocol.hello.getMessage());
			expect(ServerProtocol.hello.getMessage());
			network.sendMessage(ClientProtocol.newgame.getMessage());
			expect(ServerProtocol.ready.getMessage());
			
			
		} catch (IncorrectNetworkMessageException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void init_as_server() {
		try {
			expect(ClientProtocol.hello.getMessage());
			network.sendMessage(ServerProtocol.hello.getMessage());
			expect(ClientProtocol.newgame.getMessage());
			network.sendMessage(ServerProtocol.ready.getMessage());
		} catch (IncorrectNetworkMessageException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendTurn(DiceRollHolder holder, SequenceOfMoves moves) {
		network.sendMessage(ClientProtocol.diceMessage(holder, moves));
	}
	
	public DiceAndSequencePair getTurn() {
		String getMessage;
		while ((getMessage = network.getQueue().poll()) == null) {}
		System.out.println("Got turn " + getMessage);
		return new DiceAndSequencePair(ServerProtocol.parseDiceMessageIntoDiceRoll(getMessage), ServerProtocol.parseDiceMessageIntoSequence(getMessage));
	}
	
	public void terminate() {
		network.sendMessage(ClientProtocol.bye.getMessage());
	}
	
	public boolean expect(String message) throws IncorrectNetworkMessageException {
		String getMessage; 
		
		while ((getMessage = network.getQueue().poll()) == null) {}
		
		boolean result = (message.equals(getMessage));
		if (!result) { 
			throw new IncorrectNetworkMessageException(message, getMessage);
		} 
		return result;
		
	}
	
}

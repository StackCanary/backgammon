package backgammon.client.socket;

public class IncorrectNetworkMessageException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public IncorrectNetworkMessageException(String expected, String message) {
		super("Expected " + expected + ", instead received " + message);
	}
	
}

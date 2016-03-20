package backgammon.client.control;

import backgammon.client.ui.shape.EmptyCircle;
import backgammon.client.ui.shape.HeadCircle;

public class Event {
	public EventT me;
	public EmptyCircle eCircle;
	public HeadCircle hCircle;
	
	public enum EventT {
		add,
		remove,
		highlight,
	}
	
	public Event(EventT e, EmptyCircle eCircle, HeadCircle hCircle) {
		this.me = e;
		this.eCircle = eCircle;
		this.hCircle = hCircle;
	}
	
}

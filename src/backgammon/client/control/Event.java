package backgammon.client.control;

import backgammon.client.ui.shape.EmptyCircle;
import backgammon.client.ui.shape.HeadCircle;

public class Event {
	public EmptyCircle eCircle;
	public HeadCircle hCircle;
	
	public Event(EmptyCircle eCircle, HeadCircle hCircle) {
		this.eCircle = eCircle;
		this.hCircle = hCircle;
	}
	
}

package backgammon.server.engine;

public class TriangleS {
	
	private int counters;
	private Color color;
	private int numberOfTriangle;
	
	public TriangleS (int numberOfTriangle){
		this.numberOfTriangle = numberOfTriangle;
	}
	
	public int getCounters() {
		return counters;
	}
	
	public void setCounters(int counters) {
		this.counters = counters;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getNumberOfTriangle() {
		return numberOfTriangle;
	}
	
	public void setNumberOfTriangle(int numberOfTriangle) {
		this.numberOfTriangle = numberOfTriangle;
	}
	
	
	public void incrementCounter(int numberCounter, Color colorCircle){
		
		this.counters += numberCounter;
		this.color = colorCircle;
			
	}
	
	public void decrementCounter(int numberCounter, Color colorCircle){
		this.counters -= numberCounter;
		this.color = colorCircle;
		
		
	}
	
	
	
	
	
	
	
	

}

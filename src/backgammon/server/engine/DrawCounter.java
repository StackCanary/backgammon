package backgammon.server.engine;

import java.util.ArrayList;

public class DrawCounter {
	
	// draws a counter into a certain position
	
	public void drawCounterAt(int positionOfTriangle, int numberOfcirclesAdd, Color colorOfCirle,  ArrayList<TriangleS> triangle){
		
		for(TriangleS temp : triangle){
			
			if(temp.getNumberOfTriangle() == positionOfTriangle){
			
				
				temp.incrementCounter(numberOfcirclesAdd, colorOfCirle);
				
			}	
		}
		
	}	
	
		
	
	// deletes a counter at a certain triangle 
	
	public void deleteCounterAt(int positionOfTriangle, int numberOfcirclesDelete, Color colorOfCirle,  ArrayList<TriangleS> triangle){

		
		for(TriangleS temp : triangle){
			
			if(temp.getNumberOfTriangle() == positionOfTriangle){
				
				temp.decrementCounter(numberOfcirclesDelete, colorOfCirle);
				
				
			}
		}	
	}
}



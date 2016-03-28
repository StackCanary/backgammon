package backgammon.server.engine;

import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.naming.InitialContext;

import backgammon.client.config.Config.Side;
import backgammon.client.ui.shape.Triangle;


public class Board {
	
	
	
	
	public ArrayList<TriangleS> createInitialStateOfBoard(ArrayList<TriangleS> initial){
		
		DrawCounter draw = new DrawCounter();
		
		for (int i = 1; i <= 6 * 4; i++) {
			
			TriangleS triangle = new TriangleS(i); // check what happens with the position
			initial.add(triangle);
		
		}
		
		draw.drawCounterAt(1, 2, Color.BLUE, initial);
		draw.drawCounterAt(6, 5, Color.GREEN, initial);
		
		draw.drawCounterAt(8, 3, Color.GREEN, initial);
		draw.drawCounterAt(12, 5, Color.BLUE, initial);
		
		
		draw.drawCounterAt(13, 5, Color.GREEN, initial);
		draw.drawCounterAt(17, 3, Color.BLUE, initial);
		
		draw.drawCounterAt(19, 5, Color.GREEN, initial);
		draw.drawCounterAt(24, 2, Color.BLUE, initial);
		
		return initial;
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}

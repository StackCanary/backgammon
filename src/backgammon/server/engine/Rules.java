package backgammon.server.engine;

import java.math.*;




import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.InitialContext;

import com.sun.org.apache.xerces.internal.dom.PSVIDOMImplementationImpl;

import backgammon.client.config.Config.Side;
import backgammon.client.control.TriangleController;
import backgammon.client.ui.shape.Triangle;
import backgammon.client.ui.ui.BoardView;
import sun.nio.cs.ext.TIS_620;

public class Rules {

	boolean endOfMove = false;
	private int movesLeft ;
	Board board = new Board();
	ArrayList<TriangleS> stateBoard = board.createInitialStateOfBoard(new ArrayList<TriangleS>()); // how the board is stored







	// should represent who plays first;



	public boolean firstStage(int nFirstDice, int nSecondDice, TriangleS fTriangle, TriangleS sTriangle, Color color){

		//should implement the method while movesLeft > 0;

		DrawCounter draw = new DrawCounter();


		if(this.movesLeft == 0 && nFirstDice != nSecondDice){

			this.movesLeft = nFirstDice + nSecondDice;
		}

		else if(this.movesLeft == 0 && nFirstDice == nSecondDice){

			this.movesLeft = 2 * (nFirstDice + nSecondDice);

		}





		int difference = Math.abs(fTriangle.getNumberOfTriangle() - sTriangle.getNumberOfTriangle());
		int wayOfCollecting = sTriangle.getNumberOfTriangle() - fTriangle.getNumberOfTriangle();

		// and difference is positive to the if statement

		// and is the players turn to play 
		if(fTriangle.getCounters() > 1   && (difference == nFirstDice || difference == nSecondDice || 
				difference == nFirstDice + nSecondDice) && (this.movesLeft >= nFirstDice || this.movesLeft >= nSecondDice) && fTriangle
				.getColor() == color){
			if((color == Color.GREEN && wayOfCollecting < 0) || (color == Color.BLUE && wayOfCollecting > 0)){

				//the second is non-empty and has a friendly circle

				if(fTriangle.getColor() == sTriangle.getColor()){



					draw.deleteCounterAt(fTriangle.getNumberOfTriangle(), 1, color, this.stateBoard);
					draw.drawCounterAt(sTriangle.getNumberOfTriangle(), 1, color, this.stateBoard); //delete

					this.movesLeft = this.movesLeft - difference;
					if(this.movesLeft == 0){
						this.endOfMove = true;

					}


					return true;



				}

				// the second has one enemy circle

				else if(fTriangle.getColor() != sTriangle.getColor() && sTriangle.getCounters() == 1){

					draw.deleteCounterAt(fTriangle.getNumberOfTriangle(), 1, color, this.stateBoard);

					if(color == Color.BLUE){

						draw.drawCounterAt(24, 1, Color.GREEN, this.stateBoard);

					}

					else{

						draw.drawCounterAt(1, 1, Color.BLUE, this.stateBoard);

					}

					draw.drawCounterAt(sTriangle.getNumberOfTriangle(), 1, color, this.stateBoard);
					this.movesLeft = this.movesLeft - difference;
					if(this.movesLeft == 0){
						this.endOfMove = true;
					}


					return true; 

				}



				// the second triangle is empty

				else if(sTriangle.getCounters() == 0){


					draw.drawCounterAt(sTriangle.getNumberOfTriangle(), 1, color, this.stateBoard);
					draw.deleteCounterAt(fTriangle.getNumberOfTriangle(), 1, color, this.stateBoard);

					this.movesLeft = this.movesLeft - difference;

					if(this.movesLeft == 0){

						this.endOfMove = true;
					}


					return true;


				}
			}
		}


		return false;






	}


	// checks if the circles are all collected to one square of the board  
	public static boolean checkIfLastStageBlue(ArrayList<TriangleS> state){



		int counterOfCirclesAtBlue = 0;


		Iterator<TriangleS> iteratorBlue = state.iterator();

		for(int j = 1; j <= 24; j++){ // checks for all the circles at the blue side to check if they are 15

			TriangleS triangleBlue = iteratorBlue.next();

			if(triangleBlue.getNumberOfTriangle() >= 1 && triangleBlue.getNumberOfTriangle() <= 18 &&
					triangleBlue.getColor() == Color.BLUE){

				counterOfCirclesAtBlue += triangleBlue.getCounters();
			}

			else{
				continue;
			}

		}

		if(counterOfCirclesAtBlue > 0 ){
			return false;
		}

		else{
			return true;
		}

	}


	public static boolean checkIfLastStageGreen(ArrayList<TriangleS> state, Color green){


		green = Color.GREEN;

		int counterOfCirlesAtGreen = 0;

		Iterator<TriangleS> iteratorGreen = state.iterator();


		for(int i = 1; i <= 24; i++){  // checks the green circles at the green square

			TriangleS triangleGreen = iteratorGreen.next();

			if(triangleGreen.getNumberOfTriangle() >= 7 && triangleGreen.getNumberOfTriangle() <= 24 &&
					triangleGreen.getColor() == green){

				counterOfCirlesAtGreen += triangleGreen.getCounters();	
			}	

			else{
				continue; 
			}
		}




		if( counterOfCirlesAtGreen > 0){
			return false;
		}

		else{
			return true;
		}

	}



	public void laststageCollection(int nFirstDice, int nSecondDice, TriangleS fTriangle, TriangleS sTriangle, Color color,
			ArrayList<TriangleS> state){


		DrawCounter draw = new DrawCounter();


		if(this.movesLeft == 0 && nFirstDice != nSecondDice){

			this.movesLeft = nFirstDice + nSecondDice;
		}

		else if(this.movesLeft == 0 && nFirstDice == nSecondDice){

			this.movesLeft = 2 * (nFirstDice + nSecondDice);

		}



		boolean endOfMove = false; 

		int difference = Math.abs(fTriangle.getNumberOfTriangle() - sTriangle.getNumberOfTriangle());
		int wayOfCollecting = sTriangle.getNumberOfTriangle() - fTriangle.getNumberOfTriangle();


		//when number of triangle is 0
		if(fTriangle.getCounters() > 1   && (difference == nFirstDice || difference == nSecondDice || 
				difference == nFirstDice + nSecondDice) && (this.movesLeft >= nFirstDice || this.movesLeft >= nSecondDice) && fTriangle
				.getColor() == color && (sTriangle.getNumberOfTriangle()) == 0) {

			if((color == Color.GREEN && wayOfCollecting < 0) || (color == Color.BLUE && wayOfCollecting > 0)){


				draw.deleteCounterAt(fTriangle.getNumberOfTriangle(), 1, color, this.stateBoard);

				this.movesLeft = this.movesLeft - difference;

				if(this.movesLeft == 0){

					this.endOfMove = true;
				}


			}
		}





	}


	public boolean checkWinner(ArrayList<TriangleS> state){

		int countsBlue = 0;
		int countsGreen = 0;



		for(TriangleS temp : state){

			if(temp.getColor() == Color.BLUE && temp.getCounters() > 0 ){

				countsBlue += temp.getCounters();

			}

			else if(temp.getColor() == Color.GREEN && temp.getCounters() > 0){

				countsGreen += temp.getCounters();
			}

			else{
				continue;
			}
		}

		if(countsBlue == 0){

			System.out.println("Blue wins");
			return true;

		}

		else if(countsGreen == 0){

			System.out.println("Green wins");
			return true;


		}
		
		else{
			return false;
			
		}










	}












	public static void main(String[] args){

		
		
		
		
		
		
	}

}







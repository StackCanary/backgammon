

	package backgammon.client.ui.shape;
	import java.awt.Color;

	import java.awt.Graphics;
	import java.awt.Point;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;

	import javax.swing.JComponent;

	import backgammon.client.ui.ui.BoardView;

	import code.*;

	public class DiceDraw extends JComponent {
		
		boolean clicked;
		
		public DiceDraw(){
			
			 addMouseListener(new MouseAdapter() {
		            @Override
		            public void mousePressed(MouseEvent e) {
		            	
		            	
		            	Point hey = e.getPoint();
		            	System.out.println(hey);
		            	
		            	if(e.getY() <= 262 && e.getY() >= 242 && e.getX() >= 292 && e.getX() <= 343){
		            	repaint(); 
		            	}
		            }
		            
		        });
			
			
		}
		
		public void paint(Graphics g){
			
		
		
			g.drawRect(285, 210, 25, 25);
			g.drawRect(320, 210, 25, 25);
			g.drawRect(292, 240, 50, 20);
			
			
			Dice dice = new Dice();
			
			dice.roll();
			
			g.drawString(dice.fDice, 295, 227);
			g.drawString(dice.sDice, 329, 227);
			g.drawString("Throw", 298, 255);
		}
		
		
		
		
		
		

	}


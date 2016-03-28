package backgammon.engine.board;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Stream;

import backgammon.client.config.Config.Side;

public class UniversalBoard extends BasicBoard {
	Queue<MoveHolder> queue = new SynchronousQueue<MoveHolder>();
	
	public UniversalBoard(List<TriangleInterface> triangles, Side black) {
		super(triangles, black);
	}

	public void addToQueue(MoveHolder holder) {
		queue.add(holder);
	}
	
	public void gameLoop() {
		
		Thread t = new Thread(
		new Runnable() {
			
			@Override
			public void run() {
				while(!gameOver) {
					MoveHolder holder;
					while((holder = queue.poll()) != null) {
						makeAllMoves(holder);
					}
				}				
			}
			
		});
		
		t.run();
	}
}

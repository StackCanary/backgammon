package backgammon.engine.ai;

import java.util.List;

public interface Scorable {
	
	public int getScore();
	public List<Scorable> getChildren(Scorable o); 

}

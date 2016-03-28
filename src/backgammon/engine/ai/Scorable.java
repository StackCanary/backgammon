package backgammon.engine.ai;

import java.util.List;

import backgammon.client.config.Config.Side;

public interface Scorable {
	
	public int getScore(Side max);

}

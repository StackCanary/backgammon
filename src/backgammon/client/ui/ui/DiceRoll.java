package backgammon.client.ui.ui;

public class DiceRoll {
	
	public int pos1;
	public int end1;
	
	public int pos2;
	public int end2;
	
	public int pos3;
	public int end3;
	
	public int pos4;
	public int end4;
	
	public boolean doubleRoll = false;
	
	
	public DiceRoll(int pos1, int end1, int pos2, int end2) {
		this.pos1 = pos1;
		this.end1 = end1;
		this.pos2 = pos2;
		this.end2 = end2;
	}
	
	
	public DiceRoll(int pos1, int end1, int pos2, int end2, int pos3, int end3, int pos4, int end4) {
		this.pos1 = pos1;
		this.end1 = end1;
		this.pos2 = pos2;
		this.end2 = end2;
		this.pos3 = pos3;
		this.end3 = end3;
		this.pos4 = pos4;
		this.end4 = end4;
		
		doubleRoll = true;
	}



	public String getMessage() {
		String result = null;
		
		if (doubleRoll) {
			result = String.format(
					"(%d | %d), (%d | %d), (%d | %d), (%d | %d);", pos1, end1, pos2, end2, pos3, end3, pos4, end4
					);
		} else {
			result = String.format(
					"(%d | %d), (%d | %d);", pos1, end1, pos2, end2
					);
		}
		return result;
	}
}

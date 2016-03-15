package backgammon.client.config;

import java.awt.Color;

public class Config {
	public enum Side {
		white (Color.green),
		black (Color.blue),
		chance (Color.cyan);

		public final Color color;
		
		Side(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return this.color;
		}
		
		public static Color enumToColor(Side side) {
			return side.getColor();
		}
	}
}

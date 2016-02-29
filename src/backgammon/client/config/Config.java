package backgammon.client.config;

import java.awt.Color;

public class Config {
	public enum Side {
		white (Color.orange),
		black (Color.black);

		public final Color color;
		
		Side(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return this.color;
		}
		
		public static Color enumToColor(Side side) {
			if (side == white) {
				return Color.orange;
			}
			
			if (side == black) {
				return Color.blue;
			}
			
			return null;
		}
	}
}

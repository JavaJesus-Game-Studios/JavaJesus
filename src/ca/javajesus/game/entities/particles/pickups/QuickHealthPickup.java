package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.game.Game;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class QuickHealthPickup extends Pickup {

	private static final long serialVersionUID = -5640816578680329613L;

	public QuickHealthPickup(Level level, int x, int y) {
		super(level, x, y, null, new int[] { 0xFFFFFFFF, 0xFF990000, 0xFFFF0000 }, 0, 5, 1);

	}
	
	public void render(Screen screen) {
		screen.render((int) this.x, (int) this.y, tileNumber, color, 1, 1,
				sheet);
		if (bounds.intersects(Game.player.getBounds())) {
			Game.player.setHealth(Game.player.getHealth() + 20);
			level.remEntity(this);
		}
	}

}

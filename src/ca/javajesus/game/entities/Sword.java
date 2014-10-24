package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Sword extends Entity {
	
	protected SpriteSheet sheet = SpriteSheet.swords;
	protected int tileNumber;
	protected int color;
	protected int width;
	protected int height;
	private final Rectangle hitBox = new Rectangle(10, 10);

	public Sword(Level level, int tileNumber, int color, double x, double y, Player player) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public void tick() {
		
	}

	public void render(Screen screen) {
		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
		hitBox.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {
			if (entity instanceof Mob) {
				if (hitBox.intersects(((Mob) entity).hitBox)) {
					((Mob) entity).health--;
				}
			}

		}
	}

}

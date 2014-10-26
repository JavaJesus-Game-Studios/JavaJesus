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
	protected int degrees;
	protected int tickCount;

	public Sword(Level level, int tileNumber, int color, double x, double y,
			Player player) {
		super(level);
		this.tileNumber = tileNumber;
		this.color = color;
		this.x = x;
		this.y = y - 10;

	}

	public void tick() {

		tickCount++;
		
		this.x += 10 * Math.sin(tickCount * 3.0);
		this.y += (1/12.0);

		if (tickCount > 60) {
			level.remEntity(this);
		}

	}

	public void swing() {

	}

	public void render(Screen screen) {

		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
		hitBox.setLocation((int) this.x, (int) this.y);
		for (Entity entity : level.getEntities()) {
			if (entity instanceof Mob) {
				if (hitBox.intersects(((Mob) entity).hitBox)) {
					// ((Mob) entity).health--;
				}
			}

		}
	}

}

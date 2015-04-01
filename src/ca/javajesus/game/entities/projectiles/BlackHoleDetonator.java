package ca.javajesus.game.entities.projectiles;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.particles.BlackHole;
import ca.javajesus.game.entities.vehicles.Vehicle;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class BlackHoleDetonator extends Projectile {

	public BlackHoleDetonator(Level level, double x, double y, double xPos,
			double yPos, Mob mob, double damage) {
		super(level, 2, 1, SpriteSheet.particles.boxes, new int[] { 0xFF000000,
				0xFF000000, 0xFFFFFF00 }, x, y, 6, xPos, yPos, mob, damage);
		sound.fire(sound.gunshot);
	}

	public BlackHoleDetonator(Level level, double x, double y, int direction,
			Mob mob, double damage) {
		super(level, 2, 1, SpriteSheet.particles.boxes, new int[] { 0xFF000000,
				0xFF000000, 0xFFFFFF00 }, x, y, 6, direction, mob, damage);
		sound.fire(sound.gunshot);
	}

	public void render(Screen screen) {

		renderOnTop = true;

		if (hasCollided((int) x, (int) y)) {
			level.remEntity(this);
			level.addEntity(new BlackHole(level, x, y - 8));
			return;
		}

		this.y += speed * yPoint;
		this.x += speed * xPoint;

		bounds.setLocation((int) this.x - (this.width / 2), (int) this.y
				- (this.height / 2));
		for (Entity entity : level.getEntities()) {
			if (entity instanceof SolidEntity) {
				if (bounds.intersects(((SolidEntity) entity).getBounds())) {
					level.remEntity(this);
					level.addEntity(new BlackHole(level, x, y - 8));
					return;
				} else if (bounds.intersects(((SolidEntity) entity).shadow)) {
					renderOnTop = false;
				}
			}
			if (entity instanceof Mob) {
				Mob mobs = (Mob) entity;
				if (bounds.intersects(mobs.getBounds())) {
					if (mobs != mob) {
						if (mobs instanceof Vehicle) {
							mobs.damage((int) damage, (int) damage + 4);
							level.remEntity(this);
							level.addEntity(new BlackHole(level, x, y - 8));
						} else if (!mobs.isDead()) {
							mobs.damage((int) damage, (int) damage + 4);
							level.remEntity(this);
							level.addEntity(new BlackHole(level, x, y - 8));
							if (mobs.getHealth() < 0 && mob instanceof Player) {
								((Player) mob).score += 10;
							}
						}
					}
				}
			}

		}
		screen.render((int) this.x, (int) this.y, tileNumber, color, 1, 1,
				sheet);
	}

}

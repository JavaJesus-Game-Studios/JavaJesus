package ca.javajesus.game.entities.particles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class HealthBar extends Particle {

	private int yOffset;
	private int xOffset;
	private int yChange;
	private int tickCount;
	private double health;
	private double startHealth;
	private final double SEGMENT = 1 / 13.0;
	public int x, y;

	private Mob mob;

	public HealthBar(Level level, int x, int y, Mob mob) {
		super(level, 2 * SpriteSheet.particles.boxes, new int[] { 0xFF111111,
				0xFF000000, 0xFFDD0000 }, x, y);
		this.mob = mob;
		this.yOffset = mob.getHeight() / 2;
		this.xOffset = mob.getWidth() / 2;
		this.health = mob.getHealth();
		this.startHealth = mob.getStartHealth();
	}

	public void render(Screen screen) {

		this.x = mob.getX();
		this.y = mob.getY();

		screen.render(this.x - xOffset, this.y + yOffset, tileNumber + yChange
				* sheet.boxes, color, 0, sheet);
		screen.render(this.x - xOffset + 8, this.y + yOffset, tileNumber + 1
				+ yChange * sheet.boxes, color, 0, sheet);
	}

	public void tick() {
		this.health = mob.getHealth();
		updateHealthBar();
	}

	public void updateHealthBar() {

		if (mob.isOnFire()) {
			if (tickCount % 10 == 0)
				mob.damage(1, 2);
			tickCount++;
		}

		if (tickCount == 500 && mob.isOnFire()) {
			mob.setOnFire(false);
			tickCount = 0;
		}
		
		if (((double) health / startHealth) >= 1) {
			yChange = 0;
			this.color[2] = 0xFF0079E0;
		} else if (((double) health / startHealth) >= 1 - SEGMENT) {
			yChange = 1;
			this.color[2] = 0xFF0079E0;
		} else if ((double) health / startHealth >= 1 - 2 * SEGMENT) {
			yChange = 2;
			this.color[2] = 0xFF0079E0;
		} else if ((double) health / startHealth >= 1 - 3 * SEGMENT) {
			yChange = 3;
			this.color[2] = 0xFF0079E0;
		} else if ((double) health / startHealth >= 1 - 4 * SEGMENT) {
			yChange = 4;
			this.color[2] = 0xFFFF6000;
		} else if ((double) health / startHealth >= 1 - 5 * SEGMENT) {
			yChange = 5;
			this.color[2] = 0xFFFF6000;
		} else if (((double) health / startHealth) >= 1 - 6 * SEGMENT) {
			yChange = 6;
			this.color[2] = 0xFFFF6000;
		} else if ((double) health / startHealth >= 1 - 7 * SEGMENT) {
			yChange = 7;
			this.color[2] = 0xFFFF6000;
		} else if ((double) health / startHealth >= 1 - 8 * SEGMENT) {
			yChange = 8;
			this.color[2] = 0xFFFF6000;
		} else if (((double) health / startHealth) >= 1 - 9 * SEGMENT) {
			yChange = 9;
			this.color[2] = 0xFFE50000;
		} else if (((double) health / startHealth) >= 1 - 10 * SEGMENT) {
			yChange = 10;
			this.color[2] = 0xFFE50000;
		} else if (((double) health / startHealth) >= 1 - 11 * SEGMENT) {
			yChange = 11;
			this.color[2] = 0xFFE50000;
		} else {
			yChange = 12;
			this.color[2] = 0xFFE50000;
		}
		if (health <= 0) {
			level.remEntity(this);
			mob.kill();
		}
	}
}
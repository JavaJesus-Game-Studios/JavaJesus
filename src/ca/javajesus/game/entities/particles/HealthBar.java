package ca.javajesus.game.entities.particles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class HealthBar extends Particle {

	private int yOffset;
	private int xOffset = 0;
	private int tickCount = 0;
	private double health;
	private double startHealth;
	private int yChange;
	public boolean renderOnTop = false;

	private Mob mob;

	public HealthBar(Level level, int tileNumber, double x, double y, Mob mob,
			int yChange) {
		super(level, tileNumber, Colors.get(-1, 111, -1, 400), x, y);
		this.mob = mob;
		this.yOffset = 14;
		this.health = mob.getHealth();
		this.startHealth = mob.getStartHealth();
		this.yChange = yChange;
	}

	public HealthBar(Level level, int tileNumber, double x, double y, Mob mob,
			int yChange, int yOffset) {
		super(level, tileNumber, Colors.get(-1, 111, -1, 400), x, y);
		this.mob = mob;
		this.yOffset = yOffset;
		this.yChange = yChange;
	}

	public void render(Screen screen) {

		this.x = mob.getX() - xOffset / 2 + 1;
		this.y = mob.getY() + yChange;

		screen.render(this.x + 3, this.y, tileNumber + yOffset * 32, color, 1,
				1, sheet);
		screen.render(this.x - 5, this.y, tileNumber + 1 + yOffset * 32, color,
				1, 1, sheet);
	}

	public void tick() {
		this.health = mob.getHealth();
		updateHealthBar();
	}

	public void setOffset(int yTileOffset) {
		this.tileNumber = yTileOffset * 32;
	}

	public void updateHealthBar() {

		mob.checkTile(this.x, this.y);

		if (mob.isOnFire()) {
			if (tickCount % 10 == 0)
				mob.damage(0.1, 0.3);
			tickCount++;
		}

		if (tickCount == 500 && mob.isOnFire()) {
			mob.setOnFire(false);
			tickCount = 0;
		}

		if ((health > 11 * startHealth / 12.0) && (health <= startHealth)) {
			setOffset(2);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#0079e0"));
			xOffset = 1;
		} else if ((health > 10 * startHealth / 12.0)
				&& (health <= 11 * startHealth / 12.0)) {
			setOffset(3);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#0079e0"));
			xOffset = 2;
		} else if ((health > 9 * startHealth / 12.0)
				&& (health <= 10 * startHealth / 12.0)) {
			setOffset(4);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#0079e0"));
			xOffset = 3;
		} else if ((health > 8 * startHealth / 12.0)
				&& (health <= 9 * startHealth / 12.0)) {
			setOffset(5);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#ff6000"));
			xOffset = 4;
		} else if ((health > 7 * startHealth / 12.0)
				&& (health <= 8 * startHealth / 12.0)) {
			setOffset(6);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#ff6000"));
			xOffset = 5;
		} else if ((health > 6 * startHealth / 12.0)
				&& (health <= 7 * startHealth / 12.0)) {
			setOffset(7);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#ff6000"));
			xOffset = 6;
		} else if ((health > 5 * startHealth / 12.0)
				&& (health <= 6 * startHealth / 12.0)) {
			setOffset(8);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#ff6000"));
			xOffset = 7;
		} else if ((health > 4 * startHealth / 12.0)
				&& (health <= 5 * startHealth / 12.0)) {
			setOffset(9);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#ff6000"));
			xOffset = 8;
		} else if ((health > 3 * startHealth / 12.0)
				&& (health <= 4 * startHealth / 12.0)) {
			setOffset(10);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#e50000"));
			xOffset = 9;
		} else if ((health > 2 * startHealth / 12.0)
				&& (health <= 3 * startHealth / 12.0)) {
			setOffset(11);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#e50000"));
			xOffset = 10;
		} else if ((health > 100 / 12.0) && (health <= 200 / 12.0)) {
			setOffset(12);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#e50000"));
			;
			xOffset = 11;
		} else {
			setOffset(13);
			this.color = Colors.get(-1, 111, -1, Colors.fromHex("#e50000"));
			xOffset = 12;
		}
		if (health <= 0) {
			level.remEntity(this);
			mob.kill();
		}
	}
}
package ca.javajesus.items;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Gun extends Item {

	public int gunType;
	public int clipSize;
	public double ammo;
	public boolean isReloading = false;
	private int RELOAD_TIME;
	private int reloadTicks = 0;
	private int FIRE_RATE;
	private int fireTicks = 1;
	protected boolean canFire = true;
	protected int damage;
	public int playerOffset;

	public Gun(String name, int id, int xTile, int yTile, int color,
			String description, int gunHUDType, int xPlayerSheet, int clipSize, int rate,
			int reload, int damage) {
		super(name, id, xTile, yTile, color, description);
		this.gunType = gunHUDType;
		this.playerOffset = xPlayerSheet;
		this.clipSize = clipSize;
		this.ammo = clipSize;
		this.RELOAD_TIME = reload * 10;
		this.FIRE_RATE = rate * 10;
		this.damage = damage;
	}

	public void tick() {
		if (isReloading) {
			if (ammo < clipSize)
				ammo += (double) clipSize / RELOAD_TIME;
			else {
				isReloading = false;
				reloadTicks = 0;
				ammo = clipSize;
				return;
			}
			if (reloadTicks >= RELOAD_TIME) {
				isReloading = false;
				reloadTicks = 0;
				ammo = clipSize;
				return;
			}
			reloadTicks++;
		}

		if (fireTicks % FIRE_RATE == 0) {
			canFire = true;
		}

		fireTicks++;
	}

	public void reload() {
		isReloading = true;
	}

	public void fire(Level level, double x, double y, int dir, Player player) {
		if (ammo > 0 && !isReloading && canFire) {
			level.addEntity(new Bullet(level, x, y, dir, player, damage));
			ammo--;
			canFire = false;
		}
	}

	public void render(Screen screen) {
		screen.render(xOffset, yOffset, xTile + yTile * 32, color, 0, 1,
				SpriteSheet.items);
		screen.render(xOffset, yOffset, xTile + 1 + yTile * 32, color, 0, 1,
				SpriteSheet.items);
		screen.render(xOffset, yOffset, xTile + (yTile + 1) * 32, color, 0, 1,
				SpriteSheet.items);
		screen.render(xOffset, yOffset, xTile + 1 + (yTile + 1) * 32, color, 0,
				1, SpriteSheet.items);
	}

}

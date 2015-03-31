package ca.javajesus.items;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.projectiles.Arrow;
import ca.javajesus.game.entities.projectiles.BlackHoleDetonator;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.entities.projectiles.FireBall;
import ca.javajesus.game.entities.projectiles.Laser;
import ca.javajesus.game.entities.projectiles.Missile;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
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
	private Ammo type;

	public enum Ammo {
		BULLET, ARROW, FIREBALL, LASER, MISSILE, BLACKHOLE, FLAMETHROWER, SHELL;
	}

	public Gun(String name, int id, int xTile, int yTile, int[] color,
			String description, int gunHUDType, int xPlayerSheet, int clipSize,
			int rate, int reload, int damage, Ammo type) {
		super(name, id, xTile, yTile, color, description);
		this.gunType = gunHUDType;
		this.playerOffset = xPlayerSheet;
		this.clipSize = clipSize;
		this.ammo = clipSize;
		this.RELOAD_TIME = reload * 10;
		this.FIRE_RATE = rate * 10;
		this.damage = damage;
		this.type = type;
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
			switch (type) {
			case BULLET:
				level.addEntity(new Bullet(level, x, y, dir, player, damage));
				break;
			case ARROW:
				level.addEntity(new Arrow(level, x, y, dir, player, damage));
				break;
			case FIREBALL:
				level.addEntity(new FireBall(level, x, y, dir, player));
				break;
			case LASER:
				level.addEntity(new Laser(level, x, y, dir, player, damage));
				break;
			case MISSILE:
				level.addEntity(new Missile(level, x, y, dir, player, damage));
				break;
			case BLACKHOLE:
				level.addEntity(new BlackHoleDetonator(level, x, y, dir, player, damage));
				break;
			case FLAMETHROWER:
			    level.addEntity(new FireBall(level, x, y, dir, player));
			    level.addEntity(new FireBall(level, x, y, dir, player));
			    level.addEntity(new FireBall(level, x, y, dir, player));
			    level.addEntity(new FireBall(level, x, y, dir, player));
			    level.addEntity(new FireBall(level, x, y, dir, player));
                break;
			case SHELL:
				level.addEntity(new Bullet(level, x, y, dir, player, damage));
				level.addEntity(new Bullet(level, x, y, dir, player, damage));
				level.addEntity(new Bullet(level, x, y, dir, player, damage));
				level.addEntity(new Bullet(level, x, y, dir, player, damage));
				break;
			}
			ammo--;
			canFire = false;
		}
	}

	public void render(Screen screen) {
		screen.render(0, 0, xTile + yTile * 32, color, 0, 1, SpriteSheet.items);
		screen.render(0, 0, xTile + 1 + yTile * 32, color, 0, 1,
				SpriteSheet.items);
		screen.render(0, 0, xTile + (yTile + 1) * 32, color, 0, 1,
				SpriteSheet.items);
		screen.render(0, 0, xTile + 1 + (yTile + 1) * 32, color, 0, 1,
				SpriteSheet.items);
	}

}

package ca.javajesus.items;

import javax.sound.sampled.Clip;

import ca.javajesus.game.Game;
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

	private static final long serialVersionUID = 2308714802801627285L;
	
	public int gunType;
	public int clipSize;
	public int ammo;
	public int availableAmmo;
	public boolean isReloading = false;
	private int RELOAD_TIME;
	private int reloadTicks = 0;
	private int FIRE_RATE;
	private int fireTicks = 1;
	protected boolean canFire = true;
	protected int damage;
	public int playerOffset;
	private Ammo type;
	private Clip clip;

	public enum Ammo {
		BULLET, ARROW, FIREBALL, LASER, MISSILE, BLACKHOLE, FLAMETHROWER, SHELL;
	}

	public Gun(String name, int id, int xTile, int yTile, int[] color,
			String description, int gunHUDType, int yPlayerSheet, int clipSize,
			int rate, int reload, int damage, Ammo type, Clip clip) {
		super(name, id, xTile, yTile, color, description);
		this.gunType = gunHUDType;
		this.playerOffset = yPlayerSheet;
		this.clipSize = clipSize;
		this.ammo = clipSize;
		this.RELOAD_TIME = reload * 10;
		this.FIRE_RATE = rate * 10;
		this.damage = damage;
		this.type = type;
		this.clip = clip;
	}

	public void tick() {
		fireTicks++;
		if (isReloading && reloadTicks % 10 == 0) {
			if (ammo < clipSize && availableAmmo > 0) {
				int num = 1;
				ammo += num;
				availableAmmo -= num;
			} else {
				isReloading = false;
				reloadTicks = 0;
				return;
			}
			if (reloadTicks >= RELOAD_TIME) {
				isReloading = false;
				reloadTicks = 0;
				return;
			}
		}

		if (isReloading) {
			reloadTicks++;
		}

		if (fireTicks % FIRE_RATE == 0) {
			canFire = true;
		}

	}

	public void reload() {
		isReloading = true;

		switch (type) {
		case BULLET:
			if (Game.player.inventory.items.contains(Item.revolverAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.revolverAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		case ARROW:
			if (Game.player.inventory.items.contains(Item.arrowAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.arrowAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		case FIREBALL:
			availableAmmo = 100;
			break;
		case LASER:
			if (Game.player.inventory.items.contains(Item.laserAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.laserAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		case MISSILE:
			if (Game.player.inventory.items.contains(Item.revolverAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.revolverAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		case BLACKHOLE:
			if (Game.player.inventory.items.contains(Item.revolverAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.revolverAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		case FLAMETHROWER:
			if (Game.player.inventory.items.contains(Item.revolverAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.revolverAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		case SHELL:
			if (Game.player.inventory.items.contains(Item.revolverAmmo)) {
				for (Item e : Game.player.inventory.items) {
					if (e.equals(Item.revolverAmmo)) {
						availableAmmo = e.amount;
						e.amount -= clipSize;
						break;
					}
				}
			}
			break;
		}
	}

	public void fire(Level level, double x, double y, int dir, Player player) {
		if (ammo > 0 && !isReloading && canFire) {
			switch (type) {
			case BULLET:
				level.addEntity(new Bullet(level, x, y, dir, player, damage,
						clip));
				break;
			case ARROW:
				level.addEntity(new Arrow(level, x, y, dir, player, damage,
						clip));
				break;
			case FIREBALL:
				level.addEntity(new FireBall(level, x, y, dir, player));
				break;
			case LASER:
				level.addEntity(new Laser(level, x, y, dir, player, damage,
						clip));
				break;
			case MISSILE:
				level.addEntity(new Missile(level, x, y, dir, player, damage,
						clip));
				break;
			case BLACKHOLE:
				level.addEntity(new BlackHoleDetonator(level, x, y, dir,
						player, damage));
				break;
			case FLAMETHROWER:
				level.addEntity(new FireBall(level, x, y, dir, player));
				level.addEntity(new FireBall(level, x, y, dir, player));
				level.addEntity(new FireBall(level, x, y, dir, player));
				level.addEntity(new FireBall(level, x, y, dir, player));
				level.addEntity(new FireBall(level, x, y, dir, player));
				break;
			case SHELL:
				level.addEntity(new Bullet(level, x, y, dir, player, damage,
						clip));
				level.addEntity(new Bullet(level, x, y, dir, player, damage,
						clip));
				level.addEntity(new Bullet(level, x, y, dir, player, damage,
						clip));
				level.addEntity(new Bullet(level, x, y, dir, player, damage,
						clip));
				break;
			}
			ammo--;
			canFire = false;
			fireTicks = 0;
		}
	}

	public void render(Screen screen) {
		screen.render(0, 0, xTile + yTile * SpriteSheet.items.boxes, color, 0,
				1, SpriteSheet.items);
		screen.render(0, 0, xTile + 1 + yTile * SpriteSheet.items.boxes, color,
				0, 1, SpriteSheet.items);
		screen.render(0, 0, xTile + (yTile + 1) * SpriteSheet.items.boxes,
				color, 0, 1, SpriteSheet.items);
		screen.render(0, 0, xTile + 1 + (yTile + 1) * SpriteSheet.items.boxes,
				color, 0, 1, SpriteSheet.items);
	}

}

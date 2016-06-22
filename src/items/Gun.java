package items;

import javax.sound.sampled.Clip;

import game.SoundHandler;
import game.entities.Player;
import game.entities.projectiles.Arrow;
import game.entities.projectiles.BlackHoleDetonator;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.FireBall;
import game.entities.projectiles.Laser;
import game.entities.projectiles.Missile;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;
import utility.Direction;

/*
 * A gun is an item that also allows a player to shoot
 */
public class Gun extends Item {

	private static final long serialVersionUID = 2308714802801627285L;

	// max amount of ammo
	private int clipSize;

	// current amount of ammo
	private int ammo;

	// spare bullets that the player can reload with
	private int availableAmmo;

	// whether or not the player is reloading
	private boolean isReloading;

	// the reload time
	private int RELOAD_TIME;

	// ticks inbetween each reload
	private int reloadTicks;

	// fire rate of the gun
	private int FIRE_RATE;

	// ticks between each shot fired
	private int fireTicks = 1;

	// whether or not the gun can be fired
	private boolean canFire;

	// damage per shot fired
	private int damage;

	// offset on the spritesheet for player animation
	private int playerOffset;

	// type of ammo
	private Ammo type;

	// sound the gun will make
	private transient Clip clip;

	/*
	 * The different types of ammo available
	 */
	public enum Ammo {
		BULLET, ARROW, FIREBALL, LASER, MISSILE, BLACKHOLE, FLAMETHROWER, SHELL;
	}

	/**
	 * Creates a gun
	 * 
	 * @param name
	 *            the name of the gun
	 * @param id
	 *            the unique ID of the gun
	 * @param xTile
	 *            the xtile on the ITEM spritesheet
	 * @param yTile
	 *            the y tile on the ITEM spritesheet
	 * @param color
	 *            the color of the gun
	 * @param description
	 *            the description of the gun
	 * @param yPlayerSheet
	 *            the y tile on the PLAYER spritesheet
	 * @param clipSize
	 *            the maximum ammo clip size
	 * @param rate
	 *            the rate of fire
	 * @param reload
	 *            the rate of reload
	 * @param damage
	 *            the damage per shot
	 * @param type
	 *            the type of ammo used
	 * @param clip
	 *            the clip sound
	 */
	public Gun(String name, int id, int xTile, int yTile, int[] color, String description, int yPlayerSheet,
			int clipSize, int rate, int reload, int damage, Ammo type, Clip clip) {
		super(name, id, xTile, yTile, color, description);
		this.playerOffset = yPlayerSheet;
		this.clipSize = clipSize;
		this.ammo = clipSize;
		this.RELOAD_TIME = reload * 10;
		this.FIRE_RATE = rate * 10;
		this.damage = damage;
		this.type = type;
		this.clip = clip;
	}

	/**
	 * Updates the gun
	 */
	public void tick() {

		fireTicks++;

		// increase the count displayed while reloading
		if (isReloading && reloadTicks % 10 == 0) {
			if (ammo < clipSize && availableAmmo > 0) {
				ammo++;
				availableAmmo--;
			} else {
				isReloading = false;
				reloadTicks = 0;
				return;
			}

			// reloading is done!
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

	/**
	 * Reloads the gun
	 */
	public void reload(Inventory inven) {

		isReloading = true;

		Item bulletType = null;

		switch (type) {
		case BULLET:
			bulletType = Item.revolverAmmo;
			break;
		case ARROW:
			bulletType = Item.arrowAmmo;
			break;
		case FIREBALL:
			// TODO for mobs?
			availableAmmo = 100;
			break;
		case LASER:
			bulletType = Item.laserAmmo;
			break;
		case MISSILE:
			// TODO implement missile ammo
			bulletType = Item.revolverAmmo;
			break;
		case BLACKHOLE:
			// TODO implement red matter ammo
			availableAmmo = 100;
			break;
		case FLAMETHROWER:
			// TODO implement lighter fluid ammo
			bulletType = Item.revolverAmmo;
			break;
		case SHELL:
			// TODO shells
			bulletType = Item.revolverAmmo;
			break;
		}

		// get the right available ammo
		if (bulletType != null && inven.getMisc().contains(bulletType)) {
			for (Item e : inven.getMisc()) {
				if (e.equals(bulletType)) {
					availableAmmo = e.getQuantity();
					e.use(clipSize);
					break;
				}
			}
		}
	}

	/**
	 * Initializes the sounds of the gun
	 */
	public void initSound() {
		switch (getName()) {
		case "Shotgun":
			this.clip = SoundHandler.shotgun;
			break;
		case "Assault Rifle":
			this.clip = SoundHandler.assaultRifle;
			break;
		case "Laser Revolver":
			this.clip = SoundHandler.laser;
			break;
		default:
			this.clip = SoundHandler.revolver;
			break;
		}
	}

	/**
	 * Fires the gun
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coord
	 * @param dir
	 *            the direction
	 * @param player
	 *            the player shooting the gun
	 */
	public void fire(int x, int y, Direction dir, Player player) {

		Level level = player.getLevel();

		if (ammo > 0 && !isReloading && canFire) {
			switch (type) {
			case BULLET:
				level.add(new Bullet(level, x, y, dir, player, damage, clip));
				break;
			case ARROW:
				level.add(new Arrow(level, x, y, dir, player, damage));
				break;
			case FIREBALL:
				level.add(new FireBall(level, x, y, dir, player, damage));
				break;
			case LASER:
				level.add(new Laser(level, x, y, dir, player, damage));
				break;
			case MISSILE:
				level.add(new Missile(level, x, y, dir, player, damage, clip));
				break;
			case BLACKHOLE:
				level.add(new BlackHoleDetonator(level, x, y, dir, player, damage));
				break;
			case FLAMETHROWER:
				level.add(new FireBall(level, x, y, dir, player, damage));
				break;
			case SHELL:
				level.add(new Bullet(player.getLevel(), x, y, dir, player, damage, clip));
				level.add(new Bullet(player.getLevel(), x + 2, y + 2, dir, player, damage, clip));
				level.add(new Bullet(player.getLevel(), x - 2, y - 2, dir, player, damage, clip));
				break;
			}
			ammo--;
			canFire = false;
			fireTicks = 0;
		}
	}

	/**
	 * Displays the gun in the inventory
	 */
	public void render(final Screen screen) {
		screen.render(0, 0, xTile + yTile * SpriteSheet.items.boxes, getColor(), SpriteSheet.items);
		screen.render(0, 0, xTile + 1 + yTile * SpriteSheet.items.boxes, getColor(), SpriteSheet.items);
		screen.render(0, 0, xTile + (yTile + 1) * SpriteSheet.items.boxes, getColor(), SpriteSheet.items);
		screen.render(0, 0, xTile + 1 + (yTile + 1) * SpriteSheet.items.boxes, getColor(), SpriteSheet.items);
	}

	/**
	 * @return the audio clip used
	 */
	public final Clip getClip() {
		return clip;
	}

	/**
	 * @return the current ammo
	 */
	public final int getCurrentAmmo() {
		return ammo;
	}

	/**
	 * @return the max ammo
	 */
	public final int getClipSize() {
		return clipSize;
	}

	/**
	 * @return true if the gun is reloading
	 */
	public boolean isReloading() {
		return isReloading;
	}

	/**
	 * @return the player offset in the spritesheet
	 */
	public int getPlayerOffset() {
		return playerOffset;
	}

}

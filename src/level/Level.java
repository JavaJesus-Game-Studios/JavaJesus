package level;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import game.Game;
import game.SoundHandler;
import game.entities.Entity;
import game.entities.Mob;
import game.entities.Player;
import game.entities.Spawner;
import game.entities.particles.HealthBar;
import game.entities.projectiles.Projectile;
import game.entities.vehicles.Vehicle;
import game.graphics.JJFont;
import game.graphics.Screen;
import level.tile.Tile;
import save.SaveFile;

/*
 * A Level contains a set of tiles that can be displayed on the screen
 * It also holds a set of all entities that interact with each other
 */
public abstract class Level implements Serializable {

	private static final long serialVersionUID = -5963535226522522466L;

	// all tiles on each level
	private int[] tiles;

	// width and height in terms of tiles
	private int width, height;

	// instance of the player on the level
	private Player player;

	// list of all entities on the map
	private List<Entity> entities = new ArrayList<Entity>(Game.ENTITY_LIMIT);

	// list of all mobs on the map
	private List<Mob> mobs = new ArrayList<Mob>(Game.ENTITY_LIMIT);

	// image path to load a level
	private String imagePath;

	// where the player will go when changing levels here
	private Point spawnPoint;

	// where the level data is saved
	private SaveFile saveData;

	// whether or not entities have been added on the map
	private boolean initEntities;

	// whether or not the map is loaded
	private boolean isLoaded;

	// name of the level
	private String name;

	// the range of how many entities to render/tick on the screen
	public static final Rectangle renderRange = new Rectangle(500, 500);

	/**
	 * Creates a level from the specified image path
	 * 
	 * @param imagePath
	 *            the image path
	 * @param loadNow
	 *            whether or not to load it now
	 * @param name
	 *            the name of thsi levvel
	 */
	public Level(String imagePath, boolean loadNow, String name) {
		this.name = name;
		this.saveData = new SaveFile(name);
		spawnPoint = new Point(0, 0);
		if (imagePath != null) {
			this.imagePath = imagePath;
			if (loadNow) {
				isLoaded = true;
				loadLevelFromFile();
			}
		} else {
			throw new RuntimeException("Level path is null");
		}
	}

	/**
	 * Creates a randomly generated level
	 * 
	 * @param width
	 *            the tiles in the width
	 * @param height
	 *            the tiles in the height
	 * @param loadNow
	 *            whether or not to load it now
	 * @param name
	 *            the name of this level
	 */
	public Level(int width, int height, boolean loadNow, String name) {
		spawnPoint = new Point(0, 0);
		this.width = width;
		this.height = height;
		this.name = name;
		this.saveData = new SaveFile(name);
		tiles = new int[width * height];
		if (loadNow) {
			isLoaded = true;
			generateLevel();
		}
	}

	protected abstract void initNPCPlacement();

	protected abstract void initSpawnerPlacement();

	protected abstract void initChestPlacement();

	protected abstract void otherEntityPlacement();

	protected abstract void initMapTransporters();

	/**
	 * Loads a level
	 */
	public void load() {
		if (!isLoaded) {
			if (imagePath != null) {
				loadLevelFromFile();
			} else {
				generateLevel();
			}
			loadEntities();
			isLoaded = true;
		}
	}

	/**
	 * TODO create an instance variable
	 * 
	 * @return the background music for this level
	 */
	public Clip getBackgroundMusic() {

		return SoundHandler.background1;
	}

	/**
	 * Loads an image from the file
	 */
	private void loadLevelFromFile() {
		try {
			// load the file
			BufferedImage image = ImageIO.read(Level.class.getResource(imagePath));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];

			// get the tile colors
			int[] tileColors = image.getRGB(0, 0, width, height, null, 0, width);

			// initialize the tiles
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					tileCheck: for (Tile t : Tile.tiles) {
						if (t != null && t.getLevelColor() == tileColors[x + y * width]) {
							if (t == Tile.GRASS) {
								t = Tile.GRASS();
							} else if (t == Tile.WASTELAND_GROUND1) {
								t = Tile.CONCRETE();
							}
							this.tiles[x + y * width] = t.getId();
							break tileCheck;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates a random level
	 */
	protected void generateLevel() {

		// fill it with grass and stone
		// usually overridden in another class
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x * y % 10 < 10) {
					tiles[x + y * width] = Tile.GRASS.getId();
				} else {
					tiles[x + y * width] = Tile.STONE.getId();

				}

			}
		}

	}

	/**
	 * Changes a tile on the map
	 * 
	 * @param x
	 *            the x coord of the tile
	 * @param y
	 *            the y coord of the tile
	 * @param newTile
	 *            the tile to replace it with
	 */
	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x + y * width] = newTile.getId();
	}

	/**
	 * Removes dead mobs from the level
	 */
	public void clear() {
		for (Mob m : mobs) {
			if (m.isDead())
				remove(m);
		}
	}

	public void tick() {

		for (Entity e : this.getEntities()) {
			if (!e.getBounds().intersects(renderRange)) {
				continue;
			}
			if (e instanceof Mob) {
				if (!((Mob) e).isDead())
					e.tick();
			} else
				e.tick();
		}

		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();
		}
	}

	public void renderTile(Screen screen, int xOffset, int yOffset) {
		if (xOffset < 0)
			xOffset = 0;
		if (xOffset > ((width << 3) - screen.width))
			xOffset = ((width << 3) - screen.width);
		if (yOffset < 0)
			yOffset = 0;
		if (yOffset > ((height << 3) - screen.height))
			yOffset = ((height << 3) - screen.height);

		screen.setOffset(xOffset, yOffset);

		for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}

		}
	}

	public void renderEntities(Screen screen) {

		renderRange.setLocation(Game.player.getX() - 250, Game.player.getY() - 250);

		for (Entity entity : this.getEntities()) {
			if (!entity.getBounds().intersects(renderRange)) {
				continue;
			}
			if (entity instanceof Projectile) {
				Projectile p = (Projectile) entity;
				if (!p.renderOnTop) {
					p.render(screen);
				}
			}
			if (entity instanceof Mob) {
				Mob m = (Mob) entity;
				if (!m.renderOnTop()) {
					m.render(screen);
					if (m.getHealthBar() != null && !m.isDead())
						m.getHealthBar().render(screen);
				}
			}
		}
		for (Entity e : this.getEntities()) {
			if (!e.getBounds().intersects(renderRange)) {
				continue;
			}
			if (!(e instanceof Mob || e instanceof HealthBar || e instanceof Projectile)) {
				e.render(screen);
			}
		}
		for (Entity entity : this.getEntities()) {
			if (!entity.getBounds().intersects(renderRange)) {
				continue;
			}
			if (entity instanceof Projectile) {
				Projectile p = (Projectile) entity;
				if (p.renderOnTop) {
					p.render(screen);
				}
			}
			if (entity instanceof Mob) {
				Mob m = (Mob) entity;
				if (m.renderOnTop()) {
					m.render(screen);
					if (m.getHealthBar() != null && !m.isDead())
						m.getHealthBar().render(screen);
				}
			}
		}
	}

	public void renderFont(String msg, Screen screen, int x, int y, int[] color, int scale) {
		JJFont.render(msg, screen, x, y, color, scale);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return Tile.VOID;
		return Tile.tiles[tiles[x + y * width]];

	}

	public void addEntity(Entity entity) {
		if (!(entity instanceof Player)) {
			this.entities.add(0, entity);
		} else {
			this.entities.add(entity);
		}
		if (entity instanceof Mob) {
			if (entity instanceof Vehicle) {
				this.mobs.add((Vehicle) entity);
			} else
				this.mobs.add((Mob) entity);
			if (entity instanceof Player) {
				this.players.add((Player) entity);
			}
		}
	}

	public void addEntity(Entity entity, int index) {
		this.entities.add(index, entity);
		if (entity instanceof Mob) {
			if (entity instanceof Vehicle) {
				this.mobs.add(index, (Vehicle) entity);
			} else
				this.mobs.add(index, (Mob) entity);
			if (entity instanceof Player) {
				this.players.add(index, (Player) entity);
			}
		}

	}

	public void remEntity(Entity entity) {
		this.entities.remove(entity);
		if (entity instanceof Mob) {
			this.mobs.remove((Mob) entity);
			if (entity instanceof Player) {
				this.players.remove((Player) entity);
			}
		}
	}

	public void reset() {
		entities.clear();
		mobs.clear();
		players.clear();
		killList.clear();
		this.init();
		spawnPoint = new Point(startingSpawnPoint.x, startingSpawnPoint.y);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayer() {
		if (players.size() > 0)
			return players.get(0);
		else
			return null;
	}

	public void addSpawner(int x, int y, String type) {
		this.addEntity(new Spawner(this, x, y, type));
	}

	public void addSpawner(int x, int y, String type, int amt) {
		this.addEntity(new Spawner(this, x, y, type, amt));
	}

	public void init() {
		if (!initEntities && this.mobs.size() <= 1) {
			initEntities = true;
			initMapTransporters();
			initNPCPlacement();
			initSpawnerPlacement();
			initChestPlacement();
			otherEntityPlacement();
			initMobLocations();
		}
	}

	public void initMobLocations() {
		for (Mob m : this.getMobs()) {
			m.updateBounds();
		}
	}

	public String toString() {
		return name + "\n Mobs: " + this.getMobs();
	}

	public Screen getScreen() {
		return screen;
	}

	public void setSpawnPoint(int x, int y) {
		spawnPoint = new Point(x, y);
	}
}

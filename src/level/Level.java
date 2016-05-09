package level;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import game.Game;
import game.Hideable;
import game.SoundHandler;
import game.entities.Entity;
import game.entities.Mob;
import game.entities.Player;
import game.graphics.JJFont;
import game.graphics.Screen;
import level.tile.Tile;

/*
 * A Level contains a set of tiles that can be displayed on the screen
 * It also holds a set of all entities that interact with each other
 */
public abstract class Level implements Serializable {

	private static final long serialVersionUID = -5963535226522522466L;

	// all tiles on each level
	protected int[] tiles;

	// width and height in terms of tiles
	private int width, height;

	// list of all entities on the map
	private List<Entity> entities = new ArrayList<Entity>(Game.ENTITY_LIMIT);

	// list of all mobs on the map
	private List<Mob> mobs = new ArrayList<Mob>(Game.ENTITY_LIMIT);

	private List<Hideable> hideables = new ArrayList<Hideable>(Game.ENTITY_LIMIT);

	// image path to load a level
	private String imagePath;

	// where the player will go when changing levels here
	private Point spawnPoint = new Point(0, 0);

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
		this.width = width;
		this.height = height;
		this.name = name;
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

	/**
	 * Updates all entities and tiles on the map
	 */
	public void tick() {

		// update all entities and living mobs
		for (Entity e : getEntities()) {

			if (e.getBounds().intersects(renderRange) && (!(e instanceof Mob) || !((Mob) e).isDead())) {
				e.tick();
			}
		}

		// update all tiles
		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			}
			t.tick();
		}
	}

	/**
	 * Renders a tile on the screen
	 * 
	 * @param screen
	 *            the screen to display it on
	 * @param xOffset
	 *            the xoffset on the screen
	 * @param yOffset
	 *            the yoffset on the screen
	 */
	public void renderTile(Screen screen, int xOffset, int yOffset) {

		// if the player moves off-screen, fix the tiles in place
		if (xOffset < 0)
			xOffset = 0;

		if (xOffset > ((width << 3) - screen.getWidth()))
			xOffset = ((width << 3) - screen.getWidth());

		if (yOffset < 0)
			yOffset = 0;

		if (yOffset > ((height << 3) - screen.getHeight()))
			yOffset = ((height << 3) - screen.getHeight());

		screen.setOffset(xOffset, yOffset);

		// render the tiles visible on the screen
		for (int y = (yOffset >> 3); y < (yOffset + screen.getHeight() >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + screen.getWidth() >> 3) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}

		}
	}

	/**
	 * Displays the entities of the level on the screen
	 * 
	 * @param screen
	 *            the screen to display it on
	 * @param player
	 *            the player to render entities around
	 */
	public void renderEntities(Screen screen, Player player) {

		// the range around the player to display the entities
		renderRange.setLocation(player.getX() - 250, player.getY() - 250);

		// render everything that is behind a building first
		for (Hideable entity : hideables) {

			if (entity.getBounds().intersects(renderRange) && entity.isBehindBuilding()) {
				entity.render(screen);
			}

		}

		// now render everything else on top
		for (Entity e : this.getEntities()) {
			if (e.getBounds().intersects(renderRange)
					&& (!(e instanceof Hideable) || !((Hideable) e).isBehindBuilding())) {
				e.render(screen);
			}

		}
	}

	/**
	 * Renders text on the screen
	 * 
	 * @param msg
	 *            message to display
	 * @param screen
	 *            the screen to display it on
	 * @param x
	 *            the x offset
	 * @param y
	 *            the y offset
	 * @param color
	 *            the color of the message
	 * @param scale
	 *            how big to render it
	 */
	public void renderFont(String msg, Screen screen, int x, int y, int[] color, int scale) {
		JJFont.render(msg, screen, x, y, color, scale);
	}

	/**
	 * Gets the tile type at the specified x,y coords
	 * 
	 * @param x
	 *            the x coord in tile coordinates
	 * @param y
	 *            the y coord in tile coordinates
	 * @return the type of tile, VOID if nothing
	 */
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return Tile.VOID;
		return Tile.tiles[tiles[x + y * width]];

	}

	/**
	 * Adds an entity to this level
	 * 
	 * @param entity
	 *            the entity to add
	 */
	public void add(Entity entity) {

		if (entity instanceof Mob) {
			mobs.add((Mob) entity);
		}
		if (entity instanceof Hideable) {
			hideables.add((Hideable) entity);
		}

		entities.add(entity);
	}
	
	/**
	 * Adds entities to this level
	 * 
	 * @param entity
	 *            the entity to add
	 */
	public void add(Entity... entities) {

		for (Entity e: entities) {
			add(e);
		}
	}

	/**
	 * Removes an entity from the level
	 * 
	 * @param entity
	 *            the entity to remove
	 */
	public void remove(Entity entity) {
		entities.remove(entity);
		if (entity instanceof Mob) {
			mobs.remove(entity);
		}
		if (entity instanceof Hideable) {
			hideables.remove(entity);
		}
	}

	/**
	 * Resets a level to its simplest state
	 */
	public void reset() {
		entities.clear();
		mobs.clear();
		hideables.clear();
		loadEntities();
	}

	/**
	 * @return a list of all entities on the map
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * @return a list of all mobs on this map
	 */
	public List<Mob> getMobs() {
		return mobs;
	}

	/**
	 * Loads the entites on the level
	 */
	private void loadEntities() {
		if (entities.isEmpty()) {
			initMapTransporters();
			initNPCPlacement();
			initSpawnerPlacement();
			initChestPlacement();
			otherEntityPlacement();
		}
	}

	/**
	 * Returns a representation of this object as a string
	 */
	public String toString() {
		return name + "\n Mobs: " + this.getMobs();
	}

	/**
	 * @param x the new x point
	 * @param y the new y point
	 */
	public void setSpawnPoint(int x, int y) {
		spawnPoint = new Point(x, y);
	}
	
	/**
	 * @return the width of this level
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height of this level
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return the spawnpoint of this level
	 */
	public Point getSpawnPoint() {
		return spawnPoint;
	}
	
	/**
	 * @return true if this level is loaded
	 */
	public boolean isLoaded() {
		return isLoaded;
	}
	
	/**
	 * @return the name of the level
	 */
	public String getName() {
		return name;
	}
}

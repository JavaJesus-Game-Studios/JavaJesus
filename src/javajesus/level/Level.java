package javajesus.level;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.filechooser.FileSystemView;

import javajesus.JavaJesus;
import javajesus.SoundHandler;
import javajesus.dataIO.EntityData;
import javajesus.dataIO.LevelData;
import javajesus.entities.Damageable;
import javajesus.entities.DestructibleTile;
import javajesus.entities.Entity;
import javajesus.entities.FireEntity;
import javajesus.entities.Mob;
import javajesus.entities.Spawner;
import javajesus.entities.effects.Shadow;
import javajesus.entities.monsters.Monster;
import javajesus.entities.npcs.NPC;
import javajesus.entities.particles.HealthBar;
import javajesus.entities.particles.Particle;
import javajesus.entities.plant.Grass;
import javajesus.entities.projectiles.Projectile;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.solid.trees.Tree;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.JJFont;
import javajesus.graphics.Screen;
import javajesus.level.tile.AnimatedTile;
import javajesus.level.tile.Tile;
import javajesus.level.tile.TileAdapter;
import javajesus.quest.QuestLoader;
import javajesus.quest.factories.CharacterFactory;
import javajesus.utility.EntityComparator;
import javajesus.utility.LevelText;
import javajesus.utility.QuadTree;

/*
 * A level contains a set of tiles and set of entities that
 * are displayed to the screen and handles their
 * logic and rendering
 */
public abstract class Level {

	// all tiles on each level
	protected int[] levelTiles;

	// QuadTree to help speed up entity collision, only contains Entities in the renderRange
	private final QuadTree collisionTree = new QuadTree(0,  renderRange);
	// list of all entities on the map
	private final List<Entity> entities = new ArrayList<Entity>(JavaJesus.ENTITY_LIMIT);
	
	// list of all entities that need shadows so we don't iterate over everything every frame
	private final ArrayList<Entity> tempEntities = new ArrayList<Entity>(JavaJesus.ENTITY_LIMIT);

	// list of all mobs on the map
	private final List<Mob> mobs = new ArrayList<Mob>(JavaJesus.ENTITY_LIMIT);

	// list of all things that can be damaged
	private final List<Damageable> damageables = new ArrayList<Damageable>(JavaJesus.ENTITY_LIMIT);

	// list of text that will be rendered
	private final List<LevelText> text = new ArrayList<LevelText>(JavaJesus.ENTITY_LIMIT);

	// where the player will go when entering this level
	private final Point spawnPoint;

	// name of the level
	private final String name;

	// gets the name add-on for entity files
	public static final String ENTITY = "_entities";

	// gets the home directory
	private static final String DIR = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
			+ "/My Games/JavaJesus/File";

	// size of each level
	public static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;
	
	// the range of how many entities to render/tick on the screen
	public static final Rectangle renderRange = new Rectangle(JavaJesus.IMAGE_WIDTH, JavaJesus.IMAGE_HEIGHT);

	// global offsets for determining the range to display things
	private int xOffset, yOffset;

	// the comparator used for sorting entities
	private static final EntityComparator comparator = new EntityComparator();

	// data used for loading and saving levels
	protected String path;
	private int saveFile;
	private int levelId;

	/**
	 * Creates a level from the specified image path for the first time
	 * 
	 * @param imagePath - the image path of the original file
	 * @param loadNow   - whether or not to load it now
	 * @param name      - the name of this level
	 * @param saveFile  - the destination of the save file in My Games
	 */
	public Level(final String path, final String name, final Point spawn, int saveFile) {
		this(path, name, spawn, saveFile, -1);
	}

	public Level(final String path, final String name, final Point spawn, int saveFile, int levelId) {

		// instance data
		this.name = name;
		this.spawnPoint = spawn;
		this.path = path;
		this.saveFile = saveFile;
		this.levelId = levelId;
	}

	/**
	 * A default level for random generation
	 * 
	 * @param name
	 */
	public Level(String name, Point spawn) {

		// instance data
		this.name = name;
		this.spawnPoint = spawn;
		this.levelId = -1;
	}

	public int getLevelId() {
		return this.levelId;
	}

	/**
	 * @return whether or not the level tiles have been loaded into memory
	 */
	public boolean isLoaded() {
		return levelTiles != null;
	}

	/**
	 * Fill in the level Tiles
	 */
	public void generateLevel() throws IOException {

		// initialize tile array
		levelTiles = new int[LEVEL_WIDTH * LEVEL_HEIGHT];

		// check if save file exists
		File f = new File(DIR + saveFile);

		// file does not exist
		if (!f.exists()) {

			// make the directory
			f.mkdirs();

			// load the original files into memory
			load(path, true);

			// now save in the new folder
			save(DIR + saveFile + "/" + name);

			// directory does exist
		} else {

			// select the file
			f = new File(DIR + saveFile + "/" + name);

			// if it exists, load it
			if (f.exists()) {
				load(DIR + saveFile + "/" + name, false);

				// load from original, then save
			} else {

				// load the original files into memory
				load(path, true);

				// now save in the new folder
				save(DIR + saveFile + "/" + name);
			}
		}

		// now add any non unique quest givers
		CharacterFactory cf = CharacterFactoryFactory.make(this.getLevelId());
		if (cf != null) {
			cf.setNonUniqueCharacters(this);
			QuestLoader.initializeQuests(this);
		}
	}

	/**
	 * Loads an image from the file
	 */
	private void load(String path, boolean classpath) throws IOException {

		// files to load
		InputStream level, entities;

		// load with or without classpath
		if (classpath) {
			level = Level.class.getResourceAsStream(path);
			entities = Level.class.getResourceAsStream(path + ENTITY);
		} else {
			level = new FileInputStream(new File(path));
			entities = new FileInputStream(new File(path + ENTITY));
		}

		// load the tiles
		LevelData.load(level, levelTiles);

		// load the entity data
		EntityData.load(this, entities);
	}

	/**
	 * Saves the level data to the specified path
	 */
	private void save(String path) throws IOException {

		// save the tile data
		LevelData.save(path, levelTiles);

		// save the entity data
		EntityData.save(path + ENTITY, entities);

	}

	/**
	 * Updates all entities and tiles on the map
	 */
	public void tick() {
		// the range around the player to display the entities
		renderRange.setLocation(xOffset, yOffset);
		
		entities.sort(comparator);
		// clear the QuadTree
		collisionTree.clear();
		tempEntities.clear();
		// Get all entities currently in the render range
		getEntitiesInRange();
		// Update the entities in the render range
		for( Entity e: tempEntities ) {
			if( !(e instanceof Mob) || !((Mob)e).isDead()) {
				e.tick();
			}
		}

		// update all tiles
		for (AnimatedTile t : AnimatedTile.tileList) {
			t.tick();
		}
	}

	public List<TileAdapter> getOccupiedTiles(Entity e) {
		List<TileAdapter> list = new ArrayList<>();
		for (int x = e.getX(); x < e.getX() + e.getBounds().width; x += 8) {
			for (int y = e.getY(); y < e.getY() + e.getBounds().height; y += 8) {
				list.add(new TileAdapter(getTileFromEntityCoords(x, y), x >> 3, y >> 3));
			}
		}
		return list;
	}

	/**
	 * Renders tiles, entities, and text to the screen
	 * 
	 * @param screen - screen to render on
	 */
	public void render(Screen screen) {
		// set the screen offsets
		screen.setOffset(xOffset, yOffset);
		// render tiles and entities and text
		renderTile(screen);
		renderShadow(screen);
		renderEntities(screen);
		renderText(screen);
	}

	/**
	 * Renders a tile on the screen
	 * 
	 * @param screen  - the screen to display it on
	 * @param xOffset - the xoffset on the screen
	 * @param yOffset - the yoffset on the screen
	 */
	private void renderTile(Screen screen) {
		// render the tiles visible on the screen
		for (int y = (yOffset >> 3); y < (yOffset + renderRange.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + renderRange.width >> 3) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}

		}
	}

	/**
	 * Displays the entities of the level on the screen
	 * 
	 * @param screen - the screen to display it on
	 */
	private void renderEntities(Screen screen) {
		// render all the entities on the visible screen
		for (Entity e : tempEntities) {
			e.render(screen);
		}
	}
	/**
	 * Displays the shadows of every Entity on Screen
	 * @param screen - the screen to display it on
	 */
	private void renderShadow(Screen screen) {
		Shadow entityShadow;
		Mob m = null;
		for( Entity e : tempEntities ) {
			if( hasShadow(e) ) {
				// Check to see if the entity is a mob
				if( e instanceof Mob ) {
					m = (Mob) e;
					if( (e == m && m.getIsSwimming())) {
						continue;
					}
				}
				// otherwise get the shadow
				entityShadow = e.getSpriteShadow();
				// Set the y value for where we will render the shadow at
				if( e instanceof Tree) {
					entityShadow.render(screen, e.getX(), e.getBounds().y);
					continue;
				}
				// Check to see if the entity is grass to do custom alpha
				if( e instanceof Grass ) {
					entityShadow.render(screen, e.getX(), e.getBounds().y, 0.2f);
					continue;
				}
				// Otherwise render shadow normally
				else {
					entityShadow.render(screen, e.getX(), e.getBounds().y);
				}
	
			}
		}
	}
	/**
	 * Helper method to determine whether to render a shadow or not
	 * @param entity the entity to check
	 * @return true if we render a shadow for this entity, false if we don't
	 */
	private boolean hasShadow(Entity entity) {
		// Check to see we should not make a shadow for this entity
		if( entity instanceof FireEntity  || entity instanceof Spawner || entity instanceof DestructibleTile 
				|| entity instanceof Particle || entity instanceof HealthBar || entity instanceof Projectile 
				|| entity instanceof Building || entity instanceof Transporter ) {
			return false;
		}
		return true;
	}
	/**
	 * Helper Method to fill the CollisionTree and the tempEntities array
	 * With only the Entities in Range
	 */
	private void getEntitiesInRange() {
		for( Entity e: entities) {
			if( e instanceof Tree ) {
				if(((Tree) e).getSpriteBounds().intersects(renderRange)) {
					tempEntities.add(e);
					if(((Tree) e).getBounds().intersects(renderRange))
						collisionTree.insert(e);
				}
				continue;
			}
			if( e instanceof Building ) {
				if(((Building) e).getSpriteBounds().intersects(renderRange)) {
					tempEntities.add(e);
					if(((Building) e).getBounds().intersects(renderRange))
						collisionTree.insert(e);
				}
				continue;
			}
			if( e.getBounds().intersects(renderRange)){
				collisionTree.insert(e);
				tempEntities.add(e);
			}
		}
	}

	/**
	 * Renders text on the screen
	 * 
	 * @param msg    - message to display
	 * @param screen - the screen to display it on
	 * @param x      - the x offset
	 * @param y      - the y offset
	 * @param color  - the color of the message
	 * @param scale  - how big to render it
	 */
	public void renderText(Screen screen) {

		// render all the text
		for (LevelText t : text) {
			JJFont.render(t.getMessage(), screen, t.getX(), t.getY(), t.getColor(), t.getScale());
		}
	}

	/**
	 * Renders collision boxes onto the screen
	 * 
	 * @param screen - screen to render pixels
	 */
	public void renderCollisionBoxes(Screen screen) {
		for (Entity e : tempEntities) {
			screen.renderCollisionBox(e.getBounds());
		}
	}

	/**
	 * Sets offsets for tile and entity rendering
	 * 
	 * @param xOffset - x offset
	 * @param yOffset - y offset
	 */
	public void setOffset(int xOffset, int yOffset) {

		// base offsets
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		// if the player moves off-screen, fix the tiles in place
		if (xOffset < 0)
			this.xOffset = 0;

		if (xOffset > ((LEVEL_WIDTH << 3) - JavaJesus.IMAGE_WIDTH))
			this.xOffset = ((LEVEL_WIDTH << 3) - JavaJesus.IMAGE_WIDTH);

		if (yOffset < 0)
			this.yOffset = 0;

		if (yOffset > ((LEVEL_HEIGHT << 3) - JavaJesus.IMAGE_HEIGHT))
			this.yOffset = ((LEVEL_HEIGHT << 3) - JavaJesus.IMAGE_HEIGHT);

	}

	/**
	 * Changes a tile on the map
	 * 
	 * @param x       - the x coord of the tile
	 * @param y       - the y coord of the tile
	 * @param newTile - the tile to replace it with
	 */
	public void alterTile(int x, int y, Tile newTile) {
		this.levelTiles[x + y * LEVEL_WIDTH] = newTile.getId() & 0x00FF;
	}

	/**
	 * Removes dead mobs from the level
	 */
	public void clear() {
		for (int i = 0; i < mobs.size(); i++) {
			Mob m = mobs.get(i);
			if (m.isDead()) {
				remove(m);
				i--;
			}
		}
	}

	/**
	 * Gets the tile type at the specified x,y coords
	 * 
	 * @param x - the x coord in tile coordinates
	 * @param y - the y coord in tile coordinates
	 * @return the type of tile, VOID if nothing
	 */
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= LEVEL_WIDTH || y < 0 || y >= LEVEL_HEIGHT)
			return Tile.VOID;

		return Tile.tileList[levelTiles[x + y * LEVEL_WIDTH]];

	}

	/**
	 * Gets the tile type from the entity coordinates
	 * 
	 * @param x - the x coord in entity coordinates
	 * @param y - the y coord in entity coordinates
	 * @return the type of tile, VOID if nothing
	 */
	public Tile getTileFromEntityCoords(int x, int y) {
		return getTile(x >> 3, y >> 3);
	}

	public List<TileAdapter> getVisibleTiles(Screen screen) {

		List<TileAdapter> occupied = new ArrayList<>(500);
		List<TileAdapter> tiles = new ArrayList<>(500);
		TileAdapter tile;
		Entity e;


		// get list of all tiles that are occupied
		for (int i = 0; i < tempEntities.size(); i++) {
			e = tempEntities.get(i);
			if (e.getBounds().intersects(renderRange) && !(e instanceof Projectile) 
					&& (!(e instanceof Mob) || !((Mob) e).isDead())) {
				occupied.addAll(getOccupiedTiles(e));
			}
		}

		// only check the tiles visible on the screen
		for (int y = (yOffset >> 3); y < (yOffset + renderRange.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + renderRange.width >> 3) + 1; x++) {
				tile = new TileAdapter(getTile(x, y), x, y);
				if (occupied.contains(tile)) {
					tile.setOccupied(true);
				}
				tiles.add(tile);			
			}
		}
		return tiles;
	}

	/**
	 * Adds text to be rendered
	 * 
	 * @param message - message to display
	 * @param x       - x coordinate
	 * @param y       - y coordinate
	 * @param color   - color set
	 * @param scale   - scale of the text
	 */
	public void addText(String message, int x, int y, int[] color, int scale) {
		text.add(new LevelText(message, x, y, color, scale));
	}

	/**
	 * Adds an entity to this level
	 * 
	 * @param entity - the entity to add
	 */
	public void add(Entity entity) {

		if (entity instanceof Mob) {
			mobs.add((Mob) entity);
			
			if (entity instanceof Monster || entity instanceof NPC) {
				entities.add(((Mob) entity).getHealthBar());
			}
		}
		if (entity instanceof Damageable) {
			damageables.add((Damageable) entity);
		}
		
		if (entity instanceof Grass) {
			this.getTileFromEntityCoords(entity.getX(), entity.getY()).setGrass((Grass) entity);
		}
		
		
		entities.add(entity);
	}

	/**
	 * Removes an entity from the level
	 * 
	 * @param entity - the entity to remove
	 */
	public void remove(Entity entity) {
		entities.remove(entity);
		if (entity instanceof Mob) {
			mobs.remove((Mob) entity);
		}
		if (entity instanceof Damageable) {
			damageables.remove((Damageable) entity);
		}

	}

	/**
	 * @return a clip of the background music
	 */
	public Clip getBackgroundMusic() {
		return SoundHandler.explorationMusic;
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
	 * @return a list of all damageables on this map
	 */
	public List<Damageable> getDamageables() {
		return damageables;
	}
	
	public QuadTree getCollisionTree() {
		return collisionTree;
	}

	/**
	 * Returns a representation of this object as a string
	 */
	public String toString() {
		return name + "\n Mobs: " + this.getMobs();
	}

	/**
	 * @return the name of the level
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the spawnpoint on this level
	 */
	public Point getSpawnPoint() {
		return spawnPoint;
	}

	/**
	 * Sets the spawn point on this level in entity coordinates
	 * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public void setSpawnPoint(int x, int y) {
		spawnPoint.setLocation(x, y);
	}

	/**
	 * @return the level tiles
	 */
	public int[] getTiles() {
		return levelTiles;
	}

}

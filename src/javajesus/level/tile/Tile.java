package javajesus.level.tile;

import java.util.Random;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A Tile is a background unit on each level
 * Tiles are 8x8 and cannot interact with other entities
 */
public class Tile {
	
	// A set of all the available tiles implemented in the game
	public static final Tile[] tileList = new Tile[256];

	public static final Tile VOID = new Tile(0, true, 0xFF000000, 0, 0, SpriteSheet.tiles, new int[] { 0xFF000000, 0xFF000000, 0xFF000000 });

	public static final Tile STONE = new Tile(1, true, 0xFF555555, 1, 0, SpriteSheet.tiles, new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
	
	public static final Tile GRASS = new Tile(2, false, 0xFF00FF00, 0, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
	
	public static final Tile WATER = new AnimatedTile(3, false, 0xFF0000FF, 14, SpriteSheet.tiles,
			new int[] { 0xFF0000BB, 0xFF2222EE, 0xFF000000 }, 5, 800);
	
	public static final Tile SAND = new Tile(4, false, 0xFFFFFF00, 9, 2, SpriteSheet.tiles, new int[] { 0xFFEEBB00, 0xFFFFFF00, 0xFF000000 });
	
	public static final Tile ROAD1 = new Tile(6 ,false, 0xFF565656, 0, 2, SpriteSheet.tiles, new int[] { 0xFF222222, 0xFF353535, 0xFF000000 });
	public static final Tile ROAD2 = new Tile(7, false, 0xFF3f3f3f, 1, 2, SpriteSheet.tiles, new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
	
	public static final Tile WATERSAND = new AnimatedTile(5, false, 0xFF64FFFF, 14, SpriteSheet.tiles,
			new int[] { 0xFFDBEDD2, 0xFF2222FF, 0xFF000000 }, 5, 800);
	
	public static final Tile ROAD3 = new Tile(9, false, 0xFF9b9b9b, 2, 2, SpriteSheet.tiles, new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
	
	public static final Tile MUD = new Tile(11, false, 0xFF372201, 0, 2, SpriteSheet.tiles, new int[] { 0xFF2c1802, 0xFF000000, 0xFF000000 });
	
	public static final Tile FARMPLOT1 = new Tile(12, false, 0xFF1E6A00, 2, 1, SpriteSheet.tiles, new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
	public static final Tile FARMPLOT2 = new Tile(13, false, 0xFF123F00, 3, 1, SpriteSheet.tiles, new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
	
	public static final Tile DIRTROAD = new Tile(14, false, 0xFFA06201, 10, 2, SpriteSheet.tiles, new int[] { 0xFF935409, 0xFF000000, 0xFF000000 });
	
	public static final Tile BRICKROAD = new Tile(15, false, 0xFFA50000, 1, 0, SpriteSheet.tiles, new int[] { 0xFFD40000, 0xFFB40000, 0xFF000000 });
	
	public static final Tile GRASS_FLOWER = new Tile(16, false, 0xFF15DF00, 5, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFFFFFFFF });
	public static final Tile GRASS2 = new Tile(17, false, 0xFF00FF00, 1, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
	public static final Tile GRASS3 = new Tile(18, false, 0xFF00FF00, 4, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
	
	public static final Tile CAVEWALL = new Tile(19, true, 0xFF291900, 4, 0, SpriteSheet.tiles, new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
	public static final Tile CAVEFLOOR = new Tile(20, false, 0xFFC3BEB7, 0, 2, SpriteSheet.tiles, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
	
	public static final Tile WOODFLOOR = new Tile(21, false, 0xFF944500, 6, 0, SpriteSheet.tiles, new int[] { 0xFF753003, 0xFF291900, 0xFF000000 });
	
	public static final Tile METALWALL = new Tile(22, true, 0xFFB6B6B6, 5, 0, SpriteSheet.tiles, new int[] { 0xFFA6A6A6, 0xFF939393, 0xFF4B3618 });
	
	public static final Tile RIGHTCARPET = new Tile(23, false, 0xFFBE03DB, 7, 1, SpriteSheet.tiles, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
	
	public static final Tile LEFTCARPET = new Tile(24, false, 0xFFEC00EC, 6, 1, SpriteSheet.tiles, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
	
	public static final Tile TOPCARPET = new Tile(26, false, 0xFFFF00FF, 9, 1, SpriteSheet.tiles, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
	
	public static final Tile BOTTOMCARPET = new Tile(27, false, 0xFF6600A7, 8, 1, SpriteSheet.tiles, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
	
	public static final Tile CHECKERED_TILE = new Tile(49, false, 0xFFe3350c, 11, 1, SpriteSheet.tiles, new int[] { 0xFF111111, 0xFF111111, 0xFFFFFFFF });

	public static final Tile WOOD_WALL_HORIZONTAL_UP = new Tile(10, true, 0xFF6D4300, 7, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_HORIZONTAL_DOWN = new Tile(28, true, 0xFF835100, 8, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_VERTICAL_LEFT = new Tile(29, true, 0xFF5A3801, 9, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_VERTICAL_RIGHT = new Tile(30, true, 0xFF472C00, 10, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_CORNER_RIGHT_UP = new Tile(31, true, 0xFF520101, 11, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_CORNER_LEFT_UP = new Tile(32, true, 0xFF420101, 12, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_CORNER_RIGHT_DOWN = new Tile(33, true, 0xFF7A0000, 14, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_CORNER_LEFT_DOWN = new Tile(34, true, 0xFF310000, 13, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_INSIDE_CORNER_LEFT_UP = new Tile(43, true, 0xFFA3881A, 15, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_INSIDE_CORNER_RIGHT_UP = new Tile(44, true, 0xFF7C6816, 16, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_INSIDE_CORNER_LEFT_DOWN = new Tile(45, true, 0xFF717C16, 17, 0, SpriteSheet.tiles, 
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	public static final Tile WOOD_WALL_INSIDE_CORNER_RIGHT_DOWN = new Tile(48, true, 0xFF7d8c00, 18, 0, SpriteSheet.tiles,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });

	public static final Tile GLASS_WALL_HORIZONTAL_UP = new Tile(35, true, 0xFF016ABA, 7, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_HORIZONTAL_DOWN = new Tile(36, true, 0xFF027FDF, 8, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_VERTICAL_LEFT = new Tile(37, true, 0xFF0166f4, 9, 0, SpriteSheet.tiles, 
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_VERTICAL_RIGHT = new Tile(38, true, 0xFF0256CC, 10, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_CORNER_RIGHT_UP = new Tile(39, true, 0xFF0B9401, 11, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_CORNER_LEFT_UP = new Tile(40, true, 0xFF420101, 12, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_CORNER_RIGHT_DOWN = new Tile(41, true, 0xFF087101, 14, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	public static final Tile GLASS_WALL_CORNER_LEFT_DOWN = new Tile(42, true, 0xFFFFB400, 13, 0, SpriteSheet.tiles,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });

	public static final Tile FLOOR_CARPET_1 = new Tile(46, false,0xFF1f7a01,10, 1, SpriteSheet.tiles, new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 });

	public static final Tile LINOLEUM = new Tile(47, false, 0xFFFFFFFF, 11, 2, SpriteSheet.tiles, new int[] { 0xFFf9ffd8, 0xFF000000, 0xFF000000 });

	public static final Tile MOUNTAIN_UP = new Tile(50, true, 0xFF9e7013, 5, 2, SpriteSheet.tiles,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
	public static final Tile MOUNTAIN_DOWN = new Tile(51, true, 0xFF875a00, 6, 2, SpriteSheet.tiles,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
	public static final Tile MOUNTAIN_LEFT = new Tile(52, true, 0xFF564015, 8, 2, SpriteSheet.tiles,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
	public static final Tile MOUNTAIN_RIGHT = new Tile(53, true, 0xFF5e3f00, 7, 2, SpriteSheet.tiles, 
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });

	public static final Tile GRASS_POPPY = new Tile(54, false, 0xFF00FF00, 16, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
	public static final Tile GRASS4 = new Tile(62, false, 0xFF00FF00, 19, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
	public static final Tile GRASS5 = new Tile(63, false, 0xFF00FF00, 20, 1, SpriteSheet.tiles, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });

	public static final Tile WASTELAND_GROUND1 = new Tile(55, false, 0xFF8e8e8e, 12, 1, SpriteSheet.tiles,
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
	public static final Tile WASTELAND_GROUND2 = new Tile(56, false, 0xFF8e8e8e, 13, 1, SpriteSheet.tiles, 
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
	public static final Tile WASTELAND_GROUND3 = new Tile(57, false, 0xFF8e8e8e, 14, 1, SpriteSheet.tiles, 
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
	public static final Tile WASTELAND_GROUND4 = new Tile(58, false, 0xFF8e8e8e, 15, 1, SpriteSheet.tiles,
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
	public static final Tile WASTELAND_GROUND5 = new Tile(59, false, 0xFF8e8e8e, 0, 2, SpriteSheet.tiles,
			new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
	public static final Tile WASTELAND_GROUND6 = new Tile(60, false, 0xFF8e8e8e, 17, 1, SpriteSheet.tiles,
			new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
	public static final Tile WASTELAND_GROUND7 = new Tile(61, false, 0xFF8e8e8e, 18, 1, SpriteSheet.tiles,
			new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
	
	// base size of tiles
	public static final int SIZE = 8;

	// used for randomly getting tiles
	private static final Random random = new Random();

	// unique identifier for each tile
	private final int id;

	// whether or not an entity can walk through it
	private final boolean solid;

	// the pixel color on the level drawings
	private final int pixelColor;
	
	// Spritesheet used for rendering
	private final SpriteSheet sheet;
	
	// color set of the tile
	private final int[] color;
	
	// coordinates on spritesheet
	protected int xTile, yTile;

	/**
	 * Tile ctor()
	 * Creates a tile
	 * 
	 * @param id - unique identifier for the tile
	 * @param isSolid - determines if an entity can walk through this tile
	 * @param pixelColor - the pixel color on the png file
	 */
	protected Tile(int id, boolean isSolid, int pixelColor, int xTile, int yTile, SpriteSheet sheet, int[] color) {
		
		// instance data
		this.id = id;
		this.solid = isSolid;
		this.pixelColor = pixelColor;
		this.xTile = xTile;
		this.yTile = yTile;
		this.sheet = sheet;
		this.color = color;
		
		// Non Unique ID found
		if (tileList[id] != null)
			throw new RuntimeException("Duplicate tile id at " + id);
		
		// Add to global tile IDs
		tileList[id] = this;
	}

	/**
	 * @return the unique id of a tile
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Whether or not an entity can walk through this tile
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @return the pixel color in the png file
	 */
	public int getPixelColor() {
		return pixelColor;
	}

	/**
	 * @return the spritesheet for this tile
	 */
	public final SpriteSheet getSpriteSheet() {
		return sheet;
	}

	/**
	 * @return A random Grass tile
	 */
	public static Tile GRASS() {

		if (random.nextInt(100) == 0) {
			return GRASS_FLOWER;
		} else if (random.nextInt(100) == 0) {
			return GRASS_POPPY;
		} else if (random.nextInt(6) == 0) {
			return GRASS2;
		} else if (random.nextInt(6) == 0) {
			return GRASS3;
		} else if (random.nextInt(6) == 0) {
			return GRASS4;
		} else if (random.nextInt(6) == 0) {
			return GRASS5;
		} else {
			return GRASS;
		}
	}

	/**
	 * @return a random Concrete tile
	 */
	public static Tile CONCRETE() {
		
		if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND1;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND2;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND3;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND4;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND7;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND6;
		} else {
			return WASTELAND_GROUND5;
		}
	}

	/**
	 * Rounds a number to be divisible by
	 * the tile size
	 * 
	 * @param num - the number to become evenly divisible
	 * @return evenly divisible num by tile size
	 */
	public static final int snapToCorner(int num) {
		return num - (num % Tile.SIZE);
		
	}

	/**
	 * Most tiles don't have any logic
	 */
	public void tick() {}

	/**
	 * render()
	 * Renders the tile on the screen
	 * 
	 * @param screen - screen to render on
	 * @param level - the level the tile is on
	 * @param x - the x offset
	 * @param y - the y offset
	 */
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, xTile, yTile, sheet, false, color);
	}

	/**
	 * @return The id of the tile as a string
	 */
	public String toString() {
		return String.valueOf(id);
	}

	/**
	 * Determines if an id is a grass tile
	 * 
	 * @param num - the id to check
	 * @return true if the ID is a grass tile
	 */
	public static boolean isGrass(int num) {
		return num == Tile.GRASS.getId() || num == Tile.GRASS2.getId() || num == Tile.GRASS3.getId()
				|| num == Tile.GRASS4.getId() || num == Tile.GRASS5.getId() || num == Tile.GRASS_POPPY.getId()
				|| num == Tile.GRASS_FLOWER.getId();
	}
}

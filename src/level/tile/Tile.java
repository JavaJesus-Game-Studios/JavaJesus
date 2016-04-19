package level.tile;

import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.util.Random;

import level.Level;

/*
 * A Tile is a background unit on each level
 * Tiles are 8x8 and cannot interact with other entities
 */
public abstract class Tile {

	// A set of all Tile types in the game
	public static final Tile[] tiles = new Tile[256];

	// base size of tiles
	public static final int SIZE = 8;

	// used for randomly getting tiles
	private static final Random random = new Random();

	public static final Tile VOID = new BasicSolidTile(0, 0, 0, new int[] { 0xFF000000, 0xFF000000, 0xFF000000 },
			0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 },
			0xFF555555);
	public static final Tile GRASS = new BaseTile(2, 0, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 },
			0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3,
			new int[][] { { 0, 14 }, { 1, 14 }, { 2, 14 }, { 3, 14 }, { 2, 14 }, { 1, 14 } },
			new int[] { 0xFF0000BB, 0xFF2222EE, 0xFF000000 }, 0xFF0000FF, 800);
	public static final Tile SAND = new BaseTile(4, 9, 2, new int[] { 0xFFEEBB00, 0xFFFFFF00, 0xFF000000 }, 0xFFFFFF00);
	public static final Tile ROAD1 = new BaseTile(6, 0, 2, new int[] { 0xFF222222, 0xFF353535, 0xFF000000 },
			0xFF565656);
	public static final Tile ROAD2 = new BaseTile(7, 1, 2, new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 },
			0xFF3f3f3f);
	public static final Tile WATERSAND = new AnimatedTile(5,
			new int[][] { { 0, 14 }, { 1, 14 }, { 2, 14 }, { 3, 14 }, { 2, 14 }, { 1, 14 } },
			new int[] { 0xFFDBEDD2, 0xFF2222FF, 0xFF000000 }, 0xFF64FFFF, 800);
	public static final Tile ROAD3 = new BaseTile(9, 2, 2, new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 },
			0xFF9b9b9b);
	public static final Tile MUD = new BaseTile(11, 0, 2, new int[] { 0xFF2c1802, 0xFF000000, 0xFF000000 }, 0xFF372201);
	public static final Tile FARMPLOT1 = new BaseTile(12, 2, 1, new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 },
			0xFF1E6A00);
	public static final Tile FARMPLOT2 = new BaseTile(13, 3, 1, new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 },
			0xFF123F00);
	public static final Tile DIRTROAD = new BaseTile(14, 10, 2, new int[] { 0xFF935409, 0xFF000000, 0xFF000000 },
			0xFFA06201);
	public static final Tile BRICKROAD = new BaseTile(15, 1, 0, new int[] { 0xFFD40000, 0xFFB40000, 0xFF000000 },
			0xFFA50000);
	public static final Tile GRASS_FLOWER = new BaseTile(16, 5, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFFFFFFFF },
			0xFF15DF00);
	public static final Tile GRASS2 = new BaseTile(17, 1, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 },
			0xFF00FF00);
	public static final Tile GRASS3 = new BaseTile(18, 4, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 },
			0xFF00FF00);
	public static final Tile CAVEWALL = new BasicSolidTile(19, 4, 0, new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 },
			0xFF291900);
	public static final Tile CAVEFLOOR = new BaseTile(20, 0, 2, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 },
			0xFFC3BEB7);
	public static final Tile WOODFLOOR = new BaseTile(21, 6, 0, new int[] { 0xFF753003, 0xFF291900, 0xFF000000 },
			0xFF944500);
	public static final Tile METALWALL = new BasicSolidTile(22, 5, 0, new int[] { 0xFFA6A6A6, 0xFF939393, 0xFF4B3618 },
			0xFFB6B6B6);
	public static final Tile RIGHTCARPET = new BaseTile(23, 7, 1, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 },
			0xFFBE03DB);
	public static final Tile LEFTCARPET = new BaseTile(24, 6, 1, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 },
			0xFFEC00EC);
	public static final Tile TOPCARPET = new BaseTile(26, 9, 1, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 },
			0xFFFF00FF);
	public static final Tile BOTTOMCARPET = new BaseTile(27, 8, 1, new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 },
			0xFF6600A7);
	public static final Tile CHECKERED_TILE = new BaseTile(49, 11, 1, new int[] { 0xFF111111, 0xFF111111, 0xFFFFFFFF },
			0xFFe3350c);

	public static final Tile WOOD_WALL_HORIZONTAL_UP = new BasicSolidTile(10, 7, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF6D4300);
	public static final Tile WOOD_WALL_HORIZONTAL_DOWN = new BasicSolidTile(28, 8, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF835100);
	public static final Tile WOOD_WALL_VERTICAL_LEFT = new BasicSolidTile(29, 9, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF5A3801);
	public static final Tile WOOD_WALL_VERTICAL_RIGHT = new BasicSolidTile(30, 10, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF472C00);
	public static final Tile WOOD_WALL_CORNER_RIGHT_UP = new BasicSolidTile(31, 11, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF520101);
	public static final Tile WOOD_WALL_CORNER_LEFT_UP = new BasicSolidTile(32, 12, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF420101);
	public static final Tile WOOD_WALL_CORNER_RIGHT_DOWN = new BasicSolidTile(33, 14, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF7A0000);
	public static final Tile WOOD_WALL_CORNER_LEFT_DOWN = new BasicSolidTile(34, 13, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF310000);
	public static final Tile WOOD_WALL_INSIDE_CORNER_LEFT_UP = new BasicSolidTile(43, 15, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFFA3881A);
	public static final Tile WOOD_WALL_INSIDE_CORNER_RIGHT_UP = new BasicSolidTile(44, 16, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF7C6816);
	public static final Tile WOOD_WALL_INSIDE_CORNER_LEFT_DOWN = new BasicSolidTile(45, 17, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF717C16);
	public static final Tile WOOD_WALL_INSIDE_CORNER_RIGHT_DOWN = new BasicSolidTile(48, 18, 0,
			new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B }, 0xFF7d8c00);

	public static final Tile GLASS_WALL_HORIZONTAL_UP = new BasicSolidTile(35, 7, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF016ABA);
	public static final Tile GLASS_WALL_HORIZONTAL_DOWN = new BasicSolidTile(36, 8, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF027FDF);
	public static final Tile GLASS_WALL_VERTICAL_LEFT = new BasicSolidTile(37, 9, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF0166f4);
	public static final Tile GLASS_WALL_VERTICAL_RIGHT = new BasicSolidTile(38, 10, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF0256CC);
	public static final Tile GLASS_WALL_CORNER_RIGHT_UP = new BasicSolidTile(39, 11, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF0B9401);
	public static final Tile GLASS_WALL_CORNER_LEFT_UP = new BasicSolidTile(40, 12, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF420101);
	public static final Tile GLASS_WALL_CORNER_RIGHT_DOWN = new BasicSolidTile(41, 14, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF087101);
	public static final Tile GLASS_WALL_CORNER_LEFT_DOWN = new BasicSolidTile(42, 13, 0,
			new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFFFFB400);

	public static final Tile FLOOR_CARPET_1 = new BaseTile(46, 10, 1, new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 },
			0xFF1f7a01);

	public static final Tile LINOLEUM = new BaseTile(47, 11, 2, new int[] { 0xFFf9ffd8, 0xFF000000, 0xFF000000 },
			0xFFFFFFFF);

	public static final Tile MOUNTAIN_UP = new BasicSolidTile(50, 5, 2,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 }, 0xFF9e7013);
	public static final Tile MOUNTAIN_DOWN = new BasicSolidTile(51, 6, 2,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 }, 0xFF875a00);
	public static final Tile MOUNTAIN_LEFT = new BasicSolidTile(52, 8, 2,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 }, 0xFF564015);
	public static final Tile MOUNTAIN_RIGHT = new BasicSolidTile(53, 7, 2,
			new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 }, 0xFF5e3f00);

	public static final Tile GRASS_POPPY = new BaseTile(54, 16, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 },
			0xFF00FF00);
	public static final Tile GRASS4 = new BaseTile(62, 19, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 },
			0xFF00FF00);
	public static final Tile GRASS5 = new BaseTile(63, 20, 1, new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 },
			0xFF00FF00);

	public static final Tile WASTELAND_GROUND1 = new BaseTile(55, 12, 1,
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);
	public static final Tile WASTELAND_GROUND2 = new BaseTile(56, 13, 1,
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);
	public static final Tile WASTELAND_GROUND3 = new BaseTile(57, 14, 1,
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);
	public static final Tile WASTELAND_GROUND4 = new BaseTile(58, 15, 1,
			new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);
	public static final Tile WASTELAND_GROUND5 = new BaseTile(59, 0, 2,
			new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);
	public static final Tile WASTELAND_GROUND6 = new BaseTile(60, 17, 1,
			new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);
	public static final Tile WASTELAND_GROUND7 = new BaseTile(61, 18, 1,
			new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 }, 0xFF8e8e8e);

	// unique identifier for each tile
	private byte id;

	// whether or not an entity can walk through it
	protected boolean solid;

	// the pixel color on the level drawings
	private int levelColor;

	/**
	 * Creates a tile
	 * 
	 * @param id
	 *            UNIQUE identifier for this type of tile
	 * @param isSolid
	 *            determines if an entity can walk through this tile
	 * @param levelColor
	 *            the pixel color on the level file
	 */
	public Tile(int id, boolean isSolid, int levelColor) {
		this.id = (byte) id;
		if (tiles[id] != null)
			throw new RuntimeException("Duplicate tile id on " + id);
		this.solid = isSolid;
		this.levelColor = levelColor;
		tiles[id] = this;
	}

	/**
	 * @return the UNIQUE identifier between 0 and 256
	 */
	public byte getId() {
		return id;
	}

	/**
	 * @return Whether or not an entity can walk through this tile
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @return the pixel color on the level file
	 */
	public int getLevelColor() {
		return levelColor;
	}

	/**
	 * @return the spritesheet that has the tiles
	 */
	public static final SpriteSheet getSpriteSheet() {
		return SpriteSheet.tiles;
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

	public abstract void tick();

	public abstract void render(Screen screen, Level level, int x, int y);

	/**
	 * @return The id of the tile as a string
	 */
	public String toString() {
		return String.valueOf(id);
	}

	/**
	 * Determines if an id is a grass tile
	 * 
	 * @param num
	 *            the id to check
	 * @return true if the ID is a grass tile
	 */
	public static boolean isGrass(int num) {
		return num == Tile.GRASS.getId() || num == Tile.GRASS2.getId() || num == Tile.GRASS3.getId()
				|| num == Tile.GRASS4.getId() || num == Tile.GRASS5.getId() || num == Tile.GRASS_POPPY.getId()
				|| num == Tile.GRASS_FLOWER.getId();
	}
}

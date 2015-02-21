package ca.javajesus.level.tile;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public abstract class Tile {

	/**
	 * Each slots represents a different color on the sprite sheet -1 is
	 * transparent, aka 255 First digit of number is red, second digit is green,
	 * third digit is blue To convert to this 6 bit format, take the RGB value
	 * individually Multiply by 5 and divide by 255 -- Round Use that number in
	 * the respective RGB slot
	 * 
	 * Instead, use my Colours.toRGB Method if you don't want to do the work
	 * 
	 * Or, use my Colours.toHex
	 */
	public static final Tile[] tiles = new Tile[256];

	public static final Tile VOID = new BasicSolidTile(0, 0, 0, Colors.get(000,
			-1, -1, -1), 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, Colors.get(-1,
			Colors.fromHex("#7f7f7f"), Colors.fromHex("#c3c3c3"), -1), 0xFF555555);
	public static final Tile GRASS = new BaseTile(2, 0, 1, Colors.get(-1, 131,
			141, -1), 0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3, new int[][] {
			{ 0, 14 }, { 1, 14 }, { 2, 14 }, { 3, 14 }, { 2, 14 }, { 1, 14 } },
			Colors.get(-1, 004, 115, -1), 0xFF0000FF, 800);
	public static final Tile SAND = new BaseTile(4, 0, 1, Colors.get(-1, 540,
			550, -1), 0xFFFFFF00);
	public static final Tile FIRE = new AnimatedTile(5, new int[][] {
			{ 0, 15 }, { 1, 15 }, { 2, 15 }, { 3, 15 }, { 4, 15 }, { 3, 15 },
			{ 2, 15 }, { 1, 15 } }, Colors.get(Colors.fromHex("#F51F07"),
			Colors.fromHex("#F7790A"), 540, -1) , 0xFFFF0000, 100);
	public static final Tile ROAD1 = new BaseTile(6, 0, 2, Colors.get(-1, 000,
			-1, -1), 0xFF565656);
	public static final Tile ROAD2 = new BaseTile(7, 1, 2, Colors.get(-1, 000,
			Colors.fromHex("#BFAD47"), -1), 0xFFE9E400);
	public static final Tile WATERSAND = new AnimatedTile(8, new int[][] {
			{ 0, 14 }, { 1, 14 }, { 2, 14 }, { 3, 14 }, { 2, 14 }, { 1, 14 } },
			Colors.get(-1, Colors.fromHex("#DBEDD2"), 115, -1), 0xFF64FFFF, 1000);
	public static final Tile ROAD3 = new BaseTile(9, 2, 2, Colors.get(-1, 000,
			Colors.fromHex("#BFAD47"), -1), 0xFFFFE400);
	public static final Tile WOOD_WALL = new BasicSolidTile(10, 2, 0, Colors.get(-1,
			Colors.fromHex("#693609"), 000, -1), 0xFF6d4300);
	public static final Tile MUD = new BaseTile(11, 0, 2, Colors.get(-1, Colors.fromHex("#4a2d00"),
			-1, -1), 0xFF372201);
	public static final Tile FARMPLOT1 = new BaseTile(12, 2, 1, Colors.get(-1, Colors.fromHex("#0c6600"),
			Colors.fromHex("#5d3102"), -1), 0xFF1e6a00);
	public static final Tile FARMPLOT2 = new BaseTile(13, 3, 1, Colors.get(-1, Colors.fromHex("#0c6600"),
			Colors.fromHex("#5d3102"), -1), 0xFF123f00);
	public static final Tile DIRTROAD = new BaseTile(14, 0, 2, Colors.get(-1, Colors.fromHex("#935409"),-1, -1),
			0xFFa06201);
	public static final Tile BRICKROAD = new BaseTile(15, 1, 0, Colors.get(-1, Colors.fromHex("#d40000"),
			Colors.fromHex("#b40000"), -1), 0xFFa50000);
	public static final Tile GRASS_FLOWER = new BaseTile(16, 5, 1, Colors.get(-1, 131,
			141, 444), 0xFF15df00);
	public static final Tile GRASS2 = new BaseTile(17, 1, 1, Colors.get(-1, 131,
			141, -1), 0xFF00FF00);
	public static final Tile GRASS3 = new BaseTile(18, 4, 1, Colors.get(-1, 131,
			141, -1), 0xFF00FF00);
	public static final Tile CAVEWALL = new BasicSolidTile(19, 4, 0, Colors.get(-1, Colors.fromHex("#4a2d00"),
			Colors.fromHex("291900"), -1), 0xFF291900);


	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColour;
	private boolean hasMob;
	protected SpriteSheet sheet;

	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour,
			SpriteSheet sheet) {
		this.id = (byte) id;
		if (tiles[id] != null)
			throw new RuntimeException("Duplicate tile id on " + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColour = levelColour;
		tiles[id] = this;
		this.sheet = sheet;
	}

	public byte getId() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isEmitter() {
		return emitter;
	}

	public int getLevelColour() {
		return levelColour;
	}

	public boolean hasMob() {
		return hasMob;
	}

	public SpriteSheet getSpriteSheet() {
		return sheet;
	}

	public abstract void tick();

	public abstract void render(Screen screen, Level level, int x, int y);

}

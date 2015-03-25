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

	public static final Tile VOID = new BasicSolidTile(0, 0, 0, new int[] {
			0xFF000000, 0xFF000000, 0xFF000000 }, 0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0, new int[] {
			0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 }, 0xFF555555);
	public static final Tile GRASS = new BaseTile(2, 0, 1, new int[] {
			0xFF339933, 0xFF33BB33, 0xFF000000 }, 0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3, new int[][] {
			{ 0, 14 }, { 1, 14 }, { 2, 14 }, { 3, 14 }, { 2, 14 }, { 1, 14 } },
			new int[] { 0xFF0000BB, 0xFF2222EE, 0xFF000000 }, 0xFF0000FF, 800);
	public static final Tile SAND = new BaseTile(4, 0, 1, new int[] {
			0xFFEEBB00, 0xFFFFFF00, 0xFF000000 }, 0xFFFFFF00);
	public static final Tile ROAD1 = new BaseTile(6, 0, 2, new int[] {
			0xFF000000, 0xFF000000, 0xFF000000 }, 0xFF565656);
	public static final Tile ROAD2 = new BaseTile(7, 1, 2, new int[] {
			0xFF000000, 0xFFBFAD47, 0xFF000000 }, 0xFFE9E400);
	public static final Tile WATERSAND = new AnimatedTile(8, new int[][] {
			{ 0, 14 }, { 1, 14 }, { 2, 14 }, { 3, 14 }, { 2, 14 }, { 1, 14 } },
			new int[] { 0xFFDBEDD2, 0xFF2222FF, 0xFF000000 }, 0xFF64FFFF, 1000);
	public static final Tile ROAD3 = new BaseTile(9, 2, 2, new int[] {
			0xFF000000, 0xFFBFAD47, 0xFF000000 }, 0xFFFFE400);
	public static final Tile MUD = new BaseTile(11, 0, 2, new int[] {
			0xFF4A2D00, 0xFF000000, 0xFF000000 }, 0xFF372201);
	public static final Tile FARMPLOT1 = new BaseTile(12, 2, 1, new int[] {
			0xFF0C6600, 0xFF5D3102, 0xFF000000 }, 0xFF1E6A00);
	public static final Tile FARMPLOT2 = new BaseTile(13, 3, 1, new int[] {
			0xFF0C6600, 0xFF5D3102, 0xFF000000 }, 0xFF123F00);
	public static final Tile DIRTROAD = new BaseTile(14, 0, 2, new int[] {
			0xFF935409, 0xFF000000, 0xFF000000 }, 0xFFA06201);
	public static final Tile BRICKROAD = new BaseTile(15, 1, 0, new int[] {
			0xFFD40000, 0xFFB40000, 0xFF000000 }, 0xFFA50000);
	public static final Tile GRASS_FLOWER = new BaseTile(16, 5, 1, new int[] {
			0xFF339933, 0xFF33BB33, 0xFFFFFFFF }, 0xFF15DF00);
	public static final Tile GRASS2 = new BaseTile(17, 1, 1, new int[] {
			0xFF339933, 0xFF33BB33, 0xFF000000 }, 0xFF00FF00);
	public static final Tile GRASS3 = new BaseTile(18, 4, 1, new int[] {
			0xFF339933, 0xFF33BB33, 0xFF000000 }, 0xFF00FF00);
	public static final Tile CAVEWALL = new BasicSolidTile(19, 4, 0, new int[] {
			0xFF92928D, 0xFF636361, 0xFF000000 }, 0xFF291900);
	public static final Tile CAVEFLOOR = new BaseTile(20, 0, 2, new int[] {
			0xFFAEAEAD, 0xFF000000, 0xFF000000 }, 0xFFC3BEB7);
	public static final Tile WOODFLOOR = new BaseTile(21, 6, 0, new int[] {
			0xFF6D4300, 0xFF3E1900, 0xFF000000 }, 0xFF944500);
	public static final Tile METALWALL = new BasicSolidTile(22, 5, 0,
			new int[] { 0xFFA6A6A6, 0xFF939393, 0xFF4B3618 }, 0xFFB6B6B6);
	public static final Tile RIGHTCARPET = new BaseTile(23, 7, 1, new int[] {
			0xFFA40101, 0xFFEBCB00, 0xFF000000 }, 0xFFBE03DB);
	public static final Tile LEFTCARPET = new BaseTile(24, 6, 1, new int[] {
			0xFFA40101, 0xFFEBCB00, 0xFF000000 }, 0xFFEC00EC);
	public static final Tile TOPCARPET = new BaseTile(26, 9, 1, new int[] {
			0xFFA40101, 0xFFEBCB00, 0xFF000000 }, 0xFFFF00FF);
	public static final Tile BOTTOMCARPET = new BaseTile(27, 8, 1, new int[] {
			0xFFA40101, 0xFFEBCB00, 0xFF000000 }, 0xFF6600A7);

	public static final Tile WOOD_WALL_HORIZONTAL_UP = new BasicSolidTile(10,
			7, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B }, 0xFF6D4300);
	public static final Tile WOOD_WALL_HORIZONTAL_DOWN = new BasicSolidTile(28,
			8, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B }, 0xFF835100);
	public static final Tile WOOD_WALL_VERTICAL_LEFT = new BasicSolidTile(29,
			9, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B }, 0xFF5A3801);
	public static final Tile WOOD_WALL_VERTICAL_RIGHT = new BasicSolidTile(30,
			10, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B }, 0xFF472C00);
	public static final Tile WOOD_WALL_CORNER_RIGHT_UP = new BasicSolidTile(31,
			11, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B }, 0xFF520101);
	public static final Tile WOOD_WALL_CORNER_LEFT_UP = new BasicSolidTile(32,
			12, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B }, 0xFF420101);
	public static final Tile WOOD_WALL_CORNER_RIGHT_DOWN = new BasicSolidTile(
			33, 14, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B },
			0xFF7A0000);
	public static final Tile WOOD_WALL_CORNER_LEFT_DOWN = new BasicSolidTile(
			34, 13, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B },
			0xFF310000);
	public static final Tile WOOD_WALL_INSIDE_CORNER_RIGHT_UP = new BasicSolidTile(
			43, 15, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B },
			0xFFA3881A);
	public static final Tile WOOD_WALL_INSIDE_CORNER_LEFT_UP = new BasicSolidTile(
			44, 16, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B },
			0xFF7C6816);
	public static final Tile WOOD_WALL_INSIDE_CORNER_RIGHT_DOWN = new BasicSolidTile(
			45, 16, 0, new int[] { 0xFF693609, 0xFF7C3F0F, 0xFF48260B },
			0xFF717C16);

	public static final Tile GLASS_WALL_HORIZONTAL_UP = new BasicSolidTile(35,
			7, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF016ABA);
	public static final Tile GLASS_WALL_HORIZONTAL_DOWN = new BasicSolidTile(
			36, 8, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA },
			0xFF027FDF);
	public static final Tile GLASS_WALL_VERTICAL_LEFT = new BasicSolidTile(37,
			9, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF5A3801);
	public static final Tile GLASS_WALL_VERTICAL_RIGHT = new BasicSolidTile(38,
			10, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF0256CC);
	public static final Tile GLASS_WALL_CORNER_RIGHT_UP = new BasicSolidTile(
			39, 11, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA },
			0xFF0B9401);
	public static final Tile GLASS_WALL_CORNER_LEFT_UP = new BasicSolidTile(40,
			12, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA }, 0xFF420101);
	public static final Tile GLASS_WALL_CORNER_RIGHT_DOWN = new BasicSolidTile(
			41, 14, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA },
			0xFF087101);
	public static final Tile GLASS_WALL_CORNER_LEFT_DOWN = new BasicSolidTile(
			42, 13, 0, new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA },
			0xFFFFB400);

	public static final Tile Floor_Carpet_1 = new BaseTile(46, 10, 1,
			new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 }, 0xFF1f7a01);
	public static final Tile Floor_Carpet_2 = new BaseTile(47, 11, 1,
			new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 }, 0xFF1F7A01);
	public static final Tile Floor_Carpet_3 = new BaseTile(48, 12, 1,
			new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 }, 0xFF1F7A01);

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

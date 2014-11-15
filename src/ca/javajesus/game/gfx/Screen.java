package ca.javajesus.game.gfx;

import ca.javajesus.game.Game;

public class Screen {

	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

	/** 0xA indicates it will be used in binary operations, where A = an integer */
	public static final byte BIT_MIRROR_X = 0x01;
	public static final byte BIT_MIRROR_Y = 0x02;

	public int[] pixels;

	public int xOffset = 0;
	public int yOffset = 0;

	public int width;
	public int height;

	private Game game;

	public Screen(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		this.game = game;
		pixels = new int[width * height];

	}

	/** Renders things onto the screen
	 * @param d : The x position on the screen in which the tiles are rendered
	 * @param yOffset2 : The y position on the screen in which the tiles are rendered
	 * @param tile : the tile number on the SpriteSheet, used with (xTile) + (yTile) * 32
	 * @param colour : The color of the sprite. Use with Colours.get()
	 * @param mirrorDir : The direction the sprite is facing
	 * @param scale : How large the object is, usually 1
	 * @param sheet : The SpriteSheet that java will check for using the Tile parameter
	 */
	public void render(double d, double yOffset2, int tile, int colour,
			int mirrorDir, double scale, SpriteSheet sheet) {

		// WTF Mode
		//sheet.height = 0; sheet.width = 0;

		d -= xOffset;
		yOffset2 -= yOffset;

		boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
		boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;

		double scaleMap = scale - 1;
		int xTile = tile % 32;
		int yTile = tile / 32;
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;
		for (int y = 0; y < 8; y++) {
			int ySheet = y;
			if (mirrorY)
				ySheet = 7 - y;
			int yPixel = (int) (y + yOffset2 + (y * scaleMap) - ((scaleMap * 8) / 2));
			for (int x = 0; x < 8; x++) {
				int xPixel = (int) (x + d + (x * scaleMap) - ((scaleMap * 8) / 2));
				int xSheet = x;
				if (mirrorX)
					xSheet = 7 - x;

				int col = (colour >> (sheet.pixels[xSheet + ySheet
						* sheet.width + tileOffset] * 8)) & 255;
				if (col < 255) {
					for (int yScale = 0; yScale < scale; yScale++) {
						if (yPixel + yScale < 0 || yPixel + yScale >= height)
							continue;
						for (int xScale = 0; xScale < scale; xScale++) {
							if (x + d < 0 || x + d >= width)
								continue;
							pixels[(xPixel + xScale) + (yPixel + yScale)
									* width] = col;

						}
					}
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public Game getGame() {
		return game;
	}
}

package ca.javajesus.game.gfx;

import ca.javajesus.game.Game;

public class Screen {

	// adding a FF in front of a hex string converts RGB to Hexadecimal
	// ex 0xFF00FF - > 0xFFFF00FF

	// Width of Tiles
	public static final int MAP_WIDTH = 64;
	// Used in Binary bitwise looping operations with &
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

	/** 0xA indicates it will be used in binary operations, where A = an integer */
	public static final byte BIT_MIRROR_X = 0x01;
	public static final byte BIT_MIRROR_Y = 0x02;

	public int[] pixels;
	public int[] colors = new int[MAP_WIDTH * MAP_WIDTH];

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

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset, int tile, int[] color,
			int mirrorDir, SpriteSheet sheet) {
		render(xOffset, yOffset, tile, color, mirrorDir, 1, sheet);
	}

	public void render(int xOffset, int yOffset, int tile, int[] color,
			int mirrorDir, int scale, SpriteSheet sheet) {

		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
		boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;

		int scaleMap = scale - 1;
		int xTile = tile % sheet.boxes;
		int yTile = tile / sheet.boxes;
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;
		for (int y = 0; y < 8; y++) {
			int ySheet = y;
			if (mirrorY)
				ySheet = 7 - y;
			int yPixel = y + yOffset + (y * scaleMap) - ((scaleMap << 3) / 2);
			for (int x = 0; x < 8; x++) {
				int xPixel = x + xOffset + (x * scaleMap)
						- ((scaleMap << 3) / 2);
				int xSheet = x;
				if (mirrorX)
					xSheet = 7 - x;
				int col = sheet.pixels[tileOffset + xSheet + ySheet
						* sheet.width];
				switch (col) {
				case 0xFF555555: {
					col = color[0];
					break;
				}
				case 0xFFAAAAAA: {
					col = color[1];
					break;
				}
				case 0xFFFFFFFF: {
					col = color[2];
					break;
				}
				}
				if (col != 0xFF000000)
					for (int yScale = 0; yScale < scale; yScale++) {
						if (yPixel + yScale < 0 || yPixel + yScale >= height)
							continue;
						for (int xScale = 0; xScale < scale; xScale++) {
							if (x + xOffset < 0 || x + xOffset >= width)
								continue;
							pixels[(xPixel + xScale) + (yPixel + yScale)
									* width] = col;

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

	/** Used for rendering large entities */
	public void render(int xOffset, int yOffset, int color, Sprite sprite) {

		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		for (int y = 0; y < sprite.ySize; y++) {
			int yPixel = (int) (y + yOffset);
			for (int x = 0; x < sprite.xSize; x++) {
				int xPixel = (int) (x + xOffset);
				int col = (color >> (sprite.pixels[x + y * sprite.xSize] * 8)) & 255;
				if (col < 255) {
					if (xPixel >= 0 && yPixel >= 0 && xPixel < width
							&& yPixel < height)
						pixels[(xPixel) + (yPixel) * width] = col;
				}
			}
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			if (yy < 0 || yy >= height)
				break;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				if (xx < 0 || xx >= width)
					break;
				// x >> 4 adjusts the size of the tiles rendered (resolution)
				// smaller = higher res (4 is 16 bit) (3 is 8bit)
				// & = bitwise AND operator = this loops the values around to
				// the first value so it does not exceed the specified value
				// if you dont loop then it will be very choppy, not smooth
				int tileIndex = ((xx >> 3) & MAP_WIDTH_MASK)
						+ ((yy >> 3) & MAP_WIDTH_MASK) * MAP_WIDTH;
				pixels[x + y * width] = colors[tileIndex];

			}
		}
	}
}

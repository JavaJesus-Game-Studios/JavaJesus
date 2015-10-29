package game.graphics;

import java.awt.Color;
import java.util.Random;

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

	private int shader = 0;

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
	
	/**
	 * @param xOffset
	 * @param yOffset
	 * @param tile
	 * @param color
	 * @param mirrorDir Determines the direction the pixels are rendered, usually from left to right. Mirror Dir renders from right to left.
	 * @param sheet
	 */

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
				if (color != null)
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
							if (xPixel + xScale < 0 || xPixel + xScale >= width)
								continue;
							pixels[(xPixel + xScale) + (yPixel + yScale)
									* width] = (shader > 0) ? blend(col,
									shader, 0.5) : col;

						}
					}

			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/** Used for rendering large entities */
	public void render(int xOffset, int yOffset, int[] color, Sprite sprite) {

		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		for (int y = 0; y < sprite.ySize; y++) {
			int yPixel = (int) (y + yOffset);
			for (int x = 0; x < sprite.xSize; x++) {
				int xPixel = (int) (x + xOffset);
				if (xPixel >= 0 && yPixel >= 0 && xPixel < width
						&& yPixel < height) {
					int col = sprite.pixels[x + y * sprite.xSize];
					if (color != null)
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
						pixels[(xPixel) + (yPixel) * width] = (shader > 0) ? blend(
								col, shader, 0.5) : col;
				}

			}
		}
	}

	public void setShader(int color) {
		this.shader = color;
	}

	public static int blend(int color1, int color2, double ratio) {
		Color color3 = new Color(color1);
		Color color4 = new Color(color2);

		float r = (float) ratio;
		float ir = (float) 1.0 - r;

		float rgb1[] = new float[3];
		float rgb2[] = new float[3];

		color3.getColorComponents(rgb1);
		color4.getColorComponents(rgb2);

		Color color = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r
				+ rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);

		return color.getRGB();
	}
	
	/** Blends the pixels of Tiles */
	public void renderBlended(int xOffset, int yOffset, int tile, int[] color,
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
				if (color != null)
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
				//col += new Random().nextInt(100) - 50;
				if (col != 0xFF000000)
					for (int yScale = 0; yScale < scale; yScale++) {
						if (yPixel + yScale < 0 || yPixel + yScale >= height)
							continue;
						for (int xScale = 0; xScale < scale; xScale++) {
							if (xPixel + xScale < 0 || xPixel + xScale >= width)
								continue;
							pixels[(xPixel + xScale) + (yPixel + yScale)
									* width] = (shader > 0) ? blend(col,
									shader, 0.5) : col;

						}
					}

			}
		}
	}
}

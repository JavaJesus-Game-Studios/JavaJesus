package game.graphics;

import game.Game;

/*
 * Handles the pixel manipulation and data that is displayed on the screen elsewhere
 */
public class Screen {

	// the pixels in the screen
	private int[] pixels;

	// will offset the position of the screen
	private int xOffset, yOffset;

	// the dimensions of the screen
	private int width, height;

	// blends all the screen pixels by this decimal color
	private int shader;

	/**
	 * Creates a new screen that can modify pixels used for display elsewhere
	 * @param width width of the screen
	 * @param height height of the screen
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

	}

	/**
	 * Resets the pixels on the screen
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	/**
	 * Displays a tile at a particular position
	 * @param xOffset the x coordinate on the level
	 * @param yOffset the y coordinate on the level
	 * @param tile the sprite sheet offset to use
	 * @param color the color set
	 * @param mirror if the pixels should be rendered right to left
	 * @param sheet the sprite sheet to use
	 */
	public void render(int xOffset, int yOffset, int tile, int[] color,
			boolean mirror, SpriteSheet sheet) {
		render(xOffset, yOffset, tile, color, mirror, 1, sheet);
	}
	
	/**
	 * Displays a tile at a particular position
	 * @param xOffset the x coordinate on the level
	 * @param yOffset the y coordinate on the level
	 * @param tile the sprite sheet offset to use
	 * @param color the color set
	 * @param sheet the sprite sheet to use
	 */
	public void render(int xOffset, int yOffset, int tile, int[] color, SpriteSheet sheet) {
		render(xOffset, yOffset, tile, color, false, 1, sheet);
	}
	
	/**
	 * Displays a tile at a particular position
	 * @param xOffset the x coordinate on the level
	 * @param yOffset the y coordinate on the level
	 * @param tile the sprite sheet offset to use
	 * @param color the color set
	 * @param mirror if the pixels should be rendered right to left
	 * @param scale modifier to how big the sprite should be displayed
	 * @param sheet the sprite sheet to use
	 */
	public void render(int xOffset, int yOffset, int tile, int[] color,
			boolean mirror, int scale, SpriteSheet sheet) {
		this.render(xOffset, yOffset, tile, color, mirror, false, scale, sheet);
	}

	/**
	 * Displays a tile at a particular position
	 * @param xOffset the x coordinate on the level
	 * @param yOffset the y coordinate on the level
	 * @param tile the sprite sheet offset to use
	 * @param color the color set
	 * @param mirror if the pixels should be rendered right to left
	 * @param mirrorY if the pixels should be rendered bottom to top
	 * @param scale modifier to how big the sprite should be displayed
	 * @param sheet the sprite sheet to use
	 */
	public void render(int xOffset, int yOffset, int tile, int[] color,
			boolean mirror, boolean mirrorY, int scale, SpriteSheet sheet) {

		// shifts the position of the screen by the global offset
		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		int scaleMap = scale - 1;
		int xTile = tile % sheet.getNumBoxes();
		int yTile = tile / sheet.getNumBoxes();
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.getWidth();
		for (int y = 0; y < 8; y++) {
			int ySheet = y;
			
			//Mirrors bottom to top
			if (mirrorY)
				ySheet = 7 - y;
			
			int yPixel = y + yOffset + (y * scaleMap) - ((scaleMap << 3) / 2);
			for (int x = 0; x < 8; x++) {
				int xPixel = x + xOffset + (x * scaleMap)
						- ((scaleMap << 3) / 2);
				int xSheet = x;
				
				// Mirrors right to left
				if (mirror)
					xSheet = 7 - x;
				
				int col = sheet.getPixels()[tileOffset + xSheet + ySheet
						* sheet.getWidth()];
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
				if (Game.getDisplayDevScreen() && col == 0xFF000000){ // shows blank space
					col = 0xFFFF0000;
				}
				if (col != 0xFF000000)
					for (int yScale = 0; yScale < scale; yScale++) {
						if (yPixel + yScale < 0 || yPixel + yScale >= height)
							continue;
						for (int xScale = 0; xScale < scale; xScale++) {
							if (xPixel + xScale < 0 || xPixel + xScale >= width)
								continue;
							pixels[(xPixel + xScale) + (yPixel + yScale)
									* width] = (shader > 0) ? Colors.blend(col,
									shader, 0.5) : col;

						}
					}

			}
		}
	}

	/**
	 * Changes the offset of the screen
	 * @param xOffset the new horizontal offset
	 * @param yOffset the new vertical offset
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Displays a large building or object on the screen
	 * @param xOffset the x coordinate on the level
	 * @param yOffset the y coordinate on the level
	 * @param color the color set
	 * @param sprite the sprite to use
	 */
	public void render(int xOffset, int yOffset, int[] color, Sprite sprite) {

		// shifts the position of the screen by the global offset
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
						pixels[(xPixel) + (yPixel) * width] = (shader > 0) ? Colors.blend(
								col, shader, 0.5) : col;
				}

			}
		}
	}

	/**
	 * @param color the color of the new shader in decimal
	 */
	public void setShader(int color) {
		this.shader = color;
	}

	/** Blends the pixels of Tiles */
	public void renderBlended(int xOffset, int yOffset, int tile, int[] color,
			boolean mirror, int scale, SpriteSheet sheet) {

		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		int scaleMap = scale - 1;
		int xTile = tile % sheet.getNumBoxes();
		int yTile = tile / sheet.getNumBoxes();
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.getWidth();
		for (int y = 0; y < 8; y++) {
			int ySheet = y;
			/*
			if (mirrorY)
				ySheet = 7 - y; */
			int yPixel = y + yOffset + (y * scaleMap) - ((scaleMap << 3) / 2);
			for (int x = 0; x < 8; x++) {
				int xPixel = x + xOffset + (x * scaleMap)
						- ((scaleMap << 3) / 2);
				int xSheet = x;
				if (mirror)
					xSheet = 7 - x;
				int col = sheet.getPixels()[tileOffset + xSheet + ySheet
						* sheet.getWidth()];
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
									* width] = (shader > 0) ? Colors.blend(col,
									shader, 0.5) : col;

						}
					}

			}
		}
	}

	public int[] getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

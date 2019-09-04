package javajesus.graphics;

import java.awt.Rectangle;

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

	// base size of a unit tile on the spritesheet
	private static final int SIZE = 8;
	
	// bit shift constant = log2(SIZE)
	private static final int SHIFT = 3;

	/**
	 * Creates a new screen that can modify pixels used for display elsewhere
	 * 
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
	 * Sets the pixels from the spritesheet to this screen's internal pixel
	 * array
	 * 
	 * @param xOffset - x coordinate on the level
	 * @param yOffset - y coordinate on the level
	 * @param xTile - x tile on the spritesheet
	 * @param yTile - y tile on the spritesheet
	 * @param sheet - the Spritesheet to use
	 * @param mirror - whether or not to flip the x axis
	 * @param color - color set of the spritesheet
	 */
	public void render(int xOffset, int yOffset, int xTile, int yTile,
			SpriteSheet sheet, boolean mirror, int[] color) {

		// shifts the position of the screen by the global offset
		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		// loop top to bottom
		for (int y = 0; y < SIZE; y++) {

			// pixel location in pixel array
			int yPixel = y + yOffset;

			// loop left to right
			for (int x = 0; x < SIZE; x++) {

				// pixel location in pixel array
				int xPixel = x + xOffset;

				// pixel on spritesheet to use
				int xSheet = x;

				// flips x axis
				if (mirror) {
					// flip the pixel used on the spritesheet
					xSheet = (SIZE - 1) - x;
				}

				// color of pixel at spritesheet coordinates
				int col = sheet.getPixels()[((xTile << SHIFT) + xSheet + ((yTile << SHIFT) + y) * sheet.getWidth())];

				// assign the color based on color set
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
				case 0xFFE0E0E0: {
					col = color[3];
					break;
				}
				case 0xFF7A7A7A: {
					col = color[4];
					break;
				}
				}

				// only render if color is not pure black and in pixel bounds
				if (col != 0xFF000000 && yPixel >= 0 && yPixel < height
						&& xPixel >= 0 && xPixel < width) {

					// assign the color to the pixel array
					pixels[xPixel + yPixel * width] = col;

				}

			}
		}
	}
	
	/**
	 * Sets the pixels from the spritesheet to this screen's internal pixel
	 * array
	 * 
	 * @param xOffset - x coordinate on the level
	 * @param yOffset - y coordinate on the level
	 * @param xTile - x tile on the spritesheet
	 * @param yTile - y tile on the spritesheet
	 * @param sheet - the Spritesheet to use
	 * @param mirror - whether or not to flip the x axis
	 */
	public void render(int xOffset, int yOffset, int xTile, int yTile, SpriteSheet sheet, boolean mirror) {

		// shifts the position of the screen by the global offset
		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		// loop top to bottom
		for (int y = 0; y < SIZE; y++) {

			// pixel location in pixel array
			int yPixel = y + yOffset;

			// loop left to right
			for (int x = 0; x < SIZE; x++) {

				// pixel location in pixel array
				int xPixel = x + xOffset;

				// pixel on spritesheet to use
				int xSheet = x;

				// flips x axis
				if (mirror) {
					// flip the pixel used on the spritesheet
					xSheet = (SIZE - 1) - x;
				}

				// color of pixel at spritesheet coordinates
				int col = sheet.getPixels()[((xTile << SHIFT) + xSheet + ((yTile << SHIFT) + y) * sheet.getWidth())];

				// only render if color is not pure black and in pixel bounds
				if (col != 0xFF000000) {

					// assign the color to the pixel array
					pixels[xPixel + yPixel * width] = col;

				}

			}
		}
	}
	
	/**
	 * Sets the pixels from the spritesheet to this screen's internal pixel
	 * array
	 * 
	 * @param xOffset - x coordinate on the level
	 * @param yOffset - y coordinate on the level
	 * @param xTile - x tile on the spritesheet
	 * @param yTile - y tile on the spritesheet
	 * @param sheet - the Spritesheet to use
	 * @param color - color set of the spritesheet
	 * @param scale - the size modifier
	 */
	public void render(int xOffset, int yOffset, int xTile, int yTile,
			SpriteSheet sheet, int[] color, int scale) {

		// shifts the position of the screen by the global offset
		xOffset -= this.xOffset;
		yOffset -= this.yOffset;
		
		// loop top to bottom
		for (int y = 0; y < SIZE; y++) {

			// pixel location in pixel array
			int yPixel = (y * scale) + yOffset;

			// loop left to right
			for (int x = 0; x < SIZE; x++) {

				// pixel location in pixel array
				int xPixel = (x * scale) + xOffset;

				// color of pixel at spritesheet coordinates
				int col = sheet.getPixels()[((xTile << SHIFT) + x + ((yTile << SHIFT) + y) * sheet.getWidth())];

				// assign the color based on color set
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
					case 0xFFE0E0E0: {
						col = color[3];
						break;
					}
					case 0xFF7A7A7A: {
						col = color[4];
						break;
					}
					}
				
				// only render if color is not pure black and in pixel bounds
				if (col != 0xFF000000) {

					// assign scale amount of pixels
					for (int i = 0; i < scale ; i++) {
						for (int j = 0; j < scale; j++) {
							if (yPixel + i >= 0 && yPixel + i < height && xPixel + j >= 0 && xPixel + j < width) {
								// assign the color to the pixel array
								pixels[xPixel + j + (yPixel + i) * width] = col;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Changes the offset of the screen
	 * 
	 * @param xOffset the new horizontal offset
	 * @param yOffset the new vertical offset
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Displays a large building or object on the screen
	 * 
	 * @param xOffset the x coordinate on the level
	 * @param yOffset the y coordinate on the level
	 * @param color the color set
	 * @param sprite the sprite to use
	 */
	public void render(int xOffset, int yOffset, int[] color, Sprite sprite) {

		// shifts the position of the screen by the global offset
		xOffset -= this.xOffset;
		yOffset -= this.yOffset;

		// loop top to bottom
		for (int y = 0; y < sprite.ySize; y++) {

			// y coord in the pixel array
			int yPixel = y + yOffset;

			// loop left to right
			for (int x = 0; x < sprite.xSize; x++) {

				// x coord in the pixel array
				int xPixel = x + xOffset;

				// make sure the pixel is in bounds
				if (xPixel >= 0 && yPixel >= 0 && xPixel < width
						&& yPixel < height) {

					// get the color from the sprite
					int col = sprite.pixels[x + y * sprite.xSize];

					// assign the color value
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
						case 0xFFE0E0E0: {
							col = color[3];
							break;
						}
						case 0xFF7A7A7A: {
							col = color[4];
							break;
						}
						}

					// set the color value if not black
					if (col != 0xFF000000) {
						pixels[xPixel + yPixel * width] = col;
					}
				}

			}
		}
	}

	/**
	 * Renders a collision box to the screen
	 * 
	 * @param r - the rectangle to render
	 */
	public void renderCollisionBox(Rectangle r) {

		// shifts the position of the screen by the global offset
		int xOffset = (int) r.getX() - this.xOffset;
		int yOffset = (int) r.getY() - this.yOffset;

		// bounds of the rectangle
		int width = (int) r.getWidth();
		int height = (int) r.getHeight();

		// left line
		if (xOffset >= 0 && xOffset < this.width) {
			for (int i = 0; i < height; i++) {
				if (i + yOffset >= 0 && i + yOffset < this.height) {
					pixels[xOffset + (i + yOffset) * this.width] = 0xFF000000;
				}
			}
		}

		// right line
		if (xOffset + width - 1 >= 0 && xOffset + width - 1 < this.width) {
			for (int i = 0; i < height; i++) {
				if (i + yOffset >= 0 && i + yOffset < this.height) {
					pixels[(xOffset + width - 1) + (i + yOffset) * this.width] = 0xFF000000;
				}
			}
		}

		// top line
		if (yOffset >= 0 && yOffset < this.height) {
			for (int i = 0; i < width; i++) {
				if (i + xOffset >= 0 && i + xOffset < this.width) {
					pixels[(i + xOffset) + yOffset * this.width] = 0xFF000000;
				}
			}
		}

		// bottom line
		if (yOffset + height - 1 >= 0 && yOffset + height - 1 < this.height) {
			for (int i = 0; i < width; i++) {
				if (i + xOffset >= 0 && i + xOffset < this.width) {
					pixels[(i + xOffset) + (yOffset + height - 1) * this.width] = 0xFF000000;
				}
			}
		}

	}

	/**
	 * Renders the sprite in 24 bit
	 * 
	 * @param screen - the screen to display it on
	 */
	public void render24bit(int xTile, int yTile, SpriteSheet sheet) {

		// size of each box
		int modifier = SIZE;

		// 24 bit so multiply by 3
		xTile *= 3;
		yTile *= 3;

		// top to bottom
		for (int i = 0; i < 3; i++) {

			// left to right
			for (int j = 0; j < 3; j++) {

				// render the box
				render(modifier * j, modifier * i, xTile + j, yTile + i, sheet, false);

			}
		}

	}
	
	/**
	 * Renders the sprite in 48 bit
	 * 
	 * @param screen - the screen to display it on
	 */
	public void render48bit(int xTile, int yTile, SpriteSheet sheet) {

		// size of each box
		int modifier = SIZE;

		// 24 bit so multiply by 3
		xTile *= 6;
		yTile *= 6;

		// top to bottom
		for (int i = 0; i < 6; i++) {

			// left to right
			for (int j = 0; j < 6; j++) {

				// render the box
				render(modifier * j, modifier * i, xTile + j, yTile + i, sheet, false);

			}
		}

	}
	
	/**
	 * Renders the sprite in 16 bit
	 * Does NOT flip
	 * 
	 * @param screen - the screen to display it on
	 */
	public void render16bit(int xOffset, int yOffset, int xTile, int yTile, SpriteSheet sheet, int[] color) {

		// size of each box
		int modifier = SIZE;

		// 16 bit so multiply by 2
		//xTile *= 2;
		//yTile *= 2;

		// top to bottom
		for (int i = 0; i < 2; i++) {

			// left to right
			for (int j = 0; j < 2; j++) {

				// render the box
				render(xOffset + modifier * j, yOffset + modifier * i, xTile + j, yTile + i, sheet, false, color);

			}
		}

	}
	
	/**
	 * Renders another screen onto this screen
	 * 
	 * @param screen - screen whos pixels will be transfered here
	 * @param xOffset - x offset of the level
	 * @param yOffset - y offset of the level
	 */
	public void render(Screen screen, int xOffset, int yOffset) {
		
		// shifts the position of the screen by the global offset
		xOffset -= this.xOffset;
		yOffset -= this.yOffset;
		
		// transfer the pixels
		for (int i = 0; i < screen.getPixels().length; i++) {
			
			// get the coordinate
			int x = i % screen.getWidth();
			int y = i / screen.getHeight();
			
			int col = screen.getPixels()[i];
			int pixel = (xOffset + x) + (y + yOffset) * this.width;
			
			if (col != 0 && pixel >= 0 && pixel < pixels.length) {
				// assign the pixel
				pixels[pixel] = col;
			}
			
		}
	}
	
	/**
	 * renderChunk()
	 * Renders one 8x8 tile of specified position 
	 * on the new screen to THIS screen
	 * 
	 * @param screen - the screen that will be used
	 * to render the new tile pixels HERE
	 * @param xTile - the xTile
	 * @param yTile - the yTile
	 */
	public void renderChunk(Screen screen, int xTile, int yTile) {
		
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				
				// new pixel to render
				int col = screen.getPixels()[(x + xTile * SIZE) + (y + yTile * SIZE) * screen.getWidth()];
				
				// don't render black
				if (col != 0) {
					pixels[x + y * width] = col;
				}
				
			}
		}
		
	}

	/**
	 * @return Screen pixels
	 */
	public int[] getPixels() {
		return pixels;
	}

	/**
	 * @return Screen width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return Screen height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the xOffset
	 */
	public final int getxOffset() {
		return xOffset;
	}

	/**
	 * @return the yOffset
	 */
	public final int getyOffset() {
		return yOffset;
	}
}

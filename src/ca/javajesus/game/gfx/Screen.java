package ca.javajesus.game.gfx;

import ca.javajesus.game.Game;

public class Screen {

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

	private int spriteSize;

	private Game game;

	public Screen(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		this.game = game;
		pixels = new int[width * height];
		this.spriteSize = 8;

	}
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.spriteSize = 8;

	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	/**
	 * Renders things onto the screen
	 * 
	 * @param d
	 *            : The x position on the screen in which the tiles are rendered
	 * @param yOffset2
	 *            : The y position on the screen in which the tiles are rendered
	 * @param tile
	 *            : the tile number on the SpriteSheet, used with (xTile) +
	 *            (yTile) * 32
	 * @param color
	 *            : The color of the sprite. Use with colors.get()
	 * @param mirrorDir
	 *            : The direction the sprite is facing
	 * @param scale
	 *            : How large the object is, usually 1
	 * @param sheet
	 *            : The SpriteSheet that java will check for using the Tile
	 *            parameter
	 */
	public void render(double d, double yOffset2, int tile, int color,
			int mirrorDir, double scale, SpriteSheet sheet) {

		d -= xOffset;
		yOffset2 -= yOffset;

		boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
		boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;

		double scaleMap = scale - 1;
		int xTile = tile % sheet.boxes;
		int yTile = tile / sheet.boxes;
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;
		for (int y = 0; y < spriteSize; y++) {
			int ySheet = y;
			if (mirrorY)
				ySheet = 7 - y;
			int yPixel = (int) (y + yOffset2 + (y * scaleMap) - ((scaleMap * spriteSize) / 2));
			for (int x = 0; x < spriteSize; x++) {
				int xPixel = (int) (x + d + (x * scaleMap) - ((scaleMap * spriteSize) / 2));
				int xSheet = x;
				if (mirrorX)
					xSheet = 7 - x;

				int col = (color >> (sheet.pixels[xSheet + ySheet
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
	
	/** Used for rendering entities reversed */
    public void render(int xOffset, int yOffset, int dir, int color, Sprite sprite) {

        xOffset -= this.xOffset;
        yOffset -= this.yOffset;
        
        switch(dir)
        {
        case 3:
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
            break;
        case 2:
            for (int y = 0; y < sprite.ySize; y++) {
                int yPixel = (int) (y + yOffset);
                for (int x = 0; x < sprite.xSize; x++) {
                    int xPixel = (int) (x + xOffset);
                    int col = (color >> (sprite.pixels[(x) + y * sprite.xSize] * 8)) & 255;
                    if (col < 255) {
                        if (xPixel >= 150 && yPixel >= 0 && xPixel < width
                                && yPixel < height)
                            pixels[(7 - xPixel) + (yPixel) * width] = col;
                        else
                        {
                            pixels[(150-xPixel) + (yPixel) * width] = col;
                        }
                    }
                }
            }            
            break;
        case 1:
            for (int y = 0; y < sprite.ySize; y++) {
                int yPixel = (int) (y + yOffset);
                for (int x = 0; x < sprite.xSize; x++) {
                    int xPixel = (int) (x + xOffset);
                    int col = (color >> (sprite.pixels[x + y * sprite.xSize] * 8)) & 255;
                    if (col < 255) {
                        if (xPixel >= 0 && yPixel >= 0 && xPixel < width
                                && yPixel < height)
                            pixels[(yPixel) + (xPixel) * width] = col;
                    }
                }
            }
            break;
        case 0:
            for (int y = 0; y < sprite.ySize; y++) {
                int yPixel = (int) (y + yOffset);
                for (int x = 0; x < sprite.xSize; x++) {
                    int xPixel = (int) (x + xOffset);
                    int col = (color >> (sprite.pixels[x + y * sprite.xSize] * 8)) & 255;
                    if (col < 255) {
                        if (xPixel >= 0 && yPixel >= 0 && xPixel < width
                                && yPixel < height)
                            pixels[(yPixel) + (xPixel) * width] = col;
                    }
                }
            }
            break;
        }
        
    }
    
    public void render() {
    	int xOffset = 0; int yOffset = 0;
    	for (int y = 0; y < height; y++) {
    		int yy = y + yOffset;
    		if (yy < 0 || yy >= height) break;
    		for (int x = 0; x < width; x++) {
    			int xx = x + xOffset;
    			if (xx < 0 || xx >= width) break;
    			// x >> 4 adjusts the size of the tiles rendered (resolution) smaller = higher res
    			// & = bitwise AND operator = this loops the values around to the first value so it does not exceed the specified value
    			// if you dont loop then it will be very choppy, not smooth
    			int tileIndex = ((xx >> 4) & MAP_WIDTH_MASK) + ((yy >> 4) & MAP_WIDTH_MASK) * MAP_WIDTH;
    			pixels[x + y * width] = colors[tileIndex];
    			
    		}
    	}
    }
}

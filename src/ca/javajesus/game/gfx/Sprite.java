package ca.javajesus.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public int xSize, ySize;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite house = new Sprite(32, 40, 0, 0, SpriteSheet.buildings);
	public static Sprite skyscraper = new Sprite(64, 216, 2, 0, SpriteSheet.buildings);
	public static Sprite sword = new Sprite("/Swords/GreatSword_Sheet 0.png");

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.xSize = size;
		this.ySize = size;
		pixels = new int[size * size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load(xSize, ySize);
	}

	public Sprite(int xSize, int ySize, int x, int y, SpriteSheet sheet) {
		this.xSize = xSize;
		this.ySize = ySize;
		pixels = new int[xSize * ySize];
		this.x = x * xSize;
		this.y = y * ySize;
		this.sheet = sheet;
		load(xSize, ySize);
	}
	
	public Sprite(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.xSize = image.getWidth();
        this.ySize = image.getHeight();
        pixels = image.getRGB(0, 0, xSize, ySize, null, 0, xSize);

       for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff) / 64;
        }
       
    }

	private void load(int xSize, int ySize) {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				pixels[x + y * xSize] = sheet.pixels[(x + this.x)
						+ (y + this.y) * sheet.width];
			}
		}
	}

}

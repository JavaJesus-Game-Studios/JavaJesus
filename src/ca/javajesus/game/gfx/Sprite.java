package ca.javajesus.game.gfx;

public class Sprite {

	public int xSize, ySize;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite house = new Sprite(32, 40, 0, 0, SpriteSheet.buildings);
	public static Sprite skyscraper = new Sprite(64, 216, 2, 0, SpriteSheet.buildings);

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

	private void load(int xSize, int ySize) {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				pixels[x + y * xSize] = sheet.pixels[(x + this.x)
						+ (y + this.y) * sheet.width];
			}
		}
	}

}

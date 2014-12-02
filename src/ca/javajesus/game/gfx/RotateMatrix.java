package ca.javajesus.game.gfx;

public class RotateMatrix {

	private int[] pixels;
	private int width;
	private int height;

	public RotateMatrix(Sprite sprite) {
		this.width = sprite.xSize;
		this.height = sprite.ySize;
		this.pixels = sprite.pixels;
	}

	public int[] flipHorizontal() {

		// Flips the sprite horizontally.
		int[] pixels = new int[this.pixels.length];
		int row, column;
		for (int i = 0; i < this.pixels.length; i++) {
			row = (i / width);
			column = (i % width);
			pixels[row * width + column] = this.pixels[(row + 1) * width
					- column - 1];
		}
		return pixels;
	}

	public int[] flipVertical() {

		// Flips the sprite vertically.
		int[] pixels = new int[this.pixels.length];
		int row, column;
		for (int i = 0; i < this.pixels.length; i++) {
			row = (i / width);
			column = (i % width);
			pixels[row * width + column] = this.pixels[(height - row - 1)
					* width + column];
		}
		return pixels;
	}

	// 90 Degrees rotation
	public void rotateCW() {

		// Rotates the sprite 90 degrees clockwise.
		int[] pixels = new int[this.pixels.length];
		int row, column;
		for (int i = 0; i < this.pixels.length; i++) {
			row = (i / width);
			column = (i % width);
			pixels[row * width + column] = this.pixels[(height - 1) * width
					- (((row * width + column) % height) * width)
					+ (row * width + column) / height];
		}
		int w = width;
		width = height;
		height = w;
		this.pixels = pixels;
	}

	// rotate 90 degrees counter clock wise
	public void rotateCCW() {

		// Rotates the sprite 90 degrees counter-clockwise.
		int[] pixels = new int[this.pixels.length];
		int row, column;
		for (int i = 0; i < this.pixels.length; i++) {
			row = (i / width);
			column = (i % width);
			pixels[row * width + column] = this.pixels[(width - 1)
					+ (((row * width + column) % height) * width)
					- (row * width + column) / height];
		}
		int w = width;
		width = height;
		height = w;
		this.pixels = pixels;
	}

	// 180 degree rotation
	public void rotate180() {

		// Rotates the sprite 180 degrees.
		int[] pixels = new int[this.pixels.length];
		for (int i = 0; i < this.pixels.length; i++) {
			pixels[i] = this.pixels[pixels.length - i - 1];
		}
		this.pixels = pixels;
	}
}

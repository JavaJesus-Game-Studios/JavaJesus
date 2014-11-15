package ca.javajesus.game.gfx;

import java.awt.Color;

public class Colors {
	
	/** Left Shift << Is the same as multiplying by 2 ^ n */

	/** Four Colors
	 * @param color1: Black, Hex Value:#000000, RGB: (0, 0, 0)
	 * @param color2: Dark, Hex Value: #555555, RGB: (85, 85, 85)
	 * @param color3: Light, Hex Value:#AAAAAA, RGB: (170, 170, 170)
	 * @param color4: White, Hex Value: #FFFFFF, RGB: (255, 255, 255)
	 * @return
	 */
	public static int get(int color1, int color2, int color3, int color4) {
		return ((get(color4) << 24) + (get(color3) << 16) + (get(color2) << 8) + (get(color1)));
	}

	/** Nine Colors */
	public static int get(int color1, int color2, int color3, int color4,
			int color5, int color6, int color7, int color8, int color9) {
		return ((get(color9) << 24) + (get(color8) << 21) + (get(color7) << 18) + (get(color6) << 15)
				+ (get(color5) << 12) + (get(color4) << 9)
				+ (get(color3) << 6) + (get(color2) << 3) + (get(color1)));
	}

	private static int get(int color) {
		if (color < 0)
			return 255;
		int r = color / 100 % 10;
		int g = color / 10 % 10;
		int b = color % 10;
		return r * 36 + g * 6 + b;
	}

	public static int toRGB(int red, int green, int blue) {
		red = (int) (red * 5 / 255 + 0.5);
		green = (int) (green * 5 / 255 + 0.5);
		blue = (int) (blue * 5 / 255 + 0.5);
		String combine = String.valueOf(red) + String.valueOf(green)
				+ String.valueOf(blue);
		return Integer.valueOf(combine);

	}

	public static int toHex(String string) {
		int red = Color.decode(string).getRed();
		int green = Color.decode(string).getGreen();
		int blue = Color.decode(string).getBlue();
		red = (int) (red * 5 / 255 + 0.5);
		green = (int) (green * 5 / 255 + 0.5);
		blue = (int) (blue * 5 / 255 + 0.5);
		String combine = String.valueOf(red) + String.valueOf(green)
				+ String.valueOf(blue);
		return Integer.valueOf(combine);
	}
}

package javajesus.graphics;

import java.awt.Color;

/*
 * Utility class for blending colors
 */
public class JJColor {

	/**
	 * Blend two colors.
	 * 
	 * @param color1 - First color to blend.
	 * @param color2 - Second color to blend.
	 * @param ratio - Blend ratio. 0.5 will give even blend, 1.0 will return
	 * color1, 0.0 will return color2 and so on.
	 * 
	 * @return the blended color as a Color
	 */
	public static Color blend(Color color1, Color color2, double ratio) {
		float r = (float) ratio;
		float ir = (float) 1.0 - r;

		float rgb1[] = new float[3];
		float rgb2[] = new float[3];

		color1.getColorComponents(rgb1);
		color2.getColorComponents(rgb2);

		return new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);
	}

	/**
	 * Make an even blend between two colors.
	 * 
	 * @param c1 - First color to blend.
	 * @param c2 - Second color to blend.
	 * @return the Blended color.
	 */
	public static Color blend(Color color1, Color color2) {
		return blend(color1, color2, 0.5);
	}

	/**
	 * Blend two colors.
	 * 
	 * @param color1 - First color to blend.
	 * @param color2 - Second color to blend.
	 * @param ratio - Blend ratio. 0.5 will give even blend, 1.0 will return
	 * color1, 0.0 will return color2 and so on.
	 * 
	 * @return the blended color in decimal
	 */
	public static int blend(int color1, int color2, double ratio) {
		return blend(new Color(color1), new Color(color2), ratio).getRGB();
	}
}

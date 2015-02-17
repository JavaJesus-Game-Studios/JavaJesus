package ca.javajesus.game.gfx;

import java.awt.Color;

public class Colors {

	/** Left Shift << Is the same as multiplying by 2 ^ n */

	/**
	 * Four Colors
	 * 
	 * @param color1
	 *            : Black, Hex Value:#000000, RGB: (0, 0, 0)
	 * @param color2
	 *            : Dark, Hex Value: #555555, RGB: (85, 85, 85)
	 * @param color3
	 *            : Light, Hex Value:#AAAAAA, RGB: (170, 170, 170)
	 * @param color4
	 *            : White, Hex Value: #FFFFFF, RGB: (255, 255, 255)
	 * @return
	 */
	public static int get(int color1, int color2, int color3, int color4) {
		return ((get(color4) << 24) + (get(color3) << 16) + (get(color2) << 8) + (get(color1)));
	}

	/**
	 * Six Colors
	 * 
	 * @param color1
	 *            : Black, Hex Value:#000000, RGB: (0, 0, 0)
	 * @param color2
	 *            : RGB: (51, 51, 51)
	 * @param color3
	 *            : RGB: (102, 102, 102)
	 * @param color4
	 *            : RGB: (153, 153, 153)
	 * @param color5
	 *            : RGB: (204, 204, 204)
	 * @param color6
	 *            : White, Hex Value: #FFFFFF, RGB: (255, 255, 255)
	 * @return
	 */
	public static int get(int color1, int color2, int color3, int color4,
			int color5, int color6) {
		return (int) (get(color6) << 40) + get(color5 << 32) + get(color4 << 24)
				+ get(color3 << 16) + get(color2 << 8) + get(color1);
	}

	private static int get(int color) {
		if (color < 0)
			return 255;
		int r = color / 100 % 10;
		int g = color / 10 % 10;
		int b = color % 10;
		return r * 36 + g * 6 + b;
	}

	public static int fromRGB(int red, int green, int blue) {
		red = (int) (red * 5 / 255 + 0.5);
		green = (int) (green * 5 / 255 + 0.5);
		blue = (int) (blue * 5 / 255 + 0.5);
		String combine = String.valueOf(red) + String.valueOf(green)
				+ String.valueOf(blue);
		return Integer.valueOf(combine);

	}

	public static int fromHex(String string) {
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
	
	/**
	   * Blend two colors.
	   * 
	   * @param color1  First color to blend.
	   * @param color2  Second color to blend.
	   * @param ratio   Blend ratio. 0.5 will give even blend, 1.0 will return
	   *                color1, 0.0 will return color2 and so on.
	   * @return        Blended color.
	   */
	  public static Color blend (Color color1, Color color2, double ratio)
	  {
	    float r  = (float) ratio;
	    float ir = (float) 1.0 - r;

	    float rgb1[] = new float[3];
	    float rgb2[] = new float[3];    

	    color1.getColorComponents (rgb1);
	    color2.getColorComponents (rgb2);    

	    Color color = new Color (rgb1[0] * r + rgb2[0] * ir, 
	                             rgb1[1] * r + rgb2[1] * ir, 
	                             rgb1[2] * r + rgb2[2] * ir);
	    
	    return color;
	  }
	  
	  /**
	   * Make an even blend between two colors.
	   * 
	   * @param c1     First color to blend.
	   * @param c2     Second color to blend.
	   * @return       Blended color.
	   */
	  public static Color blend (Color color1, Color color2)
	  {
	    return Colors.blend (color1, color2, 0.5);
	  }
	  
	  public static int blend (int color1, int color2, double ratio)
	  {
		Color color3 = new Color(color1);
		Color color4 = new Color(color2);
		  
	    float r  = (float) ratio;
	    float ir = (float) 1.0 - r;

	    float rgb1[] = new float[3];
	    float rgb2[] = new float[3];    

	    color3.getColorComponents (rgb1);
	    color4.getColorComponents (rgb2);    

	    Color color = new Color (rgb1[0] * r + rgb2[0] * ir, 
	                             rgb1[1] * r + rgb2[1] * ir, 
	                             rgb1[2] * r + rgb2[2] * ir);
	    
	    return color.getRGB();
	  }
}

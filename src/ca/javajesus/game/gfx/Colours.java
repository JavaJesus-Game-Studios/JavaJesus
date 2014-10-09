package ca.javajesus.game.gfx;

import java.awt.Color;

public class Colours {

    public static int get(int colour1, int colour2, int colour3, int colour4) {
        return ((get(colour4) << 24) + (get(colour3) << 16)
                + (get(colour2) << 8) + (get(colour1)));
    }

    private static int get(int colour) {
        if (colour < 0)
            return 255;
        int r = colour / 100 % 10;
        int g = colour / 10 % 10;
        int b = colour % 10;
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

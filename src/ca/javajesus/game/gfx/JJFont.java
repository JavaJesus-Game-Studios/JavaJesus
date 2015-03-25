package ca.javajesus.game.gfx;


public class JJFont {
    private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      "
            + "abcdefghijklmnopqrstuvwxyz      "
            + "0123456789.,:;'\"!?$%()-=+/      ";

    public static void render(String msg, Screen screen, int x, int y,
            int[] color, int scale) {
        msg = msg.toString();

        for (int i = 0; i < msg.length(); i++) {
            int charIndex = chars.indexOf(msg.charAt(i));
            if (charIndex >= 0)
                screen.render(x + (i * 8), y, charIndex + 0 * 32, color,
                        0x00,  scale, SpriteSheet.letters);

        }

    }

}

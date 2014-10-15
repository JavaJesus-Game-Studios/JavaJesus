package ca.javajesus.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
    public String path;
    public int width;
    public int height;

    public int[] pixels;
    
    public int size;

    public static SpriteSheet tiles = new SpriteSheet("/Tiles/tile_sheet.png");
    public static SpriteSheet buildings = new SpriteSheet("/Tiles/building_sheet.png");
    public static SpriteSheet guns = new SpriteSheet("/Tiles/firearm_sheet.png");
    public static SpriteSheet letters = new SpriteSheet("/Tiles/letter_sheet.png");
    public static SpriteSheet particles = new SpriteSheet("/Tiles/particle_sheet.png");
    public static SpriteSheet swords = new SpriteSheet("/Tiles/sword_sheet.png");
    
    public static SpriteSheet enemies = new SpriteSheet("/Sprites/mob_enemy_sheet.png");
    public static SpriteSheet npcs = new SpriteSheet("/Sprites/mob_friendly_sheet.png");
    public static SpriteSheet player = new SpriteSheet("/Sprites/player_sheet.png");
    public static SpriteSheet vehicles = new SpriteSheet("/Sprites/vehicle_sheet.png");
    
    public SpriteSheet(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.size = width * height;
        pixels = image.getRGB(0, 0, width, height, null, 0, width);

       for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff) / 64;
        }

    }

}

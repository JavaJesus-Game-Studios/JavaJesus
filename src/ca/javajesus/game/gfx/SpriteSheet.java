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
    public static SpriteSheet guns = new SpriteSheet("/Tiles/Inventory&Weapon_Sprites/firearm_sheet.png");
    public static SpriteSheet letters = new SpriteSheet("/GUI/GUI_Menus/letter_sheet.png");
    public static SpriteSheet particles = new SpriteSheet("/Tiles/Particles&Hud_Sprites/particle_sheet.png");
    public static SpriteSheet swords = new SpriteSheet("/Sprites/Player_Sprites/player_sword_sheet.png");
    public static SpriteSheet enemies = new SpriteSheet("/Sprites/EnemyNPC_Sprites/mob_enemy_sheet.png");
    public static SpriteSheet npcs = new SpriteSheet("/Sprites/FriendlyNPC_Sprites/mob_friendly_sheet.png");
    public static SpriteSheet player = new SpriteSheet("/Sprites/Player_Sprites/player_sheet.png");
    public static SpriteSheet vehicles = new SpriteSheet("/Sprites/Vehicle_Sprites/vehicle_sheet.png");
    public static SpriteSheet items = new SpriteSheet("/Tiles/Inventory&Weapon_Sprites/item_sheet.png");
        
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

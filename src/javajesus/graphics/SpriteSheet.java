package javajesus.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/*
 * Buffered Image of a tile sheet file used for
 * rendering textures at different positions
 */
public class SpriteSheet implements Serializable {
	
	// Spritesheets used in Java Jesus
    public static SpriteSheet tiles = new SpriteSheet("/tile_sheet.png", 32);
    public static SpriteSheet hud_weapons = new SpriteSheet("/HUD/hud_sheet.png", 10 * 3);
    public static SpriteSheet letters = new SpriteSheet("/Effects/letter_sheet.png", 32);
    public static SpriteSheet particles = new SpriteSheet("/Effects/particle_sheet.png", 32);
    public static SpriteSheet explosions = new SpriteSheet("/Effects/explosion_sheet.png", 56);
    public static SpriteSheet swords = new SpriteSheet("/Sprites/Player_Sprites/player_sword_sheet.png", 60);
    public static SpriteSheet enemies = new SpriteSheet("/Sprites/EnemyNPC_Sprites/mob_enemy_sheet.png", 40);
    public static SpriteSheet npcs = new SpriteSheet("/Sprites/FriendlyNPC_Sprites/mob_friendly_sheet.png", 32);
    public static SpriteSheet characters = new SpriteSheet("/Sprites/FriendlyNPC_Sprites/Character_Sheet.png", 32);
    public static SpriteSheet player = new SpriteSheet("/Sprites/Player_Sprites/player_sheet.png", 32);
    public static SpriteSheet playerGuns = new SpriteSheet("/Sprites/Player_Sprites/player_gun_sheet.png", 50);
    public static SpriteSheet playerVestedGuns = new SpriteSheet("/Sprites/Player_Sprites/player_vested_gun_sheet.png", 50);
    public static SpriteSheet playerKnightedGuns = new SpriteSheet("/Sprites/Player_Sprites/player_knight_armor_gun_sheet.png", 50);
    public static SpriteSheet playerHornedGuns = new SpriteSheet("/Sprites/Player_Sprites/player_horned_armor_gun_sheet.png", 50);
    public static SpriteSheet playerIstrahiimGuns = new SpriteSheet("/Sprites/Player_Sprites/player_istrahiim_armor_gun_sheet.png", 50);
    public static SpriteSheet vehicles = new SpriteSheet("/Sprites/Vehicle_Sprites/vehicle_sheet.png", 36);
    public static SpriteSheet gui_items = new SpriteSheet("/GUI/Inventory/item_gui_sheet.png", 10 * 2);
	public static SpriteSheet pickups = new SpriteSheet("/pickup_sheet.png", 10);
    public static SpriteSheet horses = new SpriteSheet("/Sprites/Vehicle_Sprites/Horse_Sheet.png", 32);
	
	// serialization
	private static final long serialVersionUID = 8851049246466607160L;
	
	// width and height of the spritesheet
    private int width, height;
    
    // number of boxes in a row in the spritesheet
    private int numBoxes;

    // pixel array of the spritesheet in memory
    private int[] pixels;
        
	/**
	 * Creates a Spritesheet object
	 * 
	 * @param path the file path to the spritesheet
	 * @param boxes the number of boxes in a row
	 */
    private SpriteSheet(String path, int boxes) {
    	
    	// set the num of boxes
    	numBoxes = boxes;
    	
    	// spritesheet in memory
        BufferedImage image = null;
        
        // can't initialize if there is no spritesheet found
        try {
        	
            image = ImageIO.read(SpriteSheet.class.getResource(path));
            
            // initialize instance data
            width = image.getWidth();
            height = image.getHeight();
            pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
            
        } catch (IOException e) {
        	System.err.println("Error loading spritesheet: " + path);
            e.printStackTrace();
        }

    }

    /**
     * @return width of the spritesheet
     */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height of the spritesheet
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the numBoxes
	 */
	public int getNumBoxes() {
		return numBoxes;
	}

	/**
	 * @return the pixels in the spritesheet
	 */
	public int[] getPixels() {
		return pixels;
	}
    
}

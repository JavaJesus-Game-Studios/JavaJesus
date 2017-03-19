package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet implements java.io.Serializable{
	
	private static final long serialVersionUID = 8851049246466607160L;
	
	// width and height of the spritesheet
    private int width, height;
    
    // number of boxes in a row in the spritesheet
    private int numBoxes;

    // pixel array of the spritesheet in memory
    private int[] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet("/Tiles/tile_sheet.png", 32);
    public static SpriteSheet guns = new SpriteSheet("/Tiles/Inventory&Weapon_Sprites/firearm_sheet.png", 32);
    public static SpriteSheet letters = new SpriteSheet("/GUI/GUI_Menus/letter_sheet.png", 32);
    public static SpriteSheet particles = new SpriteSheet("/Tiles/Particles&Hud_Sprites/particle_sheet.png", 32);
    public static SpriteSheet explosions = new SpriteSheet("/Tiles/Particles&Hud_Sprites/Exlplosion_Sheet.png", 56);
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
    public static SpriteSheet items = new SpriteSheet("/Tiles/Inventory&Weapon_Sprites/item_sheet.png", 32);
	public static SpriteSheet horses = new SpriteSheet("/Sprites/Vehicle_Sprites/Horse_Sheet.png", 32);
        
	/**
	 * Creates a Spritesheet object
	 * @param path the file path to the spritesheet
	 * @param boxes the number of boxes in a row
	 */
    public SpriteSheet(String path, int boxes) {
    	
    	// set the num of boxes
    	setNumBoxes(boxes);
    	
    	// spritesheet in memory
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
        	System.err.println("Error loading spritesheet: " + path);
            e.printStackTrace();
        }

        // can't initialize if there is no spritesheet found
        if (image == null) {
            return;
        }

        // initialize instance data
        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
       
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
	 * @param numBoxes the numBoxes to set
	 */
	public void setNumBoxes(int numBoxes) {
		this.numBoxes = numBoxes;
	}

	/**
	 * @return the pixels in the spritesheet
	 */
	public int[] getPixels() {
		return pixels;
	}
    
}

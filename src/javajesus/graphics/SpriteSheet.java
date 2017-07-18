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
	 ////// GUI ///////
    public static SpriteSheet hudWeapons = new SpriteSheet("/VISUAL_DATA/GUI/HUD/hud_sheet.png", 8 * 3);
    public static SpriteSheet inventoryItems = new SpriteSheet("/VISUAL_DATA/GUI/INVENTORY/inventory_sheet.png", 6 * 3);
    public static SpriteSheet letters = new SpriteSheet("/VISUAL_DATA/GUI/FONT/letter_sheet.png", 26);
    public static SpriteSheet statusBars = new SpriteSheet("/VISUAL_DATA/GUI/HUD/STATUS_BARS/actor_bars.png", 28);
	
	////// TILES ///////
    public static SpriteSheet tiles = new SpriteSheet("/VISUAL_DATA/TILES/tile_sheet.png", 32);
    	//NATURAL
    public static SpriteSheet dirt = new SpriteSheet("/VISUAL_DATA/TILES/OVERWORLD/NATURAL/dirt.png", 8);
    public static SpriteSheet grass = new SpriteSheet("/VISUAL_DATA/TILES/OVERWORLD/NATURAL/grass.png", 8);
    public static SpriteSheet mountain = new SpriteSheet("/VISUAL_DATA/TILES/OVERWORLD/NATURAL/mountain.png", 7);
    
    	//URBAN
    public static SpriteSheet urbanRoads = new SpriteSheet("/VISUAL_DATA/TILES/OVERWORLD/URBAN/urban_roads.png", 4);
    public static SpriteSheet wasteland = new SpriteSheet("/VISUAL_DATA/TILES/OVERWORLD/URBAN/wasteland.png", 7);
    
    ///// ANIMATED /////
    public static SpriteSheet fireSmall = new SpriteSheet("/VISUAL_DATA/EFFECTS/ANIMATIONS/fire_8Bit.png", 5);
    public static SpriteSheet fireLarge = new SpriteSheet("/VISUAL_DATA/EFFECTS/ANIMATIONS/fire_16Bit.png", 10);
    public static SpriteSheet waves = new SpriteSheet("/VISUAL_DATA/EFFECTS/ANIMATIONS/waves.png", 5);
    
    ///// EFFECTS /////
    public static SpriteSheet projectiles = new SpriteSheet("/VISUAL_DATA/EFFECTS/projectile_sheet.png", 8);
    public static SpriteSheet explosionSmall = new SpriteSheet("/VISUAL_DATA/Effects/ANIMATIONS/explosion_16Bit.png", 28);
    public static SpriteSheet explosionLarge = new SpriteSheet("/VISUAL_DATA/Effects/ANIMATIONS/explosion_32Bit.png", 50);
      
    ///// PLAYER //////
    public static SpriteSheet player = new SpriteSheet("/VISUAL_DATA/PLAYER/player_sheet.png", 32);
    public static SpriteSheet playerGuns = new SpriteSheet("/VISUAL_DATA/PLAYER/player_gun_sheet.png", 50);
    public static SpriteSheet playerVestGuns = new SpriteSheet("/VISUAL_DATA/PLAYER/player_vested_gun_sheet.png", 50);
    public static SpriteSheet playerKnightGuns = new SpriteSheet("/VISUAL_DATA/PLAYER/player_knight_armor_gun_sheet.png", 50);
    public static SpriteSheet playerHornedGuns = new SpriteSheet("/VISUAL_DATA/PLAYER/player_horned_armor_gun_sheet.png", 50);
    public static SpriteSheet playerIstrahiimGuns = new SpriteSheet("/VISUAL_DATA/PLAYER/player_istrahiim_armor_gun_sheet.png", 50);
    public static SpriteSheet playerSwords = new SpriteSheet("/VISUAL_DATA/PLAYER/player_sword_sheet.png", 60);
    public static SpriteSheet playerHorse = new SpriteSheet("/VISUAL_DATA/PLAYER/player_horse_sheet.png", 32);

    
    ///// ACTORS /////
    	//FRIENDLY
    public static SpriteSheet characters = new SpriteSheet("/VISUAL_DATA/ACTORS/FRIENDLY/character_sheet.png", 32);
    public static SpriteSheet mobFriends = new SpriteSheet("/VISUAL_DATA/ACTORS/FRIENDLY/mob_friendly_sheet.png", 40);
    
    	//ENEMY
    public static SpriteSheet mobEnemies = new SpriteSheet("/VISUAL_DATA/ACTORS/ENEMY/mob_enemy_sheet.png", 40);
    public static SpriteSheet bosses = new SpriteSheet("/VISUAL_DATA/ACTORS/ENEMY/boss_sheet.png", 40);
    
    	//ANIMAL
    public static SpriteSheet quadrapeds = new SpriteSheet("/VISUAL_DATA/ACTORS/ANIMAL/four_legged.png", 20);
    public static SpriteSheet bipeds = new SpriteSheet("/VISUAL_DATA/ACTORS/ANIMAL/two_legged.png", 7);


    ///// VEHICLES //////
    public static SpriteSheet vehicles = new SpriteSheet("/VISUAL_DATA/VEHICLES/vehicle_sheet.png", 36);
    
    ///// STATICS //////
    	//INTERACTIVE
	public static SpriteSheet pickups = new SpriteSheet("/VISUAL_DATA/STATICS/INTERACTIVE/pickup_sheet.png", 10);
	public static SpriteSheet chests = new SpriteSheet("/VISUAL_DATA/STATICS/INTERACTIVE/chests.png", 8);
	
	// serialization
	private static final long serialVersionUID = 8851049246466607160L;
	
	// width and height of the spritesheet
    private int width, height;
    
    // number of tiles in a row in the spritesheet
    private int numTiles;

    // pixel array of the spritesheet in memory
    private int[] pixels;
        
	/**
	 * Creates a Spritesheet object
	 * 
	 * @param path the file path to the spritesheet
	 * @param tiles the number of tiles in a row
	 */
    private SpriteSheet(String path, int tiles) {
    	
    	// set the num of tiles in a row
    	numTiles = tiles;
    	
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
	 * @return the number of tiles in a row
	 */
	public int getTilesPerRow() {
		return numTiles;
	}

	/**
	 * @return the pixels in the spritesheet
	 */
	public int[] getPixels() {
		return pixels;
	}
    
}

package javajesus.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Sprite {

	// width and height
	public int xSize, ySize;
	
	// offset in spritesheet
	private int x, y;
	
	// pixel data of the image
	public int[] pixels;
	
	// the sprite sheet it was loaded from
	private SpriteSheet sheet;
	
	// Trees
	public static Sprite DEAD_SEQUOIA = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Dead_Sequoia.png");
	public static Sprite SMALL_SEQUOIA = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Sequoia_Small.png");
	public static Sprite MEDIUM_SEQUOIA = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Sequoia_Medium.png");
	public static Sprite LARGE_SEQUOIA = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Sequoia_Large.png");
	public static Sprite SMALL_DECIDUOUS = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Small_Tree.png");
	public static Sprite DEAD_SEQUOIA_SMALL = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Dead_Sequoia_Small.png");
	public static Sprite DEAD_SEQUOIA_MEDIUM = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Dead_Sequoia_Medium.png");
	public static Sprite REDWOOD_LARGE = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Redwood_Large.png");
	public static Sprite REDWOOD_MEDIUM = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Redwood_Medium.png");
	public static Sprite REDWOOD_SMALL = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Redwood_Small.png");
	public static Sprite SEQUOIA_EXTRA_LARGE = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Sequoia_Extra_Large.png");
	public static Sprite SEQUOIA_EXTRA_SMALL = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/CONIFEROUS/Sequoia_Extra_Small.png");
	public static Sprite SMALL_TREE_AUTUMN = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Small_Tree_Autumn.png");
	public static Sprite SMALL_TREE_WINTER = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Small_Tree_Winter.png");
	public static Sprite MEDIUM_TREE = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Medium_Tree.png");
	public static Sprite MEDIUM_TREE_AUTUMN = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Medium_Tree_Autumn.png");
	public static Sprite MEDIUM_TREE_WINTER = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Medium_Tree_Winter.png");
	public static Sprite LARGE_TREE = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Large_Tree.png");
	public static Sprite LARGE_TREE_AUTUMN = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Large_Tree_Autumn.png");
	public static Sprite LARGE_TREE_WINTER = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/Large_Tree_Winter.png");
	public static Sprite WHITE_OAK = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/White_Oak.png");
	public static Sprite WHITE_OAK_SMALL = new Sprite("/VISUAL_DATA/STATICS/NATURAL/TREES/DECIDUOUS/White_Oak_Small.png");
	
	//Generic STATICS
	public static Sprite castle_tower = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Castle_Tower.png");
	public static Sprite catholic_church = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Catholic_Church.png");
	public static Sprite cave_entrance = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Cave_Entrance.png");
	public static Sprite hut_exterior = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Hut_Exterior.png");
	public static Sprite nice_house = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Nice_House.png");
	public static Sprite poor_house = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Poor_House.png");
	public static Sprite skyscraper = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Skyscraper.png");
	public static Sprite generic_hospital = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Hospital.png");
	public static Sprite catholic_chapel = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Catholic_Chapel.png");
	public static Sprite factory = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Factory.png");
	public static Sprite apartment = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Apartments_1.png");
	public static Sprite castle = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Castle_1.png");
	public static Sprite nice_house_2 = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Generic_Nice_House_2.png");
	public static Sprite gunstore = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Gun_Store.png");
	public static Sprite hotel = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Hotel.png");
	public static Sprite mineshaft = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Mine_Shaft.png");
	public static Sprite modern_skyscraper = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Modern_Skyscraper.png");
	public static Sprite police_building = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Police_Building.png");
	public static Sprite prison = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Prison.png");
	public static Sprite projects = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Projects.png");
	public static Sprite ranchero_house = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Ranchero_House.png");
	public static Sprite refugee_tent = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Refugee_Tent.png");
	public static Sprite russian_orthodox_church = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Russian_Orthodox_Church.png");
	public static Sprite tippee = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Tippee.png");
	public static Sprite warehouse = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Warehouse.png");
	public static Sprite shantyhouse = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/EXTERIORS/Shanty_House.png");
	
	//Unique HippyVille STATICS
	public static Sprite greattree = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/HIPPY_VILLE/EXTERIORS/The_Great_Tree.png");
	public static Sprite treehouse = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/HIPPY_VILLE/EXTERIORS/Tree_House.png");
	public static Sprite grizzly = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/HIPPY_VILLE/EXTERIORS/UC_Grizzly.png");
	
	//Unique Oakwood STATICS
	public static Sprite oakwoodcityhall = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/OAKWOOD/EXTERIORS/Oakwood_City_Hall.png");
	
	//Unique San Cisco STATICS
	public static Sprite sanCisco_skyscraper = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_CISCO/EXTERIORS/San_Cisco_Skyscraper_Triangle.png");
	public static Sprite chinatown_house = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_CISCO/EXTERIORS/Chinatown_House.png");
	public static Sprite russian_club = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_CISCO/EXTERIORS/Russian_Club.png");
	public static Sprite triad_HQ = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_CISCO/EXTERIORS/Triad_HQ.png");
	public static Sprite san_cisco_city_hall = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_CISCO/EXTERIORS/San_Cisco_City_Hall.png");
	
	//Unique San Juan STATICS
	public static Sprite jugle_hq = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_JUAN/EXTERIORS/Jugle_HQ.png");
	public static Sprite quacker_hq = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_JUAN/EXTERIORS/Quacker_HQ.png");
	public static Sprite sanJuan_City_Hall = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_JUAN/EXTERIORS/San_Juan_City_Hall.png");
	public static Sprite theHub = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_JUAN/EXTERIORS/The_Hub.png");
	
	//Unique Sequoia City STATICS
	public static Sprite sequoiaCinema = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SEQUOIA_CITY/EXTERIORS/Sequoia_City_Cinema.png");
	public static Sprite sequoiaSchool = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/SEQUOIA_CITY/EXTERIORS/Sequoia_City_School.png");
	
	//Unique TechTopia STATICS
	public static Sprite cafe = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Cafe.png");
	public static Sprite cardinalUniversity = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Cardinal_University.png");
	public static Sprite pear_hq = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Pear_HQ.png");
	public static Sprite techTopia_city_hall = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Tech_Topia_City_Hall.png");
	public static Sprite weirdTechBuilding1 = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Weird_Tech_Building_1.png");
	public static Sprite weirdTechBuilding2 = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Weird_Tech_Building_2.png");
	public static Sprite radardish = new Sprite("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/EXTERIORS/Radar_Dish.png");

	/**
	 * For square images
	 * @param size size of the image
	 * @param x horizontal offset in spritesheet
	 * @param y vertical offset in spritesheet
	 * @param sheet spritesheet to load an image from
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.xSize = size;
		this.ySize = size;
		pixels = new int[size * size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load(xSize, ySize);
	}

	/**
	 * For rectangular images
	 * @param xSize image width
	 * @param ySize image height
	 * @param x horizontal offset in spritesheet
	 * @param y vertical offset in spritesheet
	 * @param sheet spritesheet to load an image from
	 */
	public Sprite(int xSize, int ySize, int x, int y, SpriteSheet sheet) {
		this.xSize = xSize;
		this.ySize = ySize;
		pixels = new int[xSize * ySize];
		this.x = x * xSize;
		this.y = y * ySize;
		this.sheet = sheet;
		load(xSize, ySize);
	}
	
	/**
	 * For individual sprites not on a spritesheet
	 * @param path the file path to the sprite image
	 */
	public Sprite(String path) {
		
		// image in memory
        BufferedImage image = null;

        try {
            image = ImageIO.read(Sprite.class.getResource(path));
        } catch (IOException e) {
        	System.err.println("Error loading sprite: " + path);
            e.printStackTrace();
        }

        // return if load was unsuccessful
        if (image == null) {
            return;
        }

        // initialize instance data
        this.xSize = image.getWidth();
        this.ySize = image.getHeight();
        pixels = image.getRGB(0, 0, xSize, ySize, pixels, 0, xSize);
       
    }

	/**
	 * Copy the specified pixels from the spritesheet here
	 * @param xSize width of sprite
	 * @param ySize height of sprite
	 */
	private void load(int xSize, int ySize) {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				pixels[x + y * xSize] = sheet.getPixels()[(x + this.x)
						+ (y + this.y) * sheet.getWidth()];
			}
		}
	}
	
	/**
	 * @return width of the sprite
	 */
	public int getWidth() {
		return xSize;
	}
	
	/**
	 * @return height of the sprite
	 */
	public int getHeight() {
		return ySize;
	}

}

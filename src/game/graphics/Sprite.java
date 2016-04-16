package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Sprite implements Serializable {

	private static final long serialVersionUID = 5323068320900962483L;
	
	public int xSize, ySize;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	// Trees
	public transient static Sprite DEAD_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Dead_Sequoia.png");
	public transient static Sprite SMALL_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Sequoia_Small.png");
	public transient static Sprite MEDIUM_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Sequoia_Medium.png");
	public transient static Sprite LARGE_SEQUOIA = new Sprite("/Buildings/Trees/Coniferous_Trees/Sequoia_Large.png");
	public transient static Sprite SMALL_DECIDUOUS = new Sprite("/Buildings/Trees/Deciduous_Trees/Small_Tree.png");
	
	//Generic Buildings
	public transient static Sprite castle_tower = new Sprite("/Buildings/Generic Exteriors/Generic_Castle_Tower.png");
	public transient static Sprite catholic_church = new Sprite("/Buildings/Generic Exteriors/Generic_Catholic_Church.png");
	public transient static Sprite cave_entrance = new Sprite("/Buildings/Generic Exteriors/Generic_Cave_Entrance.png");
	public transient static Sprite hut_exterior = new Sprite("/Buildings/Generic Exteriors/Generic_Hut_Exterior.png");
	public transient static Sprite nice_house = new Sprite("/Buildings/Generic Exteriors/Generic_Nice_House.png");
	public transient static Sprite poor_house = new Sprite("/Buildings/Generic Exteriors/Generic_Poor_House.png");
	public transient static Sprite skyscraper = new Sprite("/Buildings/Generic Exteriors/Generic_Skyscraper.png");
	public transient static Sprite generic_hospital = new Sprite("/Buildings/Generic Exteriors/Generic_Hospital.png");
	public transient static Sprite catholic_chapel = new Sprite("/Buildings/Generic Exteriors/Catholic_Chapel.png");
	public transient static Sprite factory = new Sprite("/Buildings/Generic Exteriors/Factory.png");
	public transient static Sprite apartment = new Sprite("/Buildings/Generic Exteriors/Generic_Apartments_1.png");
	public transient static Sprite castle = new Sprite("/Buildings/Generic Exteriors/Generic_Castle_1.png");
	public transient static Sprite nice_house_2 = new Sprite("/Buildings/Generic Exteriors/Generic_Nice_House_2.png");
	public transient static Sprite gunstore = new Sprite("/Buildings/Generic Exteriors/Gun_Store.png");
	public transient static Sprite hotel = new Sprite("/Buildings/Generic Exteriors/Hotel.png");
	public transient static Sprite mineshaft = new Sprite("/Buildings/Generic Exteriors/Mine_Shaft.png");
	public transient static Sprite modern_skyscraper = new Sprite("/Buildings/Generic Exteriors/Modern_Skyscraper.png");
	public transient static Sprite police_building = new Sprite("/Buildings/Generic Exteriors/Police_Building.png");
	public transient static Sprite prison = new Sprite("/Buildings/Generic Exteriors/Prison.png");
	public transient static Sprite projects = new Sprite("/Buildings/Generic Exteriors/Projects.png");
	public transient static Sprite ranchero_house = new Sprite("/Buildings/Generic Exteriors/Ranchero_House.png");
	public transient static Sprite refugee_tent = new Sprite("/Buildings/Generic Exteriors/Refugee_Tent.png");
	public transient static Sprite russian_orthodox_church = new Sprite("/Buildings/Generic Exteriors/Russian_Orthodox_Church.png");
	public transient static Sprite tippee = new Sprite("/Buildings/Generic Exteriors/Tippee.png");
	public transient static Sprite warehouse = new Sprite("/Buildings/Generic Exteriors/Warehouse.png");
	public transient static Sprite shantyhouse = new Sprite("/Buildings/Generic Exteriors/Shanty_House.png");
	
	//Unique HippyVille Buildings
	public transient static Sprite greattree = new Sprite("/Buildings/Unique_HippyVille_Exteriors/The_Great_Tree.png");
	public transient static Sprite treehouse = new Sprite("/Buildings/Unique_HippyVille_Exteriors/Tree_House.png");
	public transient static Sprite grizzly = new Sprite("/Buildings/Unique_HippyVille_Exteriors/UC_Grizzly.png");
	
	//Unique Oakwood Buildings
	public transient static Sprite oakwoodcityhall = new Sprite("/Buildings/Unique_Oakwood_Exteriors/Oakwood_City_Hall.png");
	
	//Unique San Cisco Buildings
	public transient static Sprite sanCisco_skyscraper = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/San_Cisco_Skyscraper_Triangle.png");
	public transient static Sprite chinatown_house = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/Chinatown_House.png");
	public transient static Sprite russian_club = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/Russian_Club.png");
	public transient static Sprite triad_HQ = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/Triad_HQ.png");
	public transient static Sprite san_cisco_city_hall = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/San_Cisco_City_Hall.png");
	
	//Unique San Juan Buildings
	public transient static Sprite jungle_hq = new Sprite("/Buildings/Unique_San_Juan_Exteriors/Jugle_HQ.png");
	public transient static Sprite quacker_hq = new Sprite("/Buildings/Unique_San_Juan_Exteriors/Quacker_HQ.png");
	public transient static Sprite sanJuan_City_Hall = new Sprite("/Buildings/Unique_San_Juan_Exteriors/San_Juan_City_Hall.png");
	public transient static Sprite theHub = new Sprite("/Buildings/Unique_San_Juan_Exteriors/The_Hub.png");
	
	//Unique Sequoia City Buildings
	public transient static Sprite sequoiaCinema = new Sprite("/Buildings/Unique_Sequoia_City_Exteriors/Sequoia_City_Cinema.png");
	public transient static Sprite sequoiaSchool = new Sprite("/Buildings/Unique_Sequoia_City_Exteriors/Sequoia_City_School.png");
	
	//Unique TechTopia Buildings
	public transient static Sprite cafe = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Cafe.png");
	public transient static Sprite cardinalUniversity = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Cardinal_University.png");
	public transient static Sprite pear_hq = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Pear_HQ.png");
	public transient static Sprite techTopia_city_hall = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Tech_Topia_City_Hall.png");
	public transient static Sprite weirdTechBuilding1 = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Weird_Tech_Building_1.png");
	public transient static Sprite weirdTechBuilding2 = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Weird_Tech_Building_2.png");
	public transient static Sprite radardish = new Sprite("/Buildings/Unique_TechTopia_Exteriors/Radar_Dish.png");

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.xSize = size;
		this.ySize = size;
		pixels = new int[size * size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load(xSize, ySize);
	}

	public Sprite(int xSize, int ySize, int x, int y, SpriteSheet sheet) {
		this.xSize = xSize;
		this.ySize = ySize;
		pixels = new int[xSize * ySize];
		this.x = x * xSize;
		this.y = y * ySize;
		this.sheet = sheet;
		load(xSize, ySize);
	}
	
	public Sprite(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Sprite.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.xSize = image.getWidth();
        this.ySize = image.getHeight();
        pixels = image.getRGB(0, 0, xSize, ySize, pixels, 0, xSize);
       
    }

	private void load(int xSize, int ySize) {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				pixels[x + y * xSize] = sheet.pixels[(x + this.x)
						+ (y + this.y) * sheet.width];
			}
		}
	}
	
	public int getWidth() {
		return xSize;
	}
	
	public int getHeight() {
		return ySize;
	}

}

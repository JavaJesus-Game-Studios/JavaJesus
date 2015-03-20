package ca.javajesus.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public int xSize, ySize;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	//Generic Buildings
	public static Sprite castle_tower = new Sprite("/Buildings/Generic Exteriors/Generic_Castle_Tower.png");
	public static Sprite catholic_church = new Sprite("/Buildings/Generic Exteriors/Generic_Catholic_Church.png");
	public static Sprite cave_entrance = new Sprite("/Buildings/Generic Exteriors/Generic_Cave_Entrance.png");
	public static Sprite hut_exterior = new Sprite("/Buildings/Generic Exteriors/Generic_Hut_Exterior.png");
	public static Sprite nice_house = new Sprite("/Buildings/Generic Exteriors/Generic_Nice_House.png");
	public static Sprite poor_house = new Sprite("/Buildings/Generic Exteriors/Generic_Poor_House.png");
	public static Sprite skyscraper = new Sprite("/Buildings/Generic Exteriors/Generic_Skyscraper.png");
	public static Sprite generic_hospital = new Sprite("/Buildings/Generic Exteriors/Generic_Hospital.png");
	public static Sprite catholic_chapel = new Sprite("/Buildings/Generic Exteriors/Catholic_Chapel.png");
	public static Sprite factory = new Sprite("/Buildings/Generic Exteriors/Factory.png");
	public static Sprite apartment = new Sprite("/Buildings/Generic Exteriors/Generic_Apartments_1.png");
	public static Sprite castle = new Sprite("/Buildings/Generic Exteriors/Generic_Castle_1.png");
	public static Sprite nice_house_2 = new Sprite("/Buildings/Generic Exteriors/Generic_Nice_House_2.png");
	public static Sprite gunstore = new Sprite("/Buildings/Generic Exteriors/Gun_Store.png");
	public static Sprite hotel = new Sprite("/Buildings/Generic Exteriors/Hotel.png");
	public static Sprite mineshaft = new Sprite("/Buildings/Generic Exteriors/Mine_Shaft.png");
	public static Sprite modern_skyscraper = new Sprite("/Buildings/Generic Exteriors/Modern_Skyscraper.png");
	public static Sprite police_building = new Sprite("/Buildings/Generic Exteriors/Police_Building.png");
	public static Sprite prison = new Sprite("/Buildings/Generic Exteriors/Prison.png");
	public static Sprite projects = new Sprite("/Buildings/Generic Exteriors/Projects.png");
	public static Sprite ranchero_house = new Sprite("/Buildings/Generic Exteriors/Ranchero_House.png");
	public static Sprite refugee_tent = new Sprite("/Buildings/Generic Exteriors/Refugee_Tent.png");
	public static Sprite russian_orthodox_church = new Sprite("/Buildings/Generic Exteriors/Russian_Orthodox_Church.png");
	public static Sprite tippee = new Sprite("/Buildings/Generic Exteriors/Tippee.png");
	public static Sprite warehouse = new Sprite("/Buildings/Generic Exteriors/Warehouse.png");
	public static Sprite shantyhouse = new Sprite("/Buildings/Generic Exteriors/Shanty_House.png");
	
	
	//Unique San Cisco Buildings
	public static Sprite sanCisco_skyscraper = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/San_Cisco_Skyscraper_Triangle.png");
	public static Sprite chinatown_house = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/Chinatown_House.png");
	public static Sprite russian_club = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/Russian_Club.png");
	public static Sprite triad_HQ = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/Triad_HQ.png");
	public static Sprite san_cisco_city_hall = new Sprite("/Buildings/Unique_San_Cisco_Exteriors/San_Cisco_City_Hall.png");


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
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            return;
        }

        this.xSize = image.getWidth();
        this.ySize = image.getHeight();
        pixels = image.getRGB(0, 0, xSize, ySize, null, 0, xSize);

       for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff) / 64;
        }
       
    }

	private void load(int xSize, int ySize) {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				pixels[x + y * xSize] = sheet.pixels[(x + this.x)
						+ (y + this.y) * sheet.width];
			}
		}
	}

}

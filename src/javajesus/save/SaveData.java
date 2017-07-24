package javajesus.save;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import javajesus.entities.Damageable;
import javajesus.entities.DestructibleTile;
import javajesus.entities.Entity;
import javajesus.entities.Player;
import javajesus.entities.Type;
import javajesus.level.Level;

/*
 * Loads and Saves Entity Data
 * and contains helper methods
 * to achieve those ends
 */
public class SaveData {
	
	// different types of data saving and loading
	private static final byte TYPE1 = 0x00, TYPE2 = 0x01, TYPE3 = 0x02, TYPE4 = 0x03;
	
	// position of type data
	private static final long TYPE_MASK = Long.decode("0x00FFFFFFFFFFFFFF");
	private static final long EXTRA_MASK = Long.decode("0x00000000000000FF");
	
	/**
	 * Type 1 does not have any additional data
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return the compressed data
	 */
	public static final long type1(short x, short y) {
		
		// construct the data
		long data = (((long) TYPE1) << 56);
		
		// add the x
		data |= (((long) x) << 40);
		
		// add the y
		data |= (((long) y) << 24);
		
		return data;
	}
	
	/**
	 * Type 2 saves an entity's health
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param maxHealth - max health
	 * @return the compressed data
	 */
	public static final long type2(short x, short y, short maxHealth) {
		
		// construct the data
		long data = type1(x, y);
		
		// set the type
		data &= TYPE_MASK;
		data |= (((long) TYPE2) << 56);
		
		// add the health
		data |= (((long) maxHealth) << 8);
		
		return data;
	}
	
	/**
	 * Type 3 requires a special byte of extra data
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param extra - extra data to be stored
	 * @return the compressed data
	 */
	public static final long type3(short x, short y, byte extra) {
		
		// construct the data
		long data = type1(x, y);

		// set the type
		data &= TYPE_MASK;
		data |= (((long) TYPE3) << 56);

		// add the extra data
		data |= (extra & EXTRA_MASK);

		return data;
	}
	
	/**
	 * Type 4 requires health and an extra byte of data
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param maxHealth - max Health
	 * @param extra - extra byte of data
	 * @return the compressed data
	 */
	public static final long type4(short x, short y, short maxHealth, byte extra) {
		
		// construct the data
		long data = type1(x, y);
		
		// set the type
		data &= TYPE_MASK;
		data |= (((long) TYPE4) << 56);
		
		// add the health
		data |= (((long) maxHealth) << 8);
		
		// add the extra data
		data |= (extra & EXTRA_MASK);
		
		return data;
	}
	
	/**
	 * Loads a list of entities from the file path
	 * into the level
	 * 
	 * @param level - the level to add the entities into
	 * @param file - the file to load
	 * @throws IOException
	 */
	public static final void load(Level level, File file) throws IOException {
		
		// create an input stream for entities
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		
		// read in chunks of 9 bytes
		while (is.available() >= 9) {

			// create new data for the entity
			byte[] data = new byte[9];
			is.read(data);

			// ID of entity
			byte id = data[0];

			// coordinates
			short x = ByteBuffer.wrap(data).getShort(2);
			short y = ByteBuffer.wrap(data).getShort(4);

			// extra data
			short health = ByteBuffer.wrap(data).getShort(6);
			byte type = data[8];
			
			// construct the entity
			Entity e = getEntity(id, level, x, y);
			
			// set health data
			if (e instanceof Damageable) {
				((Damageable) e).setMaxHealth(health);
			}
			
			// set type data
			if (e instanceof Type) {
				((Type) e).setType(type);
			}
			
			// add this entity to the level
			level.add();

		}

		// free resources
		is.close();
	}
	
	/**
	 * Saves a list of entities from the entity list
	 * to the file path
	 * 
	 * @param path - path of the file to save
	 * @param entities - the list of entities to save
	 * @throws IOException
	 */
	public static final void save(String path, List<Entity> entities) throws IOException {

		// open the tile output stream
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(path), false));

		// save the entity data
		for (Entity e : entities) {

			// don't save the player here
			if (!(e instanceof Player)) {
				os.write(ByteBuffer.allocate(9).put(e.getId()).putLong(e.getData()).array());
			}

		}

		// free resources
		os.close();
	}
	
	/**
	 * Gets the entity based off the ID it found
	 * 
	 * @param id - the ID in the save file
	 * @return the entity based on the ID
	 */
	private static final Entity getEntity(int id, Level level, short x, short y) {

		switch (id) {
		case Entity.DESTRUCTIBLE_TILE:
			return new DestructibleTile(level, x, y);
		case Entity.FIRE_ENTITY:
			return new DestructibleTile(level, x, y);
		case Entity.STOOL:
			return new DestructibleTile(level, x, y);
		case Entity.BED:
			return new DestructibleTile(level, x, y);
		case Entity.BENCH:
			return new DestructibleTile(level, x, y);
		case Entity.CHAIR_FRONT:
			return new DestructibleTile(level, x, y);
		case Entity.CHAIR_SIDE:
			return new DestructibleTile(level, x, y);
		case Entity.CHEST:
			return new DestructibleTile(level, x, y);
		case Entity.COMPUTER_MONITOR:
			return new DestructibleTile(level, x, y);
		case Entity.COMPUTER_TOWER:
			return new DestructibleTile(level, x, y);
		case Entity.DINING_TABLE:
			return new DestructibleTile(level, x, y);
		case Entity.FILING_CABINET:
			return new DestructibleTile(level, x, y);
		case Entity.LONG_TABLE:
			return new DestructibleTile(level, x, y);
		case Entity.NIGHTSTAND:
			return new DestructibleTile(level, x, y);
		case Entity.SOFA:
			return new DestructibleTile(level, x, y);
		case Entity.SQUARE_TABLE:
			return new DestructibleTile(level, x, y);
		case Entity.TELEVISION:
			return new DestructibleTile(level, x, y);
		case Entity.THRONE:
			return new DestructibleTile(level, x, y);
		case Entity.DEAD_SEQUOIA:
			return new DestructibleTile(level, x, y);
		case Entity.SMALL_SEQUOIA:
			return new DestructibleTile(level, x, y);
		case Entity.MEDIUM_SEQUOIA:
			return new DestructibleTile(level, x, y);
		case Entity.LARGE_SEQUOIA:
			return new DestructibleTile(level, x, y);
		case Entity.GENERIC_TREE:
			return new DestructibleTile(level, x, y);
		case Entity.APARTMENT_HIGH_RISE:
			return new DestructibleTile(level, x, y);
		case Entity.CASTLE:
			return new DestructibleTile(level, x, y);
		case Entity.CASTLE_TOWER:
			return new DestructibleTile(level, x, y);
		case Entity.CATHOLIC_CHAPEL:
			return new DestructibleTile(level, x, y);
		case Entity.CATHOLIC_CHURCH:
			return new DestructibleTile(level, x, y);
		case Entity.CAVE_ENTRANCE:
			return new DestructibleTile(level, x, y);
		case Entity.FACTORY:
			return new DestructibleTile(level, x, y);
		case Entity.GENERIC_HOSPITAL:
			return new DestructibleTile(level, x, y);
		case Entity.GUNSTORE:
			return new DestructibleTile(level, x, y);
		case Entity.HOTEL:
			return new DestructibleTile(level, x, y);
		case Entity.HUT:
			return new DestructibleTile(level, x, y);
		case Entity.MINESHAFT:
			return new DestructibleTile(level, x, y);
		case Entity.MODERN_SKYSCRAPER:
			return new DestructibleTile(level, x, y);
		case Entity.NICE_HOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.NICE_HOUSE2:
			return new DestructibleTile(level, x, y);
		case Entity.POLICE:
			return new DestructibleTile(level, x, y);
		case Entity.POOR_HOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.PRISON:
			return new DestructibleTile(level, x, y);
		case Entity.PROJECTS:
			return new DestructibleTile(level, x, y);
		case Entity.RANCHERO_HOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.REFUGEE_TENT:
			return new DestructibleTile(level, x, y);
		case Entity.RUSSIAN_ORTHODOX_CHURCH:
			return new DestructibleTile(level, x, y);
		case Entity.SHANTY_HOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.SKYSCRAPER:
			return new DestructibleTile(level, x, y);
		case Entity.TIPPEE:
			return new DestructibleTile(level, x, y);
		case Entity.WAREHOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.GREAT_TREE:
			return new DestructibleTile(level, x, y);
		case Entity.TREE_HOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.UC_GRIZZLY:
			return new DestructibleTile(level, x, y);
		case Entity.OAKWOOD_CITY_HALL:
			return new DestructibleTile(level, x, y);
		case Entity.CHINATOWN_HOUSE:
			return new DestructibleTile(level, x, y);
		case Entity.RUSSIAN_CLUB:
			return new DestructibleTile(level, x, y);
		case Entity.SAN_CISCO_CITY_HALL:
			return new DestructibleTile(level, x, y);
		case Entity.SAN_CISCO_SKYSCRAPER:
			return new DestructibleTile(level, x, y);
		case Entity.TRIAD_HQ:
			return new DestructibleTile(level, x, y);
		case Entity.JUNGLE_HQ:
			return new DestructibleTile(level, x, y);
		case Entity.QUACKER_HQ:
			return new DestructibleTile(level, x, y);
		case Entity.SAN_JUAN_CITY_HALL:
			return new DestructibleTile(level, x, y);
		case Entity.THE_HUB:
			return new DestructibleTile(level, x, y);
		case Entity.SEQUOIA_CINEMA:
			return new DestructibleTile(level, x, y);
		case Entity.SEQUOIA_SCHOOL:
			return new DestructibleTile(level, x, y);
		case Entity.CAFE:
			return new DestructibleTile(level, x, y);
		case Entity.CARDINAL_UNIVERSITY:
			return new DestructibleTile(level, x, y);
		case Entity.PEAR_HQ:
			return new DestructibleTile(level, x, y);
		case Entity.RADAR_DISH:
			return new DestructibleTile(level, x, y);
		case Entity.TECHTOPIA_CITY_HALL:
			return new DestructibleTile(level, x, y);
		case Entity.WEIRD_TECH_BUILDING1:
			return new DestructibleTile(level, x, y);
		case Entity.WEIRD_TECH_BUILDING2:
			return new DestructibleTile(level, x, y);
		default:
			return null;
		}

	}

}

package javajesus.dataIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import javajesus.entities.Chest;
import javajesus.entities.Damageable;
import javajesus.entities.DestructibleTile;
import javajesus.entities.Entity;
import javajesus.entities.FireEntity;
import javajesus.entities.Spawner;
import javajesus.entities.Type;
import javajesus.entities.animals.Cat;
import javajesus.entities.animals.Chicken;
import javajesus.entities.animals.Cow;
import javajesus.entities.animals.Dog;
import javajesus.entities.animals.Duck;
import javajesus.entities.animals.Fox;
import javajesus.entities.animals.Pigeon;
import javajesus.entities.bosses.Cyclops;
import javajesus.entities.monsters.Bandito;
import javajesus.entities.monsters.Centaur;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.EvilFox;
import javajesus.entities.monsters.EvilOrangutan;
import javajesus.entities.monsters.GangMember;
import javajesus.entities.monsters.Monkey;
import javajesus.entities.monsters.Skeleton;
import javajesus.entities.npcs.Citizen;
import javajesus.entities.npcs.Peasant;
import javajesus.entities.npcs.aggressive.Gorilla;
import javajesus.entities.npcs.aggressive.Knight;
import javajesus.entities.npcs.aggressive.NativeAmerican;
import javajesus.entities.npcs.aggressive.Orangutan;
import javajesus.entities.npcs.aggressive.PoliceOfficer;
import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.entities.npcs.aggressive.TechWarrior;
import javajesus.entities.solid.buildings.AlphaCave;
import javajesus.entities.solid.buildings.ApartmentHighRise;
import javajesus.entities.solid.buildings.Castle;
import javajesus.entities.solid.buildings.CastleTower;
import javajesus.entities.solid.buildings.CatholicChapel;
import javajesus.entities.solid.buildings.CatholicChurch;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.Factory;
import javajesus.entities.solid.buildings.GenericHospital;
import javajesus.entities.solid.buildings.GunStore;
import javajesus.entities.solid.buildings.Hotel;
import javajesus.entities.solid.buildings.Hut;
import javajesus.entities.solid.buildings.MineShaft;
import javajesus.entities.solid.buildings.ModernSkyscraper;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.NiceHouse2;
import javajesus.entities.solid.buildings.Police;
import javajesus.entities.solid.buildings.PoorHouse;
import javajesus.entities.solid.buildings.Prison;
import javajesus.entities.solid.buildings.Projects;
import javajesus.entities.solid.buildings.RancheroHouse;
import javajesus.entities.solid.buildings.RefugeeTent;
import javajesus.entities.solid.buildings.RussianOrthodoxChurch;
import javajesus.entities.solid.buildings.ShantyHouse;
import javajesus.entities.solid.buildings.Skyscraper;
import javajesus.entities.solid.buildings.Tippee;
import javajesus.entities.solid.buildings.Warehouse;
import javajesus.entities.solid.buildings.hippyville.GreatTree;
import javajesus.entities.solid.buildings.hippyville.TreeHouse;
import javajesus.entities.solid.buildings.hippyville.UCGrizzly;
import javajesus.entities.solid.buildings.oakwood.OakwoodCityHall;
import javajesus.entities.solid.buildings.sancisco.ChinatownHouse;
import javajesus.entities.solid.buildings.sancisco.RussianClub;
import javajesus.entities.solid.buildings.sancisco.SanCiscoCityHall;
import javajesus.entities.solid.buildings.sancisco.SanCiscoSkyscraper;
import javajesus.entities.solid.buildings.sancisco.TriadHQ;
import javajesus.entities.solid.buildings.sanjuan.JungleHQ;
import javajesus.entities.solid.buildings.sanjuan.QuackerHQ;
import javajesus.entities.solid.buildings.sanjuan.SanJuanCityHall;
import javajesus.entities.solid.buildings.sanjuan.TheHub;
import javajesus.entities.solid.buildings.sequoia.SequoiaCinema;
import javajesus.entities.solid.buildings.sequoia.SequoiaSchool;
import javajesus.entities.solid.buildings.techtopia.Cafe;
import javajesus.entities.solid.buildings.techtopia.CardinalUniversity;
import javajesus.entities.solid.buildings.techtopia.PearHQ;
import javajesus.entities.solid.buildings.techtopia.RadarDish;
import javajesus.entities.solid.buildings.techtopia.TechTopiaCityHall;
import javajesus.entities.solid.buildings.techtopia.WeirdTechBuilding1;
import javajesus.entities.solid.buildings.techtopia.WeirdTechBuilding2;
import javajesus.entities.solid.furniture.Bar;
import javajesus.entities.solid.furniture.Bed;
import javajesus.entities.solid.furniture.Bench;
import javajesus.entities.solid.furniture.CardTable;
import javajesus.entities.solid.furniture.ChairSide;
import javajesus.entities.solid.furniture.ComputerMonitor;
import javajesus.entities.solid.furniture.ComputerTower;
import javajesus.entities.solid.furniture.FilingCabinet;
import javajesus.entities.solid.furniture.LongTable;
import javajesus.entities.solid.furniture.Nightstand;
import javajesus.entities.solid.furniture.PoolTable;
import javajesus.entities.solid.furniture.ShortTable;
import javajesus.entities.solid.furniture.Sofa;
import javajesus.entities.solid.furniture.SquareTable;
import javajesus.entities.solid.furniture.Stool;
import javajesus.entities.solid.furniture.Television;
import javajesus.entities.solid.furniture.Throne;
import javajesus.entities.solid.trees.DeadSequoia;
import javajesus.entities.solid.trees.DeadSequoiaMedium;
import javajesus.entities.solid.trees.DeadSequoiaSmall;
import javajesus.entities.solid.trees.GenericTree;
import javajesus.entities.solid.trees.LargeSequoia;
import javajesus.entities.solid.trees.LargeTree;
import javajesus.entities.solid.trees.LargeTreeAutumn;
import javajesus.entities.solid.trees.LargeTreeWinter;
import javajesus.entities.solid.trees.MediumSequoia;
import javajesus.entities.solid.trees.MediumTree;
import javajesus.entities.solid.trees.MediumTreeAutumn;
import javajesus.entities.solid.trees.MediumTreeWinter;
import javajesus.entities.solid.trees.RedwoodLarge;
import javajesus.entities.solid.trees.RedwoodMedium;
import javajesus.entities.solid.trees.RedwoodSmall;
import javajesus.entities.solid.trees.SequoiaExtraLarge;
import javajesus.entities.solid.trees.SequoiaExtraSmall;
import javajesus.entities.solid.trees.SmallSequoia;
import javajesus.entities.solid.trees.SmallTreeAutumn;
import javajesus.entities.solid.trees.SmallTreeWinter;
import javajesus.entities.solid.trees.WhiteOak;
import javajesus.entities.solid.trees.WhiteOakSmall;
import javajesus.entities.vehicles.Boat;
import javajesus.entities.vehicles.CenturyLeSabre;
import javajesus.entities.vehicles.Horse;
import javajesus.entities.vehicles.SportsCar;
import javajesus.entities.vehicles.Truck;
import javajesus.level.CharacterFactoryFactory;
import javajesus.level.Level;
import javajesus.quest.factories.CharacterFactory;

/*
 * Loads and Saves Entity Data
 * and contains helper methods
 * to achieve those ends
 */
public class EntityData {
	
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
	 * @param fileData - the file stream to load
	 * @throws IOException
	 */
	public static final void load(Level level, InputStream fileData) throws IOException {
		
		// create an input stream for entities
		BufferedInputStream is = new BufferedInputStream(fileData);
		
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
			level.add(e);

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
			
			// don't save any entity with an ID of -1
			if (e.getId() != -1) {
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
	public static final Entity getEntity(int id, Level level, short x, short y) {

		try {

			CharacterFactory characterFactory = CharacterFactoryFactory.make(level.getLevelId());
			if (characterFactory != null) {
				if (!characterFactory.created(id)) {
					characterFactory.set(level, id, x, y);
				}
				if (characterFactory.created(id)) {
					return characterFactory.make(id);
				}
			}
			
		    switch (id) {
	        case Entity.DESTRUCTIBLE_TILE:
	            return new DestructibleTile(level, x, y);
	        case Entity.FIRE_ENTITY:
	            return new FireEntity(level, x, y);
	        case Entity.STOOL:
	            return new Stool(level, x, y);
	        case Entity.BED:
	            return new Bed(level, x, y);
	        case Entity.BENCH:
	            return new Bench(level, x, y);
	        case Entity.POOL_TABLE:
	            return new PoolTable(level, x, y);
	        case Entity.CHAIR_SIDE:
	            return new ChairSide(level, x, y);
	        case Entity.CHEST:
	            return new Chest(level, x, y);
	        case Entity.COMPUTER_MONITOR:
	            return new ComputerMonitor(level, x, y);
	        case Entity.COMPUTER_TOWER:
	            return new ComputerTower(level, x, y);
	        case Entity.DINING_TABLE:
	            return new ShortTable(level, x, y);
	        case Entity.FILING_CABINET:
	            return new FilingCabinet(level, x, y);
	        case Entity.LONG_TABLE:
	            return new LongTable(level, x, y);
	        case Entity.NIGHTSTAND:
	            return new Nightstand(level, x, y);
	        case Entity.SOFA:
	            return new Sofa(level, x, y);
	        case Entity.SQUARE_TABLE:
	            return new SquareTable(level, x, y);
	        case Entity.TELEVISION:
	            return new Television(level, x, y);
	        case Entity.THRONE:
	            return new Throne(level, x, y);
	        case Entity.DEAD_SEQUOIA:
	            return new DeadSequoia(level, x, y);
	        case Entity.SMALL_SEQUOIA:
	            return new SmallSequoia(level, x, y);
	        case Entity.MEDIUM_SEQUOIA:
	            return new MediumSequoia(level, x, y);
	        case Entity.LARGE_SEQUOIA:
	            return new LargeSequoia(level, x, y);
	        case Entity.GENERIC_TREE:
	            return new GenericTree(level, x, y);
	        case Entity.APARTMENT_HIGH_RISE:
	            return new ApartmentHighRise(level, x, y);
	        case Entity.CASTLE:
	            return new Castle(level, x, y);
	        case Entity.CASTLE_TOWER:
	            return new CastleTower(level, x, y);
	        case Entity.CATHOLIC_CHAPEL:
	            return new CatholicChapel(level, x, y);
	        case Entity.CATHOLIC_CHURCH:
	            return new CatholicChurch(level, x, y);
	        case Entity.CAVE_ENTRANCE:
	            return new CaveEntrance(level, x, y);
	        case Entity.FACTORY:
	            return new Factory(level, x, y);
	        case Entity.GENERIC_HOSPITAL:
	            return new GenericHospital(level, x, y);
	        case Entity.GUNSTORE:
	            return new GunStore(level, x, y);
	        case Entity.HOTEL:
	            return new Hotel(level, x, y);
	        case Entity.HUT:
	            return new Hut(level, x, y);
	        case Entity.MINESHAFT:
	            return new MineShaft(level, x, y);
	        case Entity.MODERN_SKYSCRAPER:
	            return new ModernSkyscraper(level, x, y);
	        case Entity.NICE_HOUSE:
	            return new NiceHouse(level, x, y);
	        case Entity.NICE_HOUSE2:
	            return new NiceHouse2(level, x, y);
	        case Entity.POLICE:
	            return new Police(level, x, y);
	        case Entity.POOR_HOUSE:
	            return new PoorHouse(level, x, y);
	        case Entity.PRISON:
	            return new Prison(level, x, y);
	        case Entity.PROJECTS:
	            return new Projects(level, x, y);
	        case Entity.RANCHERO_HOUSE:
	            return new RancheroHouse(level, x, y);
	        case Entity.REFUGEE_TENT:
	            return new RefugeeTent(level, x, y);
	        case Entity.RUSSIAN_ORTHODOX_CHURCH:
	            return new RussianOrthodoxChurch(level, x, y);
	        case Entity.SHANTY_HOUSE:
	            return new ShantyHouse(level, x, y);
	        case Entity.SKYSCRAPER:
	            return new Skyscraper(level, x, y);
	        case Entity.TIPPEE:
	            return new Tippee(level, x, y);
	        case Entity.WAREHOUSE:
	            return new Warehouse(level, x, y);
	        case Entity.GREAT_TREE:
	            return new GreatTree(level, x, y);
	        case Entity.TREE_HOUSE:
	            return new TreeHouse(level, x, y);
	        case Entity.UC_GRIZZLY:
	            return new UCGrizzly(level, x, y);
	        case Entity.OAKWOOD_CITY_HALL:
	            return new OakwoodCityHall(level, x, y);
	        case Entity.CHINATOWN_HOUSE:
	            return new ChinatownHouse(level, x, y);
	        case Entity.RUSSIAN_CLUB:
	            return new RussianClub(level, x, y);
	        case Entity.SAN_CISCO_CITY_HALL:
	            return new SanCiscoCityHall(level, x, y);
	        case Entity.SAN_CISCO_SKYSCRAPER:
	            return new SanCiscoSkyscraper(level, x, y);
	        case Entity.TRIAD_HQ:
	            return new TriadHQ(level, x, y);
	        case Entity.JUNGLE_HQ:
	            return new JungleHQ(level, x, y);
	        case Entity.QUACKER_HQ:
	            return new QuackerHQ(level, x, y);
	        case Entity.SAN_JUAN_CITY_HALL:
	            return new SanJuanCityHall(level, x, y);
	        case Entity.THE_HUB:
	            return new TheHub(level, x, y);
	        case Entity.SEQUOIA_CINEMA:
	            return new SequoiaCinema(level, x, y);
	        case Entity.SEQUOIA_SCHOOL:
	            return new SequoiaSchool(level, x, y);
	        case Entity.CAFE:
	            return new Cafe(level, x, y);
	        case Entity.CARDINAL_UNIVERSITY:
	            return new CardinalUniversity(level, x, y);
	        case Entity.PEAR_HQ:
	            return new PearHQ(level, x, y);
	        case Entity.RADAR_DISH:
	            return new RadarDish(level, x, y);
	        case Entity.TECHTOPIA_CITY_HALL:
	            return new TechTopiaCityHall(level, x, y);
	        case Entity.WEIRD_TECH_BUILDING1:
	            return new WeirdTechBuilding1(level, x, y);
	        case Entity.WEIRD_TECH_BUILDING2:
	            return new WeirdTechBuilding2(level, x, y);
	        case Entity.SPAWNER:
	            return new Spawner(level, x, y, 0);
	        case Entity.CENTAUR:
	            return new Centaur(level, x, y);
	        case Entity.CYCLOPS:
	            return new Cyclops(level, x, y);
	        case Entity.DEMON:
	            return new Demon(level, x, y);
	        case Entity.GANG_MEMBER:
	            return new GangMember(level, x, y, GangMember.RUSSIAN);
	        case Entity.MONKEY:
	            return new Monkey(level, x, y);
	        case Entity.COW:
	            return new Cow(level, x, y);
	        case Entity.CAT:
	            return new Cat(level, x, y);
	        case Entity.DOG:
	            return new Dog(level, x, y);
	        case Entity.FOX:
	            return new Fox(level, x, y);
	        case Entity.HORSE:
	            return new Horse(level, x, y);
	        case Entity.CENTURY_LESABRE:
	            return new CenturyLeSabre(level, x, y);
	        case Entity.TRUCK:
	            return new Truck(level, x, y);
	        case Entity.SPORTS_CAR:
	            return new SportsCar(level, x, y);
	        case Entity.BOAT:
	            return new Boat(level, x, y);
	        case Entity.BANDITO:
	            return new Bandito(level, x, y);
	        case Entity.SKELETON:
	            return new Skeleton(level, x, y);
	        case Entity.CARD_TABLE:
	            return new CardTable(level, x, y);
	        case Entity.BAR:
	            return new Bar(level, x, y);
            case Entity.DEAD_SEQUOIA_SMALL:
                return new DeadSequoiaSmall(level, x, y);
            case Entity.DEAD_SEQUOIA_MEDIUM:
                return new DeadSequoiaMedium(level, x, y);
            case Entity.REDWOOD_LARGE:
                return new RedwoodLarge(level, x, y);
            case Entity.REDWOOD_MEDIUM:
                return new RedwoodMedium(level, x, y);
            case Entity.REDWOOD_SMALL:
                return new RedwoodSmall(level, x, y);
            case Entity.SEQUOIA_EXTRA_LARGE:
                return new SequoiaExtraLarge(level, x, y);
            case Entity.SEQUOIA_EXTRA_SMALL:
                return new SequoiaExtraSmall(level, x, y);
            case Entity.SMALL_TREE_AUTUMN:
                return new SmallTreeAutumn(level, x, y);
            case Entity.SMALL_TREE_WINTER:
                return new SmallTreeWinter(level, x, y);
            case Entity.MEDIUM_TREE:
                return new MediumTree(level, x, y);
            case Entity.MEDIUM_TREE_AUTUMN:
                return new MediumTreeAutumn(level, x, y);
            case Entity.MEDIUM_TREE_WINTER:
                return new MediumTreeWinter(level, x, y);
            case Entity.LARGE_TREE:
                return new LargeTree(level, x, y);
            case Entity.LARGE_TREE_AUTUMN:
                return new LargeTreeAutumn(level, x, y);
            case Entity.LARGE_TREE_WINTER:
                return new LargeTreeWinter(level, x, y);
            case Entity.WHITE_OAK:
                return new WhiteOak(level, x, y);
            case Entity.WHITE_OAK_SMALL:
                return new WhiteOakSmall(level, x, y);
            case Entity.EVILFOX:
            	return new EvilFox(level, x, y);
            case Entity.CHICKEN:
				return new Chicken(level, x, y);
			case Entity.PIGEON:
				return new Pigeon(level, x, y);
			case Entity.DUCK:
				return new Duck(level, x, y);
			case Entity.ALPHA_CAVE_ENTRANCE:
				return new AlphaCave(level, x, y);
			case Entity.EVIL_ORANGUTAN:
				return new EvilOrangutan(level, x, y);
			case Entity.ORANGUTAN:
				return new Orangutan(level, x, y);
			case Entity.GORILLA:
				return new Gorilla(level, x, y);
			case Entity.NATIVE_AMERICAN:
				return new NativeAmerican(level, x, y, NativeAmerican.MALE);
			case Entity.POLICE_OFFICER:
				return new PoliceOfficer(level, x, y, PoliceOfficer.MALE);
			case Entity.SWAT_OFFICER:
				return new SWATOfficer(level, x, y);
			case Entity.TECH_WARRIOR:
				return new TechWarrior(level, x, y);
			case Entity.CITIZEN:
				return new Citizen(level, x, y, Citizen.MALE);
			case Entity.PEASANT:
				return new Peasant(level, x, y, Peasant.MALE);
			case Entity.KNIGHT:
				return new Knight(level, x, y);
	        default:
	        	System.err.println("CRITICAL ERROR! CRITICAL ERROR! SAVING NULL ENTITY: " + id);
	        	System.err.println("MAKE SURE TO ADD ENTITY CASE IN ENTITYDATA.JAVA IN DATAIO");
	            return null;
	        }
		    
		} catch(IOException e) {
		    e.printStackTrace();
		    return null;
		}

	}

}

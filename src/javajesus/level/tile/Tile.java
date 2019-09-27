package javajesus.level.tile;

import java.util.Random;

import javajesus.entities.plant.Grass;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

/*
 * A Tile is a background unit on each level
 * Tiles are 8x8 and cannot interact with other entities
 */
public class Tile {
	
	// A set of all the available tiles implemented in the game
	public static final Tile[] tileList = new Tile[256];
	
	public static final Tile VOID = new Tile(0, true, 0xFF000000, 3, 0, SpriteSheet.urbanRoads,
			new int[] { 0xFF000000, 0xFF000000, 0xFF000000 });
	
	/////// NATURAL ////////
	
	
		//Grass
		public static final Tile GRASS0 = new Tile(1, false, 0xFF00FF00, 0, 0, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile GRASS1 = new Tile(2, false, 0xFF00FF01, 1, 0, SpriteSheet.grass,
				new int[] { 0xFF339933, 0xFF33BB33, 0xFFFFFFFF });
		public static final Tile GRASS2 = new Tile(3, false, 0xFF00FF02, 2, 0, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile GRASS3 = new Tile(4, false, 0xFF00FF03, 3, 0, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile GRASS4 = new Tile(5, false, 0xFF00FF04, 4, 0, SpriteSheet.grass,
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile GRASS5 = new Tile(6, false, 0xFF00FF05, 5, 0, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile GRASS6 = new Tile(7, false, 0xFF00FF06, 6, 0, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		
		public static final Tile DEAD_GRASS0 = new Tile(8, false, 0xFF00FF07, 0, 3, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile DEAD_GRASS1 = new Tile(9, false, 0xFF00DF08, 1, 3, SpriteSheet.grass,
				new int[] { 0xFF339933, 0xFF33BB33, 0xFFFFFFFF });
		public static final Tile DEAD_GRASS2 = new Tile(10, false, 0xFF00FF09, 2, 3, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile DEAD_GRASS3 = new Tile(11, false, 0xFF01FF01, 3, 3, SpriteSheet.grass, 
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		public static final Tile DEAD_GRASS4 = new Tile(12, false, 0xFF01FF01, 4, 3, SpriteSheet.grass,
				new int[] { 0xFF339933, 0xFF33BB33, 0xFF000000 });
		
		public static final Tile FARMPLOT1 = new Tile(13, false, 0xFF02FF02, 0, 7, SpriteSheet.grass,
				new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
		public static final Tile FARMPLOT2 = new Tile(14, false, 0xFF01FF03, 1, 7, SpriteSheet.grass,
				new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
		public static final Tile FARMPLOT3 = new Tile(15, false, 0xFF01FF04, 2, 7, SpriteSheet.grass,
				new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
		public static final Tile FARMPLOT4 = new Tile(16, false, 0xFF01FF05, 3, 7, SpriteSheet.grass,
				new int[] { 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
		
			//TRANSITION TILES
			public static final Tile GRASS_DIRT_BTM_RGHT = new Tile(17, false,0xFF01FF06,
					0, 1, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_DIRT_TOP_LFT = new Tile(18, false,0xFF01FF07,
					1, 1, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_DIRT_TOP_RGHT = new Tile(19, false,0xFF01FF08,
					2, 1, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_DIRT_BTM_LFT = new Tile(20, false,0xFF01FF09,
					3, 1, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			public static final Tile GRASS_DGRASS_BTM_RGHT = new Tile(21, false,0xFF03FF04,
					0, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_DGRASS_TOP_LFT = new Tile(22, false,0xFF03FF05,
					1, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_DGRASS_TOP_RGHT = new Tile(23, false,0xFF03FF06,
					2, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_DGRASS_BTM_LFT = new Tile(24, false,0xFF03FF07,
					3, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			public static final Tile GRASS_MUD_BTM_RGHT = new Tile(25, false,0xFF03FF08,
					4, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_MUD_TOP_LFT = new Tile(26, false,0xFF03FF09,
					5, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_MUD_TOP_RGHT = new Tile(27, false,0xFF04FF00,
					6, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_MUD_BTM_LFT = new Tile(28, false,0xFF04FF01,
					7, 2, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			public static final Tile GRASS_ROAD_BTM_RGHT = new Tile(29, false,0xFF04FF02,
					0, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_ROAD_TOP_LFT = new Tile(30, false,0xFF04FF03,
					1, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_ROAD_TOP_RGHT = new Tile(31, false,0xFF04FF03,
					2, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile GRASS_ROAD_BTM_LFT = new Tile(32, false,0xFF04FF04,
					3, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			//DEAD GRASS
			public static final Tile DGRASS_DIRT_BTM_RGHT = new Tile(33, false,0xFF04FF05,
					0, 4, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_DIRT_TOP_LFT = new Tile(34, false,0xFF04FF06,
					1, 4, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_DIRT_TOP_RGHT = new Tile(35, false,0xFF04FF07,
					2, 4, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_DIRT_BTM_LFT = new Tile(36, false,0xFF04FF08,
					3, 4, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			public static final Tile DGRASS_SAND_BTM_RGHT = new Tile(37, false,0xFF05FF03,
					0, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_SAND_TOP_LFT = new Tile(38, false,0xFF05FF04,
					1, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_SAND_TOP_RGHT = new Tile(39, false,0xFF05FF05,
					2, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_SAND_BTM_LFT = new Tile(40, false,0xFF05FF06,
					3, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			public static final Tile DGRASS_MUD_BTM_RGHT = new Tile(41, false,0xFF05FF07,
					4, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_MUD_TOP_LFT = new Tile(42, false,0xFF05FF08,
					5, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_MUD_TOP_RGHT = new Tile(43, false,0xFF05FF09,
					6, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_MUD_BTM_LFT = new Tile(44, false,0xFF06FF00,
					7, 5, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			
			public static final Tile DGRASS_ROAD_BTM_RGHT = new Tile(45, false,0xFF06FF01,
					4, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_ROAD_TOP_LFT = new Tile(46, false,0xFF06FF02,
					5, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_ROAD_TOP_RGHT = new Tile(47, false,0xFF06FF03,
					6, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
			public static final Tile DGRASS_ROAD_BTM_LFT = new Tile(48, false,0xFF06FF04,
					7, 6, SpriteSheet.grass, new int[]{ 0xFF0C6600, 0xFF5D3102, 0xFF000000 });
	

		//DIRT
		public static final Tile SAND = new Tile(49, false, 0xFFFFFF00, 0, 0, SpriteSheet.dirt,
				new int[] { 0xFFEEBB00, 0xFFFFFF00, 0xFF000000 });
		public static final Tile SAND2 = new Tile(50, false, 0xFFFFFF01, 1, 0, SpriteSheet.dirt,
				new int[] { 0xFFEEBB00, 0xFFFFFF00, 0xFF000000 });
		
		public static final Tile MUD = new Tile(51, false, 0xFF372201, 0, 2, SpriteSheet.dirt,
				new int[] { 0xFF2c1802, 0xFF000000, 0xFF000000 });
		public static final Tile MUD2 = new Tile(52, false, 0xFF372202, 1, 2, SpriteSheet.dirt,
				new int[] { 0xFF2c1802, 0xFF000000, 0xFF000000 });
		
		public static final Tile DIRTROAD = new Tile(53, false, 0xFFA06201, 0, 3, SpriteSheet.dirt,
				new int[] { 0xFF935409, 0xFF000000, 0xFF000000 });
		public static final Tile DIRTROAD2 = new Tile(54, false, 0xFFA06202, 1, 3, SpriteSheet.dirt,
				new int[] { 0xFF935409, 0xFF000000, 0xFF000000 });
		
		public static final Tile CAVEFLOOR = new Tile(56, false, 0xFFC3BEB8, 8, 3, SpriteSheet.dirt,
				new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
	
		public static final Tile CAVEWALL_TOP = new Tile(106, true, 0xFF291900, 0, 2, SpriteSheet.mountain, 
				new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
		public static final Tile CAVEWALL_BTM = new Tile(107, true, 0xFF291900, 1, 2, SpriteSheet.mountain, 
				new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
		public static final Tile CAVEWALL_RGHT = new Tile(108, true, 0xFF291900, 2, 2, SpriteSheet.mountain, 
				new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
		public static final Tile CAVEWALL_LFT = new Tile(109, true, 0xFF291900, 3, 2, SpriteSheet.mountain, 
				new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
		public static final Tile CAVEWALL_TOPLFT_CORN_IN = new Tile(55, true, 0xFFC3BEB7, 7, 3, SpriteSheet.dirt,
				new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile CAVEWALL_TOPRGHT_CORN_IN = new Tile(58, true, 0xFFC8BEC1, 2, 3,
				SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile CAVEWALL_BTMRGHT_CORN_IN = new Tile(59, true, 0xFFC8BEC2, 3, 3, 
				SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile CAVEWALL_BTMRLFT_CORN_IN = new Tile(60, true, 0xFFC8BEC3, 4, 3,
				SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile CAVEWALL_TOPRGHT_CORN_OUT = new Tile(110, true, 0xFF291900, 0, 3, SpriteSheet.mountain, 
				new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
		public static final Tile CAVEWALL_TOPLFT_CORN_OUT = new Tile(111, true, 0xFF291900, 1, 3, SpriteSheet.mountain, 
				new int[] { 0xFF92928D, 0xFF636361, 0xFF000000 });
		public static final Tile CAVEWALL_BTMLFT_CORN_OUT = new Tile(61, true, 0xFFC8BEC4, 5, 3, 
				SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile CAVEWALL_BTMRGHT_CORN_OUT = new Tile(57, true, 0xFFC3BEB9, 6, 3, SpriteSheet.dirt,
				new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		
			//TRANSITION TILES
			public static final Tile SAND_DIRT_BTM_RGHT = new Tile(62, false, 0xFFFFFF10, 2, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_DIRT_TOP_LFT = new Tile(63, false, 0xFFFFFF11, 3, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_DIRT_TOP_RGHT = new Tile(64, false, 0xFFFFFF12, 4, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_DIRT_BTM_LFT = new Tile(65, false, 0xFFFFFF13, 5, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			
			public static final Tile SAND_ROAD_BTM_RGHT = new Tile(66, false, 0xFFFFFF20, 6, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_ROAD_TOP_LFT = new Tile(67, false, 0xFFFFFF21, 7, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_ROAD_TOP_RGHT = new Tile(68, false, 0xFFFFFF22, 8, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_ROAD_BTM_LFT = new Tile(69, false, 0xFFFFFF23, 9, 0, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			
			public static final Tile SAND_GRASS_BTM_RGHT = new Tile(70, false, 0xFFFFFF20, 2, 1, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_GRASS_TOP_LFT = new Tile(71, false, 0xFFFFFF21, 3, 1, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_GRASS_TOP_RGHT = new Tile(72, false, 0xFFFFFF22, 4, 1, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile SAND_GRASS_BTM_LFT = new Tile(73, false, 0xFFFFFF23, 5, 1, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			
			public static final Tile MUD_DIRT_BTM_RGHT = new Tile(74, false, 0xFFFFFF10, 2, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile MUD_DIRT_TOP_LFT = new Tile(75, false, 0xFFFFFF11, 3, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile MUD_DIRT_TOP_RGHT = new Tile(76, false, 0xFFFFFF12, 4, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile MUD_DIRT_BTM_LFT = new Tile(77, false, 0xFFFFFF13, 5, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			
			public static final Tile MUD_ROAD_BTM_RGHT = new Tile(78, false, 0xFFFFFF20, 6, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile MUD_ROAD_TOP_LFT = new Tile(79, false, 0xFFFFFF21, 7, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile MUD_ROAD_TOP_RGHT = new Tile(80, false, 0xFFFFFF22, 8, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
			public static final Tile MUD_ROAD_BTM_LFT = new Tile(81, false, 0xFFFFFF23, 9, 2, 
					SpriteSheet.dirt, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });			
			

		//MOUNTAIN
		public static final Tile MOUNTAIN_UP = new Tile(82, true, 0xFF9e7013, 0, 0, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_DOWN = new Tile(83, true, 0xFF875a00, 1, 0, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_LFT = new Tile(84, true, 0xFF564015, 2, 0, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_RGHT = new Tile(85, true, 0xFF5e3f00, 3, 0, SpriteSheet.mountain, 
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		
		public static final Tile MOUNTAIN_CORN_UP_RGHT = new Tile(86, true, 0xFF9e7013, 0, 1, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_CORN_UP_LFT = new Tile(87, true, 0xFF875a00, 1, 1, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_CORN_DWN_RGHT = new Tile(88, true, 0xFF564015, 2, 1, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_CORN_DWN_LFT = new Tile(89, true, 0xFF5e3f00, 3, 1, SpriteSheet.mountain, 
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		
		public static final Tile MOUNTAIN_INCORN_LFT_UP = new Tile(90, true, 0xFF9e7013, 4, 0, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_INCORN_RGHT_UP = new Tile(91, true, 0xFF875a00, 5, 0, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_INCORN_LFT_DWN = new Tile(92, true, 0xFF564015, 6, 0, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile MOUNTAIN_INCORN_RGHT_DWN = new Tile(93, true, 0xFF5e3f00, 7, 0, SpriteSheet.mountain, 
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });

		public static final Tile DMOUNTAIN_UP = new Tile(94, true, 0xFF9e7013, 4, 1, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_DOWN = new Tile(95, true, 0xFF875a00, 5, 1, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_LFT = new Tile(96, true, 0xFF564015, 6, 1, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_RGHT = new Tile(97, true, 0xFF5e3f00, 7, 1, SpriteSheet.mountain, 
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });

		public static final Tile DMOUNTAIN_CORN_UP_RGHT = new Tile(98, true, 0xFF9e7013, 4, 2, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_CORN_UP_LFT = new Tile(99, true, 0xFF875a00, 5, 2, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_CORN_DWN_RGHT = new Tile(100, true, 0xFF564015, 6, 2, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_CORN_DWN_LFT = new Tile(101, true, 0xFF5e3f00, 7, 2, SpriteSheet.mountain, 
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		
		public static final Tile DMOUNTAIN_INCORN_LFT_UP = new Tile(102, true, 0xFF9e7013, 4, 3, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_INCORN_RGHT_UP = new Tile(103, true, 0xFF875a00, 5, 3, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_INCORN_LFT_DWN = new Tile(104, true, 0xFF564015, 6, 3, SpriteSheet.mountain,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile DMOUNTAIN_INCORN_RGHT_DWN = new Tile(105, true, 0xFF5e3f00, 7, 3, SpriteSheet.mountain, 
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });

		
		/////// URBAN ////////
	
		//ROADS
		public static final Tile CAR_ROAD = new Tile(112,false, 0xFF565656, 0, 0, 
				SpriteSheet.urbanRoads, new int[] { 0xFF222222, 0xFF353535, 0xFF000000 });
		public static final Tile CAR_ROAD_DIV_HORZ = new Tile(113,false, 0xFF565656, 1, 0,
				SpriteSheet.urbanRoads, new int[] { 0xFF222222, 0xFF353535, 0xFF000000 });
		public static final Tile CAR_ROAD_DIV_VERT = new Tile(114,false, 0xFF565656, 2, 0,
				SpriteSheet.urbanRoads,new int[] { 0xFF222222, 0xFF353535, 0xFF000000 });
		public static final Tile BRICKROAD = new Tile(115, false, 0xFFA50000, 0, 1, 
				SpriteSheet.urbanRoads, new int[] { 0xFFD40000, 0xFFB40000, 0xFF000000 });
		
		//SIDEWALK
		public static final Tile SIDEWALK_MID = new Tile(116, false, 0xFF3f3f3f, 1, 1,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		
			//ROAD
		public static final Tile SIDEWALK_BTTMEDGE_RD = new Tile(117, false, 0xFF3f3f3f, 0, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPEDGE_RD = new Tile(118, false, 0xFF3f3f3f, 1, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_RGHTEDGE_RD = new Tile(119, false, 0xFF3f3f3f, 2, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_LFTEDGE_RD = new Tile(120, false, 0xFF3f3f3f, 3, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_RGHT_RD = new Tile(121, false, 0xFF3f3f3f, 4, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_LFT_RD = new Tile(122, false, 0xFF3f3f3f, 5, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_RGHT_RD = new Tile(123, false, 0xFF3f3f3f, 6, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_LFT_RD = new Tile(124, false, 0xFF3f3f3f, 7, 2,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_IN_RGHT_RD = new Tile(125, false, 0xFF3f3f3f, 0, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_IN_LFT_RD = new Tile(126, false, 0xFF3f3f3f, 1, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_IN_RGHT_RD = new Tile(127, false, 0xFF3f3f3f, 2,3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_IN_LFT_RD = new Tile(128, false, 0xFF3f3f3f, 3, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		
			//BRICKROAD
		public static final Tile SIDEWALK_BTTMEDGE_BRD = new Tile(129, false, 0xFF3f3f3f, 4, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPEDGE_BRD = new Tile(130, false, 0xFF3f3f3f, 5, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_RGHTEDGE_BRD = new Tile(131, false, 0xFF3f3f3f, 6, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_LFTEDGE_BRD = new Tile(132, false, 0xFF3f3f3f, 7, 3,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_RGHT_BRD = new Tile(133, false, 0xFF3f3f3f, 0, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_LFT_BRD = new Tile(134, false, 0xFF3f3f3f, 1, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_RGHT_BRD = new Tile(135, false, 0xFF3f3f3f, 2, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_LFT_BRD = new Tile(136, false, 0xFF3f3f3f, 3, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_IN_RGHT_BRD = new Tile(137, false, 0xFF3f3f3f, 4, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_IN_LFT_BRD = new Tile(138, false, 0xFF3f3f3f, 5, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_IN_RGHT_BRD = new Tile(139, false, 0xFF3f3f3f, 6, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_IN_LFT_BRD = new Tile(140, false, 0xFF3f3f3f, 7, 4,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
			//GRASS
		public static final Tile SIDEWALK_BTTMEDGE_GRSS = new Tile(141, false, 0xFF3f3f3f, 0, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPEDGE_GRSS = new Tile(142, false, 0xFF3f3f3f, 1, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_RGHTEDGE_GRSS = new Tile(143, false, 0xFF3f3f3f, 2, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_LFTEDGE_GRSS = new Tile(144, false, 0xFF3f3f3f, 3, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_RGHT_GRSS = new Tile(145, false, 0xFF3f3f3f, 4, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_LFT_GRSS = new Tile(146, false, 0xFF3f3f3f, 5, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_RGHT_GRSS = new Tile(147, false, 0xFF3f3f3f, 6, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_LFT_GRSS = new Tile(148, false, 0xFF3f3f3f, 7, 5,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_IN_RGHT_GRSS = new Tile(149, false, 0xFF3f3f3f, 0, 6,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_TOPCORN_IN_LFT_GRSS = new Tile(150, false, 0xFF3f3f3f, 1, 6,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_IN_RGHT_GRSS = new Tile(151, false, 0xFF3f3f3f, 2, 6,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
		public static final Tile SIDEWALK_BTTMCORN_IN_LFT_GRSS = new Tile(152, false, 0xFF3f3f3f, 3, 6,
				SpriteSheet.urbanRoads,new int[] { 0xFFa7a7a7, 0xFF3c3c3c, 0xFF000000 });
	
	
		//Wasteland
		public static final Tile WASTELAND_GROUND1 = new Tile(153, false, 0xFF8e8e8e, 0, 0,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND2 = new Tile(154, false, 0xFF8e8e8e, 1, 0,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND3 = new Tile(155, false, 0xFF8e8e8e, 2, 0,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND4 = new Tile(156, false, 0xFF8e8e8e, 3, 0,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND5 = new Tile(157, false, 0xFF8e8e8e, 4, 0,
				SpriteSheet.wasteland, new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND6 = new Tile(158, false, 0xFF8e8e8e, 5, 0,
				SpriteSheet.wasteland, new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND7 = new Tile(159, false, 0xFF8e8e8e, 6, 0, 
				SpriteSheet.wasteland, new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		public static final Tile WASTELAND_GROUND8 = new Tile(160, false, 0xFF8e8e8e, 7, 0,
				SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		/**
		 * Shit's getting deleted, this frees up 8 tiles 
		 * 
		 *
		public static final Tile DWASTELAND_GROUND1 = new Tile(161, false, 0xFF8e8e8e, 0, 1,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND2 = new Tile(162, false, 0xFF8e8e8e, 1, 1,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND3 = new Tile(163, false, 0xFF8e8e8e, 2, 1,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND4 = new Tile(164, false, 0xFF8e8e8e, 3, 1,
				SpriteSheet.wasteland, new int[] { 0xFFa7a7a7, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND5 = new Tile(165, false, 0xFF8e8e8e, 4, 1,
				SpriteSheet.wasteland, new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND6 = new Tile(166, false, 0xFF8e8e8e, 5, 1,
				SpriteSheet.wasteland, new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND7 = new Tile(167, false, 0xFF8e8e8e, 6, 1, 
				SpriteSheet.wasteland, new int[] { 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		public static final Tile DWASTELAND_GROUND8 = new Tile(168, false, 0xFF8e8e8e, 7, 1,
				SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		*/
		
			//TRANSITIONS
			public static final Tile WASTELAND_DIRT_BTTM_RGHT = new Tile(169, false, 0xFF8e8e8e, 0, 2,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_DIRT_TOP_LFT = new Tile(170, false, 0xFF8e8e8e, 1, 2,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_DIRT_TOP_RGHT = new Tile(171, false, 0xFF8e8e8e, 2, 2,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_DIRT_BTTM_LFT = new Tile(172, false, 0xFF8e8e8e, 3, 2,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			
			public static final Tile WASTELAND_SAND_BTTM_RGHT = new Tile(173, false, 0xFF8e8e8e, 0, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_SAND_TOP_LFT = new Tile(174, false, 0xFF8e8e8e, 1, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_SAND_TOP_RGHT = new Tile(175, false, 0xFF8e8e8e, 2, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_SAND_BTTM_LFT = new Tile(176, false, 0xFF8e8e8e, 3, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			
			public static final Tile WASTELAND_DGRASS_BTTM_RGHT = new Tile(177, false, 0xFF8e8e8e, 4, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_DGRASS_TOP_LFT = new Tile(178, false, 0xFF8e8e8e, 5, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_DGRASS_TOP_RGHT = new Tile(179, false, 0xFF8e8e8e, 6, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
			public static final Tile WASTELAND_DGRASS_BTTM_LFT = new Tile(180, false, 0xFF8e8e8e, 7, 3,
					SpriteSheet.wasteland, new int[]{ 0xFF8e8e8e, 0xFF000000, 0xFF000000 });
		

	/////// INTERIOR //////
	
		//Floors
		public static final Tile WOODFLOOR1 = new Tile(181, false, 0xFF944500, 0, 0, SpriteSheet.floors, 
				new int[] { 0xFF753003, 0xFF291900, 0xFF000000 });
		public static final Tile WOODFLOOR2 = new Tile(182, false, 0xFF944500, 1, 0, SpriteSheet.floors, 
				new int[] { 0xFF753003, 0xFF291900, 0xFF000000 });
		public static final Tile SHACKFLOOR_VERT = new Tile(183, false, 0xFF944500, 2, 0, SpriteSheet.floors, 
				new int[] { 0xFF753003, 0xFF291900, 0xFF000000 });
		public static final Tile SHACKFLOOR_HORZ = new Tile(184, false, 0xFF944500, 0, 2, SpriteSheet.floors, 
				new int[] { 0xFF753003, 0xFF291900, 0xFF000000 });
		public static final Tile STONEFLOOR_SMLL = new Tile(185, false, 0xFF555555, 3, 0, SpriteSheet.floors,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile STONEFLOOR_LRGE = new Tile(186, false, 0xFF555555, 1, 2, SpriteSheet.floors,
				new int[] { 0xFF7F7F7F, 0xFFC3C3C3, 0xFF000000 });
		public static final Tile CHECKERED_TILE = new Tile(187, false, 0xFFe3350c, 4, 0, SpriteSheet.floors,
				new int[] { 0xFF111111, 0xFF111111, 0xFFFFFFFF });
		public static final Tile LINOLEUM = new Tile(188, false, 0xFFFFFFFF, 5, 0, SpriteSheet.floors,
				new int[] { 0xFFf9ffd8, 0xFF000000, 0xFF000000 });
		public static final Tile WAREHOUSE = new Tile(189, false, 0xFFFFFFFF, 2, 2, SpriteSheet.floors,
				new int[] { 0xFFf9ffd8, 0xFF000000, 0xFF000000 });

		public static final Tile RGHTCARPET = new Tile(190, false, 0xFFBE03DB, 0, 1, SpriteSheet.floors,
				new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
		public static final Tile LFTCARPET = new Tile(191, false, 0xFFEC00EC, 1, 1, SpriteSheet.floors, 
				new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
		public static final Tile TOPCARPET = new Tile(192, false, 0xFFFF00FF, 2, 1, SpriteSheet.floors,
				new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
		public static final Tile BTMCARPET = new Tile(193, false, 0xFF6600A7, 3, 1, SpriteSheet.floors, 
				new int[] { 0xFFA40101, 0xFFEBCB00, 0xFF000000 });
		
		public static final Tile FLOOR_CARPET_1 = new Tile(194, false,0xFF1f7a01, 4, 1, SpriteSheet.floors,
				new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 });
		public static final Tile FLOOR_CARPET_SHAG = new Tile(195, false,0xFF1f7a01, 5, 1, SpriteSheet.floors,
				new int[] { 0xFF1F7901, 0xFF1F4001, 0xFF000000 });
	
		//Walls
		public static final Tile NICE_WALL_UP = new Tile(196, true, 0xFF6D4300, 0, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_DWN = new Tile(197, true, 0xFF835100, 1, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_LFT = new Tile(198, true, 0xFF5A3801, 2, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_RGHT = new Tile(199, true, 0xFF472C00, 3, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALLCORN_RGHT_UP = new Tile(200, true, 0xFF520101, 4, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALLCORN_LFT_UP = new Tile(201, true, 0xFF420101, 5, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALLCORN_RGHT_DWN = new Tile(202, true, 0xFF7A0000, 6, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALLCORN_LFT_DWN = new Tile(203, true, 0xFF310000, 7, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_INCORN_LFTUP = new Tile(204, true, 0xFFA3881A, 8, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_INCORN_RGHTUP = new Tile(205, true, 0xFF7C6816, 9, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_INCORN_LFTDWN = new Tile(206, true, 0xFF717C16, 10, 0, SpriteSheet.walls, 
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile NICE_WALL_INCORN_RGHTDWN = new Tile(207, true, 0xFF7d8c00, 11, 0, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		
		public static final Tile STONE_WALL_UP = new Tile(208, true, 0xFF6D4300, 0, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_DWN = new Tile(209, true, 0xFF835100, 1, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_LFT = new Tile(210, true, 0xFF5A3801, 2, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_RGHT = new Tile(211, true, 0xFF472C00, 3, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALLCORN_RGHT_UP = new Tile(212, true, 0xFF520101, 4, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALLCORN_LFT_UP = new Tile(213, true, 0xFF420101, 5, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALLCORN_RGHT_DWN = new Tile(214, true, 0xFF7A0000, 6, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALLCORN_LFT_DWN = new Tile(215, true, 0xFF310000, 7, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_INCORN_LFTUP = new Tile(216, true, 0xFFA3881A, 8, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_INCORN_RGHTUP = new Tile(217, true, 0xFF7C6816, 9, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_INCORN_LFTDWN = new Tile(218, true, 0xFF717C16, 10, 1, SpriteSheet.walls, 
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile STONE_WALL_INCORN_RGHTDWN = new Tile(219, true, 0xFF7d8c00, 11, 1, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		
		public static final Tile SHACK_WALL_UP = new Tile(220, true, 0xFF6D4300, 0, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_DWN = new Tile(221, true, 0xFF835100, 1, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_LFT = new Tile(222, true, 0xFF5A3801, 2, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_RGHT = new Tile(223, true, 0xFF472C00, 3, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALLCORN_RGHT_UP = new Tile(224, true, 0xFF520101, 4, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALLCORN_LFT_UP = new Tile(225, true, 0xFF420101, 5, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALLCORN_RGHT_DWN = new Tile(226, true, 0xFF7A0000, 6, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALLCORN_LFT_DWN = new Tile(227, true, 0xFF310000, 7, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_INCORN_LFTUP = new Tile(228, true, 0xFFA3881A, 8, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_INCORN_RGHTUP = new Tile(229, true, 0xFF7C6816, 9, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_INCORN_LFTDWN = new Tile(230, true, 0xFF717C16, 10, 2, SpriteSheet.walls, 
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
		public static final Tile SHACK_WALL_INCORN_RGHTDWN = new Tile(231, true, 0xFF7d8c00, 11, 2, SpriteSheet.walls,
				new int[] { 0xFFffe45d, 0xFF7C3F0F, 0xFF48260B });
	
		public static final Tile GLASS_WALL_HORIZONTAL_UP = new Tile(232, true, 0xFF016ABA, 0, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_HORIZONTAL_DOWN = new Tile(233, true, 0xFF027FDF, 1, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_VERTICAL_LFT = new Tile(234, true, 0xFF0166f4, 2, 3, SpriteSheet.walls, 
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_VERTICAL_RGHT = new Tile(235, true, 0xFF0256CC, 3, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_CORNER_RGHT_UP = new Tile(236, true, 0xFF0B9401, 4, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_CORNER_LFT_UP = new Tile(237, true, 0xFF420101, 5, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_CORNER_RGHT_DOWN = new Tile(238, true, 0xFF087101, 6, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
		public static final Tile GLASS_WALL_CORNER_LFT_DOWN = new Tile(239, true, 0xFFFFB400, 7, 3, SpriteSheet.walls,
				new int[] { 0xFF76C0F8, 0xFF76C0F8, 0xFF016ABA });
	
	
	/////// ANIMATED /////////
	
		//Water
		public static final Tile SEA1 = new AnimatedTile(240, false, 0xFF0000FF, 0, SpriteSheet.waves,
				new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		public static final Tile SEA2 = new AnimatedTile(241, false, 0xFF0000FF, 1, SpriteSheet.waves,
				new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		public static final Tile SEA3 = new AnimatedTile(242, false, 0xFF0000FF, 2, SpriteSheet.waves,
				new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		public static final Tile SEA4 = new AnimatedTile(243, false, 0xFF0000FF, 3, SpriteSheet.waves,
				new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		
		public static final Tile SHORE1 = new AnimatedTile(244, false, 0xFF64FFFF, 4, SpriteSheet.waves,
			new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		public static final Tile SHORE2 = new AnimatedTile(245, false, 0xFF64FFFF, 5, SpriteSheet.waves,
				new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		public static final Tile SHORE3 = new AnimatedTile(246, false, 0xFF64FFFF, 6, SpriteSheet.waves,
				new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		public static final Tile SHORE4 = new AnimatedTile(247, false, 0xFF64FFFF, 7, SpriteSheet.waves,
				new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		
		public static final Tile SAND_WATER_BTM_RGHT = new AnimatedTile(248, false, 0xFFFFFF20, 0, 
				SpriteSheet.waveTransitions, new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		public static final Tile SAND_WATER_TOP_LFT = new AnimatedTile(249, false, 0xFFFFFF20, 1, 
				SpriteSheet.waveTransitions, new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		public static final Tile SAND_WATER_TOP_RGHT = new AnimatedTile(250, false, 0xFFFFFF20, 2, 
				SpriteSheet.waveTransitions, new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
		public static final Tile SAND_WATER_BTM_LFT = new AnimatedTile(251, false, 0xFFFFFF20, 3, 
				SpriteSheet.waveTransitions, new int[] { 0xFFb6ffd6, 0xFFb6ffd6, 0xFF78d5e9 }, 5, 800);
	
		public static final Tile MUD_WATER_BTM_RGHT = new AnimatedTile(252, false, 0xFFFFFF20, 4, 
				SpriteSheet.waveTransitions, new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		public static final Tile MUD_WATER_TOP_LFT = new AnimatedTile(253, false, 0xFFFFFF20, 5, 
				SpriteSheet.waveTransitions, new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		public static final Tile MUD_WATER_TOP_RGHT = new AnimatedTile(254, false, 0xFFFFFF20, 6, 
				SpriteSheet.waveTransitions, new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
		public static final Tile MUD_WATER_BTM_LFT = new AnimatedTile(255, false, 0xFFFFFF20, 7, 
				SpriteSheet.waveTransitions, new int[] { 0xFF2f5ed5, 0xFF2f5ed5, 0xFF7ab5d7 }, 5, 800);
	
	////////// BASE TILE DATA //////////
	
	// base size of tiles
	public static final int SIZE = 8;

	// used for randomly getting tiles
	private static final Random random = new Random();

	// unique identifier for each tile
	private final byte id;

	// whether or not an entity can walk through it
	private final boolean solid;

	// the pixel color on the level drawings
	private final int pixelColor;
	
	// Spritesheet used for rendering
	private final SpriteSheet sheet;
	
	// color set of the tile
	private final int[] color;
	
	// coordinates on spritesheet
	protected int xTile, yTile;
	
	private Grass grass;
	
	/**
	 * Tile ctor()
	 * Creates a tile
	 * 
	 * @param id - unique identifier for the tile
	 * @param isSolid - determines if an entity can walk through this tile
	 * @param pixelColor - the pixel color on the png file
	 */
	protected Tile(int id, boolean isSolid, int pixelColor, int xTile, int yTile, SpriteSheet sheet, int[] color) {
		
		// instance data
		this.id = (byte) id;
		this.solid = isSolid;
		this.pixelColor = pixelColor;
		this.xTile = xTile;
		this.yTile = yTile;
		this.sheet = sheet;
		this.color = color;
		
		// Non Unique ID found
		if (tileList[id] != null)
			throw new RuntimeException("Duplicate tile id at " + id);
		
		// Add to global tile IDs
		tileList[id] = this;
	}
	
	public void setGrass(Grass g) {
		this.grass = g;
	}
	
	public Grass getGrass() {
		return grass;
	}

	/**
	 * @return the unique id of a tile
	 */
	public byte getId() {
		return id;
	}

	/**
	 * @return Whether or not an entity can walk through this tile
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @return the pixel color in the png file
	 */
	public int getPixelColor() {
		return pixelColor;
	}

	/**
	 * @return the spritesheet for this tile
	 */
	public final SpriteSheet getSpriteSheet() {
		return sheet;
	}

	/**
	 * @return A random Grass tile
	 */
	public static Tile GRASS() {

		if (random.nextInt(25) == 0) {
			return GRASS1;
		} else if (random.nextInt(25) == 0) {
			return GRASS2;
		} else if (random.nextInt(25) == 0) {
			return GRASS3;
		} else if (random.nextInt(50) == 0) {
			return GRASS4;
		} else if (random.nextInt(75) == 0) {
			return GRASS5;
		} else if (random.nextInt(75) == 0) {
			return GRASS6;
		}else {
			return GRASS0;
		}
	}

	/**
	 * @return a random Concrete tile
	 */
	public static Tile CONCRETE() {
		
		if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND1;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND2;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND3;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND4;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND7;
		} else if (random.nextInt(100) == 0) {
			return WASTELAND_GROUND6;
		} else {
			return WASTELAND_GROUND5;
		}
	}

	/**
	 * Rounds a number to be divisible by
	 * the tile size
	 * 
	 * @param num - the number to become evenly divisible
	 * @return evenly divisible num by tile size
	 */
	public static final int snapToCorner(int num) {
		return num - (num % Tile.SIZE);
		
	}

	/**
	 * Most tiles don't have any logic
	 */
	public void tick() {}

	/**
	 * render()
	 * Renders the tile on the screen
	 * 
	 * @param screen - screen to render on
	 * @param level - the level the tile is on
	 * @param x - the x coordinate top left corner
	 * @param y - the y coordinate top left corner
	 */
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, xTile, yTile, sheet, false, color);
	}

	/**
	 * @return The id of the tile as a string
	 */
	public String toString() {
		return String.valueOf(id);
	}

	/**
	 * Determines if an id is a grass tile
	 * 
	 * @param num - the id to check
	 * @return true if the ID is a grass tile
	 */
	public static boolean isGrass(int num) {
		return (num >= 1 && num <= 12) || (num >= 17 && num <= 48);
	}
	
	/**
	 * Determines if an id is a dirt tile
	 * 
	 * @param num - the id to check
	 * @return true if the ID is dirt
	 */
	public static boolean isDirt(byte num) {
		return num >= 49 && num <=  81;
	}
	
	/**
	 * Determines if an id is farmalnd
	 * 
	 * @param num - the id to check
	 * @return true if the ID is farmland
	 */
	public static boolean isFarmalnd(byte num) {
		return num >= 13 && num <=  16;
	}
	
	/**
	 * Determines if an id is a water tile
	 * 
	 * @param num - the id to check
	 * @return true if the ID is a water tile
	 */
	public static boolean isWater(byte num) {
		return num >= (byte) 240 && num <= (byte) 255;
	}
	
	/**
	 * Determines if an id is a road tile
	 * 
	 * @param num - the id to check
	 * @return true if the ID is a road tile
	 */
	public static boolean isRoad(byte num) {
		return num >= (byte) 112 && num <= (byte) 140;
	}
	
	/**
	 * Determines if an id is a wood tile
	 * 
	 * @param num - the id to check
	 * @return true if the ID is a wood tile
	 */
	public static boolean isWood(byte num) {
		return num >= (byte) 181 && num <= (byte) 184;
	}
	
	/**
	 * Renders the tile to the screen
	 * in the upper left corner
	 * 
	 * @param screen- screen to render on
	 */
	public void render(Screen screen) {
		screen.render(0, 0, xTile, yTile, sheet, false, color);
	}
	
	/**
	 * Renders the tile to the screen
	 * in the upper left corner
	 * 
	 * @param screen- screen to render on
	 */
	public void render(Screen screen, int xOffset, int yOffset) {
		screen.render(xOffset, yOffset, xTile, yTile, sheet, false, color);
	}
	
	/**
	 * @return x tile on spritesheet
	 */
	public int getXTile() {
		return xTile;
	}
	
	/**
	 * @return y tile on spritesheet
	 */
	public int getYTile() {
		return yTile;
	}
	
	/**
	 * @return the color set for this tile
	 */
	public int[] getColor() {
		return color;
	}
}

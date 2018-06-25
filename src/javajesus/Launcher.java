package javajesus;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import engine.IGameLogic;
import engine.Input;
import engine.Window;
import javajesus.dataIO.PlayerData;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.gui.ControlsGUI;
import javajesus.gui.CreditsGUI;
import javajesus.gui.PlayerCreationGUI;
import javajesus.items.Item;
import javajesus.level.Level;
import javajesus.level.LevelTester;
import javajesus.level.RandomLevel;
import javajesus.level.RoadLevel;
import javajesus.level.sandbox.SandboxIslandLevel;
import javajesus.level.sandbox.SandboxOriginalLevel;
import javajesus.utility.Direction;
import javajesus.utility.GameMode;

/**
 * This is the JavaJesus Launcher window
 * 
 * It uses the JavaJesus external engine to
 * instantiate a game of a specified type
 */
public class Launcher extends Canvas implements IGameLogic {
	
	// Launch this level by clicking STORY!
	//private static final Level temp = new SandboxFirstLevel();

	// Used for serialization
	private static final long serialVersionUID = 1L;

	// Version of the game
	private final String VERSION = "Alpha 1.0.0";

	// Last known update
	private final String LAST_UPDATED = "Last Updated 6/25/2018";
	
	// launcher font
	private static final Font LAUNCHER_FONT = new Font(JavaJesus.FONT_NAME, 0, 15);

	// offset of the sword to render
	private int swordOffset;

	// starting position of sword
	private int swordStart = 110;

	// determines if one of the buttons has been clicked
	private boolean isClicked;

	// The selected button that was clicked
	private Drawable selectedButton;

	// Id of the page
	private int pageId;

	// the randomly generated background level
	public static final Level level = new RandomLevel(new Point(10, 10));

	// the image of the level
	private static final BufferedImage image =
			new BufferedImage(JavaJesus.IMAGE_WIDTH, JavaJesus.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

	// Pixel data to be used in the buffered image
	private int[] pixels;

	// Processes the level pixels
	private Screen screen = new Screen(JavaJesus.IMAGE_WIDTH, JavaJesus.IMAGE_HEIGHT);
	
	// level width and height
	public static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;
	
	// offsets for the scrolling effect
	private static final int SCROLL_OFFSET = LEVEL_WIDTH * 8 - 336;
	
	// offset of the level
	private int xOffset, yOffset;
	
	// mouse coordinates
	private int mouseX, mouseY, mouseButton;
	
	// IDs of the page screens
	private static final int MAINMENU = 0, SANDBOXMENU = 1, OPTIONSMENU = 2, STORYMENU = 3, AUDIOMENU = 4, FIXEDPLAYERMENU = 7;

	// Ids of the buttons
	private static final int STORY = 0, SANDBOX = 1, OPTIONS = 2, CREDIT = 3, QUIT = 4,
			BACK = 7, AUDIO = 8, VIDEO = 9, CONTROLS = 10, MUTE = 13, SLOT = 14, DELETE_1 = 15, DELETE_2 = 16, DELETE_3 = 17, FIXED_SELECTED = 18;

	// Buttons on the launcher
	private LauncherButton story, sandbox, options, credits, audio, video, controls, mute,
	back, quit, delete_1, delete_2, delete_3;
	
	// Slot buttons on the launcher
	private SlotButton slot_1, slot_2, slot_3;
	
	// sandbox level selector
	private SandboxPanel sandboxPanel;

	// buffered images that are displayed on the screen
	private BufferedImage background, sword_selector;
	
	// direction the level moves
	private Direction dir = Direction.SOUTH_EAST;
	
	// whether or not the loop should continue running
	private boolean running = true;
	
	// layout for the launcher
	private CardLayout cardLayout;
	
	// IDs for the cardlayout
	public static final String MAIN = "Main", CREATION = "Creation", CREDITS = "Credits", CONTROLGUI = "Controls";
	
	// JPanel of the player creation GUI
	private PlayerCreationGUI playerCreationGUI;
	
	// the display of this launcher
	private JPanel display;

	/**
	 * Constructor that creates the JFrame
	 */
	public Launcher() {
		
		// map pixels
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		SoundHandler.playLoop(SoundHandler.alphaTheme);
		
		// initialize JSwing stuff
		display = new JPanel(cardLayout = new CardLayout(0, 0));
		display.add(this, MAIN);
		display.add(playerCreationGUI = new PlayerCreationGUI(this), CREATION);
		display.add(new CreditsGUI(this), CREDITS);
		display.add(new ControlsGUI(this), CONTROLGUI);
		
		// show the main display
		cardLayout.show(display, MAIN);
		
	}

	/**
	 * Initializes instance variables and loads button images
	 */
	public void init() throws Exception {
		
		// load the random level
		level.generateLevel();
		
		BufferedImage story_on, story_off, sandbox_on, sandbox_off, options_on, options_off, credits_on, credits_off,
		        audio_on, audio_off, video_on, video_off,
		        controls_on, controls_off, mute_on, mute_off, back_on,
		        back_off, quit_on, quit_off, delete_off, delete_on;
		
		background = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/MENUS/Main_Menu.png"));

		sword_selector = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/sword_selector.png"));

		story_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/story_on.png"));

		story_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/story_off.png"));

		sandbox_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/sandbox_on.png"));

		sandbox_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/sandbox_off.png"));

		options_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/options_on.png"));

		options_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/options_off.png"));

		credits_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/credits_on.png"));

		credits_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/credits_off.png"));

		audio_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/audio_on.png"));

		audio_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/audio_off.png"));

		video_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/video_on.png"));

		video_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/video_off.png"));

		controls_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/controls_on.png"));

		controls_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/controls_off.png"));

		mute_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/mute_on.png"));

		mute_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/mute_off.png"));

		back_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/back_on.png"));

		back_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/back_off.png"));

		quit_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/quit_on.png"));

		quit_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/quit_off.png"));
		
		delete_on = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/delete_on.png"));

		delete_off = ImageIO.read(Launcher.class.getResource("/VISUAL_DATA/GUI/BUTTONS/MENU_BUTTONS/delete_off.png"));
		
		story = new LauncherButton(450, STORY, story_off, story_on);
		sandbox = new LauncherButton(500, SANDBOX, sandbox_off, sandbox_on);
		options = new LauncherButton(550, OPTIONS, options_off, options_on);
		credits = new LauncherButton(600, CREDIT, credits_off, credits_on);
		audio = new LauncherButton(450, AUDIO, audio_off, audio_on);
		video = new LauncherButton(500, VIDEO, video_off, video_on);
		controls = new LauncherButton(550, CONTROLS, controls_off, controls_on);
		slot_1 = new SlotButton(450, 1);
		slot_2 = new SlotButton(500, 2);
		slot_3 = new SlotButton(550, 3);
		delete_1 = new LauncherButton(450, DELETE_1, delete_off, delete_on);
		delete_2 = new LauncherButton(500, DELETE_2, delete_off, delete_on);
		delete_3 = new LauncherButton(550, DELETE_3, delete_off, delete_on);
		delete_1.setLeftJustified();
		delete_2.setLeftJustified();
		delete_3.setLeftJustified();
		mute = new LauncherButton(450, MUTE, mute_off, mute_on);
		back = new LauncherButton(650, BACK, back_off, back_on);
		quit = new LauncherButton(650, QUIT, quit_off, quit_on);
		
		sandboxPanel = new SandboxPanel(400);
		setFocusable(true);

	}
	
	/**
	 * Additional modifications to the window
	 */
	public void modifyWindow(Window window) {
		
		window.getContentPane().add(display);
		window.pack();
		window.setLocationRelativeTo(null);
		
		// add listeners
		window.addListeners(this, Input.MOUSE, Input.MOUSE_MOTION);
		
	}

	/**
	 * Displays the pixels onto the screen
	 */
	public void render(Window window) {
		
		// get the buffer strategy
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		// render the background tiles
		level.render(screen);

		// set the pixels of the buffered image
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * JavaJesus.IMAGE_WIDTH] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		screen.clear();

		// draw the images
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		switch (pageId) {

		case MAINMENU: {

			story.draw(g);
			sandbox.draw(g);
			options.draw(g);
			credits.draw(g);
			quit.draw(g);
			break;

		}
		case SANDBOXMENU: {

			sandboxPanel.draw(g);
			break;

		}
		case OPTIONSMENU: {

			audio.draw(g);
			video.draw(g);
			controls.draw(g);
			break;

		}
		case FIXEDPLAYERMENU:
		case STORYMENU:
			slot_1.draw(g);
			slot_2.draw(g);
			slot_3.draw(g);

			delete_1.draw(g);
			delete_2.draw(g);
			delete_3.draw(g);

			break;

		case AUDIOMENU: {
			mute.draw(g);
			break;
		}

		}

		if (pageId != MAINMENU) {
			back.draw(g);
		}

		g.setColor(Color.WHITE);
		g.setFont(LAUNCHER_FONT);
		g.drawString(VERSION, 5, 20);
		g.drawString(LAST_UPDATED, 5, JavaJesus.WINDOW_HEIGHT - 30);
		g.dispose();
		bs.show();

	}

	/**
	 * Processes button actions
	 */
	private void doAction() {

		int id = selectedButton.getActionId();

		switch (id) {

		case STORY: {
			//this.pageId = STORYMENU;
			return;
		}
		case SANDBOX: {
			this.pageId = SANDBOXMENU;
			return;
		}
		case OPTIONS: {
			this.pageId = OPTIONSMENU;
			return;
		}
		case CREDIT: {
			cardLayout.show(display, CREDITS);
			return;
		}
		case QUIT: {
			running = false;
			return;
		}
		case FIXED_SELECTED: {
			this.pageId = FIXEDPLAYERMENU;
			return;
		}
		case BACK: {
			switch (pageId) {
			case AUDIOMENU:
				this.pageId = OPTIONSMENU;
				break;
			default:
				this.pageId = MAINMENU;
			}
			return;
		}
		case AUDIO: {
			this.pageId = AUDIOMENU;
			return;
		}
		case VIDEO: {
			System.out.println("Video Coming Soon");
			return;
		}
		case CONTROLS: {
			cardLayout.show(display, CONTROLGUI);
			return;
		}
		case SLOT: {
			
			// slot file selected
			int numSlot = ((SlotButton) selectedButton).getSlot();
			
			// Game Mode based on which screen
			GameMode mode = GameMode.ADVENTURE;
			if (pageId == FIXEDPLAYERMENU) {
				mode = GameMode.SANDBOX;
			} 
			
			// load if it exists
			if (PlayerData.exists(numSlot)) {

				try {
					// create the player
					Player player = createPlayer(numSlot, mode);
					
					// stop the launcher
					running = false;

					// start the game
					new JavaJesus(mode, false, player);

				} catch (IOException e) {
					e.printStackTrace();
				}

				// create a player creation file
			} else {
				
				// show the player creation display
				playerCreationGUI.setSlot(numSlot);
				cardLayout.show(display, CREATION);
				
			}
			
			return;
		}
		case DELETE_1:
			PlayerData.delete(1);
			updateButtons();
			return;
		case DELETE_2:
			PlayerData.delete(2);
			updateButtons();
			return;
		case DELETE_3:
			PlayerData.delete(3);
			updateButtons();
			return;
		case MUTE: {
			SoundHandler.toggleMute();
			return;
		}
		default: {
			System.out.println("ID IS MISSING");
			return;
		}
		}

	}

	/**
	 * Creates the player based on the gamemode
	 * 
	 * @return the player
	 */
	private Player createPlayer(int slot, GameMode mode) throws IOException {
		
		// load player creation data
		Object[] data = PlayerData.load(slot);
		int skin = (int) data[0];
		int hair = (int) data[1];
		int shirt = (int) data[2];
		int pants = (int) data[3];
		byte gender = (byte) data[4];
		Item weapon = (Item) data[5];
		String name = (String) data[6];
		
		// level to set the player
		Level level = getLevel(mode, slot);
		
		// load the music!
		SoundHandler.playLoop(level.getBackgroundMusic());
		
		// load the level
		if (!level.isLoaded()) {
			level.generateLevel();
		}
		
		// Player to create
		Player player = new Player(name, level, level.getSpawnPoint().x, level.getSpawnPoint().y, gender);
		player.setShirtColor(shirt);
		player.setSkinColor(skin);
		player.setHairColor(hair);
		player.setPantsColor(pants);
		player.getInventory().add(weapon);
		
		// add the player to the level
		level.add(player);
		
		return player;
	}

	/**
	 * Gets the level to initialize
	 * 
	 * @param mode - gamemode
	 * @return - the level
	 */
	private Level getLevel(GameMode mode, int slot) {
		
		if (mode == GameMode.ADVENTURE) {
			//Level.createStoryLevels();
			//return LordHillsboroughsDomain.level;
			return null;
		} else {
			return sandboxPanel.getLevel(slot);
		}
		
	}

	/**
	 * Input checking for the window
	 */
	public void input(Window window) {
		mouseX = window.getMouseX();
		mouseY = window.getMouseY();
		mouseButton = window.getMouseButton();
	}

	/**
	 * Updates the background level
	 */
	@Override
	public void update() {
		yOffset = (yOffset + 1) % SCROLL_OFFSET;
		if (dir == Direction.SOUTH_EAST) {
			xOffset++;
		} else {
			xOffset--;
		}
		if (xOffset <= 0) {
			dir = Direction.SOUTH_EAST;
			xOffset = 0;
		} else if (xOffset >= SCROLL_OFFSET) {
			dir = Direction.SOUTH_WEST;
			xOffset = SCROLL_OFFSET - 1;
		}
		
		// now set the offset
		level.setOffset(xOffset, yOffset);
		
		if (isClicked) {
			swordOffset += 10;
			if (swordOffset > 100) {
				swordOffset = 0;
				isClicked = false;
				doAction();
			}
		}
		
	}

	/**
	 * Called after the internal loop terminates
	 */
	@Override
	public void onClose() {
		
	}

	/**
	 * The internal loop condition statement
	 */
	@Override
	public boolean running() {
		return running;
	}
	
	/**
	 * Updates the slot buttons
	 */
	public void updateButtons() {
		// update all the slot buttons
		slot_1.update();
		slot_2.update();
		slot_3.update();
	}
	
	/**
	 * Launcher Button that is used on the launcher screen
	 */
	private class LauncherButton implements Drawable {

		// upper left corner
		private int x, y;
		
		// action ID
		private int actionId;
		
		// images of button states
		private BufferedImage imageOff, imageOn;

		/**
		 * LauncherButton ctor()
		 * 
		 * @param yPos - vertical postion on screen
		 * @param actionId - id when button is clicked
		 * @param imageOff - off state
		 * @param imageOn - on state
		 */
		public LauncherButton(int yPos, int actionId, BufferedImage imageOff, BufferedImage imageOn) {
			x = JavaJesus.WINDOW_WIDTH / 2 - imageOff.getWidth() / 2;
			y = yPos;
			this.imageOff = imageOff;
			this.imageOn = imageOn;
			this.actionId = actionId;
		}
		
		/**
		 * Sets a button left justified
		 */
		public void setLeftJustified() {
			x = 20;
		}

		/**
		 * @return the actionID
		 */
		public int getActionId() {
			return actionId;
		}

		/**
		 * Renders the button on the screen
		 */
		public void draw(Graphics g) {
			
			// mouse inside bounds
			if (mouseX > x && mouseX < x + imageOff.getWidth() && mouseY > y
					&& mouseY < y + imageOff.getHeight()) {
				
				// draw the on state
				g.drawImage(imageOn, x, y, null);
				
				// draw the sword
				if (!isClicked || selectedButton == this) {
					g.drawImage(sword_selector, x - swordStart + swordOffset, y, null);
				}
				
				// mouse is clicked
				if (!isClicked && mouseButton == 1) {
					SoundHandler.play(SoundHandler.sheathe);
					selectedButton = this;
					isClicked = true;
				}

			} else {
				
				// draw the off state
				g.drawImage(imageOff, x, y, null);
			}
		}
	}
	
	/**
	 * Slot Button that is used for player save files
	 */
	private class SlotButton implements Drawable {

		// upper left corner
		private int x, y;

		// slot used to check for save file
		private int slot;
		
		// font used to render the text
		private final Font font = new Font(JavaJesus.FONT_NAME, 0, 35);
		
		// Text to render
		private String text;

		/**
		 * SlotButton ctor()
		 * 
		 * @param yPos - y position on screen
		 * @param actionId - action ID
		 */
		private SlotButton(int yPos, int slot) {
			y = yPos;
			this.slot = slot;
			
			// get the text
			update();
			
		}
		
		/**
		 * Sets the text to render
		 */
		private void update() {
			if (PlayerData.exists(slot)) {
				text = (String) PlayerData.load(slot)[6];
			} else {
				text = "Empty";
			}
		}

		/**
		 * @return action ID
		 */
		public int getActionId() {
			return SLOT;
		}
		
		/**
		 * @return the slot number
		 */
		public int getSlot() {
			return slot;
		}

		/**
		 * Renders the button on the screen
		 * @param g - graphics
		 */
		public void draw(Graphics g) {
			
			// set the font
			g.setFont(font);
			
			// center the text
			FontMetrics fm = g.getFontMetrics();
			x = JavaJesus.WINDOW_WIDTH / 2 - fm.stringWidth(text) / 2;
			
			// set the right color
			if (mouseX > x && mouseX < x + fm.stringWidth(text) && mouseY > y
					&& mouseY < y + fm.getHeight()) {
				
				// mouse is hovering
				g.setColor(Color.WHITE);
				
				// draw sword selector
				if (!isClicked || selectedButton == this) {
					g.drawImage(sword_selector, x - swordStart + swordOffset, y, null);
				}
				
				// mouse clicked
				if (!isClicked && mouseButton == 1) {
					SoundHandler.play(SoundHandler.sheathe);
					selectedButton = this;
					isClicked = true;
				}

			} else {
				
				// Not hovering
				g.setColor(Color.BLACK);
			}
			
			// draw the text
			g.drawString(text, x, y + fm.getHeight());
		}
	}
	
	/*
	 * Draws the sandbox level selector
	 */
	private class SandboxPanel implements Drawable {
		
		// level paths
		private static final String originalPath = "/VISUAL_DATA/MAPS/original_map.png", 
				islandPath = "/VISUAL_DATA/MAPS/island_map.png",
				genericPath = "/VISUAL_DATA/MAPS/generic_map.png",
				roadTesterPath = "/VISUAL_DATA/MAPS/road_tester_map.png";
		
		// dimensions of the panel
		private static final int width = 200, height = 200;
		
		// possible levels
		private BufferedImage original, island, generic, roadTester;
		
		// current displayed level
		private int selected;
		
		// list of level displays
		private static final int ORIGINAL = 0, ISLAND = 1, TILE_TESTER = 2, ROAD_TESTER = 3, RANDOM = 4;
		
		// coordinates
		private int x, y;
		
		// click cooldown
		private boolean cooldown;
		
		// font used to render the text
		private final Font font = new Font(JavaJesus.FONT_NAME, 0, 30);

		// Text to render
		private String name;
		
		// back and next arrows
		private final String back = "<", next = ">";
		
		/**
		 * SandboxPanel ctor()
		 * Loads level files and displays them in a window
		 * 
		 * @throws IOException
		 */
		private SandboxPanel(int yPos) throws IOException {
			
			// instance data
			this.x = JavaJesus.WINDOW_WIDTH / 2 - width / 2;
			this.y = yPos;
			
			// load the files
			original = ImageIO.read(Level.class.getResource(originalPath));
			island = ImageIO.read(Level.class.getResource(islandPath));
			generic = ImageIO.read(Level.class.getResource(genericPath));
			roadTester = ImageIO.read(Level.class.getResource(roadTesterPath));
			
			// sets the name
			update();
			
		}

		/**
		 * @return The level that was selected
		 */
		public Level getLevel(int slot) {

			// get the right level
			try {
				switch (selected) {
				case ORIGINAL:
					return  SandboxOriginalLevel.alpha = new SandboxOriginalLevel(slot);
				case ISLAND:
					return new SandboxIslandLevel(slot);
				case TILE_TESTER:
					return new LevelTester(slot);
				case ROAD_TESTER:
					return new RoadLevel(slot);
				case RANDOM:
					return Launcher.level;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
		
		/**
		 * Loops the levels around
		 */
		private void update() {
			
			// loop around
			if (selected < 0) {
				selected = 4;
			}
			
			// loop around
			if (selected > 4) {
				selected = 0;
			}
			
			// get the right name
			switch (selected) {
			case ORIGINAL:
				name = "ALPHA";
				break;
			case ISLAND:
				name = "Island";
				break;
			case TILE_TESTER:
				name = "Tile Tester";
				break;
			case ROAD_TESTER:
				name = "Road Tester";
				break;
			case RANDOM:
				name = "Random";
				break;
			}
			
		}

		/**
		 * Draws the image to the screen
		 */
		@Override
		public void draw(Graphics g) {
			
			// reset the cooldown
			if (cooldown) {
				cooldown = mouseButton == 1;
			}
			
			// center the image
			x = JavaJesus.WINDOW_WIDTH / 2 - width / 2;

			// draw the selected level
			switch (selected) {
			case ORIGINAL:
				g.drawImage(original, x, y, width, height, null);
				break;
			case ISLAND:
				g.drawImage(island, x, y, width, height, null);
				break;
			case TILE_TESTER:
			case RANDOM:
				g.drawImage(generic, x, y, width, height, null);
				break;
			case ROAD_TESTER:
				g.drawImage(roadTester, x, y, width, height, null);
				break;
			}
			
			// mouse inside bounds
			if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {

				// mouse clicked
				if (!cooldown && mouseButton == 1) {
					cooldown = true;
					SoundHandler.play(SoundHandler.click);
					selectedButton = this;
					isClicked = true;
					return;
				}
			}
			
			// set the font
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			
			// draw selector arrows
			g.setColor(Color.WHITE);
			g.drawString(back, x - fm.stringWidth(back) + 3, y + height / 2 + fm.getHeight());
			g.drawString(next, x + width, y + height / 2 + fm.getHeight());
			
			// mouse inside back
			if (mouseX > x - fm.stringWidth(back) + 3 && mouseX < x && mouseY > y + height / 2
			        && mouseY < y + height / 2 + fm.getHeight()) {

				// mouse clicked
				if (!cooldown && mouseButton == 1) {
					cooldown = true;
					SoundHandler.play(SoundHandler.click);
					selected--;
					update();
					return;
				}
			}
			
			// mouse inside next
			if (mouseX > x + width && mouseX < x + width + fm.stringWidth(next) && mouseY > y + height / 2
			        && mouseY < y + height / 2 + fm.getHeight()) {

				// mouse clicked
				if (!cooldown && mouseButton == 1) {
					cooldown = true;
					SoundHandler.play(SoundHandler.click);
					selected++;
					update();
					return;
				}
			}

			// center the text
			x = JavaJesus.WINDOW_WIDTH / 2 - fm.stringWidth(name) / 2;
			
			// draw the text
			g.setColor(Color.WHITE);
			g.drawString(name, x, y + height + fm.getHeight() + 5);

		}

		/**
		 * ID returned when clicked
		 */
		@Override
		public int getActionId() {
			return FIXED_SELECTED;
		}
		
	}
	
	/**
	 * Buttons in the launcher
	 */
	private interface Drawable {
		
		// how to render the button
		public void draw(Graphics g);
		
		// the action ID when clicked
		public int getActionId();
		
	}

}

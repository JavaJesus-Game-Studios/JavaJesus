package ca.javajesus.game;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.gui.PauseGUI;
import ca.javajesus.game.gui.ScreenGUI;
import ca.javajesus.game.gui.intro.IntroGUI;
import ca.javajesus.game.gui.inventory.InventoryGUI;

public class Display extends Canvas {

	private static final long serialVersionUID = -3366355134015679332L;
	
	public static final String NAME = "Java Jesus by the Coders of Anarchy";
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	
	protected static JFrame frame;
	
	/** Creates the buffered image to be rendered onto the game screen */
	protected transient BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	/** Pixel data to be used in the buffered image */
	protected int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();
	
	public Screen screen;
	
	protected PlayerHUD hud;
	
	/** Used for displaying different GUIs on the display */
	private static PauseGUI pause;
	private static InventoryGUI inventory;
	private static JPanel display;
	private static IntroGUI introScreen;
	
	private static CardLayout cardlayout;
	private static int guiID = 0;
	
	public static boolean inGameScreen;
	
	public Display() {
		screen = new Screen(WIDTH, HEIGHT);
		inventory = new InventoryGUI(Game.player);
		hud = new PlayerHUD(Game.player);
		pause = new PauseGUI();
		introScreen = new IntroGUI();
		
		display = new JPanel(new CardLayout());
		display.add(introScreen, "Intro");
		display.add(this, "Main");
		display.add(inventory, "Inventory");
		display.add(pause, "Pause");
		
		cardlayout = (CardLayout) display.getLayout();
		
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().add(display);
		frame.pack();
		frame.requestFocus();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		new ChatHandler();
	}
	

	public static void displayInventory() {
		guiID = 2;
		cardlayout.show(display, "Inventory");
		inventory.requestFocusInWindow();
		inGameScreen = false;
		inventory.inventory.repaint();
	}

	public static void displayPause() {
		guiID = 3;
		cardlayout.show(display, "Pause");
		pause.requestFocusInWindow();
		inGameScreen = false;
	}

	public static void displayGame() {
		inGameScreen = true;
		guiID = 1;
		cardlayout.show(display, "Main");
		display.getComponent(1).requestFocusInWindow();
	}
	
	public void sendCrashReportToScreen(Exception e) {
		
		Graphics g = getGraphics();
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.WHITE);
		g.drawString(e.toString(), 0, 50);
		for (int i = 0; i < e.getStackTrace().length; i++) {
			g.drawString(e.getStackTrace()[i].toString(), 0, 100 + 50 * i);
		}
		
	}
	
	public void tick() {
		int hours = Game.hours;
		if (hours >= 6 && hours < 10) {
			screen.setShader(0x5C3D99);
		} else if (hours >= 10 && hours < 17) {
			screen.setShader(0);
		} else if (hours >= 17 && hours < 21) {
			screen.setShader(0xB24700);
		} else {
			screen.setShader(0x0A1433);
		}
		
		if (inGameScreen) {
			Game.getLevel().tick();
		} else {
			((ScreenGUI) display.getComponent(guiID)).tick();
		}
	}
	
	protected void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = Game.player.getX() - (screen.width / 2);
		int yOffset = Game.player.getY() - (screen.height / 2);
		if (Game.player.isDriving) {
			xOffset = Game.player.vehicle.getX() - (screen.width / 2);
			yOffset = Game.player.vehicle.getY() - (screen.height / 2);
		}

		if (inGameScreen) {
			Game.getLevel().renderTile(screen, xOffset, yOffset);
			Game.getLevel().renderEntities(screen);
		}

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				pixels[x + y * WIDTH] = screen.pixels[x + y * screen.width];
			}

		}
		screen.clear();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString(Game.player + ": " + Game.player.getX() + ", " + Game.player.getY()
				+ " Time: " + Game.hours + ":" + Game.minutes, 5, 20);
		hud.draw(g);
		ChatHandler.drawMessages(g);
		g.dispose();
		bs.show();
	}

}

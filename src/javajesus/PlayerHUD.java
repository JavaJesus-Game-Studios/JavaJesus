package javajesus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.utility.GameMode;

/*
 * Manages the display of the current equipped item status and health bars stats
 */
public class PlayerHUD {

	// the player to use
	private Player player;

	// the different item elements on the HUD
	private BufferedImage item, heart_full, heart_half, heart_empty, orb_full, orb_half, orb_empty, box;

	// modifier to inflate the hud
	private final static int MODIFIER = 4;
	
	// item size = 3x3 x 8
	private static final int SIZE = 24;
	
	// handles the image processing
	private Screen screen;
	
	// item pixels
	int[] pixels;

	// bar offsets
	private final static int BAR_XOFFSET = 750, BAR_YOFFSET = 50, BAR_VSPACE = 30, NUM_HEARTS = 6;

	// offsets of various HUD elements
	private static int box_yOffset, gun_xOffset, gun_yOffset, string_xOffset, string_yOffset;
	
	// font of ammo string
	private static final Font font = new Font(JavaJesus.FONT_NAME, 0, 20);

	/**
	 * Loads all the images of the weapons
	 */
	public PlayerHUD(Player player) {
		
		// initialize instance data
		this.player = player;
		item = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
		screen = new Screen(SIZE, SIZE);
		pixels = ((DataBufferInt) item.getRaster().getDataBuffer()).getData();

		// initialize all the images into memory that will be rendered on the screen
		try {

			// load the other HUD elements
			heart_full = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/heart_full.png"));
			heart_half = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/heart_half.png"));
			heart_empty = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/heart_empty.png"));
			orb_full = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/orb_full.png"));
			orb_half = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/orb_half.png"));
			orb_empty = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/orb_empty.png"));
			box = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/box.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// initialize box offset
		box_yOffset = JavaJesus.WINDOW_HEIGHT - (box.getHeight() * MODIFIER) - JavaJesus.HUD_OFFSET;

	}

	/**
	 * Draws the sprites and stat bars
	 */
	public void draw(Graphics g) {

		// draws a box to display the gun in
		g.drawImage(box, 0, box_yOffset, box.getWidth() * MODIFIER, box.getHeight() * MODIFIER, null);

		// initialize offsets
		gun_xOffset = (box.getWidth() * MODIFIER) / 2 - (item.getWidth() * MODIFIER) / 2;
		gun_yOffset = (box.getHeight() * MODIFIER) / 2 - (item.getHeight() * MODIFIER) / 2 + box_yOffset;
		string_xOffset = gun_xOffset + 15;
		string_yOffset = gun_yOffset + 60;
		
		// render the item
		renderItem();

		// draw the item
		g.drawImage(item, gun_xOffset, gun_yOffset, item.getWidth() * MODIFIER, item.getHeight() * MODIFIER, null);
		
		// draw the ammo info if a gun
		if (player.getEquippedGun() != null) {
			g.setFont(font);
			g.setColor(Color.YELLOW);
			g.drawString((int) player.getEquippedGun().getCurrentAmmo() + " / " + player.getEquippedGun().getClipSize(),
			        string_xOffset, string_yOffset);
		}
		
		// used for calculating which heart to draw
		double health = player.getCurrentHealth();

		// draw 6 hearts
		for (int i = 0; i < NUM_HEARTS; i++) {

			BufferedImage image;

			if (health - (1 / (double) NUM_HEARTS * player.getMaxHealth()) > 0) {
				health -= 1 / (double) NUM_HEARTS * player.getMaxHealth();
				image = heart_full;
			} else if (health - (1 / (double) (NUM_HEARTS * 2) * player.getMaxHealth()) > 0) {
				health -= 1 / (double) (NUM_HEARTS * 2) * player.getMaxHealth();
				image = heart_half;
			} else {
				image = heart_empty;

			}

			g.drawImage(image, BAR_XOFFSET + i * image.getWidth(), box_yOffset + BAR_YOFFSET, image.getWidth(),
			        image.getHeight(), null);
		}

		// used for calculating which orb to draw
		double stamina = player.getCurrentStamina();

		// draw 6 orbs
		for (int i = 0; i < NUM_HEARTS; i++) {

			BufferedImage image;

			if (stamina - (1 / (double) NUM_HEARTS * player.getMaxStamina()) > 0) {
				stamina -= 1 / (double) NUM_HEARTS * player.getMaxStamina();
				image = orb_full;
			} else if (stamina - (1 / (double) (NUM_HEARTS * 2) * player.getMaxStamina()) > 0) {
				stamina -= 1 / (double) (NUM_HEARTS * 2) * player.getMaxStamina();
				image = orb_half;
			} else {
				image = orb_empty;

			}

			g.drawImage(image, BAR_XOFFSET + i * image.getWidth(), box_yOffset + BAR_VSPACE + BAR_YOFFSET,
			        image.getWidth(), image.getHeight(), null);
		}

		// for survival mode
		if (JavaJesus.mode == GameMode.FIXED) {
			g.setColor(Color.white);
			g.setFont(new Font(JavaJesus.FONT_NAME, Font.BOLD, 30));
			g.drawString("Kills: " + JavaJesus.score, JavaJesus.WINDOW_WIDTH / 2 - 10, 30);
		}

	}

	/**
	 * Renders the right item to the screen
	 */
	private void renderItem() {

		// make sure player is here
		if (player != null) {
			
			// check if a gun is equipped
			if (player.getEquippedGun() != null) {

				player.getEquippedGun().renderHUD(screen);
				
				// check if a sword is equipped
			} else if (player.getEquippedSword() != null) {

				player.getEquippedSword().renderHUD(screen);
			}
			
		}
		
		// add screen pixels to image pixels
		for (int y = 0; y < screen.getHeight(); y++) {
			for (int x = 0; x < screen.getWidth(); x++) {
				pixels[x + y * screen.getWidth()] = screen.getPixels()[x + y * screen.getWidth()];
			}

		}
		
		// clear the screen
		screen.clear();
		
	}

}

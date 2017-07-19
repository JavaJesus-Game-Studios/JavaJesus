package javajesus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.utility.GameMode;

import javax.imageio.ImageIO;

/*
 * Manages the display of the current equipped item status and health bars stats
 */
public class PlayerHUD {

	// the player to use
	private Player player;

	// the different item elements on the HUD
	private BufferedImage item, box;

	// number of heart/stamina states
	private static final int NUM_STATES = 8;

	// size of images drawn through graphics
	private static final int HEART_SIZE = 75, STAMINA_SIZE = 150;

	// list of heart segments and stamina segments
	private static final BufferedImage[] hearts = new BufferedImage[NUM_STATES],
			stamina = new BufferedImage[NUM_STATES];

	// modifier to inflate the hud
	private final static int MODIFIER = 4;

	// item size = 3x3 x 8
	private static final int SIZE = 24;

	// handles the item image processing
	private Screen screen;

	// item pixels
	int[] pixels;

	// bar offsets
	private final static int BAR_XOFFSET = 810, BAR_YOFFSET = -50;
	
	// offsets for heart inside of stamina bar
	private final static int BAR_XSPACE = 38, BAR_VSPACE = 40;

	// offsets of various HUD elements
	private static int box_yOffset, gun_xOffset, gun_yOffset, string_xOffset,
			string_yOffset;

	// font of ammo string
	private static final Font font = new Font(JavaJesus.FONT_NAME, 0, 20);

	// font of score board
	private static final Font score_font = new Font(JavaJesus.FONT_NAME,
			Font.BOLD, 30);
	
	// the buffered image elements
	private BufferedImage bar, heart;

	/**
	 * Loads all the images of the weapons
	 */
	public PlayerHUD(Player player) {

		// initialize instance data
		this.player = player;
		item = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		screen = new Screen(SIZE, SIZE);
		pixels = ((DataBufferInt) item.getRaster().getDataBuffer()).getData();

		// initialize all the images into memory that will be rendered on the
		// screen
		try {
			box = ImageIO.read(PlayerHUD.class
					.getResource("/VISUAL_DATA/GUI/HUD/box.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create a temporary screen class to load the buffered image data
		Screen tempH = new Screen(SIZE, SIZE);

		// initialize the hearts
		for (int i = 0; i < NUM_STATES; i++) {

			// create the buffered images
			hearts[i] = new BufferedImage(SIZE, SIZE,
					BufferedImage.TYPE_INT_ARGB);

			// get heart and stamina pixels
			int[] hp = ((DataBufferInt) hearts[i].getRaster().getDataBuffer())
					.getData();

			// render the different states to screen first
			tempH.render24bit(i, 2, SpriteSheet.hudWeapons);

			// set the pixel data
			// add screen pixels to image pixels
			for (int y = 0; y < tempH.getHeight(); y++) {
				for (int x = 0; x < tempH.getWidth(); x++) {

					// color of the pixel with alpha
					int colH = tempH.getPixels()[x + y * tempH.getWidth()];

					// if not black, make it opaque
					if (colH != 0) {
						colH = colH | 0xFF000000;
					}

					// set the buffered image pixels
					hp[x + y * tempH.getWidth()] = colH;
				}

			}

			// clear the screens
			tempH.clear();
		}

		// create a temporary screen class to load the buffered image data
		Screen tempS1 = new Screen(SIZE * 2, SIZE * 2);
		Screen tempS2 = new Screen(SIZE * 2, SIZE * 2);

		// initialize the stamina display
		for (int i = 0; i < NUM_STATES / 2; i++) {

			// create the buffered images
			stamina[i] = new BufferedImage(SIZE * 2, SIZE * 2,
					BufferedImage.TYPE_INT_ARGB);
			stamina[i + NUM_STATES / 2] = new BufferedImage(SIZE * 2, SIZE * 2,
					BufferedImage.TYPE_INT_ARGB);

			// get heart and stamina pixels
			int[] s1 = ((DataBufferInt) stamina[i].getRaster().getDataBuffer())
					.getData();
			int[] s2 = ((DataBufferInt) stamina[i + NUM_STATES / 2].getRaster().getDataBuffer())
					.getData();

			// render the different states to screen first
			tempS1.render48bit(i, 2, SpriteSheet.hudWeapons);
			tempS2.render48bit(i, 3, SpriteSheet.hudWeapons);

			// set the pixel data
			// add screen pixels to image pixels
			for (int y = 0; y < tempS1.getHeight(); y++) {
				for (int x = 0; x < tempS1.getWidth(); x++) {

					// color of the pixel with alpha
					int colS1 = tempS1.getPixels()[x + y * tempS1.getWidth()];
					int colS2 = tempS2.getPixels()[x + y * tempS2.getWidth()];

					// if not black, make it opaque
					if (colS1 != 0) {
						colS1 = colS1 | 0xFF000000;
					}
					if (colS2 != 0) {
						colS2 = colS2 | 0xFF000000;
					}

					// set the buffered image pixels
					s1[x + y * tempS1.getWidth()] = colS1;
					s2[x + y * tempS2.getWidth()] = colS2;
				}

			}

			// clear the screens
			tempS1.clear();
			tempS2.clear();
		}

		// initialize box offset
		box_yOffset = JavaJesus.WINDOW_HEIGHT - (box.getHeight() * MODIFIER)
				- JavaJesus.HUD_OFFSET;
		
		// give default buffered images
		heart = hearts[0];
		bar = stamina[0];

	}

	/**
	 * Draws the sprites and stat bars
	 */
	public void draw(Graphics g) {

		// draws a box to display the gun in
		g.drawImage(box, 0, box_yOffset, box.getWidth() * MODIFIER,
				box.getHeight() * MODIFIER, null);

		// initialize offsets
		gun_xOffset = (box.getWidth() * MODIFIER) / 2
				- (item.getWidth() * MODIFIER) / 2;
		gun_yOffset = (box.getHeight() * MODIFIER) / 2
				- (item.getHeight() * MODIFIER) / 2 + box_yOffset;
		string_xOffset = gun_xOffset;
		string_yOffset = gun_yOffset;

		// render the item
		renderItem();

		// draw the item
		g.drawImage(item, gun_xOffset, gun_yOffset, item.getWidth() * MODIFIER,
				item.getHeight() * MODIFIER, null);

		// draw the ammo info if a gun
		if (player.getEquippedGun() != null) {
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString((int) player.getEquippedGun().getCurrentAmmo() + " / "
					+ player.getEquippedGun().getClipSize(), string_xOffset,
					string_yOffset);
		}
		
		g.drawImage(bar, BAR_XOFFSET, box_yOffset
				+ BAR_YOFFSET, STAMINA_SIZE, STAMINA_SIZE, null);

		g.drawImage(heart, BAR_XOFFSET + BAR_XSPACE, box_yOffset + BAR_YOFFSET + BAR_VSPACE,
				HEART_SIZE, HEART_SIZE, null);

		// for survival mode
		if (JavaJesus.mode == GameMode.FIXED) {

			// set display font
			g.setColor(Color.BLACK);
			g.setFont(score_font);

			// center based on text and font metrics
			String score = "Kills: " + JavaJesus.score;
			FontMetrics fm = g.getFontMetrics();
			g.drawString(score,
					JavaJesus.WINDOW_WIDTH / 2 - fm.stringWidth(score) / 2,
					fm.getHeight());
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

				// color of the pixel with alpha
				int col = screen.getPixels()[x + y * screen.getWidth()];

				// if not black, make it opaque
				if (col != 0) {
					col = col | 0xFF000000;
				}

				// set the buffered image pixels
				pixels[x + y * screen.getWidth()] = col;
			}

		}

		// clear the screen
		screen.clear();

	}
	
	/**
	 * Logic of the hud display
	 */
	protected void update() {
		
		// used for calculating which stamina bar to draw
		double remainingStamina = player.getCurrentStamina();

		// stamina per bar
		double sPerBar = player.getMaxStamina();

		// render fractions
		if (remainingStamina > (7.0 / NUM_STATES) * sPerBar) {
			bar = stamina[0];
		} else if (remainingStamina > (6.0 / NUM_STATES) * sPerBar) {
			bar = stamina[1];
		} else if (remainingStamina > (5.0 / NUM_STATES) * sPerBar) {
			bar = stamina[2];
		} else if (remainingStamina > (4.0 / NUM_STATES) * sPerBar) {
			bar = stamina[3];
		} else if (remainingStamina > (3.0 / NUM_STATES) * sPerBar) {
			bar = stamina[4];
		} else if (remainingStamina > (2.0 / NUM_STATES) * sPerBar) {
			bar = stamina[5];
		} else if (remainingStamina > (1.0 / NUM_STATES) * sPerBar) {
			bar = stamina[6];
		} else {
			bar = stamina[7];
		}

		// used for calculating which heart to draw
		double remainingHealth = player.getCurrentHealth();

		// health per heart
		double hPerHeart = player.getMaxHealth();

		// render fractions
		if (remainingHealth > (7.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[0];
		} else if (remainingHealth > (6.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[1];
		} else if (remainingHealth > (5.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[2];
		} else if (remainingHealth > (4.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[3];
		} else if (remainingHealth > (3.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[4];
		} else if (remainingHealth > (2.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[5];
		} else if (remainingHealth > (1.0 / NUM_STATES) * hPerHeart) {
			heart = hearts[6];
		} else {
			heart = hearts[7];
		}
		
	}

}

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entities.Player;

/*
 * Manages the display of the current equipped item status and health bars stats
 */
public class PlayerHUD {

	// the player to use
	private Player player;

	// the different gun types
	private BufferedImage assaultRifle, crossbow, laserRevolver, revolver, shotgun, bazooka, heart_full, heart_half, heart_empty, orb_full, orb_half, orb_empty;

	private final static int XOFFSET = 20, YOFFSET = 650, MODIFIER = 4, AMMO_OFFSET = 724, BAR_OFFSET = 750,
			BAR_VSPACE = 30, NUM_HEARTS = 6;

	/**
	 * Loads all the images of the weapons
	 */
	public PlayerHUD(Player player) {
		this.player = player;
		try {
			assaultRifle = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/AssaultRifle_Icon.png"));
			crossbow = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/Crossbow_Icon.png"));
			laserRevolver = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/LaserRevolver_Icon.png"));
			revolver = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/Revolver_Icon.png"));
			shotgun = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/Shotgun_Icon.png"));
			bazooka = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/Bazooka_Icon.png"));
			heart_full = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/heart_full.png"));
			heart_half = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/heart_half.png"));
			heart_empty = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/heart_empty.png"));
			orb_full = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/orb_full.png"));
			orb_half = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/orb_half.png"));
			orb_empty = ImageIO.read(PlayerHUD.class.getResource("/GUI/GUI_Hud/orb_empty.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws the sprites and stat bars
	 */
	public void draw(Graphics g) {

		// draws the gun and ammo
		BufferedImage gun = getGunType();
		if (gun != null) {
			g.drawImage(gun, XOFFSET, YOFFSET, gun.getWidth() * MODIFIER, gun.getHeight() * MODIFIER, null);
			g.drawString((int) player.getInventory().getGun().getCurrentAmmo() + " / " + player.getInventory().getGun().getClipSize(),
					XOFFSET, AMMO_OFFSET);
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
			
			g.drawImage(image, BAR_OFFSET + i * image.getWidth(), YOFFSET, image.getWidth(), image.getHeight(), null);
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
			
			g.drawImage(image, BAR_OFFSET + i * image.getWidth(), YOFFSET + BAR_VSPACE, image.getWidth(), image.getHeight(), null);
		}
		
	}

	/**
	 * Returns the equipped gun type of the player
	 */
	private BufferedImage getGunType() {

		// Checks if there is a gun to display
		if (player == null || player.getInventory().getGun() == null) {
			return null;
		}
		switch (player.getInventory().getGun().getName()) {
		case "Revolver":
			return revolver;
		case "Laser Revolver":
			return laserRevolver;
		case "Shotgun":
			return shotgun;
		case "Assault Rifle":
			return assaultRifle;
		case "Crossbow":
			return crossbow;
		case "Bazooka":
			return bazooka;
		default:
			return null;
		}
	}

}

package game;

import game.entities.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * Manages the display of the current equipped item status and health bars stats
 */
public class PlayerHUD {

	// the player to use
	private Player player;
	
	// the different gun types
	private BufferedImage assaultRifle, crossbow, laserRevolver, revolver, shotgun, bazooka;

	private final static int XOFFSET = 20, YOFFSET = 650, MODIFIER = 4, AMMO_OFFSET = 724, BAR_OFFSET = 750,
			BAR_VSPACE = 30, BAR_WIDTH_MODIFIER = 150, BAR_HEIGHT = 20;

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
			g.drawString((int) player.getInventory().getGun().ammo + " / " + player.getInventory().getGun().clipSize, XOFFSET, AMMO_OFFSET);
		}

		// draws the health bar
		g.setColor(Color.red);
		g.fillRect(BAR_OFFSET, YOFFSET,
				(int) (BAR_WIDTH_MODIFIER * ((double) player.getCurrentHealth() / player.getMaxHealth())), BAR_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString(player.getCurrentHealth() + " / " + player.getMaxHealth(), BAR_OFFSET, YOFFSET);

		// draws the shield bar
		g.setColor(Color.blue);
		g.fillRect(BAR_OFFSET, YOFFSET + BAR_VSPACE, (int) (BAR_WIDTH_MODIFIER * (player.getCurrentShield() / player.getMaxShield())),
				BAR_HEIGHT);

		// draws the stamina bar
		g.setColor(Color.green);
		g.fillRect(BAR_OFFSET, YOFFSET + 2 * BAR_VSPACE,
				(int) (BAR_WIDTH_MODIFIER * (player.getCurrentStamina() / player.getMaxStamina())), BAR_HEIGHT);
	}

	/**
	 * Returns the equipped gun type of the player
	 */
	private BufferedImage getGunType() {

		// Checks if there is a gun to display
		if (player == null || player.getInventory().getGun() == null) {
			return null;
		}
		switch (player.getInventory().getGun().gunType) {
		case 0:
			return revolver;
		case 1:
			return laserRevolver;
		case 2:
			return shotgun;
		case 3:
			return assaultRifle;
		case 4:
			return crossbow;
		case 5:
			return bazooka;
		default:
			return null;
		}
	}

}

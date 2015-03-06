package ca.javajesus.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ca.javajesus.game.entities.Player;

public class PlayerHUD {

	private Player player;
	BufferedImage assaultRifle;
	BufferedImage crossbow;
	BufferedImage laserRevolver;
	BufferedImage revolver;
	BufferedImage shotgun;

	public PlayerHUD(Player player) {
		this.player = player;
		try {
			assaultRifle = ImageIO.read(PlayerHUD.class
					.getResource("/GUI/GUI_Hud/AssaultRifle_Icon.png"));
			crossbow = ImageIO.read(PlayerHUD.class
					.getResource("/GUI/GUI_Hud/Crossbow_Icon.png"));
			laserRevolver = ImageIO.read(PlayerHUD.class
					.getResource("/GUI/GUI_Hud/LaserRevolver_Icon.png"));
			revolver = ImageIO.read(PlayerHUD.class
					.getResource("/GUI/GUI_Hud/Revolver_Icon.png"));
			shotgun = ImageIO.read(PlayerHUD.class
					.getResource("/GUI/GUI_Hud/Shotgun_Icon.png"));
		} catch (Exception e) {

		}
	}

	public void draw(Graphics g) {
		BufferedImage gun = getGunType();
		g.drawImage(gun, 20, 650, gun.getWidth() * 4, gun.getHeight() * 4, null);
		g.drawString((int) player.gun.ammo + " / " + player.gun.clipSize, 20, 724);

		g.setColor(Color.red);
		g.fillRect(750, 650,
				(int) (150 * (player.health / player.startHealth)), 20);

		g.setColor(Color.blue);
		g.fillRect(750, 680, 150, 20);

		g.setColor(Color.green);
		g.fillRect(750, 710,
				(int) (150 * (player.stamina / player.startStamina)), 20);
	}

	private BufferedImage getGunType() {
		switch (player.gun.gunType) {
		case 0:
			return revolver;
		case 1:
			return laserRevolver;
		case 2:
			return assaultRifle;
		case 3:
			return shotgun;
		default:
			return crossbow;
		}
	}

}
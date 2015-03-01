package ca.javajesus.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ca.javajesus.game.entities.Player;

public class PlayerHUD {

	private Player player;
	BufferedImage gun1;
	BufferedImage gun2;
	BufferedImage gun3;
	BufferedImage gun4;
	BufferedImage gun5;
	BufferedImage gun6;

	public PlayerHUD(Player player) {
		this.player = player;
		try {
			gun1 = ImageIO.read(PlayerHUD.class
					.getResource("/Tiles/Inventory&Weapon_Sprites/uzi.png"));
		} catch (Exception e) {

		}
	}

	public void draw(Graphics g) {
		BufferedImage gun = getGunType();
		g.drawImage(gun, 20, 650, gun.getWidth() * 4, gun.getHeight() * 4, null);
		g.drawString(" 3 / 6", 20, 724);
		
		g.setColor(Color.red);
		g.fillRect(750, 650, (int) (150 * (player.health / player.startHealth)), 20);
		
		g.setColor(Color.blue);
		g.fillRect(750, 680, 150, 20);
		
		g.setColor(Color.green);
		g.fillRect(750, 710, (int) (150 * (player.stamina / player.startStamina)), 20);
	}

	private BufferedImage getGunType() {
		switch (player.gunType) {
		default:
			return gun1;
		}
	}

}

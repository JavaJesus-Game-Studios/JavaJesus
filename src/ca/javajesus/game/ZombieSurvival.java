package ca.javajesus.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import ca.javajesus.game.gui.Launcher;
import ca.javajesus.level.Level;
import ca.javajesus.level.zombie.ZombieMap1;

public class ZombieSurvival extends Game {

	private static final long serialVersionUID = 1L;

	public ZombieSurvival() {
		super();
	}
	
	protected Level getLevel() {
		if (player == null) {
			return new ZombieMap1();
		}
		return player.getLevel();

	}

	protected void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = (int) player.x - (screen.width / 2);
		int yOffset = (int) player.y - (screen.height / 2);
		if (player.isDriving) {
			xOffset = (int) player.vehicle.x - (screen.width / 2);
			yOffset = (int) player.vehicle.y - (screen.height / 2);
		}

		getLevel().renderTile(screen, xOffset, yOffset);
		getLevel().renderEntities(screen);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colorCode = screen.pixels[x + y * screen.width];
				if (colorCode < 255)
					pixels[x + y * WIDTH] = colors[colorCode];
			}

		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString("Player: " + (int) player.x + ", " + (int) player.y, 5, 20);
		g.drawString("Score: " + player.score, 700, 20);
		if (player.hasDied) {
			g.setFont(new Font("Verdana", 0, 50));
			g.setColor(Color.BLACK);
			g.drawString("RIP", WIDTH * SCALE / 2 - 50, HEIGHT * SCALE / 2);
			frame.dispose();
			new Launcher(0).startMenu();
			running = false;
			return;
		}
		g.dispose();
		bs.show();
	}

}
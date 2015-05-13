package ca.javajesus.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import ca.javajesus.game.gui.Launcher;
import ca.javajesus.level.RandomLevel;

public class ZombieEndurance extends Game {
	
	private static final long serialVersionUID = -7481471327948380362L;

	public ZombieEndurance(Launcher launcher) {
		super(launcher, 1);
	}
	
	protected void render() {
		super.render();
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setFont(new Font("Verdana", 0, 20));
		g.setColor(Color.YELLOW);
		g.drawString("Score: " + player.score, 700, 20);
		ChatHandler.drawMessages(g);
		g.dispose();
		bs.show();
	}

}

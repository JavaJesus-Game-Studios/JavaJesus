package ca.javajesus.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ca.javajesus.game.gui.MapGUI;

public class ChatHandler {

	public static ArrayList<String> chatlog = new ArrayList<String>();

	protected static ArrayList<String> chatwindow = new ArrayList<String>();

	private static ArrayList<Color> colors = new ArrayList<Color>();

	private static int ticks = 0;
	private static boolean isVisible = false;
	private static boolean tickTimer = false;

	public static BufferedImage image;

	public ChatHandler() {

		try {
			image = ImageIO.read(ChatHandler.class
					.getResource("/GUI/GUI_Hud/GUI_Dialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void sendMessage(String string, Color color) {
		tickTimer = true;
		ticks = 0;
		isVisible = true;
		chatlog.add(string);
		String check = string;
		while (!(check.length() <= 45)) {
			updateWindow(check.substring(0, stringSplitter(check)), color);
			check = check.substring(stringSplitter(check));
		}
		updateWindow(check, color);
	}

	public static void drawMessages(Graphics g) {
		if (isVisible) {
			g.drawImage(image, 0, 550, null);
			int yOffset = 670;
			for (int i = 0; i < chatwindow.size(); i++) {
				if (chatwindow.get(i) != null) {
					g.setColor(colors.get(i));
					g.drawString(chatwindow.get(i), 10, yOffset);
					yOffset -= 20;
				}
			}
		}
		if (tickTimer) {
			ticks++;
		}
		if (ticks > 500) {
			isVisible = false;
			ticks = 0;
			tickTimer = false;
		}
	}

	private static void updateWindow(String string, Color color) {
		if (chatwindow.size() < 6) {
			chatwindow.add(0, string);
			colors.add(0, color);
		} else {
			chatwindow.remove(5);
			colors.remove(5);
			chatwindow.add(0, string);
			colors.add(0, color);
		}

	}

	private static int stringSplitter(String string) {
		for (int i =46; i > 0; i--) {
			if (string.charAt(i) == ' ') {
				return i;
			}
		}
		return 45;
	}
}

package ca.javajesus.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import ca.javajesus.game.gfx.Screen;

public class ChatHandler {

	public static ArrayList<String> chatlog = new ArrayList<String>();

	protected static ArrayList<String> chatwindow = new ArrayList<String>();

	private static ArrayList<Color> colors = new ArrayList<Color>();

	private static int ticks = 0;
	private static boolean isVisible = true;
	private static boolean tickTimer = false;

	public ChatHandler() {

	}

	public static void sendMessage(String string, Color color) {
		tickTimer = true;
		ticks = 0;
		isVisible = true;
		chatlog.add(string);
		updateWindow(string, color);
	}

	public static void drawMessages(Graphics g) {
		if (isVisible) {
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

}

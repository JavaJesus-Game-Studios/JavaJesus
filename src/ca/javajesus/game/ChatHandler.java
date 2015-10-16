package ca.javajesus.game;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ChatHandler {

	private static final int LENGTH = 90;

	// Time in seconds that the message appears
	private static final int TIME_DISPLAYED = 4;

	public static ArrayList<String> chatlog = new ArrayList<String>();

	protected static ArrayList<String> chatwindow = new ArrayList<String>();

	private static ArrayList<Color> colors = new ArrayList<Color>();

	private static long lastTime;
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
		lastTime = System.currentTimeMillis();
		isVisible = true;
		chatlog.add(string);
		String check = string;
		while (!(check.length() <= LENGTH)) {
			updateWindow(check.substring(0, stringSplitter(check)), color);
			check = check.substring(stringSplitter(check));
		}
		updateWindow(check, color);
	}

	public static void drawMessages(Graphics g) {
		if (isVisible) {
			g.drawImage(image, 0, Display.FRAME_HEIGHT - image.getHeight() + 22,
					Display.FRAME_WIDTH + 20, image.getHeight(), null);
			FontMetrics font = g.getFontMetrics();
			int yOffset = 730;
			for (int i = 0; i < chatwindow.size(); i++) {
				if (chatwindow.get(i).contains(":")) {
					int split = chatwindow.get(i).indexOf(":") + 1;
					String name = chatwindow.get(i).substring(0, split);
					String message = chatwindow.get(i).substring(split);
					g.setColor(Color.WHITE);
					g.drawString(name, 10, yOffset);
					g.setColor(colors.get(i));
					g.drawString(message, 10 + font.stringWidth(name), yOffset);
					yOffset -= 20;
				} else if (chatwindow.get(i) != null) {
					g.setColor(colors.get(i));
					g.drawString(chatwindow.get(i), 10, yOffset);
					yOffset -= 20;
				}
			}
		}
		if (tickTimer
				&& (System.currentTimeMillis() > lastTime
						+ (TIME_DISPLAYED * 1000))) {
			isVisible = false;
			tickTimer = false;
			lastTime = 0;
		}
	}

	private static void updateWindow(String string, Color color) {
		if (chatwindow.size() < 13) {
			chatwindow.add(0, string);
			colors.add(0, color);
		} else {
			chatwindow.remove(12);
			colors.remove(12);
			chatwindow.add(0, string);
			colors.add(0, color);
		}

	}

	public static void toggle() {
		tickTimer = false;
		lastTime = 0;
		isVisible = !isVisible;
	}

	private static int stringSplitter(String string) {
		for (int i = LENGTH; i > 0; i--) {
			if (string.charAt(i) == ' ') {
				return i;
			}
		}
		return LENGTH;
	}
}

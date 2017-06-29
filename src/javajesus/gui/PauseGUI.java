package javajesus.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javajesus.JavaJesus;
import javajesus.Launcher;
import javajesus.SoundHandler;
import javajesus.save.GameData;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PauseGUI extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	// / Used for serialization
	private static final long serialVersionUID = 1L;

	// offset of the sword to render
	private int swordOffset;

	// starting position of sword
	private int swordStart = 110;

	// determines if one of the buttons has been clicked
	private boolean isClicked;

	// The selected button that was clicked
	private LauncherButton selectedButton;

	// Id of the page
	private int pageId;

	// tracks the mouse movement
	private int mouseX, mouseY, mouseButton;

	/**
	 * IDs of the page screens
	 */
	private static final int MAINMENU = 0, OPTIONSMENU = 1, AUDIOMENU = 2;

	/**
	 * Ids of the buttons
	 */
	private static final int RESUME = 0, SAVE = 1, LOAD = 2, OPTIONS = 3,
			BACK = 4, QUIT = 5, AUDIO = 6, VIDEO = 7, CONTROLS = 8, MUTE = 9;

	// Buttons on the launcher
	private LauncherButton resume, save, load, options, back, quit, audio,
			video, controls, mute;

	// buffered images that are displayed on the screen
	private BufferedImage background, sword_selector;

	/**
	 * Creates the pause menu
	 */
	public PauseGUI() {
		
		// set the size
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT));
		
		// add input listener
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		// load instance variables and buttons
		BufferedImage resume_on, resume_off, save_on, save_off, options_on, options_off, load_on, load_off, audio_on, audio_off, video_on, video_off, controls_on, controls_off, mute_on, mute_off, back_on, back_off, quit_on, quit_off;
		
		try {
			background = ImageIO.read(Launcher.class
					.getResource("/GUI/GUI_Menus/Pause_Menu.png"));

			sword_selector = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/sword_selector.png"));

			resume_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/resume_on.png"));

			resume_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/resume_off.png"));

			save_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/save_on.png"));

			save_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/save_off.png"));

			load_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/load_on.png"));

			load_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/load_off.png"));

			options_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/options_on.png"));

			options_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/options_off.png"));

			audio_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/audio_on.png"));

			audio_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/audio_off.png"));

			video_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/video_on.png"));

			video_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/video_off.png"));

			controls_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/controls_on.png"));

			controls_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/controls_off.png"));

			mute_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/mute_on.png"));

			mute_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/mute_off.png"));

			back_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/back_on.png"));

			back_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/back_off.png"));

			quit_on = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/quit_on.png"));

			quit_off = ImageIO.read(Launcher.class
					.getResource("/GUI/Buttons/quit_off.png"));

			resume = new LauncherButton(450, RESUME, resume_off, resume_on);
			save = new LauncherButton(500, SAVE, save_off, save_on);
			load = new LauncherButton(550, LOAD, load_off, load_on);
			options = new LauncherButton(600, OPTIONS, options_off, options_on);

			audio = new LauncherButton(450, AUDIO, audio_off, audio_on);
			video = new LauncherButton(500, VIDEO, video_off, video_on);
			controls = new LauncherButton(550, CONTROLS, controls_off,
					controls_on);
			mute = new LauncherButton(450, MUTE, mute_off, mute_on);

			back = new LauncherButton(650, BACK, back_off, back_on);
			quit = new LauncherButton(650, QUIT, quit_off, quit_on);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Paints the images on the pause menu
	 */
	protected void paintComponent(Graphics g) {
		
		// draw the background
		g.drawImage(background, 0, 0, JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT, null);

		// draw different buttons
		switch (pageId) {

		case MAINMENU: {

			resume.draw(g);
			save.draw(g);
			load.draw(g);
			options.draw(g);
			quit.draw(g);
			break;

		}
		case OPTIONSMENU: {
			audio.draw(g);
			video.draw(g);
			controls.draw(g);
			break;
		}

		case AUDIOMENU: {
			mute.draw(g);
			break;
		}

		}

		if (pageId != MAINMENU) {
			back.draw(g);
		}

		if (isClicked) {
			swordOffset += 10;
			if (swordOffset > 100) {
				swordOffset = 0;
				isClicked = false;
				doAction();
			}
		}
	}

	/**
	 * Actions based on button clicked
	 */
	private void doAction() {

		int id = selectedButton.getActionId();
		selectedButton = null;

		switch (id) {
		case RESUME: {
			resume();
			return;
		}
		case SAVE: {
			GameData.savePlayerLevelData();
			GameData.saveLevels();
			return;
		}
		case LOAD: {
			return;
		}
		case OPTIONS: {
			this.pageId = OPTIONSMENU;
			return;
		}
		case AUDIO: {
			this.pageId = AUDIOMENU;
			return;
		}
		case VIDEO: {
			System.out.println("Video coming soon");
			return;
		}
		case CONTROLS: {
			System.out.println("Controls coming soon");
			return;
		}
		case MUTE: {
			SoundHandler.toggleMute();
			return;
		}
		case BACK: {
			switch (pageId) {
			case OPTIONSMENU:
				this.pageId = MAINMENU;
				break;
			case AUDIOMENU:
				this.pageId = OPTIONSMENU;
				break;
			}
			return;
		}
		case QUIT: {
			JavaJesus.playerDied();
			return;
		}
		default: {
			System.err.println("ID NOT FOUND ON PAUSE SCREEN");
			return;
		}
		}

	}

	/**
	 * Launcher Button that is used on the launcher screen
	 */
	private class LauncherButton {

		private int x, y;
		private int actionId;
		private BufferedImage imageOff, imageOn;

		public LauncherButton(int yPos, int actionId, BufferedImage imageOff,
				BufferedImage imageOn) {
			x = JavaJesus.WINDOW_WIDTH / 2 - imageOff.getWidth() / 2;
			y = yPos;
			this.imageOff = imageOff;
			this.imageOn = imageOn;
			this.actionId = actionId;
		}

		public int getActionId() {
			return actionId;
		}

		
		public void draw(Graphics g) {
			if (mouseX > x
					&& mouseX < x + imageOff.getWidth()
					&& mouseY > y
					&& mouseY < y + imageOff.getHeight()) {
				g.drawImage(imageOn, x, y, null);

				if (!isClicked || selectedButton == this) {
					g.drawImage(sword_selector, x - swordStart + swordOffset,
							y, null);
				}
				if (mouseButton == 1) {
					mouseButton = 0;
					SoundHandler.play(SoundHandler.sheathe);
					isClicked = true;
					selectedButton = this;
				}

			} else {
				g.drawImage(imageOff, x, y, null);
			}
		}
	}
	
	/**
	 * Returns to game play
	 */
	private void resume() {
		JavaJesus.displayGame();
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseButton = 0;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * Closes the panel when escape is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			resume();
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}

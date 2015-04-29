package ca.javajesus.game.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.saves.GameData;

public class PausePanelGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private int swordOffset;
	private boolean nextScreen = false;
	private int buttonId = 0;
	private int id;
	public int width = 800;
	public int height = 750;
	protected int button_width = 80;
	protected int button_height = 40;
	InputHandler input;
	public boolean resumeIsPressed = false;

	public SoundHandler sound = SoundHandler.sound;
	Thread thread;

	public PausePanelGUI() {
		this.id = 0;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.input = new InputHandler(this);
		setPreferredSize(new Dimension(width, height));
		swordOffset = 0;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(Launcher.class
					.getResource("/GUI/GUI_Menus/Main_Menu.png")), 0, 0, 800,
					800, null);

			switch (id) {

			case 0: {
				/** Resume Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/resume_on.png")), 365,
							450, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 0;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/resume_off.png")), 365,
							450, 100, 30, null);
				}

				/** Save Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 510
						&& InputHandler.MouseY < 510 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/save_on.png")), 365,
							510, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 510, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 1;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/save_off.png")), 365,
							510, 100, 30, null);
				}

				/** Load Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 570
						&& InputHandler.MouseY < 570 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/load_on.png")), 365,
							570, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 570, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 2;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/load_off.png")), 365,
							570, 100, 30, null);
				}

				/** Options */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 630
						&& InputHandler.MouseY < 630 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/options_on.png")), 365,
							630, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 630, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 3;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/options_off.png")), 365,
							630, 100, 30, null);
				}
				break;

			}
			case 1: {

				// Save Screen

			}
			case 2: {
				// Load Screen
			}
			case 3: {
				/** Audio Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/audio_on.png")), 365,
							450, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 4;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/audio_off.png")), 365,
							450, 100, 30, null);
				}

				/** Video Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 510
						&& InputHandler.MouseY < 510 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/video_on.png")), 365,
							510, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 510, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 5;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/video_off.png")), 365,
							510, 100, 30, null);
				}

				/** Controls Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 570
						&& InputHandler.MouseY < 570 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/controls_on.png")), 365,
							570, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 570, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 6;
					}
				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/controls_off.png")),
							365, 570, 100, 30, null);
				}
				break;
			}

			case 4: {
				/** Mute Button */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 450
						&& InputHandler.MouseY < 450 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/mute_on.png")), 365,
							450, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 450, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 7;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/mute_off.png")), 365,
							450, 100, 30, null);
				}
				break;
			}

			}

			if (id != 0) {
				/** Back */
				if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
						&& InputHandler.MouseY > 630
						&& InputHandler.MouseY < 630 + 30) {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/back_on.png")), 365,
							630, 100, 30, null);
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/sword_selector.png")),
							365 - 110 + swordOffset, 630, 100, 30, null);
					if (InputHandler.MouseButton == 1) {
						InputHandler.MouseButton = 0;
						sound.play(sound.sheathe);
						nextScreen = true;
						buttonId = 8;
					}

				} else {
					g.drawImage(ImageIO.read(Launcher.class
							.getResource("/GUI/Buttons/back_off.png")), 365,
							630, 100, 30, null);
				}
			}

			/** Main Menu */
			if (InputHandler.MouseX > 365 && InputHandler.MouseX < 365 + 80
					&& InputHandler.MouseY > 690
					&& InputHandler.MouseY < 690 + 30) {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/GUI/Buttons/quit_on.png")), 365, 690,
						100, 30, null);
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/GUI/Buttons/sword_selector.png")),
						365 - 110 + swordOffset, 690, 100, 30, null);
				if (InputHandler.MouseButton == 1) {
					InputHandler.MouseButton = 0;
					sound.play(sound.sheathe);
					nextScreen = true;
					buttonId = 9;
				}

			} else {
				g.drawImage(ImageIO.read(Launcher.class
						.getResource("/GUI/Buttons/quit_off.png")), 365, 690,
						100, 30, null);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (nextScreen) {
			swordOffset += 10;
			if (swordOffset > 100) {
				swordOffset = 0;
				nextScreen = false;
				transferScreen(buttonId);
			}
		}
	}

	private void transferScreen(int id) {

		switch (id) {
		case 0: {
			// resume
			resumeIsPressed = true;
			return;
		}
		case 1: {
			// save
			GameData.saveGame();
			return;
		}
		case 2: {
			// load
			//Launcher.load = true;
			//Game.init();
			return;
		}
		case 3: {
			// options
			this.id = 3;
			return;
		}
		case 4: {
			// Audio
			this.id = 4;
			return;
		}
		case 5: {
			// Video
			System.out.println("Video coming soon");
			return;
		}
		case 6: {
			// Controls
			System.out.println("Controls coming soon");
			return;
		}
		case 7: {
			// Mute
			if (!sound.muted) {
				sound.muted = true;
				sound.background1.stop();
			} else {
				sound.muted = false;
				sound.playLoop(sound.background1);
			}
			return;
		}
		case 8: {
			// Back
			this.id = 0;
			return;
		}
		case 9: {
			// Main Menu
			Game.returnToMenu = true;
			return;
		}
		default: {
			return;
		}
		}

	}

}

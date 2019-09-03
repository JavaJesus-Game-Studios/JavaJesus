package javajesus.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import editors.quest.QuestDataBuilder;
import javajesus.JavaJesus;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;
import javajesus.quest.Quest;
import javajesus.utility.JJStrings;

/*
 * The Dialogue Screen
 */
public class DialogueGUI extends JPanel {

	// serialization
	private static final long serialVersionUID = 1L;

	// text fields that get updated with the quest
	private final JJTextArea dialogue;

	// background image of the head
	private final JJPanel head;

	// selector dialogue
	private final JJSelector option1, option2, option3;

	// which option is selected
	private int selected = 0;

	// the npc questgiver
	private NPC giver;
	private Player player;

	/**
	 * Constructs the dialogue screen based on the NPC spoken to
	 * 
	 * @param character - the character talking with the player
	 */
	public DialogueGUI() {

		// set the window to default size
		setPreferredSize(new Dimension(JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT));

		// set the border
		setBorder(new JJBorder());

		// set the page layout vertically
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// set filler at the very top
		add(Box.createVerticalGlue());

		// get the npc head path
		add(head = new JJPanel());

		// set up the scroll pane
		JScrollPane pane = new JScrollPane(dialogue = new JJTextArea(JJStrings.ACTOR_DIALOGUE_BOX));
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.getVerticalScrollBar().setUI(new VerticalSliderUI(JJStrings.DIALOGUE_TRACK));
		pane.setBorder(null);

		// add the text of the current state
		add(pane);

		// set up the scroll pane for the selector panel
		JJPanel selector = new JJPanel(JJStrings.DIALOGUE_BOX);
		selector.setLayout(new BoxLayout(selector, BoxLayout.Y_AXIS));
		selector.setBorder(new EmptyBorder(20, 20, 10, 5));
		selector.add(option1 = new JJSelector(""));
		selector.add(option2 = new JJSelector(""));
		selector.add(option3 = new JJSelector(""));
		option1.selected = true;
		option1.update();

		// add the selector pane
		add(selector);
	}

	/**
	 * Updates which Label is selected
	 */
	public void up() {

		if (selected == 0) {
			selected = 2;
		} else {
			selected--;
		}

		select(selected);

	}

	/**
	 * Updates which Label is selected
	 */
	public void down() {
		if (selected == 2) {
			selected = 0;
		} else {
			selected++;
		}

		select(selected);
	}

	/**
	 * Perform an action on the selected label
	 */
	public void doAction() {

		// get the giver's quest
		Quest quest = giver.getCurrentQuest();

		if (selected == 0) {
			quest.selectOption(QuestDataBuilder.KEY_TRIGGERS1);
		} else if (selected == 1) {
			quest.selectOption(QuestDataBuilder.KEY_TRIGGERS2);
		} else {
			quest.selectOption(QuestDataBuilder.KEY_TRIGGERS3);
		}

		// reset selected
		select(selected = 0);

		// update the screen
		update(giver, player);

	}

	/**
	 * @param num - the number to select
	 */
	private void select(int num) {

		// set all to false to begin with
		option1.selected = false;
		option2.selected = false;
		option3.selected = false;

		// set the right num true
		if (num == 0) {
			if (!option1.isEmpty) {
				option1.selected = true;
			} else {
				down();
				return;
			}

		} else if (num == 1) {
			if (!option2.isEmpty) {
				option2.selected = true;
			} else {
				down();
				return;
			}
		} else {
			if (!option3.isEmpty) {
				option3.selected = true;
			} else {
				down();
				return;
			}
		}

		// now update them
		option1.update();
		option2.update();
		option3.update();

		// repaint
		repaint();

	}

	/**
	 * Updates the components based on the character's information
	 * 
	 * @param character - character the player is speaking to
	 */
	public void update(NPC character, Player player) {

		// return if either the character or quest is null
		if (character == null || character.getCurrentQuest() == null) {
			return;
		}

		// set the npc
		this.giver = character;

		// set head image
		head.setPath(character.getHeadPath());

		// get the quest
		Quest quest = character.getCurrentQuest();
		quest.setPlayer(this.player = player);

		// update the text box
		dialogue.setText(quest.getDialogue());

		// update the selector box
		option1.setText(quest.getOption(1));
		option2.setText(quest.getOption(2));
		option3.setText(quest.getOption(3));

		// revalidate any new components
		revalidate();

		// now repaint the screen
		repaint();
	}

	private class JJSelector extends JTextArea {

		// serialization
		private static final long serialVersionUID = 1L;

		// whether or not it is selected
		private boolean selected;

		// whether or not it is an empty option
		private boolean isEmpty;

		// the font
		private Font font = new Font(JavaJesus.FONT_NAME, 0, 15);

		private JJSelector(String text) {
			super(text);

			// invisible background
			setBackground(new Color(0, 0, 0, 0));

			// options
			setEditable(false);
			setLineWrap(true);
			setWrapStyleWord(true);
			setFocusable(false);
			
			// set up the text
			setFont(font);
			setForeground(Color.WHITE);
		}
		
		@Override
		public void setText(String text) {
			super.setText(text);
			isEmpty = text.isEmpty();
		}

		/**
		 * Updates the color
		 */
		private void update() {

			// pick the right color
			if (selected) {
				setForeground(Color.YELLOW);
			} else {
				setForeground(Color.WHITE);
			}

			// repaint
			repaint();
		}
	}

	/*
	 * For a JTextArea with a custom background
	 */
	private class JJTextArea extends JTextArea {

		// serialization
		private static final long serialVersionUID = 1L;

		// background head
		private BufferedImage background;

		// the font
		private Font font = new Font(JavaJesus.FONT_NAME, 0, 20);

		/**
		 * @param path - path to image to load
		 */
		private JJTextArea(String path) {

			// load the background
			try {
				background = ImageIO.read(DialogueGUI.class.getResource(path));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// set dimensions
			setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));

			// set the default background transparent
			setBackground(new Color(0, 0, 0, 0));

			// set up the text
			setFont(font);
			setForeground(Color.WHITE);

			// set other attributes
			setEditable(false);
			setLineWrap(true);
			setWrapStyleWord(true);
			setFocusable(false);

			// add a border for the text to fit in
			setBorder(new EmptyBorder(20, 10, 10, 5));
		}

		/**
		 * Override the background image
		 */
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

			// now draw text
			super.paintComponent(g);
		}

	}

	/*
	 * For a JPanel with a custom background
	 */
	private class JJPanel extends JPanel {

		// serialization
		private static final long serialVersionUID = 1L;

		// background head
		private BufferedImage background;

		/**
		 * Default ctor()
		 */
		public JJPanel() {

		}

		/**
		 * @param path - background image
		 */
		public JJPanel(String path) {
			try {

				background = ImageIO.read(DialogueGUI.class.getResource(path));

				// set the size
				setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * @param path - path of the image to display
		 */
		public void setPath(String path) {
			try {

				background = ImageIO.read(DialogueGUI.class.getResource(path));

				// set the size
				setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));

			} catch (IOException e) {
				e.printStackTrace();
			}
			revalidate();
		}

		/**
		 * Override the background image
		 */
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(background, (getWidth() - background.getWidth()) / 2, 0, background.getWidth(), getHeight(),
			        null);
		}

	}

}

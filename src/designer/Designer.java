package designer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javajesus.graphics.Screen;
import javajesus.level.tile.Tile;

/*
 * Driver class of the level designer
 */
public class Designer extends JPanel implements MouseListener, ActionListener{
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// gets the base directory
    private static final String DIR = "res/WORLD_DATA/";
	
	// height of the window
	private static final int WIDTH = 1000, HEIGHT = 800;
	
	// number of tiles on level
	private static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;
	
	// buttons at the top
	private final JButton save, load;
	private final JTextField name;
	private TileGUI selected;
	
	// holds all the tile guis
	private JPanel content;
	
	// ids for tile guis
	private static final int SELECTOR = 0, DISPLAY = 1;
	
	// whether or not the mouse is pressed down
	private boolean pressed;
	
	/**
	 * First method called in the program
	 * @param args - run time arguments
	 */
	public static void main(String[] args) {
		
		// set up the frame
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Designer(WIDTH, HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setTitle("Level Designer for Java Jesus by Derek Jow");
		frame.setVisible(true);
		frame.toFront();
	}
	
	
	/**
	 * Creates the display of the designer
	 * @param width - width of the window
	 * @param height - height of the window
	 */
	public Designer(int width, int height) {
		
		// set up the panel
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));
		
		// top panel
		JPanel top = new JPanel(new FlowLayout());
		top.add(new JLabel("Name:"));
		top.add(name = new JTextField());
		name.setText("This is a new level.png");
		top.add(save = new JButton("Save"));
		save.addActionListener(this);
		top.add(load = new JButton("Load"));
		load.addActionListener(this);
		top.add(new JLabel("Selected: "));
		top.add(selected = new TileGUI(Tile.VOID, SELECTOR, 25, 25));
		
		// viewing panel
		JPanel viewing = new JPanel(new BorderLayout(0, 0));
		
		// tile selector on the left side
		JPanel leftContent = new JPanel();
		GridLayout l = new GridLayout(128, 2);
		leftContent.setLayout(l);
		leftContent.setMinimumSize(new Dimension(60, Integer.MAX_VALUE));
		
		// add all the tiles
		for (Tile t: Tile.tileList) {
			TileGUI tile = new TileGUI(t, SELECTOR, 30, 30);
			tile.addMouseListener(this);
			leftContent.add(tile);
		}
		
		// displays all the tiles in the center
		content = new JPanel();
		GridLayout layout = new GridLayout(LEVEL_WIDTH, LEVEL_HEIGHT);
		content.setLayout(layout);
		for (int i = 0; i < layout.getRows() * layout.getColumns(); i++) {
			TileGUI tile = new TileGUI(Tile.GRASS(), DISPLAY);
			tile.addMouseListener(this);
			content.add(tile);
		}
		
		// assemble the viewing panel
		JScrollPane pane = new JScrollPane(leftContent);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		viewing.add(pane, BorderLayout.WEST);
		viewing.add(new JScrollPane(content), BorderLayout.CENTER);
		
		// assemble the panel
		add(top, BorderLayout.NORTH);
		add(viewing, BorderLayout.CENTER);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Updates the logic of a tile if
	 * the mouse was hovered over it while 
	 * pressing
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
		//make sure the mouse is pressed
		if (pressed) {
			
			// now update the tile
			TileGUI tile = (TileGUI) e.getSource();
			
			// do the correct action
			if (tile.getId() == SELECTOR) {
				selected.setTile(tile.getTile());
			}
			
			if (tile.getId() == DISPLAY) {
				tile.setTile(selected.getTile());
			}
			
		}
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Updates with the logic of whatever tile was pressed
	 * @param e - the mouse event fired
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
		// get the source
		TileGUI tile = (TileGUI) e.getSource();
		
		// do the correct action
		if (tile.getId() == SELECTOR) {
			selected.setTile(tile.getTile());
		}
		
		if (tile.getId() == DISPLAY) {
			tile.setTile(selected.getTile());
		}
		
		// mouse is held down
		pressed = true;
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		// mouse is no longer down
		pressed = false;
		
	}


	/**
	 * Fired when buttons are pressed
	 * @param e - the event fired
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// save
		if (e.getSource() == save) {
			save();
			saveRenderedLevel();
			
			// load
		} else if (e.getSource() == load) {
			load();
		}
		
	}
	
	/**
	 * Saves the fully rendered image to the level directory
	 */
	private void save() {
		
		// create the buffered image
		BufferedImage level = new BufferedImage(LEVEL_WIDTH, LEVEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) level.getRaster().getDataBuffer()).getData();
		
		// loop through all the children
		for (int i = 0; i < content.getComponentCount(); i++) {
			
			// each component is a tile GUI
			Tile t = ((TileGUI) content.getComponent(i)).getTile();
			pixels[i] = t.getPixelColor();
		}
		
		// now the buffered image is filled
		try {
		    File output = new File(DIR + name.getText());
		    ImageIO.write(level, "png", output);
		    System.out.println("Saved");
		    System.out.println(output.getAbsolutePath());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	/**
	 * Saves the fully rendered image to the level directory
	 */
	private void saveRenderedLevel() {
		
		// base modifier of tiles
		int modifier = 8;
		
		// create the buffered image
		BufferedImage level = new BufferedImage(LEVEL_WIDTH * modifier, LEVEL_HEIGHT * modifier, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) level.getRaster().getDataBuffer()).getData();
		
		// create the screen for processing
		Screen screen = new Screen(LEVEL_WIDTH * modifier, LEVEL_HEIGHT * modifier);
		
		// loop through all the children
		for (int i = 0; i < content.getComponentCount(); i++) {
			
			int xPos = i % LEVEL_WIDTH;
			int yPos = i / LEVEL_WIDTH;
			
			// each component is a tile GUI
			Tile t = ((TileGUI) content.getComponent(i)).getTile();
			
			// render the tile
			t.render(screen, xPos * modifier, yPos * modifier);
		}
		
		// now extract the pixel data
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels()[i];
		}
		
		// now the buffered image is filled
		try {
		    File output = new File(DIR + "Rendered" + name.getText());
		    ImageIO.write(level, "png", output);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	/**
	 * Tries to load a png file
	 */
	private void load() {

		// check if the file exists
		try {
			
			// create the buffered image
			BufferedImage level = ImageIO.read(Designer.class.getResource("/WORLD_DATA/" + name.getText()));
			
			// stores level colors
			int[] pixels = new int[level.getWidth() * level.getHeight()];
			
			// fill the pixel array
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = level.getRGB(i % level.getWidth(), i / level.getWidth());
			}

			// loop through all the children
			for (int i = 0; i < content.getComponentCount(); i++) {

				// find the corresponding tile with the pixel color from the file
				for (Tile tile: Tile.tileList) {
					
					if (tile != null) {
						// found a match
						if (pixels[i] == tile.getPixelColor()) {
							((TileGUI) content.getComponent(i)).setTile(tile);
							break;
						}
					}
				}
				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

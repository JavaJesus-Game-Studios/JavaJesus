package designer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javajesus.entities.DestructibleTile;
import javajesus.entities.Entity;
import javajesus.entities.FireEntity;
import javajesus.entities.solid.furniture.Bed;
import javajesus.entities.solid.furniture.Stool;
import javajesus.graphics.Screen;
import javajesus.level.tile.Tile;

/*
 * Driver class of the level designer
 */
public class Designer extends JPanel implements MouseListener, ActionListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// gets the base directory
	private static final String BASE = "/WORLD_DATA/";

	// gets the top level directory
	private static final String DIR = "res" + BASE;

	// gets the directory for quick saving
	private static final String TEMP = "DESIGNER/Temp";

	// gets the extension for PNGS
	private static final String PNG = ".png";
	
	// gets the name addon for entitiy files
	private static final String ENTITY = "_entities";

	// height of the window
	private static final int WIDTH = 1000, HEIGHT = 800;

	// number of tiles on level
	private static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;

	// constants for cardlayout
	private static final String TILE_DISPLAY = "Tiles", ENTITY_DISPLAY = "Entities";

	// max number of tiles/entities in the list selector
	private static final int MAX_ENTITIES = 256;
	
	// max number of tiles/entities in the list selector
	private static final int MAX_ENTITIES_LEVEL = LEVEL_WIDTH * LEVEL_HEIGHT;

	// list of all entities that can be placed
	private static final EntityGUI[] entityList = new EntityGUI[MAX_ENTITIES];

	// size of each tile/entity selector
	private static final int PANEL_SIZE = 50;

	// buttons at the top
	private final JButton save, load, saveRendered, loadPNG, setAll, plusSize, minusSize, plusZoom, minusZoom, undo,
	        tiles, entities;
	private final JTextField name;
	private TileGUI selected;
	private EntityGUI selectedEntity;
	private JLabel sizeLabel;

	// the layout used to switch between tiles and entities
	private final CardLayout cl;
	private final JPanel leftMain;

	// zoom scale of the tiles
	public static int zoomScale = 8;

	// size
	private int size = 1;

	// holds all the tile guis
	private JPanel content;

	// ids for tile guis
	private static final int SELECTOR = 0, DISPLAY = 1;

	// whether or not the mouse is pressed down
	private boolean pressed;

	/**
	 * First method called in the program
	 * 
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
	 * 
	 * @param width - width of the window
	 * @param height - height of the window
	 */
	public Designer(int width, int height) {

		// init the entity list
		initEntities();

		// set up the panel
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));

		// top panel
		JPanel top = new JPanel(new FlowLayout());
		top.add(new JLabel("Name:"));
		top.add(name = new JTextField());
		name.setText("New");
		name.setColumns(10);
		top.add(save = new JButton("Save"));
		save.addActionListener(this);
		top.add(saveRendered = new JButton("Save Display"));
		saveRendered.addActionListener(this);
		top.add(load = new JButton("Load"));
		load.addActionListener(this);
		top.add(loadPNG = new JButton("Load PNG"));
		loadPNG.addActionListener(this);
		top.add(new JLabel("Selected: "));
		top.add(selected = new TileGUI(Tile.VOID, SELECTOR, 25, 25));

		// viewing panel
		JPanel viewing = new JPanel(new BorderLayout(0, 0));

		// tile selector on the left side
		JPanel leftContent = new JPanel();
		leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
		leftContent.setMinimumSize(new Dimension(60, Integer.MAX_VALUE));

		// set the tiles and entity buttons
		JPanel leftTop = new JPanel();
		leftTop.add(tiles = new JButton("T"));
		tiles.addActionListener(this);
		leftTop.add(entities = new JButton("E"));
		entities.addActionListener(this);
		leftContent.add(leftTop);

		// create the overview for tiles and entities
		leftMain = new JPanel(cl = new CardLayout());

		// create the tile list
		JPanel leftTiles = new JPanel(new GridLayout(128, 2));

		// add all the tiles
		for (Tile t : Tile.tileList) {
			TileGUI tile = new TileGUI(t, SELECTOR, PANEL_SIZE, PANEL_SIZE);
			tile.addMouseListener(this);
			leftTiles.add(tile);
		}

		// create the entity list
		JPanel leftEntities = new JPanel(new GridLayout(128, 2));
		for (int i = 0; i < entityList.length; i++) {
			EntityGUI entity = entityList[i];
			leftEntities.add(entity);
			entity.addMouseListener(this);
		}

		// compose the left side
		leftMain.add(leftTiles, TILE_DISPLAY);
		leftMain.add(leftEntities, ENTITY_DISPLAY);
		leftContent.add(leftMain);

		// displays all the tiles in the center
		content = new JPanel();
		GridLayout layout = new GridLayout(LEVEL_WIDTH, LEVEL_HEIGHT);
		content.setLayout(layout);
		for (int i = 0; i < layout.getRows() * layout.getColumns(); i++) {
			TileGUI tile = new TileGUI(Tile.GRASS(), DISPLAY, zoomScale, zoomScale);
			tile.addMouseListener(this);
			content.add(tile);
		}

		// assemble the tools on the right side
		JPanel rightContent = new JPanel();
		rightContent.setMinimumSize(new Dimension(20, Integer.MAX_VALUE));
		rightContent.setLayout(new BoxLayout(rightContent, BoxLayout.Y_AXIS));
		JLabel text = new JLabel("Tools");
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightContent.add(text);
		rightContent.add(setAll = new JButton("Fill"));
		setAll.addActionListener(this);
		setAll.setAlignmentX(Component.CENTER_ALIGNMENT);

		// brush size has a flow layout
		text = new JLabel("Brush Size");
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightContent.add(text);
		JPanel brush = new JPanel();
		brush.add(minusSize = new JButton("-"));
		minusSize.addActionListener(this);
		brush.add(sizeLabel = new JLabel(String.valueOf(size)));
		brush.add(plusSize = new JButton("+"));
		plusSize.addActionListener(this);
		brush.setMaximumSize(new Dimension(Integer.MAX_VALUE, brush.getPreferredSize().height));
		rightContent.add(brush);

		// zooming
		text = new JLabel("Zoom");
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightContent.add(text);
		JPanel zoom = new JPanel();
		zoom.add(minusZoom = new JButton("-"));
		minusZoom.addActionListener(this);
		zoom.add(plusZoom = new JButton("+"));
		plusZoom.addActionListener(this);
		zoom.setMaximumSize(new Dimension(Integer.MAX_VALUE, brush.getPreferredSize().height));
		rightContent.add(zoom);

		// undo
		rightContent.add(undo = new JButton("Undo"));
		undo.setAlignmentX(Component.CENTER_ALIGNMENT);
		undo.addActionListener(this);

		// assemble the viewing panel
		JScrollPane pane = new JScrollPane(leftContent);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		viewing.add(pane, BorderLayout.WEST);
		viewing.add(new JScrollPane(content), BorderLayout.CENTER);
		viewing.add(rightContent, BorderLayout.EAST);

		// assemble the panel
		add(top, BorderLayout.NORTH);
		add(viewing, BorderLayout.CENTER);

	}

	/**
	 * Not used
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	/**
	 * Updates the logic of a tile if the mouse was hovered over it while
	 * pressing
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

		// make sure the mouse is pressed
		if (pressed) {

			// try to paint if on the canvas
			if (e.getSource() instanceof TileGUI) {

				// now update the tile
				TileGUI tile = (TileGUI) e.getSource();

				// draw tile only if no entity is selected
				if (selectedEntity == null) {

					if (tile.getId() == DISPLAY) {

						// point of origin
						int xPos = tile.getX();
						int yPos = tile.getY();

						// set over an area
						for (int x = 0; x < size; x++) {
							for (int y = 0; y < size; y++) {

								// get next the component
								TileGUI next = (TileGUI) content.getComponentAt(xPos + (x * zoomScale),
								        yPos + (y * zoomScale));
								if (next != null) {

									// set the tile
									next.setTile(selected.getTile());
								}

							}
						}
					}

					// entity is selected
				} else {
					
					// the source tile contains the entity
					tile.addEntity(selectedEntity.getEntity());
					
					// draw the entity
					for (int x = 0; x < selectedEntity.getXTiles(); x++) {
						for (int y = 0; y < selectedEntity.getYTiles(); y++) {

							// next tile over
							TileGUI next = (TileGUI) content.getComponentAt(tile.getX() + (x * zoomScale),
							        tile.getY() + (y * zoomScale));

							// render if the tile exists
							if (next != null) {
								next.render(selectedEntity, x, y);
							}
						}
					}
				}
			}

		}

	}

	/**
	 * Not used
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Updates with the logic of whatever tile was pressed
	 * 
	 * @param e - the mouse event fired
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		// get the source
		if (e.getSource() instanceof TileGUI) {

			// source is a tile
			TileGUI tile = (TileGUI) e.getSource();

			// do the correct action
			if (tile.getId() == SELECTOR) {
				selected.setTile(tile.getTile());
				selectedEntity = null;
			}

			if (tile.getId() == DISPLAY) {

				// draw tile only if no entity is selected
				if (selectedEntity == null) {

					// save to temporary file
					quickSave();

					// point of origin
					int xPos = tile.getX();
					int yPos = tile.getY();

					// set over an area
					for (int x = 0; x < size; x++) {
						for (int y = 0; y < size; y++) {

							// get next the component
							TileGUI next = (TileGUI) content.getComponentAt(xPos + (x * zoomScale),
							        yPos + (y * zoomScale));
							if (next != null) {

								// set the tile
								next.setTile(selected.getTile());
							}

						}
					}

					// selected entity is not null
				} else {
					
					// the source tile contains the entity
					tile.addEntity(selectedEntity.getEntity());
					
					// draw the entity
					for (int x = 0; x < selectedEntity.getXTiles(); x++) {
						for (int y = 0; y < selectedEntity.getYTiles(); y++) {

							// next tile over
							TileGUI next = (TileGUI) content.getComponentAt(tile.getX() + (x * zoomScale),
							        tile.getY() + (y * zoomScale));

							// render if the tile exists
							if (next != null) {
								next.render(selectedEntity, x, y);
							}
						}
					}

				}

			}

			// entity gui was clicked
		} else if (e.getSource() instanceof EntityGUI) {
			selectedEntity = (EntityGUI) e.getSource();
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
	 * 
	 * @param e - the event fired
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// save
		if (e.getSource() == save) {
			save();

			// load
		} // save
		if (e.getSource() == saveRendered) {
			saveRenderedLevel();

			// load
		} else if (e.getSource() == load) {
			load();

			// fill all
		} else if (e.getSource() == loadPNG) {
			loadPNG();

			// fill all
		} else if (e.getSource() == setAll) {

			// loop through all the children
			for (int i = 0; i < content.getComponentCount(); i++) {

				// each component is a tile GUI
				((TileGUI) content.getComponent(i)).setTile(selected.getTile());
				;
			}

			// decrease brush size
		} else if (e.getSource() == minusSize) {

			// don't decrease to zero
			if (size > 1) {
				size--;
				sizeLabel.setText(String.valueOf(size));
			}

			// increase brush size
		} else if (e.getSource() == plusSize) {

			size++;
			sizeLabel.setText(String.valueOf(size));

			// decrease zoom
		} else if (e.getSource() == minusZoom) {

			// don't decrease to zero
			if (zoomScale > 8) {
				zoomScale -= 8;
			}

			// change all tile sizes
			for (int i = 0; i < content.getComponentCount(); i++) {
				((TileGUI) content.getComponent(i)).changeSize(zoomScale, zoomScale);
			}

			// repaint the content pane
			content.revalidate();

			// increase zoom size
		} else if (e.getSource() == plusZoom) {

			zoomScale += 8;
			// change all tile sizes
			for (int i = 0; i < content.getComponentCount(); i++) {
				((TileGUI) content.getComponent(i)).changeSize(zoomScale, zoomScale);
			}

			// repaint the content pane
			content.revalidate();

			// load from temporary file
		} else if (e.getSource() == undo) {
			quickLoad();

			// display tiles
		} else if (e.getSource() == tiles) {
			cl.show(leftMain, TILE_DISPLAY);

			// display entities
		} else if (e.getSource() == entities) {
			cl.show(leftMain, ENTITY_DISPLAY);
		}

	}

	/**
	 * Saves the file temporarily
	 */
	private void quickSave() {

		// tile data
		byte[] data = new byte[LEVEL_WIDTH * LEVEL_HEIGHT];

		// loop through all the children
		for (int i = 0; i < content.getComponentCount(); i++) {

			// each component is a tile GUI
			data[i] = ((TileGUI) content.getComponent(i)).getTile().getId();
		}

		File output = new File(DIR + TEMP);

		// open the output stream
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(output, false))) {

			// save the tile data
			os.write(data);

		} catch (FileNotFoundException e) {

			System.err.println("Making Directory: " + DIR);

			// make the directory and try again
			if (output.mkdirs()) {
				quickSave();
			}

			// output stream could not open
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves the level data to a file
	 */
	private void save() {

		// tile data
		byte[] data = new byte[LEVEL_WIDTH * LEVEL_HEIGHT];
		
		// entity data and index
		byte[][] entities = new byte[MAX_ENTITIES_LEVEL][];
		int index = 0;

		// loop through all the children
		for (int i = 0; i < content.getComponentCount(); i++) {
			
			// get the tile
			TileGUI tile = (TileGUI) content.getComponent(i);

			// each component is a tile GUI
			data[i] = tile.getTile().getId();
			
			// check if there is an entity
			if (tile.entityExists()) {
				entities[index++] = ByteBuffer.allocate(9).put(tile.getEntityId()).putLong(tile.getEntityData()).array();
			}
		}

		// output sources
		File outputLevel = new File(DIR + name.getText());
		File outputEntities = new File(DIR + name.getText() + ENTITY);
		
		try {
			
			// open the tile output stream
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputLevel, false));

			// save the tile data
			os.write(data);
			
			// close the tile output stream
			os.close();
			
			// entity output stream
			BufferedOutputStream eos = new BufferedOutputStream(new FileOutputStream(outputEntities, false));
			
			// save the entity data
			for (int i = 0; i < entities.length; i++) {
				
				// write to file
				if (entities[i] != null) {
					eos.write(entities[i]);
					
					// no more entities in list
				} else {
					break;
				}
			}
			
			// free resources
			eos.close();

		} catch (FileNotFoundException e) {

			System.err.println("Making Directory: " + DIR);

			// make the directory and try again
			if (outputLevel.mkdirs()) {
				save();
			}

			// output stream could not open
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
		BufferedImage level = new BufferedImage(LEVEL_WIDTH * modifier, LEVEL_HEIGHT * modifier,
		        BufferedImage.TYPE_INT_RGB);
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
			File output = new File(DIR + name.getText() + PNG);
			ImageIO.write(level, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tries to load a png file
	 */
	private void loadPNG() {

		// check if the file exists
		try {

			// create the buffered image
			BufferedImage level = ImageIO.read(Designer.class.getResource(BASE + name.getText() + PNG));

			// stores level colors
			int[] pixels = new int[level.getWidth() * level.getHeight()];

			// fill the pixel array
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = level.getRGB(i % level.getWidth(), i / level.getWidth());
			}

			// loop through all the children
			for (int i = 0; i < content.getComponentCount(); i++) {

				// find the corresponding tile with the pixel color from the
				// file
				for (Tile tile : Tile.tileList) {

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

	/**
	 * Loads level data into the editor
	 */
	private void load() {

		// open the input files
		File inputLevel = new File(DIR + name.getText());
		File inputEntities = new File(DIR + name.getText() + ENTITY);

		try {
			
			// open the tile input stream
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(inputLevel));

			// read into the data tile array
			byte[] data = new byte[LEVEL_WIDTH * LEVEL_HEIGHT];
			is.read(data);
			
			// free resources
			is.close();
			
			// loop through all the children
			for (int i = 0; i < content.getComponentCount(); i++) {

				// find the corresponding tile with the pixel color from the
				// file
				for (Tile tile : Tile.tileList) {

					if (tile != null) {

						// found a match
						if (data[i] == tile.getId()) {
							((TileGUI) content.getComponent(i)).setTile(tile);
							break;
						}
					}
				}

			}
			
			// create an input stream for entities
			BufferedInputStream eis = new BufferedInputStream(new FileInputStream(inputEntities));
			while (eis.available() >= 9) {
				
				// create new data for the entity
				data = new byte[9];
				eis.read(data);
				
				// ID of entity
				byte id = data[0];
				
				// coordinates
				int xPos = (ByteBuffer.wrap(data).getShort(2) & 0x0000FFFF)  * zoomScale / 8;
				int yPos = (ByteBuffer.wrap(data).getShort(4) & 0x0000FFFF) * zoomScale / 8;
				
				// get the entity
				EntityGUI e = getEntity(id);
				
				// add the entity to the tile
				((TileGUI) content.getComponentAt(xPos, yPos)).addEntity(e.getEntity());

				// draw the entity
				for (int x = 0; x < e.getXTiles(); x++) {
					for (int y = 0; y < e.getYTiles(); y++) {

						// next tile over
						TileGUI next = (TileGUI) content.getComponentAt(xPos + (x * zoomScale),
						        yPos + (y * zoomScale));

						// render if the tile exists
						if (next != null) {
							next.render(e, x, y);
						}
					}
				}
				
			}
			
			// free resources
			eis.close();

		} catch (Exception e) {
			System.err.println("Could not load file: " + DIR + name.getText());
			e.printStackTrace();
		}

	}

	/**
	 * Tries to load the temporary file
	 */
	private void quickLoad() {

		// open the file
		File input = new File(DIR + TEMP);

		// open the output stream
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(input))) {

			// tile data
			byte[] data = new byte[LEVEL_WIDTH * LEVEL_HEIGHT];
			int counter = 0;

			// read all the bytes in the save file
			while (is.available() > 0) {
				data[counter++] = is.readByte();
			}

			// loop through all the children
			for (int i = 0; i < content.getComponentCount(); i++) {

				// find the corresponding tile with the pixel color from the
				// file
				for (Tile tile : Tile.tileList) {

					if (tile != null) {

						// found a match
						if (data[i] == tile.getId()) {
							((TileGUI) content.getComponent(i)).setTile(tile);
							break;
						}
					}
				}

			}

		} catch (Exception e) {
			System.err.println("Could not load file: " + DIR + TEMP);
		}
	}

	/**
	 * Helper method to add entities to JPanel
	 */
	private void initEntities() {

		// make sure there are no null tiles by first setting everything to a
		// stool
		for (int i = 0; i < entityList.length; i++) {
			entityList[i] = new EntityGUI(new Stool(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
		}

		// add all the unique items
		entityList[Entity.DESTRUCTIBLE_TILE] = new EntityGUI(new DestructibleTile(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
		entityList[Entity.FIRE_ENTITY] = new EntityGUI(new FireEntity(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
		entityList[Entity.STOOL] = new EntityGUI(new Stool(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
		entityList[Entity.BED] = new EntityGUI(new Bed(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 3);
		// add all buildings, furniture, etc.
		// additional notes: create one of each type for PEASANTS (MALE, FEMALE, BOY, GIRL)
		// create one of each type for GANG MEMBER
		// create one of each type for Native Americans
		// for spawners just put  type as 0 for now

	}

	/**
	 * Gets the entity based off the ID it found
	 * 
	 * @param id - the ID in the save file
	 * @return the entity based on the ID
	 */
	private EntityGUI getEntity(int id) {

		switch (id) {
		case Entity.DESTRUCTIBLE_TILE:
			return new EntityGUI(new DestructibleTile(null, 0, 0), 0, 0, 1, 1);
		case Entity.FIRE_ENTITY:
			return new EntityGUI(new FireEntity(null, 0, 0), 0, 0, 1, 1);
		case Entity.STOOL:
			return new EntityGUI(new Stool(null, 0, 0), 0, 0, 1, 1);
		case Entity.BED:
			return new EntityGUI(new Bed(null, 0, 0), 0, 0, 3, 3);
		default:
			return null;
		}

	}

}

package editors;

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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javajesus.entities.Chest;
import javajesus.entities.DestructibleTile;
import javajesus.entities.Entity;
import javajesus.entities.FireEntity;
import javajesus.entities.Spawner;
import javajesus.entities.animals.Cat;
import javajesus.entities.animals.Cow;
import javajesus.entities.animals.Dog;
import javajesus.entities.animals.Fox;
import javajesus.entities.monsters.Bandito;
import javajesus.entities.monsters.Centaur;
import javajesus.entities.monsters.Cyclops;
import javajesus.entities.monsters.Demon;
import javajesus.entities.monsters.GangMember;
import javajesus.entities.monsters.Monkey;
import javajesus.entities.monsters.Skeleton;
import javajesus.entities.npcs.Citizen;
import javajesus.entities.npcs.Gaucho;
import javajesus.entities.npcs.Knight;
import javajesus.entities.npcs.Panchombre;
import javajesus.entities.npcs.Peasant;
import javajesus.entities.npcs.aggressive.Companion;
import javajesus.entities.npcs.aggressive.Gorilla;
import javajesus.entities.npcs.aggressive.NativeAmerican;
import javajesus.entities.npcs.aggressive.PoliceOfficer;
import javajesus.entities.npcs.aggressive.SWATOfficer;
import javajesus.entities.npcs.aggressive.TechWarrior;
import javajesus.entities.npcs.characters.Bautista;
import javajesus.entities.npcs.characters.Daughter;
import javajesus.entities.npcs.characters.Jesus;
import javajesus.entities.npcs.characters.Jobs;
import javajesus.entities.npcs.characters.Kobe;
import javajesus.entities.npcs.characters.LordHillsborough;
import javajesus.entities.npcs.characters.Octavius;
import javajesus.entities.npcs.characters.Ranchero;
import javajesus.entities.npcs.characters.Son;
import javajesus.entities.npcs.characters.Wife;
import javajesus.entities.npcs.characters.Zorra;
import javajesus.entities.solid.buildings.ApartmentHighRise;
import javajesus.entities.solid.buildings.Castle;
import javajesus.entities.solid.buildings.CastleTower;
import javajesus.entities.solid.buildings.CatholicChapel;
import javajesus.entities.solid.buildings.CatholicChurch;
import javajesus.entities.solid.buildings.CaveEntrance;
import javajesus.entities.solid.buildings.Factory;
import javajesus.entities.solid.buildings.GenericHospital;
import javajesus.entities.solid.buildings.GunStore;
import javajesus.entities.solid.buildings.Hotel;
import javajesus.entities.solid.buildings.Hut;
import javajesus.entities.solid.buildings.MineShaft;
import javajesus.entities.solid.buildings.ModernSkyscraper;
import javajesus.entities.solid.buildings.NiceHouse;
import javajesus.entities.solid.buildings.NiceHouse2;
import javajesus.entities.solid.buildings.Police;
import javajesus.entities.solid.buildings.PoorHouse;
import javajesus.entities.solid.buildings.Prison;
import javajesus.entities.solid.buildings.Projects;
import javajesus.entities.solid.buildings.RancheroHouse;
import javajesus.entities.solid.buildings.RefugeeTent;
import javajesus.entities.solid.buildings.RussianOrthodoxChurch;
import javajesus.entities.solid.buildings.ShantyHouse;
import javajesus.entities.solid.buildings.Skyscraper;
import javajesus.entities.solid.buildings.Tippee;
import javajesus.entities.solid.buildings.Warehouse;
import javajesus.entities.solid.buildings.hippyville.GreatTree;
import javajesus.entities.solid.buildings.hippyville.TreeHouse;
import javajesus.entities.solid.buildings.hippyville.UCGrizzly;
import javajesus.entities.solid.buildings.oakwood.OakwoodCityHall;
import javajesus.entities.solid.buildings.sancisco.ChinatownHouse;
import javajesus.entities.solid.buildings.sancisco.RussianClub;
import javajesus.entities.solid.buildings.sancisco.SanCiscoCityHall;
import javajesus.entities.solid.buildings.sancisco.SanCiscoSkyscraper;
import javajesus.entities.solid.buildings.sancisco.TriadHQ;
import javajesus.entities.solid.buildings.sanjuan.JungleHQ;
import javajesus.entities.solid.buildings.sanjuan.QuackerHQ;
import javajesus.entities.solid.buildings.sanjuan.SanJuanCityHall;
import javajesus.entities.solid.buildings.sanjuan.TheHub;
import javajesus.entities.solid.buildings.sequoia.SequoiaCinema;
import javajesus.entities.solid.buildings.sequoia.SequoiaSchool;
import javajesus.entities.solid.buildings.techtopia.Cafe;
import javajesus.entities.solid.buildings.techtopia.CardinalUniversity;
import javajesus.entities.solid.buildings.techtopia.PearHQ;
import javajesus.entities.solid.buildings.techtopia.RadarDish;
import javajesus.entities.solid.buildings.techtopia.TechTopiaCityHall;
import javajesus.entities.solid.buildings.techtopia.WeirdTechBuilding1;
import javajesus.entities.solid.buildings.techtopia.WeirdTechBuilding2;
import javajesus.entities.solid.furniture.Bar;
import javajesus.entities.solid.furniture.Bed;
import javajesus.entities.solid.furniture.Bench;
import javajesus.entities.solid.furniture.CardTable;
import javajesus.entities.solid.furniture.ChairSide;
import javajesus.entities.solid.furniture.ComputerMonitor;
import javajesus.entities.solid.furniture.ComputerTower;
import javajesus.entities.solid.furniture.FilingCabinet;
import javajesus.entities.solid.furniture.LongTable;
import javajesus.entities.solid.furniture.Nightstand;
import javajesus.entities.solid.furniture.PoolTable;
import javajesus.entities.solid.furniture.ShortTable;
import javajesus.entities.solid.furniture.Sofa;
import javajesus.entities.solid.furniture.SquareTable;
import javajesus.entities.solid.furniture.Stool;
import javajesus.entities.solid.furniture.Television;
import javajesus.entities.solid.furniture.Throne;
import javajesus.entities.solid.trees.DeadSequoia;
import javajesus.entities.solid.trees.DeadSequoiaSmall;
import javajesus.entities.solid.trees.GenericTree;
import javajesus.entities.solid.trees.LargeSequoia;
import javajesus.entities.solid.trees.MediumSequoia;
import javajesus.entities.solid.trees.SmallSequoia;
import javajesus.entities.vehicles.Boat;
import javajesus.entities.vehicles.CenturyLeSabre;
import javajesus.entities.vehicles.Horse;
import javajesus.entities.vehicles.SportsCar;
import javajesus.entities.vehicles.Truck;
import javajesus.graphics.Screen;
import javajesus.level.tile.Tile;

/*
 * Driver class of the level editors
 */
public class LevelEditor extends JPanel implements MouseListener, ActionListener {

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

	// gets the name add-on for entity files
	private static final String ENTITY = "_entities";

	// dimensions of the window
	private static final int WIDTH = 1000, HEIGHT = 800;

	// number of tiles on level
	private static final int LEVEL_WIDTH = 200, LEVEL_HEIGHT = 200;

	// constants for card layout
	private static final String TILE_DISPLAY = "Tiles", ENTITY_DISPLAY = "Entities";

	// max number of tiles/entities in the list selector
	private static final int MAX_ENTITIES = 256;

	// max number of tiles/entities in the list selector
	private static final int MAX_ENTITIES_LEVEL = LEVEL_WIDTH * LEVEL_HEIGHT;

	// list of all entities that can be placed
	private static final EntityGUI[] entityList = new EntityGUI[MAX_ENTITIES];

	// size of each tile/entity selector
	private static final int PANEL_SIZE = 50;

	// path of the loaded file
	private static String filePath = new File(DIR + "New").getPath();

	// buttons at the top
	private final JButton save, saveRendered, setAll, plusSize, minusSize, plusZoom, minusZoom, tiles, entities, open,
	        randomGrass;
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
		frame.getContentPane().add(new LevelEditor(WIDTH, HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setTitle("Level Editor for Java Jesus by Derek Jow");
		frame.setVisible(true);
		frame.toFront();
	}

	/**
	 * Creates the display of the editors
	 * 
	 * @param width - width of the window
	 * @param height - height of the window
	 */
	public LevelEditor(int width, int height) {

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
		top.add(open = new JButton("Open"));
		open.addActionListener(this);
		top.add(save = new JButton("Save"));
		save.addActionListener(this);
		top.add(saveRendered = new JButton("Save Display"));
		saveRendered.addActionListener(this);
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
		/*
		 * rightContent.add(undo = new JButton("Undo"));
		 * undo.setAlignmentX(Component.CENTER_ALIGNMENT);
		 * undo.addActionListener(this);
		 */

		// generate grass
		rightContent.add(randomGrass = new JButton("Gen Grass"));
		randomGrass.addActionListener(this);
		randomGrass.setAlignmentX(Component.CENTER_ALIGNMENT);

		// assemble the viewing panel
		JScrollPane pane = new JScrollPane(leftContent);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.getVerticalScrollBar().setUnitIncrement(16);
		viewing.add(pane, BorderLayout.WEST);
		pane = new JScrollPane(content);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.getVerticalScrollBar().setUnitIncrement(16);
		viewing.add(pane, BorderLayout.CENTER);
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

								// make sure the next component is a tile GUI
								if (content.getComponentAt(xPos + (x * zoomScale),
								        yPos + (y * zoomScale)) instanceof TileGUI) {

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
					}

					// entity is selected
				} else {

					// the source tile contains the entity
					tile.addEntity(selectedEntity.getEntity());

					// draw the entity
					for (int x = 0; x < selectedEntity.getXTiles(); x++) {
						for (int y = 0; y < selectedEntity.getYTiles(); y++) {

							// get the tile gui
							if (content.getComponentAt(tile.getX() + (x * zoomScale),
							        tile.getY() + (y * zoomScale)) instanceof TileGUI) {
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
			
			// mouse not pressed
		} else {
			
			// draw an outline of the entity
			if (selectedEntity != null && e.getSource() instanceof TileGUI) {

				// tile mouse is hovering over
				TileGUI tile = (TileGUI) e.getSource();

				// get the tile gui
				if (content.getComponentAt(
						tile.getX() + ((selectedEntity.getXTiles() - 1) * zoomScale),
						tile.getY() + ((selectedEntity.getYTiles() - 1) * zoomScale)) instanceof TileGUI) {

					// next tile over
					TileGUI next = (TileGUI) content.getComponentAt(tile.getX()
							+ ((selectedEntity.getXTiles() - 1) * zoomScale),
							tile.getY() + ((selectedEntity.getYTiles() - 1) * zoomScale));

					// flicker if the tile exists
					if (next != null && next != tile) {
						next.flicker();
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

		// mouse is held down
		pressed = true;

		// get the source
		if (e.getSource() instanceof TileGUI) {

			// source is a tile
			TileGUI tile = (TileGUI) e.getSource();

			// do the correct action
			if (tile.getId() == SELECTOR) {
				selected.setTile(tile.getTile());
				selectedEntity = null;

				// tell me the ID!
				if (SwingUtilities.isRightMouseButton(e)) {
					JOptionPane.showMessageDialog(this, String.valueOf(tile.getTile().getId() & 0x000000FF));
					pressed = false;
				}
			}

			if (tile.getId() == DISPLAY) {

				// draw tile only if no entity is selected
				if (selectedEntity == null) {

					// Change to this tile type
					if (SwingUtilities.isRightMouseButton(e)) {
						selected.setTile(tile.getTile());
						selectedEntity = null;
						pressed = false;
						return;
					}

					// save to temporary file
					// quickSave();

					// point of origin
					int xPos = tile.getX();
					int yPos = tile.getY();

					// set over an area
					for (int x = 0; x < size; x++) {
						for (int y = 0; y < size; y++) {

							// get the tile gui
							if (content.getComponentAt(xPos + (x * zoomScale),
							        yPos + (y * zoomScale)) instanceof TileGUI) {
								
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

					// selected entity is not null
				} else {

					// the source tile contains the entity
					tile.addEntity(selectedEntity.getEntity());

					// draw the entity
					for (int x = 0; x < selectedEntity.getXTiles(); x++) {
						for (int y = 0; y < selectedEntity.getYTiles(); y++) {

							// get the tile gui
							if (content.getComponentAt(tile.getX() + (x * zoomScale),
							        tile.getY() + (y * zoomScale)) instanceof TileGUI) {
								
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

			// entity gui was clicked
		} else if (e.getSource() instanceof EntityGUI) {

			// unclick the mouse
			pressed = false;

			// make a copy of the selected entity gui
			selectedEntity = getEntity(((EntityGUI) e.getSource()).getEntity().getId());

			// force the user to enter entity data
			JOptionPane.showMessageDialog(this, new EntityExtrasPanel(selectedEntity));

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

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

		// open a file
		if (e.getSource() == open) {

			// create the file chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setCurrentDirectory(new File(DIR));

			// open the chooser
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

				// load the file!
				File file = fc.getSelectedFile();

				// file path
				filePath = file.getPath();

				// load from a png file
				if (filePath.contains(PNG)) {
					loadPNG(file);

					// take off the png extension
					filePath = filePath.substring(0, filePath.length() - 4);

					// take off png extension
					name.setText(file.getName().substring(0, file.getName().length() - 4));

					// load normally
				} else {
					load(file);
					name.setText(file.getName());
				}

			}

			// save
		} else if (e.getSource() == save) {
			save(new File(filePath));

		} // save
		if (e.getSource() == saveRendered) {
			saveRenderedLevel();

			// fill all tiles on screen
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
		} /*
		   * else if (e.getSource() == undo) { quickLoad();
		   * 
		   * // randomize grass tiles }
		   */ else if (e.getSource() == randomGrass) {

			// randomize each grass tile
			for (int i = 0; i < content.getComponentCount(); i++) {

				TileGUI tile = (TileGUI) content.getComponent(i);
				if (tile.getTile().equals(Tile.GRASS0) && !tile.entityExists()) {
					tile.setTile(Tile.GRASS());
				}
			}

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
	@SuppressWarnings("unused")
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
	private void save(File outputLevel) {

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
				entities[index++] = ByteBuffer.allocate(9).put(tile.getEntityId())
				        .putLong(tile.getEntityData(content.getComponent(0).getX(), content.getComponent(0).getY()))
				        .array();
			}
		}

		// output sources
		File outputEntities = new File(filePath + ENTITY);

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
				save(outputLevel);
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
	private void loadPNG(File inputLevel) {

		// check if the file exists
		try {

			// create the buffered image
			BufferedImage level = ImageIO.read(inputLevel);

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
			System.out.println("PNG cannot be loaded, please create a new file");
		}
	}

	/**
	 * Loads level data into the editor
	 */
	private void load(File inputLevel) {

		// open the input files
		File inputEntities = new File(inputLevel.getPath() + ENTITY);

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
				int xPos = (ByteBuffer.wrap(data).getShort(2) & 0x0000FFFF) * zoomScale / 8
				        + content.getComponent(0).getX();
				int yPos = (ByteBuffer.wrap(data).getShort(4) & 0x0000FFFF) * zoomScale / 8
				        + content.getComponent(0).getY();

				// get the entity
				EntityGUI e = getEntity(id);

				// save the extra data
				e.setExtra1(ByteBuffer.wrap(data).getShort(6));
				e.setExtra2(data[8]);

				// add the entity to the tile
				((TileGUI) content.getComponentAt(xPos, yPos)).addEntity(e.getEntity());

				// draw the entity
				for (int x = 0; x < e.getXTiles(); x++) {
					for (int y = 0; y < e.getYTiles(); y++) {

						// next tile over
						TileGUI next = (TileGUI) content.getComponentAt(xPos + (x * zoomScale), yPos + (y * zoomScale));

						// render if the tile exists
						if (next != null) {
							next.render(e, x, y);
						}
					}
				}

			}

			// free resources
			eis.close();

		} catch (FileNotFoundException e) {

			// create the file
			save(inputLevel);

		} catch (IOException e) {
			System.err.println("Could not load file: " + DIR + name.getText());
			e.printStackTrace();

		}

	}

	/**
	 * Tries to load the temporary file
	 */
	@SuppressWarnings("unused")
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

		try {

			// add all the unique items
			entityList[Entity.DESTRUCTIBLE_TILE] = new EntityGUI(new DestructibleTile(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 1, 1);
			entityList[Entity.FIRE_ENTITY] = new EntityGUI(new FireEntity(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
			entityList[Entity.BED] = new EntityGUI(new Bed(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 3);
			entityList[Entity.BENCH] = new EntityGUI(new Bench(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 1);
			entityList[Entity.POOL_TABLE] = new EntityGUI(new PoolTable(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 4);
			entityList[Entity.CHAIR_SIDE] = new EntityGUI(new ChairSide(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
			entityList[Entity.CHEST] = new EntityGUI(new Chest(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 1);
			entityList[Entity.COMPUTER_MONITOR] = new EntityGUI(new ComputerMonitor(null, 0, 0), PANEL_SIZE, PANEL_SIZE,
			        1, 1);
			entityList[Entity.COMPUTER_TOWER] = new EntityGUI(new ComputerTower(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1,
			        2);
			entityList[Entity.DINING_TABLE] = new EntityGUI(new ShortTable(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 3);
			entityList[Entity.FILING_CABINET] = new EntityGUI(new FilingCabinet(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1,
			        3);
			entityList[Entity.LONG_TABLE] = new EntityGUI(new LongTable(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 16, 16);
			entityList[Entity.NIGHTSTAND] = new EntityGUI(new Nightstand(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 2);
			entityList[Entity.SOFA] = new EntityGUI(new Sofa(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 2);
			entityList[Entity.SQUARE_TABLE] = new EntityGUI(new SquareTable(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.STOOL] = new EntityGUI(new Stool(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
			entityList[Entity.TELEVISION] = new EntityGUI(new Television(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.THRONE] = new EntityGUI(new Throne(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 3);
			entityList[Entity.APARTMENT_HIGH_RISE] = new EntityGUI(new ApartmentHighRise(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 9, 28);
			entityList[Entity.CASTLE] = new EntityGUI(new Castle(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 41, 22);
			entityList[Entity.CASTLE_TOWER] = new EntityGUI(new CastleTower(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 12,
			        22);
			entityList[Entity.CATHOLIC_CHAPEL] = new EntityGUI(new CatholicChapel(null, 0, 0), PANEL_SIZE, PANEL_SIZE,
			        8, 8);
			entityList[Entity.CATHOLIC_CHURCH] = new EntityGUI(new CatholicChurch(null, 0, 0), PANEL_SIZE, PANEL_SIZE,
			        11, 10);
			entityList[Entity.CAVE_ENTRANCE] = new EntityGUI(new CaveEntrance(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 6,
			        5);
			entityList[Entity.FACTORY] = new EntityGUI(new Factory(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 11);
			entityList[Entity.GENERIC_HOSPITAL] = new EntityGUI(new GenericHospital(null, 0, 0), PANEL_SIZE, PANEL_SIZE,
			        13, 10);
			entityList[Entity.GUNSTORE] = new EntityGUI(new GunStore(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 9, 6);
			entityList[Entity.HOTEL] = new EntityGUI(new Hotel(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 10);
			entityList[Entity.HUT] = new EntityGUI(new Hut(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 6, 6);
			entityList[Entity.MINESHAFT] = new EntityGUI(new MineShaft(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 15, 8);
			entityList[Entity.MODERN_SKYSCRAPER] = new EntityGUI(new ModernSkyscraper(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 13, 32);
			entityList[Entity.NICE_HOUSE] = new EntityGUI(new NiceHouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 7, 8);
			entityList[Entity.NICE_HOUSE2] = new EntityGUI(new NiceHouse2(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 7, 8);
			entityList[Entity.POLICE] = new EntityGUI(new Police(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 11);
			entityList[Entity.POOR_HOUSE] = new EntityGUI(new PoorHouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 5, 7);
			entityList[Entity.PRISON] = new EntityGUI(new Prison(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 10);
			entityList[Entity.PROJECTS] = new EntityGUI(new Projects(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 10);
			entityList[Entity.RANCHERO_HOUSE] = new EntityGUI(new RancheroHouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13,
			        8);
			entityList[Entity.REFUGEE_TENT] = new EntityGUI(new RefugeeTent(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 6, 3);
			entityList[Entity.RUSSIAN_ORTHODOX_CHURCH] = new EntityGUI(new RussianOrthodoxChurch(null, 0, 0),
			        PANEL_SIZE, PANEL_SIZE, 12, 10);
			entityList[Entity.SHANTY_HOUSE] = new EntityGUI(new ShantyHouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 5, 6);
			entityList[Entity.SKYSCRAPER] = new EntityGUI(new Skyscraper(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 11, 32);
			entityList[Entity.TIPPEE] = new EntityGUI(new Tippee(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 7);
			entityList[Entity.WAREHOUSE] = new EntityGUI(new Warehouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 8);
			entityList[Entity.GREAT_TREE] = new EntityGUI(new GreatTree(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 7, 15);
			entityList[Entity.TREE_HOUSE] = new EntityGUI(new TreeHouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 6, 15);
			entityList[Entity.UC_GRIZZLY] = new EntityGUI(new UCGrizzly(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 25, 8);
			entityList[Entity.OAKWOOD_CITY_HALL] = new EntityGUI(new OakwoodCityHall(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 14, 15);
			entityList[Entity.CHINATOWN_HOUSE] = new EntityGUI(new ChinatownHouse(null, 0, 0), PANEL_SIZE, PANEL_SIZE,
			        8, 8);
			entityList[Entity.RUSSIAN_CLUB] = new EntityGUI(new RussianClub(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 19, 8);
			entityList[Entity.SAN_CISCO_CITY_HALL] = new EntityGUI(new SanCiscoCityHall(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 24, 15);
			entityList[Entity.SAN_CISCO_SKYSCRAPER] = new EntityGUI(new SanCiscoSkyscraper(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 27, 85);
			entityList[Entity.TRIAD_HQ] = new EntityGUI(new TriadHQ(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 24, 22);
			entityList[Entity.JUNGLE_HQ] = new EntityGUI(new JungleHQ(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 19, 12);
			entityList[Entity.QUACKER_HQ] = new EntityGUI(new QuackerHQ(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 17, 13);
			entityList[Entity.SAN_JUAN_CITY_HALL] = new EntityGUI(new SanJuanCityHall(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 15, 15);
			entityList[Entity.THE_HUB] = new EntityGUI(new TheHub(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 21);
			entityList[Entity.SEQUOIA_CINEMA] = new EntityGUI(new SequoiaCinema(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 15,
			        15);
			entityList[Entity.SEQUOIA_SCHOOL] = new EntityGUI(new SequoiaSchool(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 21,
			        15);
			entityList[Entity.CAFE] = new EntityGUI(new Cafe(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 8);
			entityList[Entity.CARDINAL_UNIVERSITY] = new EntityGUI(new CardinalUniversity(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 25, 7);
			entityList[Entity.PEAR_HQ] = new EntityGUI(new PearHQ(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 13, 24);
			entityList[Entity.RADAR_DISH] = new EntityGUI(new RadarDish(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 11, 17);
			entityList[Entity.TECHTOPIA_CITY_HALL] = new EntityGUI(new TechTopiaCityHall(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 12, 12);
			entityList[Entity.WEIRD_TECH_BUILDING1] = new EntityGUI(new WeirdTechBuilding1(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 6, 17);
			entityList[Entity.WEIRD_TECH_BUILDING2] = new EntityGUI(new WeirdTechBuilding2(null, 0, 0), PANEL_SIZE,
			        PANEL_SIZE, 6, 12);
			entityList[Entity.DEAD_SEQUOIA] = new EntityGUI(new DeadSequoia(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 8);
			entityList[Entity.GENERIC_TREE] = new EntityGUI(new GenericTree(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 4);
			entityList[Entity.LARGE_SEQUOIA] = new EntityGUI(new LargeSequoia(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3,
			        8);
			entityList[Entity.MEDIUM_SEQUOIA] = new EntityGUI(new MediumSequoia(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3,
			        6);
			entityList[Entity.SMALL_SEQUOIA] = new EntityGUI(new SmallSequoia(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2,
			        4);
			entityList[Entity.SPAWNER] = new EntityGUI(new Spawner(null, 0, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 1);
			entityList[Entity.CENTAUR] = new EntityGUI(new Centaur(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 3);
			entityList[Entity.CYCLOPS] = new EntityGUI(new Cyclops(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 5, 6);
			entityList[Entity.DEMON] = new EntityGUI(new Demon(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 3);
			entityList[Entity.GANG_MEMBER] = new EntityGUI(new GangMember(null, 0, 0, GangMember.RUSSIAN), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.MONKEY] = new EntityGUI(new Monkey(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.BAUTISTA] = new EntityGUI(new Bautista(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.DAUGHTER] = new EntityGUI(new Daughter(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.CITIZEN] = new EntityGUI(new Citizen(null, 0, 0, Citizen.MALE), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.JESUS] = new EntityGUI(new Jesus(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.JOBS] = new EntityGUI(new Jobs(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.KNIGHT] = new EntityGUI(new Knight(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.KOBE] = new EntityGUI(new Kobe(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.LORD_HILLSBOROUGH] = new EntityGUI(new LordHillsborough(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.OCTAVIUS] = new EntityGUI(new Octavius(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.PEASANT] = new EntityGUI(new Peasant(null, 0, 0, Peasant.MALE), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.RANCHERO] = new EntityGUI(new Ranchero(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.SON] = new EntityGUI(new Son(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.WIFE] = new EntityGUI(new Wife(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.ZORRA] = new EntityGUI(new Zorra(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.COMPANION] = new EntityGUI(new Companion(null, 0, 0, null), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.GORILLA] = new EntityGUI(new Gorilla(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 3, 3);
			entityList[Entity.NATIVE_AMERICAN] = new EntityGUI(new NativeAmerican(null, 0, 0, NativeAmerican.MALE), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.POLICE_OFFICER] = new EntityGUI(new PoliceOfficer(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.SWAT_OFFICER] = new EntityGUI(new SWATOfficer(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.TECH_WARRIOR] = new EntityGUI(new TechWarrior(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.CAT] = new EntityGUI(new Cat(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.COW] = new EntityGUI(new Cow(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 3);
			entityList[Entity.DOG] = new EntityGUI(new Dog(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.FOX] = new EntityGUI(new Fox(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.HORSE] = new EntityGUI(new Horse(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 3);
			entityList[Entity.CENTURY_LESABRE] = new EntityGUI(new CenturyLeSabre(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 5);
			entityList[Entity.SPORTS_CAR] = new EntityGUI(new SportsCar(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 5);
			entityList[Entity.BOAT] = new EntityGUI(new Boat(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 5);
			entityList[Entity.TRUCK] = new EntityGUI(new Truck(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 5);
			entityList[Entity.GAUCHO] = new EntityGUI(new Gaucho(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.PANCHOMBRE] = new EntityGUI(new Panchombre(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.SKELETON] = new EntityGUI(new Skeleton(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.BANDITO] = new EntityGUI(new Bandito(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 2, 2);
			entityList[Entity.CARD_TABLE] = new EntityGUI(new CardTable(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 4, 4);
			entityList[Entity.BAR] = new EntityGUI(new Bar(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 6, 6);
			entityList[Entity.DEAD_SEQUOIA_SMALL] = new EntityGUI(new DeadSequoiaSmall(null, 0, 0), PANEL_SIZE, PANEL_SIZE, 1, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the entity based off the ID it found
	 * 
	 * @param id - the ID in the save file
	 * @return the entity based on the ID
	 */
	private EntityGUI getEntity(int id) {

		try {

			switch (id) {
			case Entity.DESTRUCTIBLE_TILE:
				return new EntityGUI(new DestructibleTile(null, 0, 0), 0, 0, 1, 1);
			case Entity.FIRE_ENTITY:
				return new EntityGUI(new FireEntity(null, 0, 0), 0, 0, 1, 1);
			case Entity.STOOL:
				return new EntityGUI(new Stool(null, 0, 0), 0, 0, 1, 1);
			case Entity.BED:
				return new EntityGUI(new Bed(null, 0, 0), 0, 0, 3, 3);
			case Entity.BENCH:
				return new EntityGUI(new Bench(null, 0, 0), 0, 0, 2, 1);
			case Entity.POOL_TABLE:
				return new EntityGUI(new PoolTable(null, 0, 0), 0, 0, 4, 4);
			case Entity.CHAIR_SIDE:
				return new EntityGUI(new ChairSide(null, 0, 0), 0, 0, 1, 1);
			case Entity.CHEST:
				return new EntityGUI(new Chest(null, 0, 0), 0, 0, 2, 1);
			case Entity.COMPUTER_MONITOR:
				return new EntityGUI(new ComputerMonitor(null, 0, 0), 0, 0, 1, 1);
			case Entity.COMPUTER_TOWER:
				return new EntityGUI(new ComputerTower(null, 0, 0), 0, 0, 1, 2);
			case Entity.DINING_TABLE:
				return new EntityGUI(new ShortTable(null, 0, 0), 0, 0, 3, 3);
			case Entity.FILING_CABINET:
				return new EntityGUI(new FilingCabinet(null, 0, 0), 0, 0, 1, 3);
			case Entity.LONG_TABLE:
				return new EntityGUI(new LongTable(null, 0, 0), 0, 0, 16, 16);
			case Entity.NIGHTSTAND:
				return new EntityGUI(new Nightstand(null, 0, 0), 0, 0, 1, 2);
			case Entity.SOFA:
				return new EntityGUI(new Sofa(null, 0, 0), 0, 0, 3, 2);
			case Entity.SQUARE_TABLE:
				return new EntityGUI(new SquareTable(null, 0, 0), 0, 0, 2, 2);
			case Entity.TELEVISION:
				return new EntityGUI(new Television(null, 0, 0), 0, 0, 2, 2);
			case Entity.THRONE:
				return new EntityGUI(new Throne(null, 0, 0), 0, 0, 2, 3);
			case Entity.DEAD_SEQUOIA:
				return new EntityGUI(new DeadSequoia(null, 0, 0), 0, 0, 3, 8);
			case Entity.SMALL_SEQUOIA:
				return new EntityGUI(new SmallSequoia(null, 0, 0), 0, 0, 2, 4);
			case Entity.MEDIUM_SEQUOIA:
				return new EntityGUI(new MediumSequoia(null, 0, 0), 0, 0, 3, 6);
			case Entity.LARGE_SEQUOIA:
				return new EntityGUI(new LargeSequoia(null, 0, 0), 0, 0, 3, 8);
			case Entity.GENERIC_TREE:
				return new EntityGUI(new GenericTree(null, 0, 0), 0, 0, 3, 4);
			case Entity.APARTMENT_HIGH_RISE:
				return new EntityGUI(new ApartmentHighRise(null, 0, 0), 0, 0, 9, 28);
			case Entity.CASTLE:
				return new EntityGUI(new Castle(null, 0, 0), 0, 0, 41, 22);
			case Entity.CASTLE_TOWER:
				return new EntityGUI(new CastleTower(null, 0, 0), 0, 0, 12, 22);
			case Entity.CATHOLIC_CHAPEL:
				return new EntityGUI(new CatholicChapel(null, 0, 0), 0, 0, 8, 8);
			case Entity.CATHOLIC_CHURCH:
				return new EntityGUI(new CatholicChurch(null, 0, 0), 0, 0, 11, 10);
			case Entity.CAVE_ENTRANCE:
				return new EntityGUI(new CaveEntrance(null, 0, 0), 0, 0, 6, 5);
			case Entity.FACTORY:
				return new EntityGUI(new Factory(null, 0, 0), 0, 0, 13, 11);
			case Entity.GENERIC_HOSPITAL:
				return new EntityGUI(new GenericHospital(null, 0, 0), 0, 0, 13, 10);
			case Entity.GUNSTORE:
				return new EntityGUI(new GunStore(null, 0, 0), 0, 0, 9, 6);
			case Entity.HOTEL:
				return new EntityGUI(new Hotel(null, 0, 0), 0, 0, 13, 10);
			case Entity.HUT:
				return new EntityGUI(new Hut(null, 0, 0), 0, 0, 6, 6);
			case Entity.MINESHAFT:
				return new EntityGUI(new MineShaft(null, 0, 0), 0, 0, 15, 8);
			case Entity.MODERN_SKYSCRAPER:
				return new EntityGUI(new ModernSkyscraper(null, 0, 0), 0, 0, 13, 32);
			case Entity.NICE_HOUSE:
				return new EntityGUI(new NiceHouse(null, 0, 0), 0, 0, 7, 8);
			case Entity.NICE_HOUSE2:
				return new EntityGUI(new NiceHouse2(null, 0, 0), 0, 0, 7, 8);
			case Entity.POLICE:
				return new EntityGUI(new Police(null, 0, 0), 0, 0, 13, 11);
			case Entity.POOR_HOUSE:
				return new EntityGUI(new PoorHouse(null, 0, 0), 0, 0, 5, 7);
			case Entity.PRISON:
				return new EntityGUI(new Prison(null, 0, 0), 0, 0, 13, 10);
			case Entity.PROJECTS:
				return new EntityGUI(new Projects(null, 0, 0), 0, 0, 13, 10);
			case Entity.RANCHERO_HOUSE:
				return new EntityGUI(new RancheroHouse(null, 0, 0), 0, 0, 13, 8);
			case Entity.REFUGEE_TENT:
				return new EntityGUI(new RefugeeTent(null, 0, 0), 0, 0, 6, 3);
			case Entity.RUSSIAN_ORTHODOX_CHURCH:
				return new EntityGUI(new RussianOrthodoxChurch(null, 0, 0), 0, 0, 12, 10);
			case Entity.SHANTY_HOUSE:
				return new EntityGUI(new ShantyHouse(null, 0, 0), 0, 0, 5, 6);
			case Entity.SKYSCRAPER:
				return new EntityGUI(new Skyscraper(null, 0, 0), 0, 0, 11, 32);
			case Entity.TIPPEE:
				return new EntityGUI(new Tippee(null, 0, 0), 0, 0, 4, 7);
			case Entity.WAREHOUSE:
				return new EntityGUI(new Warehouse(null, 0, 0), 0, 0, 13, 8);
			case Entity.GREAT_TREE:
				return new EntityGUI(new GreatTree(null, 0, 0), 0, 0, 7, 15);
			case Entity.TREE_HOUSE:
				return new EntityGUI(new TreeHouse(null, 0, 0), 0, 0, 6, 15);
			case Entity.UC_GRIZZLY:
				return new EntityGUI(new UCGrizzly(null, 0, 0), 0, 0, 25, 8);
			case Entity.OAKWOOD_CITY_HALL:
				return new EntityGUI(new OakwoodCityHall(null, 0, 0), 0, 0, 14, 15);
			case Entity.CHINATOWN_HOUSE:
				return new EntityGUI(new ChinatownHouse(null, 0, 0), 0, 0, 8, 8);
			case Entity.RUSSIAN_CLUB:
				return new EntityGUI(new RussianClub(null, 0, 0), 0, 0, 19, 8);
			case Entity.SAN_CISCO_CITY_HALL:
				return new EntityGUI(new SanCiscoCityHall(null, 0, 0), 0, 0, 24, 15);
			case Entity.SAN_CISCO_SKYSCRAPER:
				return new EntityGUI(new SanCiscoSkyscraper(null, 0, 0), 0, 0, 27, 85);
			case Entity.TRIAD_HQ:
				return new EntityGUI(new TriadHQ(null, 0, 0), 0, 0, 24, 22);
			case Entity.JUNGLE_HQ:
				return new EntityGUI(new JungleHQ(null, 0, 0), 0, 0, 19, 12);
			case Entity.QUACKER_HQ:
				return new EntityGUI(new QuackerHQ(null, 0, 0), 0, 0, 17, 13);
			case Entity.SAN_JUAN_CITY_HALL:
				return new EntityGUI(new SanJuanCityHall(null, 0, 0), 0, 0, 15, 15);
			case Entity.THE_HUB:
				return new EntityGUI(new TheHub(null, 0, 0), 0, 0, 13, 21);
			case Entity.SEQUOIA_CINEMA:
				return new EntityGUI(new SequoiaCinema(null, 0, 0), 0, 0, 15, 15);
			case Entity.SEQUOIA_SCHOOL:
				return new EntityGUI(new SequoiaSchool(null, 0, 0), 0, 0, 21, 15);
			case Entity.CAFE:
				return new EntityGUI(new Cafe(null, 0, 0), 0, 0, 13, 8);
			case Entity.CARDINAL_UNIVERSITY:
				return new EntityGUI(new CardinalUniversity(null, 0, 0), 0, 0, 25, 7);
			case Entity.PEAR_HQ:
				return new EntityGUI(new PearHQ(null, 0, 0), 0, 0, 13, 24);
			case Entity.RADAR_DISH:
				return new EntityGUI(new RadarDish(null, 0, 0), 0, 0, 11, 17);
			case Entity.TECHTOPIA_CITY_HALL:
				return new EntityGUI(new TechTopiaCityHall(null, 0, 0), 0, 0, 12, 12);
			case Entity.WEIRD_TECH_BUILDING1:
				return new EntityGUI(new WeirdTechBuilding1(null, 0, 0), 0, 0, 6, 17);
			case Entity.WEIRD_TECH_BUILDING2:
				return new EntityGUI(new WeirdTechBuilding2(null, 0, 0), 0, 0, 6, 12);
			case Entity.SPAWNER:
                return new EntityGUI(new Spawner(null, 0, 0, 0), 0, 0, 1, 1);
			case Entity.CENTAUR:
                return new EntityGUI(new Centaur(null, 0, 0), 0, 0, 2, 3);
			case Entity.CYCLOPS:
                return new EntityGUI(new Cyclops(null, 0, 0), 0, 0, 5, 6);
			case Entity.DEMON:
                return new EntityGUI(new Demon(null, 0, 0), 0, 0, 2, 3);
			case Entity.GANG_MEMBER:
                return new EntityGUI(new GangMember(null, 0, 0, GangMember.RUSSIAN), 0, 0, 2, 2);
			case Entity.MONKEY:
                return new EntityGUI(new Monkey(null, 0, 0), 0, 0, 2, 2);
			case Entity.BAUTISTA:
                return new EntityGUI(new Bautista(null, 0, 0), 0, 0, 2, 2);
			case Entity.DAUGHTER:
                return new EntityGUI(new Daughter(null, 0, 0), 0, 0, 2, 2);
			case Entity.CITIZEN:
                return new EntityGUI(new Citizen(null, 0, 0, Citizen.MALE), 0, 0, 2, 2);
			case Entity.JESUS:
                return new EntityGUI(new Jesus(null, 0, 0), 0, 0, 2, 2);
			case Entity.JOBS:
                return new EntityGUI(new Jobs(null, 0, 0), 0, 0, 2, 2);
			case Entity.KNIGHT:
                return new EntityGUI(new Knight(null, 0, 0), 0, 0, 2, 2);
			case Entity.KOBE:
                return new EntityGUI(new Kobe(null, 0, 0), 0, 0, 2, 2);
			case Entity.LORD_HILLSBOROUGH:
                return new EntityGUI(new LordHillsborough(null, 0, 0), 0, 0, 2, 2);
			case Entity.OCTAVIUS:
                return new EntityGUI(new Octavius(null, 0, 0), 0, 0, 2, 2);
			case Entity.PEASANT:
                return new EntityGUI(new Peasant(null, 0, 0, Peasant.MALE), 0, 0, 2, 2);
			case Entity.RANCHERO:
                return new EntityGUI(new Ranchero(null, 0, 0), 0, 0, 2, 2);
			case Entity.SON:
                return new EntityGUI(new Son(null, 0, 0), 0, 0, 2, 2);
			case Entity.WIFE:
                return new EntityGUI(new Wife(null, 0, 0), 0, 0, 2, 2);
			case Entity.ZORRA:
                return new EntityGUI(new Zorra(null, 0, 0), 0, 0, 2, 2);
			case Entity.COMPANION:
                return new EntityGUI(new Companion(null, 0, 0, null), 0, 0, 2, 2);
			case Entity.GORILLA:
                return new EntityGUI(new Gorilla(null, 0, 0), 0, 0, 3, 3);
			case Entity.NATIVE_AMERICAN:
                return new EntityGUI(new NativeAmerican(null, 0, 0, NativeAmerican.MALE), 0, 0, 2, 2);
			case Entity.POLICE_OFFICER:
                return new EntityGUI(new PoliceOfficer(null, 0, 0), 0, 0, 2, 2);
			case Entity.SWAT_OFFICER:
                return new EntityGUI(new SWATOfficer(null, 0, 0), 0, 0, 2, 2);
			case Entity.TECH_WARRIOR:
                return new EntityGUI(new TechWarrior(null, 0, 0), 0, 0, 2, 2);
			case Entity.COW:
                return new EntityGUI(new Cow(null, 0, 0), 0, 0, 2, 3);
			case Entity.CAT:
                return new EntityGUI(new Cat(null, 0, 0), 0, 0, 2, 2);
			case Entity.DOG:
                return new EntityGUI(new Dog(null, 0, 0), 0, 0, 2, 2);
			case Entity.FOX:
                return new EntityGUI(new Fox(null, 0, 0), 0, 0, 2, 2);
			case Entity.BOAT:
                return new EntityGUI(new Boat(null, 0, 0), 0, 0, 4, 5);
			case Entity.CENTURY_LESABRE:
                return new EntityGUI(new CenturyLeSabre(null, 0, 0), 0, 0, 4, 5);
			case Entity.HORSE:
                return new EntityGUI(new Horse(null, 0, 0), 0, 0, 2, 3);
			case Entity.SPORTS_CAR:
                return new EntityGUI(new SportsCar(null, 0, 0), 0, 0, 4, 5);
			case Entity.TRUCK:
                return new EntityGUI(new Truck(null, 0, 0), 0, 0, 4, 5);
			case Entity.GAUCHO:
                return new EntityGUI(new Gaucho(null, 0, 0), 0, 0, 2, 2);
			case Entity.PANCHOMBRE:
                return new EntityGUI(new Panchombre(null, 0, 0), 0, 0, 2, 2);
			case Entity.BANDITO:
                return new EntityGUI(new Bandito(null, 0, 0), 0, 0, 2, 2);
			case Entity.SKELETON:
                return new EntityGUI(new Skeleton(null, 0, 0), 0, 0, 2, 2);
			case Entity.BAR:
                return new EntityGUI(new Bar(null, 0, 0), 0, 0, 6, 6);
			case Entity.CARD_TABLE:
                return new EntityGUI(new CardTable(null, 0, 0), 0, 0, 4, 4);
			case Entity.DEAD_SEQUOIA_SMALL:
			    return new EntityGUI(new DeadSequoiaSmall(null, 0, 0), 0, 0, 1, 4);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}

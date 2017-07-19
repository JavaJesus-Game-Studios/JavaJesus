package designer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javajesus.level.tile.Tile;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * Driver class of the level designer
 */
public class Designer extends JPanel implements MouseListener{
	
	// serialization
	private static final long serialVersionUID = 1L;
	
	// height of the window
	private static final int WIDTH = 700, HEIGHT = 700;
	
	// buttons at the top
	private final JButton save, load;
	private final JTextField name;
	
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
		frame.setVisible(true);
		
	}
	
	
	/**
	 * Creates the display of the designer
	 * @param width - width of the window
	 * @param height - height of the window
	 */
	public Designer(int width, int height) {
		
		// set up the panel
		setPreferredSize(new Dimension(width, height));
		addMouseListener(this);
		setLayout(new BorderLayout(0, 0));
		
		// top panel
		JPanel top = new JPanel(new FlowLayout());
		top.add(new JLabel("Name:"));
		top.add(name = new JTextField());
		top.add(save = new JButton("Save"));
		top.add(load = new JButton("Load"));
		
		// viewing panel
		JPanel viewing = new JPanel(new BorderLayout(0, 0));
		
		// tile selector on the left side
		JScrollPane leftSide = new JScrollPane();
		JPanel leftContent = new JPanel();
		leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
		leftContent.add(new JLabel("Tile List"));
		
		// add all the tiles
		for (Tile t: Tile.tileList) {
			leftContent.add(new TileGUI(t));
		}
		
		// add to scroll pane
		leftSide.add(leftContent);
		
		// displays all the tiles in the center
		JScrollPane center = new JScrollPane();
		JPanel content = new JPanel();
		GridLayout layout = new GridLayout(200, 200);
		content.setLayout(layout);
		for (int i = 0; i < layout.getRows() * layout.getColumns(); i++) {
			content.add(new TileGUI(Tile.VOID));
		}
		
		// add to scroll
		center.add(content);
		
		// assemble the viewing panel
		viewing.add(leftSide, BorderLayout.LINE_START);
		viewing.add(center, BorderLayout.CENTER);
		
		// assemble the panel
		add(top, BorderLayout.NORTH);
		add(viewing, BorderLayout.CENTER);
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

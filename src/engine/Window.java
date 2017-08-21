package engine;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @author Derek
 *
 * The Window displays pixel information
 */
public class Window extends JFrame implements WindowListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// dimensions of the window
	private int width, height;
	
	// title of the window
	private String name;
	
	// input listener for the window
	private Input input;
	
	// whether or not the window is closed
	private boolean closed;
	
	/**
	 * Window
	 * 
	 * @param title - title of the window
	 * @param width - width of the window
	 * @param height - height of the window
	 */
	public Window(String title, int width, int height) {
		super(title);
		
		// initialize instance data
		name = title;
		this.width = width;
		this.height = height;
		input = new Input();
		
	}
	
	/**
	 * Set default parameters for the window
	 */
	public void init() {
		
		setPreferredSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// add window listener
		addWindowListener(this);
		
	}
	
	/**
	 * Adds listeners to the following component
	 * @param c - component to add listeners
	 * @param l - list of listners
	 */
	public void addListeners(Component c, int ... listeners) {
		
		for (int i = 0 ; i < listeners.length; i++) {
			if (listeners[i] == Input.KEY) {
				c.addKeyListener(input);
				setFocusable(true);
			}
			if (listeners[i] == Input.FOCUS) {
				c.addFocusListener(input);
				setFocusable(true);
			}
			if (listeners[i] == Input.MOUSE) {
				c.addMouseListener(input);
				setFocusable(true);
			}
			if (listeners[i] == Input.MOUSE_MOTION) {
				c.addMouseMotionListener(input);
				setFocusable(true);
			}
		}
		
	}

	/**
	 * isKeyPressed()
	 * 
	 * @param keyCode - key identifier: Ex. KeyEvent.VK_SPACE
	 * @return whether or not it is pressed
	 */
	public boolean isKeyPressed(int keyCode) {
		return input.isKeyPressed(keyCode);
	}
	
	/**
	 * Toggles a particular key
	 * 
	 * @param keyCode - key to toggle
	 */
	public void toggle(int keyCode) {
		input.toggle(keyCode);
	}
	
	/**
	 * Turns off a particular key
	 * 
	 * @param keyCode - key to turn off
	 */
	public void disable(int keyCode) {
		input.disable(keyCode);
	}

	/**
	 * @return width of the window
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height of the window
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return whether or not the window is closed
	 */
	public boolean isClosed() {
		return closed;
	}
	
	/**
	 * cleanup()
	 * 
	 * destroy this window
	 */
	public void cleanup() {
		setVisible(false);
		dispose();
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * windowClosed()
	 * Called when the window is closed
	 */
	public void windowClosed(WindowEvent arg0) {
		closed = true;
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the mouseX
	 */
	public final int getMouseX() {
		return input.mouseX;
	}

	/**
	 * @return the mouseY
	 */
	public final int getMouseY() {
		return input.mouseY;
	}

	/**
	 * @return the mouseDX
	 */
	public final int getMouseDX() {
		return input.mouseDX;
	}

	/**
	 * @return the mouseDY
	 */
	public final int getMouseDY() {
		return input.mouseDY;
	}

	/**
	 * @return the mousePX
	 */
	public final int getMousePX() {
		return input.mousePX;
	}

	/**
	 * @return the mousePY
	 */
	public final int getMousePY() {
		return input.mousePY;
	}

	/**
	 * @return the mouseButton
	 */
	public final int getMouseButton() {
		return input.mouseButton;
	}

	/**
	 * @return the dragged
	 */
	public final boolean isDragged() {
		return input.dragged;
	}

}

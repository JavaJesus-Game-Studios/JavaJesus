package engine;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * Handles game input from keyboard and mouse
 */
public class Input implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
	
	// create an array of keys which determine which key is pressed
	private boolean[] keys = new boolean[1024];
	
	// different states of the mouse
	// D = Drag, P = Pressed
	public int mouseX, mouseY, mouseDX, mouseDY, mousePX, mousePY, mouseButton;
	
	// whether or not the mouse is being dragged
	public boolean dragged;
	
	// list of listeners
	public static final int KEY = 0, FOCUS = 1, MOUSE = 2, MOUSE_MOTION = 3;
	
	/**
	 * isKeyPressed()
	 * For keyCode, Ex: KeyEvent.VK_UP
	 * 
	 * @param keyCode - key pressed
	 * @return whether it is down or not
	 */
	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode];
	}
	
	/**
	 * Toggles key input for a key
	 * @param keyCode - key toggled
	 */
	public void toggle(int keyCode) {
		keys[keyCode] = !keys[keyCode];
	}
	
	/**
	 * Turns off key press event for a key
	 * @param keyCode - key disabled
	 */
	public void disable(int keyCode) {
		keys[keyCode] = false;
	}
	
	/**
	 * isMouseDragged()
	 * 
	 * @return whether or not the mouse is being dragged
	 */
	public boolean isMouseDragged() {
		return dragged;
	}
	
	/**
	 * keyPressed()
	 * Sets the specified key to true
	 * 
	 * @param e - the key event
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * keyReleased()
	 * Sets the specified key to false
	 * 
	 * @param e - the key event
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * mouseDragged()
	 * New coordinates of the dragged mouse
	 */
	public void mouseDragged(MouseEvent e) {
		mouseDX = e.getX();
		mouseDY = e.getY();
		
	}

	/**
	 * mouseMoved()
	 * New coordinates of the moved mouse
	 */
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

	/**
	 * mousePressed()
	 * Updates respective information about the mouse
	 */
	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
		mousePX = e.getX();
		mousePY = e.getY();
		dragged = true;
		
	}

	/**
	 * mousePressed()
	 * Updates respective information about the mouse
	 */
	public void mouseReleased(MouseEvent arg0) {
		dragged = false;
		mouseButton = 0;
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the mouseX
	 */
	public final int getMouseX() {
		return mouseX;
	}

	/**
	 * @return the mouseY
	 */
	public final int getMouseY() {
		return mouseY;
	}

	/**
	 * @return the mouseDX
	 */
	public final int getMouseDX() {
		return mouseDX;
	}

	/**
	 * @return the mouseDY
	 */
	public final int getMouseDY() {
		return mouseDY;
	}

	/**
	 * @return the mousePX
	 */
	public final int getMousePX() {
		return mousePX;
	}

	/**
	 * @return the mousePY
	 */
	public final int getMousePY() {
		return mousePY;
	}

	/**
	 * @return the mouseButton
	 */
	public final int getMouseButton() {
		return mouseButton;
	}

	/**
	 * @return the dragged
	 */
	public final boolean isDragged() {
		return dragged;
	}

}

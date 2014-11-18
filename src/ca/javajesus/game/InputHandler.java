package ca.javajesus.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public class Key {
		private int numTimesPressed = 0;

		private boolean pressed = false;

		public int getNumTimesPressed() {
			return numTimesPressed;
		}

		public boolean isPressed() {
			return pressed;
		}

		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if (isPressed)
				numTimesPressed++;
		}
	}

	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key shift = new Key();
	public Key space = new Key();
	public Key r = new Key();
	public Key t = new Key();
	public Key f = new Key();
	public Key h = new Key();
	public Key e = new Key();
	public Key w = new Key();
	public Key s = new Key();
	public Key a = new Key();
	public Key d = new Key();
    

	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {

		toggleKey(e.getKeyCode(), false);

	}

	public void keyTyped(KeyEvent e) {

	}

	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_UP) {
			w.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			s.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			a.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			d.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			shift.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			space.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_R) {
			r.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_T) {
			t.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_F){
			f.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_H){
            h.toggle(isPressed);
        }
		
		if (keyCode == KeyEvent.VK_E) {
			e.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_W) {
            up.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_S) {
            down.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_A) {
            left.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_D) {
            right.toggle(isPressed);
        }

	}
}

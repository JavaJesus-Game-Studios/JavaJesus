package ca.javajesus.game;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import ca.javajesus.game.gui.InventoryGUI;
import ca.javajesus.game.gui.Launcher;
import ca.javajesus.game.gui.PauseGUI;
import ca.javajesus.game.gui.PausePanelGUI;
import ca.javajesus.game.gui.ScreenGUI;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public InputHandler(InventoryGUI i) {
		i.addKeyListener(this);
		i.addMouseListener(this);
		i.addMouseMotionListener(this);
	}
	
	public InputHandler(PauseGUI p){
		p.addKeyListener(this);
	}
	
	public InputHandler(ScreenGUI g) {
		g.addKeyListener(this);
		g.addMouseListener(this);
		g.addMouseMotionListener(this);
	}
	
	public InputHandler(Launcher launcher) {
		launcher.addMouseListener(this);
		launcher.addMouseMotionListener(this);
	}
	
	public InputHandler(PausePanelGUI launcher) {
		launcher.addMouseListener(this);
		launcher.addMouseMotionListener(this);
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
	public Key i = new Key();
	public Key esc = new Key();
	public Key b = new Key();
	
	public static int MouseX;
	public static int MouseY;
	public static int MouseDX; //D = drag
	public static int MouseDY;
	public static int MousePX; // P = pressed
	public static int MousePY;
	public static int MouseButton;
	public static boolean dragged = false;
    

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
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			right.toggle(isPressed);
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
            w.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_S) {
            s.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_A) {
            a.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_D) {
            d.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_I) {
            i.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
        	esc.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_B) {
            b.toggle(isPressed);
        }

	}

	public void mouseDragged(MouseEvent e) {
		MouseDX = e.getX();
		MouseDY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		MouseButton = e.getButton();
		MousePX = e.getX();
		MousePY = e.getY();
		dragged = true;
		
	}

	public void mouseReleased(MouseEvent e) {
		dragged = false;
		MouseButton = 0;
		
	}

	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}

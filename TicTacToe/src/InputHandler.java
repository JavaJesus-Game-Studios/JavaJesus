import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements MouseListener {
	
	public static int MousePX; // P = pressed
	public static int MousePY;
	public static int MouseButton;
	public static boolean dragged = false;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
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

}

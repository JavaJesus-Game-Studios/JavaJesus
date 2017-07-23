package designer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntityExtrasFrame extends JFrame implements ActionListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// dimensions of the frame
	private static final int WIDTH = 500, HEIGHT = 200;

	// the entity gui to modify
	private EntityGUI entity;

	// instance of the display
	private Display display;

	/**
	 * Creates a window for customizing entity extra data
	 * 
	 * @param title
	 */
	public EntityExtrasFrame(EntityGUI e) {
		super("Entity ID: " + e.getEntity().getId());

		// instance data
		entity = e;

		// set up the window
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// set up the pane
		getContentPane().add(display = new Display());
		pack();

		// make it visible and nice
		setLocationRelativeTo(null);
		setVisible(true);
		toFront();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// add if applicable
		if (display.getExtra1() != -1) {
			entity.setExtra1(display.getExtra1());
		}

		if (display.getExtra2() != -1) {
			entity.setExtra2(display.getExtra2());
		}

		// close this window
		setVisible(false);
		dispose();

	}

	private class Display extends JPanel {

		// serialization
		private static final long serialVersionUID = 1L;

		// text fields
		private final JTextField extra1, extra2;
		private final JButton button;

		/**
		 * Creates the central display for the window
		 */
		private Display() {

			// set the layout
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			// add pane for extras 1
			add(new JLabel("EXTRA1:"));
			add(extra1 = new JTextField());
			extra1.setText("-1");

			// add pane for extras 2
			add(new JLabel("EXTRA2:"));
			add(extra2 = new JTextField());
			extra2.setText("-1");

			// add the button
			add(button = new JButton("Confirm"));
			button.addActionListener(EntityExtrasFrame.this);
		}

		public short getExtra1() {
			return Short.parseShort(extra1.getText());
		}

		public byte getExtra2() {
			return Byte.parseByte(extra2.getText());
		}

	}

}

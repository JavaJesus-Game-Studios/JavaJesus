package editors;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EntityExtrasPanel extends JPanel implements DocumentListener {

	// serialization
	private static final long serialVersionUID = 1L;

	// dimensions of the frame
	private static final int WIDTH = 500, HEIGHT = 200;

	// the entity gui to modify
	private EntityGUI entity;

	// text fields
	private final JTextField extra1, extra2;

	/**
	 * Creates the central display for the window
	 */
	public EntityExtrasPanel(EntityGUI e) {

		// instance data
		entity = e;

		// set up the window
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// set the layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// add pane for extras 1
		add(new JLabel("EXTRA1:"));
		add(extra1 = new JTextField());
		extra1.setText("-1");
		extra1.getDocument().addDocumentListener(this);

		// add pane for extras 2
		add(new JLabel("EXTRA2:"));
		add(extra2 = new JTextField());
		extra2.setText("-1");
		extra2.getDocument().addDocumentListener(this);

	}

	/**
	 * @return Extra Data 1
	 * -1 if nothing
	 */
	public short getExtra1() {

		try {
			return Short.parseShort(extra1.getText());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * @return Extra Data 2
	 * -1 if nothing
	 */
	public byte getExtra2() {
		try {
			return (byte) Integer.parseInt(extra2.getText());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// add if applicable
		if (getExtra1() != -1) {
			entity.setExtra1(getExtra1());
		}

		if (getExtra2() != -1) {
			entity.setExtra2(getExtra2());
		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// add if applicable
		if (getExtra1() != -1) {
			entity.setExtra1(getExtra1());
		}

		if (getExtra2() != -1) {
			entity.setExtra2(getExtra2());
		}
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// add if applicable
		if (getExtra1() != -1) {
			entity.setExtra1(getExtra1());
		}

		if (getExtra2() != -1) {
			entity.setExtra2(getExtra2());
		}
	}

}

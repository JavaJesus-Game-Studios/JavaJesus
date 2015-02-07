package ca.javajesus.game.gui.intro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.javajesus.game.gfx.Colors;

public class SkinColorGUI extends JPanel implements ListSelectionListener,
		ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> model;
	private JButton colorButton;
	private int color = Colors.fromHex("#FFCC99");
	private JList<String> list;

	public SkinColorGUI() {

		model = new DefaultListModel<String>();
		model.addElement("Caucasian");
		model.addElement("Latino");
		model.addElement("Asian");
		model.addElement("African American");
		
		list = new JList<String>(model);
		
		list.addListSelectionListener(this);

		colorButton = new JButton("Color");
		colorButton.addActionListener(this);

		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel p1 = new JPanel(new BorderLayout());
		JScrollPane panel = new JScrollPane(list);
		p1.add(panel, BorderLayout.CENTER);
		p1.add(colorButton, BorderLayout.SOUTH);
		
		this.add(p1, BorderLayout.CENTER);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {

			if (list.getSelectedIndex() == -1) {
				colorButton.setEnabled(false);

			} else {
				colorButton.setEnabled(true);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		int index = list.getSelectedIndex();

		switch (index) {
		case 0:
			color = Colors.fromHex("#FFCC99");
			break;
		case 1:
			color = Colors.fromHex("#CC6600");
			break;
		case 2:
			color = Colors.fromHex("#FF9900");
			break;
		default: 
			color = Colors.fromHex("#993300");
		}
	}
	
	public int getColor() {
		return color;
	}

}
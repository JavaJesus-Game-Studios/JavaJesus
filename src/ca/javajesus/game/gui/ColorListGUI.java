package ca.javajesus.game.gui;

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

public class ColorListGUI extends JPanel implements ListSelectionListener,
		ActionListener {

	private static final long serialVersionUID = 1L;
	private DefaultListModel model;
	private JButton colorButton;
	private int color = 0;
	private JList list;

	public ColorListGUI() {

		model = new DefaultListModel();
		model.addElement("Red");
		model.addElement("Blue");
		model.addElement("Green");
		
		list = new JList(model);
		
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
			color = 100;
			break;
		case 1:
			color = 400;
			break;
		default:
			color = 200;
			break;
		}
	}
	
	public int getColor() {
		return color;
	}

}

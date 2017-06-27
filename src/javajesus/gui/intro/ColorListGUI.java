package javajesus.gui.intro;

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
	private DefaultListModel<String> model;
	private JButton colorButton;
	private int color = 0xFFFF0000;
	private JList<String> list;
	
	public ColorListGUI() {
		
		model = new DefaultListModel<String>();
		model.addElement("Red");
		model.addElement("Blue");
		model.addElement("Green");
		
		list = new JList<String>(model);
		
		list.addListSelectionListener(this);

		colorButton = new JButton("Color");
		colorButton.addActionListener(this);

		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellWidth(50);
		list.setVisibleRowCount(model.getSize());
		
		JPanel p1 = new JPanel(new BorderLayout());
		JScrollPane panel = new JScrollPane(list);
		p1.add(panel, BorderLayout.CENTER);
		
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
			color = 0xFFFF0000;
			break;
		case 1:
			color = 0xFF000099;
			break;
		default:
			color = 0xFF009700;
			break;
		}
		System.out.println("TEST");
	}
	
	public int getNum() {
		if (list.isSelectedIndex(0)) {
			return 0;
		} else if (list.isSelectedIndex(1)) {
			return 1;
		} else if (list.isSelectedIndex(2)) {
			return 2;
		} else {
			return 0;
		}
	}
	
	public int getColor() {
		switch (getNum()) {
		case 0:
			color = 0xFFFF0000;
			break;
		case 1:
			color = 0xFF000099;
			break;
		default:
			color = 0xFF009700;
			break;
		}
		return color;
	}

}

import java.awt.GridLayout;

import javax.swing.JButton;


public class BoardInterface extends GridLayout {

	private static final long serialVersionUID = 1L;
	
	public BoardInterface() {
		
		this.setColumns(3);
		this.setRows(3);
		
		this.addLayoutComponent("1", new JButton());
		
	}

}

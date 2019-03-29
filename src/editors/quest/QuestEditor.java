package editors.quest;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.ui.view.ViewerListener;

public class QuestEditor implements ViewerListener {

	// dimensions of the window
	private static final int WIDTH = 960, HEIGHT = 720;
	
	private GraphView gView;
	private DataView dView;

	/**
	 * First method called in the editor
	 * 
	 * @param args - run time arguments
	 */
	public static void main(String[] args) {
		QuestEditor qe = new QuestEditor();
		qe.present();
	}
	
	public QuestEditor() {
		gView = new GraphView();
		gView.register(this);
		
		dView = new DataView();
	}
	
	public void present() {

		// construct the window
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel main = new JPanel(new BorderLayout());
		main.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		main.add(gView.getComponent(WIDTH / 2, HEIGHT), BorderLayout.WEST);
		main.add(dView.getComponent(WIDTH / 2, HEIGHT), BorderLayout.CENTER);

		frame.getContentPane().add(main);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setTitle("Quest Editor for Java Jesus");
		frame.setVisible(true);
		frame.toFront();

		gView.addEdge("AB", "A", "B");
		gView.addEdge("AC", "A", "C");
		gView.addEdge("AD", "A", "D");
		gView.addEdge("BE", "B", "E");
		gView.addEdge("BF", "B", "F");
		gView.addEdge("BG", "B", "G");
	}

	@Override
	public void buttonPushed(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buttonReleased(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewClosed(String arg0) {
		// TODO Auto-generated method stub

	}

}

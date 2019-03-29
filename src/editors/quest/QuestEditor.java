package editors.quest;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.ui.view.ViewerListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuestEditor implements ViewerListener, IDataLoaded {

	// dimensions of the window
	private static final int WIDTH = 960, HEIGHT = 500;
	
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
		dView.setDataLoaded(this);
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
		
		gView.addNode("0");

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

	@Override
	public void onLoaded(JSONObject data) {
		JSONArray array = (JSONArray) data.get(QuestDataBuilder.QUEST_PARTS);
		
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			String parent = (String) obj.get(QuestDataBuilder.PREV_STATE_ID);
			String child = (String) obj.get(QuestDataBuilder.STATE_ID);
			
			dView.nodeLoaded(gView.addNode(child), obj);
			
			gView.addEdge(parent + child, parent, child);
			
		}
		
	}

	@Override
	public void onNodeCreated(String current, String previous) {
		dView.nodeLoaded(gView.addNode(current));
		gView.addEdge(previous + current, previous, current);
		
	}

}

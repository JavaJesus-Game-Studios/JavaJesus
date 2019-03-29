package editors.quest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.graphstream.ui.view.ViewerListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.graphstream.graph.Node;

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

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Did you save the quest first?", "Exit message",
						JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					frame.dispose();
					System.exit(0);
				} else {
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		Node root = gView.addNode("0");
		dView.rootLoaded(root);

	}

	@Override
	public void buttonPushed(String id) {
		dView.select(id);

	}

	@Override
	public void buttonReleased(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewClosed(String id) {

	}

	@Override
	public void onLoaded(JSONObject data) {

		gView.reset();

		JSONArray array = (JSONArray) data.get(QuestDataBuilder.QUEST_PARTS);

		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			String child = (String) obj.get(QuestDataBuilder.STATE_ID);
			dView.nodeLoaded(gView.addNode(child), obj);

			String rawParents = (String) obj.get(QuestDataBuilder.PREV_STATE_ID);
			String[] parents = rawParents.split(",");
			for (int j = 0; j < parents.length; j++) {
				String parent = parents[j].trim();

				if (!parent.isEmpty() && !gView.nodeExists(parent)) {
					dView.nodeLoaded(gView.addNode(parent), obj);
				}

				gView.addEdge(parent + child, parent, child);
			}

		}

	}

	@Override
	public void onNodeCreated(String current, String previous) {

		String[] parents = previous.split(",");
		for (int j = 0; j < parents.length; j++) {
			String parent = parents[j].trim();

			if (!gView.nodeExists(current)) {
				dView.nodeLoaded(gView.addNode(current));
			}
			if (!parent.isEmpty() && !gView.nodeExists(parent)) {
				dView.nodeLoaded(gView.addNode(parent));
			}
			gView.addEdge(parent + current, parent, current);
		}

	}

	@Override
	public void onNodeRemoved(String current) {
		gView.removeNode(current);

	}

	@Override
	public void onNodeModified(String current, String previous) {
		String[] parents = previous.split(",");
		gView.removeEnteringEdges(current);
		for (int j = 0; j < parents.length; j++) {
			String parent = parents[j].trim();

			if (!gView.nodeExists(current)) {
				dView.nodeLoaded(gView.addNode(current));
			}
			if (!parent.isEmpty() && !gView.nodeExists(parent)) {
				dView.nodeLoaded(gView.addNode(parent));
			}
			gView.addEdge(parent + current, parent, current);
		}

	}

}
